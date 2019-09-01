/**
 * 
 */
package io.crs.hsys.client.core.filter.widget;

import java.util.List;
import java.util.logging.Logger;

import javax.inject.Inject;

import gwt.material.design.client.constants.IconType;
import io.crs.hsys.shared.dto.hotel.RateCodeDtor;

/**
 * @author robi
 *
 */
public class RateCodeFilter extends ComboBoxDtoFilter<RateCodeDtor> {
	private static Logger logger = Logger.getLogger(RateCodeFilter.class.getName());

	@Inject
	RateCodeFilter() {
		logger.info("RateCodeFilter()");
		setChipIconType(IconType.LOCAL_OFFER);
	}

	@Override
	protected void initComboBox() {
		super.initComboBox();
		setMultiple(false);
		setAllowClear(true);
	}

	@Override
	protected String createChipText(List<RateCodeDtor> selectedItems) {
		String result = null;
		for (RateCodeDtor dto : selectedItems) {
			if (result == null)
				result = dto.getCode();
			else
				result = result + ", " + dto.getCode();
		}
		return result;
	}

	@Override
	protected String createComboBoxItemText(RateCodeDtor dto) {
		return dto.getCode() + "-" + dto.getDescription();
	}
}
