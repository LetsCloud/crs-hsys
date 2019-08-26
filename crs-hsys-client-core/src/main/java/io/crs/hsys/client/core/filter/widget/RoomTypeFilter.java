/**
 * 
 */
package io.crs.hsys.client.core.filter.widget;

import java.util.List;
import java.util.logging.Logger;

import javax.inject.Inject;

import io.crs.hsys.shared.dto.hotel.RoomTypeDtor;

/**
 * @author robi
 *
 */
public class RoomTypeFilter extends ComboBoxDtoFilter<RoomTypeDtor> {
	private static Logger logger = Logger.getLogger(RoomTypeFilter.class.getName());

	@Inject
	RoomTypeFilter() {
		logger.info("RoomTypeFilter()");
	}

	@Override
	protected void initComboBox() {
		super.initComboBox();
		setMultiple(false);
		setAllowClear(true);
	}

	@Override
	protected String createChipText(List<RoomTypeDtor> selectedItems) {
		String result = null;
		for (RoomTypeDtor dto : selectedItems) {
			if (result == null)
				result = dto.getCode();
			else
				result = result + ", " + dto.getCode();
		}
		return result;
	}

	@Override
	protected String createComboBoxItemText(RoomTypeDtor dto) {
		return dto.getCode() + "-" + dto.getName();
	}
}
