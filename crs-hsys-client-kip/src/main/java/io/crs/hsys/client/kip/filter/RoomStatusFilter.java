/**
 * 
 */
package io.crs.hsys.client.kip.filter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

import javax.inject.Inject;

import com.google.gwt.user.client.ui.Label;
import com.google.web.bindery.event.shared.EventBus;

import gwt.material.design.client.base.MaterialWidget;
import gwt.material.design.client.constants.Color;
import gwt.material.design.client.ui.MaterialCheckBox;
import gwt.material.design.client.ui.MaterialPanel;

import io.crs.hsys.client.core.filter.BaseFilter;
import io.crs.hsys.client.core.i18n.CoreConstants;
import io.crs.hsys.client.core.ui.filter.FilterChangeEvent;
import io.crs.hsys.client.core.ui.filter.FilterChangeEvent.DataTable;
import io.crs.hsys.client.kip.i18n.KipMessages;
import io.crs.hsys.shared.cnst.RoomStatus;

/**
 * @author robi
 *
 */
public class RoomStatusFilter extends BaseFilter {
	private static Logger logger = Logger.getLogger(RoomStatusFilter.class.getName());

	private MaterialPanel panel;
	private Map<RoomStatus, MaterialCheckBox> checkBoxes = new HashMap<RoomStatus, MaterialCheckBox>();
	private final KipMessages i18n;
	private final CoreConstants cnst;

	@Inject
	RoomStatusFilter(EventBus eventBus, KipMessages i18n, CoreConstants cnst) {
		super(eventBus);
		logger.info("RoomStatusFilter()");
		this.i18n = i18n;
		this.cnst = cnst;

		panel = new MaterialPanel();
		initPanel();
		initWidget(panel);
	}

	private void initPanel() {
		panel.clear();
		panel.addStyleName("dataGroupBox");

		Label title = new Label(i18n.roomStatusFilterRoomStatusTitle());
		title.addStyleName("dataGroupTitle");
		panel.add(title);

		for (RoomStatus status : RoomStatus.values()) {
			checkBoxes.put(status, createCheckBox(status));
		}
		
		this.setChipLetter("st");
		this.setChipLetterBackgroundColor(Color.BLUE);
		this.setChipLetterColor(Color.WHITE);
}

	private MaterialCheckBox createCheckBox(RoomStatus status) {
		MaterialCheckBox checkBox = new MaterialCheckBox();
		checkBox.setText(cnst.roomStatusMap().get(status.toString()));
		checkBox.setMarginTop(10);

		checkBox.addValueChangeHandler(e -> {
			refreshChip();
			eventBus.fireEvent(new FilterChangeEvent(DataTable.QUOTATION));
		});

		panel.add(checkBox);
		return checkBox;
	}

	private void refreshChip() {
		StringBuilder sb = new StringBuilder();
		for (Map.Entry<RoomStatus, MaterialCheckBox> scb : checkBoxes.entrySet()) {
			if (scb.getValue().getValue()) {
				if (sb.length() > 0)
					sb.append(", ");
				sb.append(cnst.roomStatusMap().get(scb.getKey().toString()).substring(0, 1));
			}
		}
		setChipText(sb.toString());
	}

	@Override
	protected MaterialWidget getFilterWidget() {
		return panel;
	}

	public List<RoomStatus> getSelectedCleaningStatuses() {
		List<RoomStatus> result = new ArrayList<RoomStatus>();
		for (Map.Entry<RoomStatus, MaterialCheckBox> scb : checkBoxes.entrySet()) {
			if (scb.getValue().getValue())
				result.add(scb.getKey());
		}
		return result;
	}
}
