/**
 * 
 */
package io.crs.hsys.client.cfg.filter;

import java.util.List;
import java.util.logging.Logger;

import javax.inject.Inject;

import com.google.web.bindery.event.shared.EventBus;

import io.crs.hsys.client.core.filter.ComboBoxFilter;
import io.crs.hsys.shared.dto.doc.QuotationStatusDto;

/**
 * @author robi
 *
 */
public class QuotationStatusFilter extends ComboBoxFilter<QuotationStatusDto> {
	private static Logger logger = Logger.getLogger(QuotationStatusFilter.class.getName());

	@Inject
	QuotationStatusFilter(EventBus eventBus) {
		super(eventBus);
		logger.info("QuotationStatusFilter()");
	}

	@Override
	protected String createChipText(List<QuotationStatusDto> selectedItems) {
		String result = null;
		for (QuotationStatusDto dto : selectedItems) {
			if (result == null)
				result = dto.getDescription();
			else
				result = result + ", " + dto.getDescription();
		}
		return result;
	}

	@Override
	protected String createComboBoxItemText(QuotationStatusDto dto) {
		return dto.getDescription();
	}
}
