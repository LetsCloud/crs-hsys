/**
 * 
 */
package io.crs.hsys.client.kip.filter.taskgroup;

import java.util.List;
import java.util.logging.Logger;

import gwt.material.design.addins.client.combobox.MaterialComboBox;
import gwt.material.design.client.ui.MaterialChip;
import io.crs.hsys.client.core.i18n.CoreConstants;
import io.crs.hsys.client.core.i18n.CoreMessages;
import io.crs.hsys.client.core.ui.filter.AbstractFilterView;
import io.crs.hsys.shared.constans.TaskKind;

/**
 * @author robi
 *
 */
public class AbstractTaskGroupFilterView extends AbstractFilterView implements AbstractTaskGroupFilterPresenter.MyView {
	private static Logger logger = Logger.getLogger(AbstractTaskGroupFilterView.class.getName());

	private MaterialComboBox<TaskKind> taskKindComboBox;

	protected final CoreConstants i18nCoreCnst;

	protected AbstractTaskGroupFilterView(CoreMessages i18nCore, CoreConstants i18nCoreCnst) {
		super(i18nCore);
		logger.info("AbstractTaskGroupFilterView()");

		this.i18nCoreCnst = i18nCoreCnst;

		initTaskKindFilter();
//		disableOnlyActive();
	}

	@Override
	protected void initView() {
		super.initView();
		taskKindComboBox = new MaterialComboBox<TaskKind>();
	}

	private void initTaskKindFilter() {
		MaterialChip chip = new MaterialChip();
		chip.setVisible(false);
		collapsibleHeader.insert(chip, 1);

		taskKindComboBox.setMarginTop(30);
		taskKindComboBox.setLabel(i18nCore.taskGroupFilterTaskKindLabel());
		taskKindComboBox.setPlaceholder(i18nCore.taskGroupFilterTaskKindPlaceholder());

		taskKindComboBox.addSelectionHandler(e -> {
			setTaskKindChip(chip, e.getSelectedValues().get(0));
			getUiHandlers().filterChange();
		});
	}

	private void setTaskKindChip(MaterialChip chip, TaskKind taskKind) {
		Boolean visible = ((taskKind != null) && (!taskKind.equals(TaskKind.TK_ALL)));
		chip.setVisible(visible);
		if (visible)
			chip.setText(i18nCoreCnst.taskKindMap().get(taskKind.toString()));
	}
	
	protected void setTaskKindLayout() {
		taskKindComboBox.setGrid("s12 m6");
		controlPanel.add(taskKindComboBox);		
	};

	@Override
	protected void createLayout() {
		super.createLayout();
		setTaskKindLayout();
	}

	@Override
	public void setTaskKindData(List<TaskKind> taskKindData) {
		taskKindComboBox.clear();
		for (TaskKind tk : taskKindData)
			taskKindComboBox.addItem(i18nCoreCnst.taskKindMap().get(tk.toString()), tk);
	}

	@Override
	public TaskKind getSelectedTaskKind() {
		return taskKindComboBox.getSelectedValue().get(0);
	}

	@Override
	public void reset() {
		// TODO Auto-generated method stub

	}
}
