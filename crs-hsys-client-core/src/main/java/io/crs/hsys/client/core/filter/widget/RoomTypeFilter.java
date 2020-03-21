/**
 * 
 */
package io.crs.hsys.client.core.filter.widget;

import java.util.List;
import java.util.logging.Logger;

import javax.inject.Inject;

import gwt.material.design.client.constants.IconType;
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
		setChipIconType(IconType.HOTEL);
	}

	@Override
	protected void initComboBox() {
		super.initComboBox();
		setMultiple(false);
		setAllowClear(true);
	}

	@Override
	protected String createChipText(List<RoomTypeDtor> selectedItems) {
		logger.info("RoomTypeFilter().createChipText()");
		String result = null;
		for (RoomTypeDtor dto : selectedItems) {
			if (result == null)
				result = dto.getCode();
			else
				result = result + ", " + dto.getCode();
		}
		logger.info("RoomTypeFilter().createChipText()->result=" + result);
		return result;
	}

	@Override
	protected String createComboBoxItemText(RoomTypeDtor dto) {
		return dto.getCode() + "-" + dto.getName();
	}
}
