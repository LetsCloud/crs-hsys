/**
 * 
 */
package io.crs.hsys.client.core.table.filter;

import java.util.List;
import java.util.logging.Logger;

import javax.inject.Inject;

import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Widget;
import com.gwtplatform.mvp.client.ViewWithUiHandlers;

import gwt.material.design.addins.client.combobox.MaterialComboBox;
import gwt.material.design.client.ui.MaterialCheckBox;
import gwt.material.design.client.ui.MaterialChip;
import gwt.material.design.client.ui.MaterialCollapsibleHeader;
import io.crs.hsys.client.core.i18n.CoreMessages;
import io.crs.hsys.shared.dto.hotel.HotelDtor;

/**
 * @author robi
 *
 */
public class FilterWidgetView extends ViewWithUiHandlers<FilterWidgetUiHandlers>
		implements FilterWidgetPresenter.MyView {
	private static Logger logger = Logger.getLogger(FilterWidgetView.class.getName());

	interface Binder extends UiBinder<Widget, FilterWidgetView> {
	}

	private final CoreMessages i18nCore;

	private MaterialChip allItemsChip;

	@UiField
	MaterialCollapsibleHeader collapsibleHeader;

	@UiField
	MaterialChip hotelChip;

	@UiField
	MaterialComboBox<HotelDtor> hotelComboBox;

	@UiField
	MaterialCheckBox onlyActiveCheckBox;

	@Inject
	FilterWidgetView(Binder uiBinder, CoreMessages i18nCore) {
		logger.info("FilterWidgetView()");

		initWidget(uiBinder.createAndBindUi(this));

		this.i18nCore = i18nCore;

		init();
	}

	private void init() {
		allItemsChip = new MaterialChip(i18nCore.roomTypesTableOnlyActive());
		setOnlyActiveCheckBox(onlyActiveCheckBox.getValue());

		hotelComboBox.setLabel(i18nCore.roomTypesTableHotelsLabel());
		hotelComboBox.setPlaceholder(i18nCore.roomTypesTableHotelsPlaceholder());

		hotelComboBox.addSelectionHandler(e -> {
			setHotelChip(e.getSelectedValues().get(0));
			getUiHandlers().filterChange();
		});

		onlyActiveCheckBox.addValueChangeHandler(e -> {
			setOnlyActiveCheckBox(e.getValue());
			getUiHandlers().filterChange();
		});
	}

	@Override
	public void setHotelData(List<HotelDtor> hotelData) {
		for (HotelDtor hd : hotelData) {
			logger.info("RoomTypeTableView().setHotelData()->hd=" + hd.getName());
			hotelComboBox.addItem(hd.getName(), hd);
		}
	}

	@Override
	public void setSelectedHotel(HotelDtor hotelDto) {
		logger.info("RoomTypeTableView().setSelectedHotel()->hotelDto=" + hotelDto);
		Integer index = hotelComboBox.getValueIndex(hotelDto);
		logger.info("RoomTypeTableView().setSelectedHotel()->index=" + index);
		hotelComboBox.setSelectedIndex(index);
		setHotelChip(hotelDto);
	}

	private void setHotelChip(HotelDtor hoteDto) {
		hotelChip.setText(hoteDto.getName());
	}

	private void setOnlyActiveCheckBox(Boolean onlyActive) {
		if (onlyActive) {
			collapsibleHeader.add(allItemsChip);
		} else {
			collapsibleHeader.remove(allItemsChip);
		}
	}

	@Override
	public HotelDtor getSelectedHotel() {
		return hotelComboBox.getSelectedValue().get(0);
	}

	@Override
	public Boolean isOnlyActive() {
		return onlyActiveCheckBox.getValue();
	}
}