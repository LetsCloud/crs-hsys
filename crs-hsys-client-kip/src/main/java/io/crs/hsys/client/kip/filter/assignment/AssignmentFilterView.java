package io.crs.hsys.client.kip.filter.assignment;

import java.util.logging.Logger;

import javax.inject.Inject;

import gwt.material.design.addins.client.combobox.MaterialComboBox;
import gwt.material.design.client.ui.MaterialChip;
import io.crs.hsys.client.core.filter.AbstractFilterView;
import io.crs.hsys.client.core.i18n.CoreConstants;
import io.crs.hsys.client.core.i18n.CoreMessages;
import io.crs.hsys.shared.cnst.InventoryType;

public class AssignmentFilterView extends AbstractFilterView implements AssignmentFilterPresenter.MyView {
	private static Logger logger = Logger.getLogger(AssignmentFilterView.class.getName());

	private MaterialChip inventoryTypeChip;
	private MaterialComboBox<InventoryType> inventoryTypeCombo;

	@Inject
	AssignmentFilterView(CoreMessages i18nCore, CoreConstants cnstCore) {
		super(i18nCore);
	}

	@Override
	protected void createLayout() {
	}

	@Override
	protected void initView() {
		super.initView();
	}

	@Override
	public void reset() {
	}

	@Override
	public InventoryType getSelectedInventoryType() {
		// TODO Auto-generated method stub
		return null;
	}
}
