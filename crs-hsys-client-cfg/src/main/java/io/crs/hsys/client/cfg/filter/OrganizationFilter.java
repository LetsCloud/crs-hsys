/**
 * 
 */
package io.crs.hsys.client.cfg.filter;

import java.util.List;
import java.util.logging.Logger;

import javax.inject.Inject;

import com.google.web.bindery.event.shared.EventBus;

import io.crs.hsys.shared.dto.profile.OrganizationDtor;

/**
 * @author robi
 *
 */
public class OrganizationFilter extends ComboBoxFilter<OrganizationDtor> {
	private static Logger logger = Logger.getLogger(OrganizationFilter.class.getName());

	@Inject
	OrganizationFilter(EventBus eventBus) {
		super(eventBus);
		logger.info("OrganizationFilter()");
	}

	@Override
	protected String getChipText(List<OrganizationDtor> selectedItems) {
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
	protected String getComboBoxItemText(OrganizationDtor dto) {
		return dto.getName();
	}
}
