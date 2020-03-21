/**
* 
*/
package io.crs.hsys.client.inf.gps.config;

import java.util.Date;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.inject.Inject;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.logical.shared.SelectionEvent;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.i18n.client.LocaleInfo;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.user.datepicker.client.CalendarUtil;
import com.gwtplatform.mvp.client.ViewWithUiHandlers;

import gwt.material.design.client.constants.DatePickerLanguage;
import gwt.material.design.client.ui.MaterialDatePicker;
import gwt.material.design.client.ui.MaterialDropDown;
import gwt.material.design.client.ui.MaterialLink;
import gwt.material.design.client.ui.MaterialListBox;
import gwt.material.design.client.ui.MaterialDialog;
import gwt.material.design.client.ui.MaterialToast;
import gwt.material.design.client.ui.html.Option;
import io.crs.hsys.client.inf.i18n.InfConstants;

/**
 * @author CR
 *
 */
public class GpsConfigView extends ViewWithUiHandlers<GpsConfigUiHandlers> implements GpsConfigPresenter.MyView {
	private static Logger logger = Logger.getLogger(GpsConfigView.class.getName());

	private static final String DATE_FORMAT_HU = "yyyy.mm.dd.";

	interface Binder extends UiBinder<HTMLPanel, GpsConfigView> {
	}

	@UiField
	MaterialDialog modal;

	@UiField
	MaterialDropDown actualDropDown, baseDropDown;

	@UiField
	MaterialDatePicker actualFrom, actualTo, baseFrom;

	@UiField
	MaterialListBox scaleListBox;

	private final InfConstants cons;

	@Inject
	GpsConfigView(Binder binder, InfConstants cons) {
		logger.log(Level.INFO, "GpsView()");
		initWidget(binder.createAndBindUi(this));
		this.cons = cons;
	}

	@Override
	public void initView() {
	}

	private void init() {
		String locale = LocaleInfo.getCurrentLocale().getLocaleName();
		if (locale.equals("hu_HU")) {
			actualFrom.setLanguage(DatePickerLanguage.HU);
			actualFrom.setFormat(DATE_FORMAT_HU);
			actualFrom.reload();

			Timer t = new Timer() {
				@Override
				public void run() {
					actualTo.setLanguage(DatePickerLanguage.HU);
					actualTo.setFormat(DATE_FORMAT_HU);
					actualTo.reload();

					Timer t2 = new Timer() {
						@Override
						public void run() {
							baseFrom.setLanguage(DatePickerLanguage.HU);
							baseFrom.setFormat(DATE_FORMAT_HU);
							baseFrom.reload();
						}
					};
					t2.schedule(500);
				}
			};
			t.schedule(500);

		}

		actualDropDown.clear();
		for (Map.Entry<String, String> periodMap : cons.gpsPeriodMap().entrySet()) {
			MaterialLink link = new MaterialLink(periodMap.getValue());
			link.setId(periodMap.getKey());
			actualDropDown.add(link);
		}
		actualDropDown.reload();

		baseDropDown.clear();
		for (Map.Entry<String, String> periodMap : cons.gpsPeriodMap2().entrySet()) {
			MaterialLink link = new MaterialLink(periodMap.getValue());
			link.setId(periodMap.getKey());
			baseDropDown.add(link);
		}
		baseDropDown.reload();

		scaleListBox.clear();
		for (Map.Entry<String, String> acaleMap : cons.gpsScaleMap().entrySet()) {
			Option option = new Option(acaleMap.getValue());
			option.setId(acaleMap.getKey());
			scaleListBox.add(option);
		}
		scaleListBox.reload();
	}

	@UiHandler("actualDropDown")
	void onActualDropdown(SelectionEvent<Widget> callback) {
		String periodId = ((MaterialLink) callback.getSelectedItem()).getId();
		setActualPeriod(periodId, actualFrom, actualTo);
	}

	@UiHandler("baseDropDown")
	void onBaseDropdown(SelectionEvent<Widget> callback) {
		String periodId = ((MaterialLink) callback.getSelectedItem()).getId();
		setBasePeriod(periodId, baseFrom);
	}

