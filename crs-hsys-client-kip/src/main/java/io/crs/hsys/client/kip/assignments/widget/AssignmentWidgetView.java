/**
 * 
 */
package io.crs.hsys.client.kip.assignments.widget;

import java.util.logging.Level;
import java.util.logging.Logger;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.Widget;
import com.gwtplatform.mvp.client.ViewWithUiHandlers;

import gwt.material.design.client.constants.Color;
import gwt.material.design.client.ui.MaterialChip;
import gwt.material.design.client.ui.MaterialLabel;
import io.crs.hsys.shared.dto.hk.HkAssignmentDto;

/**
 * @author CR
 *
 */
public class AssignmentWidgetView extends ViewWithUiHandlers<AssignmentWidgetUiHandlers>
		implements AssignmentWidgetPresenter.MyView {
	private static Logger logger = Logger.getLogger(AssignmentWidgetView.class.getName());

	private static AssignmentItemViewUiBinder uiBinder = GWT.create(AssignmentItemViewUiBinder.class);

	interface AssignmentItemViewUiBinder extends UiBinder<Widget, AssignmentWidgetView> {
	}

	@UiField
	MaterialChip roomLabel;

	@UiField
	MaterialLabel assignedToLabel;

	@UiField
	MaterialLabel inspectorLabel;

	@UiField
	MaterialLabel cleanTypeLabel;

	@UiField
	MaterialLabel cleanTimeLabel;

	@UiField
	MaterialLabel reservationNoLabel;

	@UiField
	MaterialLabel nextArrivalLabel;

	/**
	 */
	public AssignmentWidgetView() {
		logger.log(Level.INFO, "AssignmentWidget()");
		initWidget(uiBinder.createAndBindUi(this));
	}

	public AssignmentWidgetView setRoomNo(String roomNo) {
		roomLabel.setText(roomNo);
		roomLabel.setTextColor(Color.BLACK);
		roomLabel.setBackgroundColor(Color.GREY_LIGHTEN_3);
		roomLabel.setLetter("D");
		roomLabel.setLetterBackgroundColor(Color.RED);
		roomLabel.setLetterColor(Color.WHITE);
		return this;
	}

	public AssignmentWidgetView setAssignedToName(String assignedToName) {
		assignedToLabel.setText(assignedToName);
		return this;
	}

	public AssignmentWidgetView setInspectorName(String inspectorName) {
		inspectorLabel.setText(inspectorName);
		return this;
	}

	public AssignmentWidgetView setCleanTypeTitle(String cleanTypeTitle) {
		cleanTypeLabel.setText(cleanTypeTitle);
		return this;
	}

	public AssignmentWidgetView setCleanTime(String cleanTime) {
		cleanTimeLabel.setText(cleanTime);
		return this;
	}

	public AssignmentWidgetView setReservationNo(String reservationNo) {
		reservationNoLabel.setText(reservationNo);
		return this;
	}

	public AssignmentWidgetView setNextArrival(String nextArrival) {
		nextArrivalLabel.setText(nextArrival);
		return this;
	}

	@UiHandler("editMaterialIcon")
	public void onClick(ClickEvent event) {
		logger.log(Level.INFO, "AssignmentWidget().editMaterialIcon");
		getUiHandlers().editAssignment();
	}

	@Override
	public void setData(HkAssignmentDto data) {
		setRoomNo(data.getRoomDto().getCode());
		setAssignedToName(data.getAttendantDto().getUsername());
		setInspectorName(data.getInspectorDto().getUsername());
		setCleanTypeTitle(data.getCleanTypeDto().getDescription());
		setCleanTime(data.getCleanTypeDto().getTime().toString());
		setReservationNo("456465");
		setNextArrival("20167.07.11");
	}

}
