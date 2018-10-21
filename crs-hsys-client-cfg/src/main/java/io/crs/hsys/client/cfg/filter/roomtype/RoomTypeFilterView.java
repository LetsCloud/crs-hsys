/**
 * 
 */
package io.crs.hsys.client.cfg.filter.roomtype;

import java.util.Arrays;
import java.util.Map;
import java.util.logging.Logger;

import javax.inject.Inject;

import gwt.material.design.addins.client.combobox.MaterialComboBox;
import gwt.material.design.client.ui.MaterialChip;

import io.crs.hsys.client.cfg.filter.hotelchild.AbstractHotelChildFilterView;
import io.crs.hsys.client.core.i18n.CoreConstants;
import io.crs.hsys.client.core.i18n.CoreMessages;
import io.crs.hsys.shared.constans.InventoryType;

/**
 * @author robi
 *
 */
public class RoomTypeFilterView extends AbstractHotelChildFilterView implements RoomTypeFilterPresenter.MyView {
	private static Logger logger = Logger.getLogger(RoomTypeFilterView.class.getName());

	private MaterialChip inventoryTypeChip;
	private MaterialComboBox<InventoryType> inventoryTypeCombo;

	@Inject
	RoomTypeFilterView(CoreMessages i18nCore, CoreConstants cnstCore) {
		super(i18nCore, cnstCore);
		
		initInventoryTypeFilter(cnstCore.inventoryTypeMap(), i18nCore);
	}

	@Override
	protected void createLayout() {
		hotelComboBox.setGrid("s12 m6");
		controlPanel.add(hotelComboBox);

		onlyActiveCheckBox.setGrid("s12 m6");
		controlPanel.add(onlyActiveCheckBox);
	}

	@Override
	protected void initView() {
		super.initView();
	}

	private void initInventoryTypeFilter(Map<String, String> i18nSalesTypes, CoreMessages i18nCore) {
		inventoryTypeChip = new MaterialChip();

		inventoryTypeCombo = new MaterialComboBox<InventoryType>();
		inventoryTypeCombo.setAllowClear(true);
		inventoryTypeCombo.setMarginTop(25);
		inventoryTypeCombo.setLabel(i18nCore.roomTypeFilterInventoryTypeLabel());
		inventoryTypeCombo.setPlaceholder(i18nCore.roomTypeFilterInventoryTypePlaceholder());
		inventoryTypeCombo.addSelectionHandler(e -> {
			String inventoryTypeText = null;
			if (e.getSelectedValues().get(0) != null)
				inventoryTypeText = i18nSalesTypes.get(e.getSelectedValues().get(0).toString());
			setInventoryTypeChip(inventoryTypeText);
			getUiHandlers().filterChange();
		});
		inventoryTypeCombo.addRemoveItemHandler(e -> {
			setInventoryTypeChip(null);
			getUiHandlers().filterChange();
		});
		inventoryTypeCombo.setGrid("s12 m6");
		controlPanel.add(inventoryTypeCombo);

		Arrays.asList(InventoryType.values())
				.forEach(st -> inventoryTypeCombo.addItem(i18nSalesTypes.get(st.toString()), st));
	}

	private void setInventoryTypeChip(String type) {
		if (inventoryTypeChip.isAttached()) {
			if (type == null) {
				collapsibleHeader.remove(inventoryTypeChip);
				return;
			}
			inventoryTypeChip.setText(type);
		} else {
			if (type != null) {
				inventoryTypeChip.setText(type);
				collapsibleHeader.add(inventoryTypeChip);
			}
		}
	}

	@Override
	public InventoryType getSelectedInventoryType() {
		return inventoryTypeCombo.getSingleValue();
	}

	@Override
	public void reset() {
		logger.info("RoomTypeFilterView().reset()");
		inventoryTypeCombo.setSingleValue(null);
		setInventoryTypeChip(null);
	}
}