	@SuppressWarnings("deprecation")
	private void setActualPeriod(String periodCode, MaterialDatePicker fromDatePicker,
			MaterialDatePicker toDatePicker) {
		Date fromDate = new Date();
		Date toDate = new Date();

		switch (periodCode) {
		case InfConstants.P_TODAY:
			break;
		case InfConstants.P_YESTERDAY:
			CalendarUtil.addDaysToDate(fromDate, -1);
			CalendarUtil.addDaysToDate(toDate, -1);
			break;
		case InfConstants.P_TOMORROW:
			CalendarUtil.addDaysToDate(fromDate, 1);
			CalendarUtil.addDaysToDate(toDate, 1);
			break;
		case InfConstants.P_THIS_WEEK:
			CalendarUtil.addDaysToDate(fromDate, -CalendarUtil.getStartingDayOfWeek());
			CalendarUtil.addDaysToDate(toDate, 7 - CalendarUtil.getStartingDayOfWeek());
			break;
		case InfConstants.P_LAST_WEEK:
			CalendarUtil.addDaysToDate(fromDate, -CalendarUtil.getStartingDayOfWeek() - 7);
			CalendarUtil.addDaysToDate(toDate, 7 - CalendarUtil.getStartingDayOfWeek() - 7);
			break;
		case InfConstants.P_NEXT_WEEK:
			CalendarUtil.addDaysToDate(fromDate, -CalendarUtil.getStartingDayOfWeek() + 7);
			CalendarUtil.addDaysToDate(toDate, 7 - CalendarUtil.getStartingDayOfWeek() + 7);
			break;
		case InfConstants.P_THIS_MONTH:
			CalendarUtil.setToFirstDayOfMonth(fromDate);
			CalendarUtil.setToFirstDayOfMonth(toDate);
			CalendarUtil.addMonthsToDate(toDate, 1);
			CalendarUtil.addDaysToDate(toDate, -1);
			break;
		case InfConstants.P_LAST_MONTH:
			CalendarUtil.setToFirstDayOfMonth(fromDate);
			CalendarUtil.addMonthsToDate(fromDate, -1);
			CalendarUtil.setToFirstDayOfMonth(toDate);
			CalendarUtil.addDaysToDate(toDate, -1);
			break;
		case InfConstants.P_NEXT_MONTH:
			CalendarUtil.setToFirstDayOfMonth(fromDate);
			CalendarUtil.addMonthsToDate(fromDate, 1);
			CalendarUtil.setToFirstDayOfMonth(toDate);
			CalendarUtil.addMonthsToDate(toDate, 2);
			CalendarUtil.addDaysToDate(toDate, -1);
			break;
		case InfConstants.P_THIS_YEAR:
			logger.log(Level.INFO, "GpsView.setPeriodDates()->P_THIS_YEAR");
			CalendarUtil.setToFirstDayOfMonth(fromDate);
			fromDate.setMonth(0);
			CalendarUtil.setToFirstDayOfMonth(toDate);
			toDate.setMonth(11);
			CalendarUtil.addDaysToDate(toDate, 30);
			break;
		case InfConstants.P_LAST_YEAR:
			logger.log(Level.INFO, "GpsView.setPeriodDates()->P_LAST_YEAR");
			CalendarUtil.setToFirstDayOfMonth(fromDate);
			fromDate.setMonth(0);
			CalendarUtil.addMonthsToDate(fromDate, -12);
			CalendarUtil.setToFirstDayOfMonth(toDate);
			toDate.setMonth(11);
			CalendarUtil.addDaysToDate(toDate, 30);
			CalendarUtil.addMonthsToDate(toDate, -12);
			break;
		case InfConstants.P_NEXT_YEAR:
			logger.log(Level.INFO, "GpsView.setPeriodDates()->P_NEXT_YEAR");
			CalendarUtil.setToFirstDayOfMonth(fromDate);
			fromDate.setMonth(0);
			CalendarUtil.addMonthsToDate(fromDate, 12);
			CalendarUtil.setToFirstDayOfMonth(toDate);
			toDate.setMonth(11);
			CalendarUtil.addDaysToDate(toDate, 30);
			CalendarUtil.addMonthsToDate(toDate, 12);
			break;
		}

		fromDatePicker.setDate(fromDate);
		if (toDatePicker != null)
			toDatePicker.setDate(toDate);
	}

	private void setBasePeriod(String periodCode, MaterialDatePicker fromDatePicker) {
		Date fromDate = actualFrom.getDate();

		if (fromDate == null)
			fromDate = new Date();

		switch (periodCode) {
		case InfConstants.P2_PREV_DAY:
			CalendarUtil.addDaysToDate(fromDate, -1);
			break;
		case InfConstants.P2_NEXT_DAY:
			CalendarUtil.addDaysToDate(fromDate, 1);
			break;
		case InfConstants.P2_PREV_WEEK:
			CalendarUtil.addDaysToDate(fromDate, -7);
			break;
		case InfConstants.P2_NEXT_WEEK:
			CalendarUtil.addDaysToDate(fromDate, 7);
			break;
		case InfConstants.P2_PREV_MONTH:
			CalendarUtil.addMonthsToDate(fromDate, -1);
			break;
		case InfConstants.P2_NEXT_MONTH:
			CalendarUtil.addMonthsToDate(fromDate, 1);
			break;
		case InfConstants.P2_PREV_YEAR:
			CalendarUtil.addMonthsToDate(fromDate, -12);
			break;
		case InfConstants.P2_NEXT_YEAR:
			CalendarUtil.addMonthsToDate(fromDate, 12);
			break;
		}

		fromDatePicker.setDate(fromDate);
	}

	@UiHandler("scaleListBox")
	void onChangeListBox(ValueChangeEvent<String> e) {
		MaterialToast.fireToast("Selected Index: " + scaleListBox.getSelectedIndex());
	}

	@UiHandler("okButton")
	void btnClose(ClickEvent e) {
		modal.close();
	}

	@UiHandler("cancelButton")
	void btnSave(ClickEvent e) {
		modal.close();
	}

	@Override
	public void openModal() {
		init();
		modal.open();
	}
}