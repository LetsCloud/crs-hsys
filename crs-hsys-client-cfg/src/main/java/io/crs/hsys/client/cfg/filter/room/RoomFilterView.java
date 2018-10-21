/**
 * 
 */
package io.crs.hsys.client.cfg.filter.room;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import javax.inject.Inject;

import gwt.material.design.addins.client.combobox.MaterialComboBox;
import gwt.material.design.client.ui.MaterialChip;

import io.crs.hsys.client.cfg.filter.hotelchild.AbstractHotelChildFilterView;
import io.crs.hsys.client.core.i18n.CoreConstants;
import io.crs.hsys.client.core.i18n.CoreMessages;
import io.crs.hsys.shared.dto.hotel.RoomTypeDto;

/**
 * @author robi
 *
 */
public class RoomFilterView extends AbstractHotelChildFilterView implements RoomFilterPresenter.MyView {
	private static Logger logger = Logger.getLogger(RoomFilterView.class.getName());

	private MaterialChip floorChip, roomTypesChip;
	private MaterialComboBox<String> floorCombo;
	private MaterialComboBox<RoomTypeDto> roomTypeCombo;

	@Inject
	RoomFilterView(CoreMessages i18nCore, CoreConstants cnstCore) {
		super(i18nCore, cnstCore);
		logger.info("RoomFilterView()");
	}

	@Override
	protected void createLayout() {
		hotelComboBox.setGrid("s12 m6");
		controlPanel.add(hotelComboBox);

		floorCombo.setGrid("s12 m6");
		controlPanel.add(floorCombo);

		roomTypeCombo.setGrid("s12 m6");
		controlPanel.add(roomTypeCombo);

		onlyActiveCheckBox.setGrid("s12 m6");
		controlPanel.add(onlyActiveCheckBox);
	}

	@Override
	protected void initView() {
		super.initView();

		initFloorFilter();
		initRoomTypeFilter();
	}

	private void initFloorFilter() {
		floorChip = new MaterialChip();

		floorCombo = new MaterialComboBox<String>();
		floorCombo.setMarginTop(25);
		floorCombo.setAllowClear(true);

		floorCombo.setLabel(i18nCore.roomFilterFloorLabel());
		floorCombo.setPlaceholder(i18nCore.roomFilterFloorPlaceholder());

		floorCombo.addSelectionHandler(e -> {
			String floorText = null;
			if (e.getSelectedValues().get(0) != null)
				floorText = e.getSelectedValues().get(0);
			setFloorChip(floorText);
			getUiHandlers().filterChange();
		});
		floorCombo.addRemoveItemHandler(e -> {
			setFloorChip(null);
			getUiHandlers().filterChange();
		});
	}

	private void setFloorChip(String type) {
		if (floorChip.isAttached()) {
			if ((type == null) || (type.isEmpty())) {
				collapsibleHeader.remove(floorChip);
				return;
			}
			floorChip.setText(i18nCore.roomFilterFloor() + type);
		} else {
			if ((type != null) && (!type.isEmpty())) {
				floorChip.setText(i18nCore.roomFilterFloor() + type);
				collapsibleHeader.add(floorChip);
			}
		}
	}

	@Override
	public void setFloors(List<String> floors) {
		if (floors != null)
			floorCombo.addItems(floors);
	}

	@Override
	public String getSelectedFloor() {
		return floorCombo.getSingleValue();
	}

	private void initRoomTypeFilter() {
		roomTypesChip = new MaterialChip();

		roomTypeCombo = new MaterialComboBox<RoomTypeDto>();
		roomTypeCombo.setMultiple(true);
		roomTypeCombo.setAllowClear(true);
		roomTypeCombo.setAllowBlank(true);
		roomTypeCombo.setCloseOnSelect(false);
		roomTypeCombo.setMarginTop(25);
		roomTypeCombo.setLabel(i18nCore.roomFilterRoomTypesLabel());
		roomTypeCombo.setPlaceholder(i18nCore.roomFilterRoomTypesPlaceholder());
		roomTypeCombo.addSelectionHandler(e -> {
			String roomTypesText = null;
			for (RoomTypeDto roomType : e.getSelectedValues()) {
				if (roomTypesText == null) {
					roomTypesText = roomType.getCode();
				} else {
					roomTypesText = roomTypesText + ", " + roomType.getCode();
				}
			}
			setRoomTypesChip(roomTypesText);
			getUiHandlers().filterChange();
		});
		roomTypeCombo.addRemoveItemHandler(e -> {
			setRoomTypesChip(null);
			getUiHandlers().filterChange();
		});
	}

	private void setRoomTypesChip(String roomTypes) {
		if (roomTypesChip.isAttached()) {
			if ((roomTypes == null) || (roomTypes.isEmpty())) {
				collapsibleHeader.remove(roomTypesChip);
				return;
			}
			roomTypesChip.setText(roomTypes);
		} else {
			if ((roomTypes != null) && (!roomTypes.isEmpty())) {
				roomTypesChip.setText(roomTypes);
				collapsibleHeader.add(roomTypesChip);
			}
		}
	}

	@Override
	public void setRoomTypeData(List<RoomTypeDto> roomTypeData) {
		logger.info("RoomFilterView().setRoomTypeData()");
		roomTypeCombo.clear();
		for (RoomTypeDto dto : roomTypeData) {
			logger.info("RoomFilterView().setRoomTypeData()->dto.getCode()=" + dto.getCode());
			roomTypeCombo.addItem(dto.getCode() + "-" + dto.getName(), dto);
		}
	}

	@Override
	public List<String> getSelectedRoomTypeKeys() {
		List<String> result = new ArrayList<String>();
		for (RoomTypeDto dto : roomTypeCombo.getSelectedValues())
			result.add(dto.getWebSafeKey());
		return result;
	}

	@Override
	public void reset() {
		logger.info("RoomFilterView().reset()");
		setFloorChip(null);
	}
}
