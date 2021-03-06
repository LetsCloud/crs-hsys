/**
 * 
 */
package io.crs.hsys.client.kip.filter;

import java.util.List;
import java.util.logging.Logger;

import javax.inject.Inject;

import io.crs.hsys.client.core.filter.widget.ComboBoxDtoFilter;
import io.crs.hsys.shared.dto.common.AppUserDtor;

/**
 * @author robi
 *
 */
public class AppUserFilter extends ComboBoxDtoFilter<AppUserDtor> {
	private static Logger logger = Logger.getLogger(AppUserFilter.class.getName());

	@Inject
	AppUserFilter() {
		logger.info("AppUserFilter()");
	}

	@Override
	protected void initComboBox() {
		super.initComboBox();
		setMultiple(false);
		setAllowClear(true);
	}

	@Override
	protected String createChipText(List<AppUserDtor> selectedItems) {
		String result = null;
		for (AppUserDtor dto : selectedItems) {
			if (result == null)
				result = dto.getCode();
			else
				result = result + ", " + dto.getCode();
		}
		return result;
	}

	@Override
	protected String createComboBoxItemText(AppUserDtor dto) {
		return dto.getCode() + "-" + dto.getName();
	}
}
