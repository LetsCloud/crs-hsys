/**
 * 
 */
package io.crs.hsys.client.kip.filter.roomstatus;

import java.util.List;
import java.util.logging.Logger;

import javax.inject.Inject;

import com.google.gwt.user.client.ui.Label;

import gwt.material.design.addins.client.combobox.MaterialComboBox;
import gwt.material.design.client.ui.MaterialCheckBox;
import gwt.material.design.client.ui.MaterialChip;
import gwt.material.design.client.ui.MaterialPanel;
import gwt.material.design.client.ui.MaterialTextBox;
import io.crs.hsys.client.core.filter.AbstractFilterView;
import io.crs.hsys.client.core.i18n.CoreMessages;
import io.crs.hsys.client.core.resources.ThemeColorsBlue;
import io.crs.hsys.client.kip.i18n.KipMessages;
import io.crs.hsys.shared.dto.hotel.RoomTypeDtor;

/**
 * @author robi
 *
 */
public class RoomStatusFilterView2 extends AbstractFilterView implements RoomStatusFilterPresenter2.MyView {
	private static Logger logger = Logger.getLogger(RoomStatusFilterView2.class.getName());

	private MaterialPanel cleaningStatusPanel;
	private MaterialPanel occupancyStatusPanel;
	private MaterialTextBox roomNumberField;
	private MaterialComboBox<RoomTypeDtor> roomTypeComboBox;
	private MaterialTextBox floorField;

	private final KipMessages i18n;

	@Inject
	RoomStatusFilterView2(CoreMessages i18nCore, KipMessages i18n) {
		super(i18nCore);
		logger.info("RoomStatusFilterView2()");
		this.i18n = i18n;
	}

	@Override
	protected void initView() {
		super.initView();
		disableOnlyActive();

		occupancyStatusPanel = new MaterialPanel();
		cleaningStatusPanel = new MaterialPanel();
		roomNumberField =  new MaterialTextBox();
		roomTypeComboBox = new MaterialComboBox<RoomTypeDtor>();
		floorField = new MaterialTextBox();

		initOccupancyStatusPanel();
		initCleaningStatusPanel();
		initRoomNumberFilter();
		initRoomTypeFilter();
		initFloorFilter();
	}

	private void initOccupancyStatusPanel() {
		Label title = new Label(i18n.roomStatusFilterOccupancyStatusTitle());
		occupancyStatusPanel.add(title);

		initCheckBox(occupancyStatusPanel, i18n.roomStatusFilterOccupancyVacant());
		initCheckBox(occupancyStatusPanel, i18n.roomStatusFilterOccupancyEarlyCheckIn());
		initCheckBox(occupancyStatusPanel, i18n.roomStatusFilterOccupancyCheckIn());
		initCheckBox(occupancyStatusPanel, i18n.roomStatusFilterOccupancyCheckedIn());
		initCheckBox(occupancyStatusPanel, i18n.roomStatusFilterOccupancyStayOver());
		initCheckBox(occupancyStatusPanel, i18n.roomStatusFilterOccupancyCheckout());
		initCheckBox(occupancyStatusPanel, i18n.roomStatusFilterOccupancyLateCheckout());
		initCheckBox(occupancyStatusPanel, i18n.roomStatusFilterOccupancyCheckedOut());
	}

	private void initCleaningStatusPanel() {
		Label title = new Label(i18n.roomStatusFilterRoomStatusTitle());
		cleaningStatusPanel.add(title);

		initCheckBox(cleaningStatusPanel, i18n.roomStatusFilterRoomDirty());
		initCheckBox(cleaningStatusPanel, i18n.roomStatusFilterRoomClean());
		initCheckBox(cleaningStatusPanel, i18n.roomStatusFilterRoomInspected());
		initCheckBox(cleaningStatusPanel, i18n.roomStatusFilterRoomOoo());
		initCheckBox(cleaningStatusPanel, i18n.roomStatusFilterRoomOos());
		initCheckBox(cleaningStatusPanel, i18n.roomStatusFilterRoomShow());
	}

