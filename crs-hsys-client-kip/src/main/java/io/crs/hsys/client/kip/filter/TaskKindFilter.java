/**
 * 
 */
package io.crs.hsys.client.kip.filter;

import java.util.List;
import java.util.logging.Logger;

import javax.inject.Inject;

import io.crs.hsys.client.core.filter.widget.ComboBoxFilter;
import io.crs.hsys.client.core.i18n.CoreConstants;
import io.crs.hsys.shared.cnst.TaskKind;

/**
 * @author robi
 *
 */
public class TaskKindFilter extends ComboBoxFilter<TaskKind> {
	private static Logger logger = Logger.getLogger(TaskKindFilter.class.getName());

	private final CoreConstants i18nCoreCnst;

	@Inject
	TaskKindFilter(CoreConstants i18nCoreCnst) {
		logger.info("TaskKindFilter()");
		this.i18nCoreCnst = i18nCoreCnst;
	}

	@Override
	protected void initComboBox() {
		super.initComboBox();
		setMultiple(false);
		setAllowClear(true);
	}

	@Override
	protected String createChipText(List<TaskKind> selectedItems) {
		if (selectedItems.isEmpty())
			return null;
		return i18nCoreCnst.taskKindMap().get(selectedItems.get(0).toString());
	}

	@Override
	protected String createComboBoxItemText(TaskKind item) {
		return i18nCoreCnst.taskKindMap().get(item.toString());
	}

}
