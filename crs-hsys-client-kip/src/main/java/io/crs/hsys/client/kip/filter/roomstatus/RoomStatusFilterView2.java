/**
 * 
 */
package io.crs.hsys.client.kip.filter.roomstatus;

import java.util.List;
import java.util.logging.Logger;

import javax.inject.Inject;

import gwt.material.design.addins.client.combobox.MaterialComboBox;
import gwt.material.design.client.ui.MaterialChip;

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

	private MaterialChip roomTypeChip;

	protected MaterialComboBox<RoomTypeDtor> roomTypeComboBox;

	@Inject
	RoomStatusFilterView2(CoreMessages i18nCore, CoreConstants cnstCore) {
		super(i18nCore);
		logger.info("RoomStatusFilterView2()");
	}

	@Override
	protected void createLayout() {
		roomTypeComboBox.setGrid("s12 m6");
		controlPanel.add(roomTypeComboBox);

		onlyActiveCheckBox.setGrid("s12 m6");
		controlPanel.add(onlyActiveCheckBox);
	}

	@Override
	protected void initView() {
		super.initView();
		initRoomTypeFilter();
	}

	private void initRoomTypeFilter() {
		roomTypeChip = new MaterialChip();
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
		roomTypeChip.setText(roomType.getCode());
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
