/**
 * 
 */
package io.crs.hsys.client.cfg.filter;

import java.util.List;
import java.util.logging.Logger;

import javax.inject.Inject;

import io.crs.hsys.client.core.filter.widget.ComboBoxDtoFilter;
import io.crs.hsys.shared.dto.profile.OrganizationDtor;

/**
 * @author robi
 *
 */
public class OrganizationFilter extends ComboBoxDtoFilter<OrganizationDtor> {
	private static Logger logger = Logger.getLogger(OrganizationFilter.class.getName());

	@Inject
	OrganizationFilter() {
		logger.info("OrganizationFilter()");
	}

	@Override
	protected String createChipText(List<OrganizationDtor> selectedItems) {
		String result = null;
		for (OrganizationDtor dto : selectedItems) {
			if (result == null)
				result = dto.getCode();
			else
				result = result + ", " + dto.getCode();
		}
		return result;
	}

	@Override
	protected String createComboBoxItemText(OrganizationDtor dto) {
		return dto.getName();
	}
}
