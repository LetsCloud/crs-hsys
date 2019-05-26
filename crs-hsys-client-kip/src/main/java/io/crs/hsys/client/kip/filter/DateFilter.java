/**
 * 
 */
package io.crs.hsys.client.kip.filter;

import java.util.Date;

import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.i18n.client.DateTimeFormat;

import gwt.material.design.client.base.MaterialWidget;
import gwt.material.design.client.constants.DatePickerLanguage;
import gwt.material.design.client.ui.MaterialDatePicker;

import io.crs.hsys.client.core.filter.BaseFilter;

/**
 * @author robi
 *
 */
public class DateFilter extends BaseFilter {

	MaterialDatePicker datePicker = new MaterialDatePicker();

	public DateFilter(String locale) {
		initWidget(datePicker);
		initDatePicker(locale);
	}

	@Override
	protected MaterialWidget getFilterWidget() {
		return datePicker;
	}

	public Date getValue() {
		return datePicker.getValue();
	}

	public void setValue(Date value) {
		datePicker.setValue(value);
		chipUpdate(value);
	}

	public void setFilterLabel(String label) {
		datePicker.setPlaceholder(label);
	}

	public void addValueChangeHandler(final ValueChangeHandler<Date> handler) {
		datePicker.addValueChangeHandler(handler);
	}

	private void initDatePicker(String locale) {
		datePicker.setAutoClose(true);

		if (locale.startsWith("hu"))
			datePicker.setLanguage(DatePickerLanguage.HU);

		datePicker.addValueChangeHandler(new ValueChangeHandler<Date>() {
			@Override
			public void onValueChange(ValueChangeEvent<Date> event) {
				chipUpdate(event.getValue());
			}
		});
	}

	private void chipUpdate(Date value) {
		if (value == null)
			setChipText(null);
		else {
			setChipText(createChipText(value));
		}
	}

	public void setFilterHeight(double value, Unit unit) {
		datePicker.getDateInput().getElement().getStyle().setHeight(value, unit);
	}

	protected String createChipText(Date value) {
		DateTimeFormat fmt = DateTimeFormat.getFormat("yyyy.MM.dd.");
		return chipLabel + fmt.format(value);
	}
}