	private void initCheckBox(MaterialPanel cleaningStatusPanel, String title) {
		MaterialChip chip = new MaterialChip();
		chip.setText(title);
		chip.setVisible(false);
		collapsibleHeader.insert(chip, 1);

		MaterialCheckBox checkBox = new MaterialCheckBox();
		checkBox.setText(title);
		checkBox.setMarginTop(10);

		checkBox.addValueChangeHandler(e -> {
			chip.setVisible(e.getValue());
			getUiHandlers().filterChange();
		});

		cleaningStatusPanel.add(checkBox);
	}

	private void initRoomNumberFilter() {
		MaterialChip chip = new MaterialChip();
		chip.setVisible(false);
		collapsibleHeader.insert(chip, 1);

		roomNumberField.setMarginTop(30);
		roomNumberField.setLabel(i18n.roomStatusFilterRoomNumberLabel());
		roomNumberField.setPlaceholder(i18n.roomStatusFilterRoomNumberPlaceholder());

		roomNumberField.addValueChangeHandler(e -> {
			setRoomNumberChip(chip, e.getValue());
			getUiHandlers().filterChange();
		});
	}

	private void setRoomNumberChip(MaterialChip chip, String roomNumber) {
		Boolean visible = ((roomNumber != null) && (!roomNumber.isEmpty()));
		chip.setVisible(visible);
		if (visible)
			chip.setText(i18n.roomStatusFilterRoomNumberChip(roomNumber));
	}

	private void initRoomTypeFilter() {
		MaterialChip chip = new MaterialChip();
		chip.setVisible(false);
		collapsibleHeader.insert(chip, 1);

//		roomTypeComboBox.setMarginTop(30);
		roomTypeComboBox.setLabel(i18n.roomStatusFilterRoomTypeLabel());
		roomTypeComboBox.setPlaceholder(i18n.roomStatusFilterRoomTypePlaceholder());

		roomTypeComboBox.addSelectionHandler(e -> {
			setRoomTypeChip(chip, e.getSelectedValues().get(0));
			getUiHandlers().filterChange();
		});
	}

	private void setRoomTypeChip(MaterialChip chip, RoomTypeDtor roomType) {
		logger.info("RoomStatusFilterView2().setRoomTypeChip()");
		Boolean visible = ((roomType != null) && (roomType.getCode() != null) && (!roomType.getCode().isEmpty()));
		chip.setVisible(visible);
		if (visible)
			chip.setText(roomType.getCode());
	}

	private void initFloorFilter() {
		MaterialChip chip = new MaterialChip();
		chip.setVisible(false);
		collapsibleHeader.insert(chip, 1);

		floorField.setLabel(i18n.roomStatusFilterRoomFloorLabel());
		floorField.setPlaceholder(i18n.roomStatusFilterRoomFloorPlaceholder());

		floorField.addValueChangeHandler(e -> {
			setFloorChip(chip, e.getValue());
			getUiHandlers().filterChange();
		});
	}

	private void setFloorChip(MaterialChip chip, String floorNumber) {
		Boolean visible = ((floorNumber != null) && (!floorNumber.isEmpty()));
		chip.setVisible(visible);
		if (visible)
			chip.setText(i18n.roomStatusFilterRoomFloorChip(floorNumber));
	}

	@Override
	protected void createLayout() {
		createOccupancyStatusLayout();

		createCleaningStatusLayout();

		roomNumberField.setGrid("s6 m4");
		controlPanel.add(roomNumberField);

		floorField.setGrid("s6 m4");
		controlPanel.add(floorField);

		roomTypeComboBox.setGrid("s6 m4");
		controlPanel.add(roomTypeComboBox);
	}

	private void createCleaningStatusLayout() {
		cleaningStatusPanel.setGrid("s6 m4");
		cleaningStatusPanel.setBorderLeft("3px solid " + ThemeColorsBlue.C_PRIMARY);
		controlPanel.add(cleaningStatusPanel);
	}

	private void createOccupancyStatusLayout() {
		occupancyStatusPanel.setGrid("s6 m4");
		occupancyStatusPanel.setBorderLeft("3px solid " + ThemeColorsBlue.C_PRIMARY);
		controlPanel.add(occupancyStatusPanel);
	}

	@Override
	public void setRoomTypeData(List<RoomTypeDtor> hotelData) {
		roomTypeComboBox.clear();
		roomTypeComboBox.addItem(i18n.roomStatusFilterRoomTypeAll(), new RoomTypeDtor());
		for (RoomTypeDtor hd : hotelData)
			roomTypeComboBox.addItem(hd.getCode(), hd);
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
