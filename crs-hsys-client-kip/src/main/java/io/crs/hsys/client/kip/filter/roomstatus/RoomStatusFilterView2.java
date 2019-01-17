/**
 * 
 */
package io.crs.hsys.client.kip.filter.roomstatus;

import java.util.List;
import java.util.logging.Logger;

import javax.inject.Inject;

import gwt.material.design.addins.client.combobox.MaterialComboBox;
import gwt.material.design.client.ui.MaterialCheckBox;
import gwt.material.design.client.ui.MaterialChip;
import gwt.material.design.client.ui.MaterialTextBox;

import io.crs.hsys.client.core.i18n.CoreConstants;
import io.crs.hsys.client.core.i18n.CoreMessages;
import io.crs.hsys.client.core.ui.filter.AbstractFilterView;
import io.crs.hsys.shared.dto.hotel.RoomTypeDtor;

/**
 * @author robi
 *
 */
public class RoomStatusFilterView2 extends AbstractFilterView implements RoomStatusFilterPresenter2.MyView {
	private static Logger logger = Logger.getLogger(RoomStatusFilterView2.class.getName());

	private MaterialChip dirtyChip, cleanChip, inspectedChip, roomTypeChip, floorChip;

	private MaterialCheckBox dirtyCheckBox, cleanCheckBox, inspectedCheckBox;
	protected MaterialComboBox<RoomTypeDtor> roomTypeComboBox;
	private MaterialTextBox floorField;

	@Inject
	RoomStatusFilterView2(CoreMessages i18nCore, CoreConstants cnstCore) {
		super(i18nCore);
		logger.info("RoomStatusFilterView2()");
		disableOnlyActive();
	}

	@Override
	protected void createLayout() {
		dirtyCheckBox.setGrid("s12 m6");
		controlPanel.add(dirtyCheckBox);

		cleanCheckBox.setGrid("s12 m6");
		controlPanel.add(cleanCheckBox);

		inspectedCheckBox.setGrid("s12 m6");
		controlPanel.add(inspectedCheckBox);

		roomTypeComboBox.setGrid("s12 m6");
		controlPanel.add(roomTypeComboBox);

		floorField.setGrid("s12 m6");
		controlPanel.add(floorField);
	}

	@Override
	protected void initView() {
		super.initView();
		initDiFiltertyr();
		initCleanFilter();
		initInspectedFilter();
		initRoomTypeFilter();
		initFloorFilter();
	}

	private void initDiFiltertyr() {
		dirtyChip = new MaterialChip();
		dirtyChip.setVisible(false);
		collapsibleHeader.insert(dirtyChip, 1);

		dirtyCheckBox = new MaterialCheckBox();
		dirtyCheckBox.setText("Piszkos");
		dirtyCheckBox.setMarginBottom(10);

		dirtyCheckBox.addValueChangeHandler(e -> {
			setDirtyChip(e.getValue());
			getUiHandlers().filterChange();
		});
	}

	private void setDirtyChip(Boolean dirty) {
		dirtyChip.setVisible(!dirty);
		if (dirty)
			dirtyChip.setText("Piszkosak");
	}

	private void initCleanFilter() {
		cleanChip = new MaterialChip();
		cleanChip.setVisible(false);
		collapsibleHeader.insert(cleanChip, 1);

		cleanCheckBox = new MaterialCheckBox();
		cleanCheckBox.setText("Tiszta");
		cleanCheckBox.setMarginBottom(10);

		cleanCheckBox.addValueChangeHandler(e -> {
			setCleanChip(e.getValue());
			getUiHandlers().filterChange();
		});
	}

	private void setCleanChip(Boolean clean) {
		cleanChip.setVisible(!clean);
		if (clean)
			cleanChip.setText("Piszkosak");
	}

	private void initInspectedFilter() {
		inspectedChip = new MaterialChip();
		inspectedChip.setVisible(false);
		collapsibleHeader.insert(inspectedChip, 1);

		inspectedCheckBox = new MaterialCheckBox();
		inspectedCheckBox.setText("Rendben");
		inspectedCheckBox.setMarginBottom(10);

		inspectedCheckBox.addValueChangeHandler(e -> {
			setInspectedhip(e.getValue());
			getUiHandlers().filterChange();
		});
	}

	private void setInspectedhip(Boolean inspected) {
		inspectedChip.setVisible(!inspected);
		if (inspected)
			inspectedChip.setText("Rendben");
	}

	private void initRoomTypeFilter() {
		roomTypeChip = new MaterialChip();
		roomTypeChip.setVisible(false);
		collapsibleHeader.insert(roomTypeChip, 1);

		roomTypeComboBox = new MaterialComboBox<RoomTypeDtor>();
		roomTypeComboBox.setLabel(i18nCore.roomTypesTableHotelsLabel());
		roomTypeComboBox.setPlaceholder(i18nCore.roomTypesTableHotelsPlaceholder());

		roomTypeComboBox.addSelectionHandler(e -> {
			setRoomTypeChip(e.getSelectedValues().get(0));
			getUiHandlers().filterChange();
		});
	}

	private void setRoomTypeChip(RoomTypeDtor roomType) {
		Boolean visible = ((roomType != null) && (!roomType.getCode().isEmpty()));
		roomTypeChip.setVisible(visible);
		if (visible)
			roomTypeChip.setText(roomType.getCode());
	}

	private void initFloorFilter() {
		floorChip = new MaterialChip();
		floorChip.setVisible(false);
		collapsibleHeader.insert(roomTypeChip, 1);

		floorField = new MaterialTextBox();
		floorField.setLabel(i18nCore.roomTypesTableHotelsLabel());
		floorField.setPlaceholder(i18nCore.roomTypesTableHotelsPlaceholder());

		floorField.addValueChangeHandler(e -> {
			setFloorChip(e.getValue());
			getUiHandlers().filterChange();
		});
	}

	private void setFloorChip(String floor) {
		Boolean visible = ((floor != null) && (!floor.isEmpty()));
		floorChip.setVisible(visible);
		if (visible)
			floorChip.setText("Emelet:" + floor);
	}

	@Override
	public void setRoomTypeData(List<RoomTypeDtor> hotelData) {
		logger.info("RoomStatusFilterView2().setRoomTypeData()");
		for (RoomTypeDtor hd : hotelData) {
			logger.info("RoomStatusFilterView2().setRoomTypeData()->code=" + hd.getCode());
			roomTypeComboBox.addItem(hd.getCode(), hd);
		}
	}

	@Override
	public void setSelectedRoomType(RoomTypeDtor roomType) {
		Integer index = roomTypeComboBox.getValueIndex(roomType);
		roomTypeComboBox.setSelectedIndex(index);
		setRoomTypeChip(roomType);
	}

	@Override
	public RoomTypeDtor getSelectedRoomType() {
		return roomTypeComboBox.getSelectedValue().get(0);
	}

	@Override
	public void reset() {
		// TODO Auto-generated method stub

	}
}
