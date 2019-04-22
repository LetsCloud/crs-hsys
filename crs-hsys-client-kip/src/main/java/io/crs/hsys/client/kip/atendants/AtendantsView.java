/**
 * 
 */
package io.crs.hsys.client.kip.atendants;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.inject.Inject;

import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Widget;
import com.gwtplatform.mvp.client.ViewWithUiHandlers;

import io.crs.hsys.client.core.i18n.CoreConstants;
import io.crs.hsys.client.core.ui.table.MaterialList;
import io.crs.hsys.shared.cnst.RoomStatus;
import io.crs.hsys.shared.dto.hk.AssignmentSummaryDto;
import io.crs.hsys.client.kip.atendants.widget.AtendantWidget;

/**
 * @author CR
 *
 */
public class AtendantsView extends ViewWithUiHandlers<AtendantsUiHandlers> implements AtendantsPresenter.MyView {
	private static Logger logger = Logger.getLogger(AtendantsView.class.getName());

	interface Binder extends UiBinder<Widget, AtendantsView> {
	}

	/*
	 * @UiField MaterialCollection materialCollection;
	 */
	@UiField
	MaterialList materialCollection;

	private final CoreConstants coreConstants;

	/**
	 */
	@Inject
	AtendantsView(Binder uiBinder, CoreConstants coreConstants) {

		this.coreConstants = coreConstants;

		initWidget(uiBinder.createAndBindUi(this));
	}

	@Override
	public void setData(List<AssignmentSummaryDto> assignmentSummary) {
		materialCollection.clear();
		for (AssignmentSummaryDto as : assignmentSummary) {
			materialCollection.add(createAtendantSum(as.getAttendantDto().getName(),
					as.getRoomSummary().get(RoomStatus.DIRTY), as.getRoomSummary().get(RoomStatus.CLEAN)));
		}
	}

	private AtendantWidget createAtendantSum(String name, Integer dirty, Integer clean) {
		AtendantWidget as = new AtendantWidget();
		as.setAttendantName(name);
		as.setDirtyLabel(coreConstants.roomStatusMap().get(RoomStatus.DIRTY.toString())+":");
		as.setDirtyValue(dirty);
		as.setCleanLabel(coreConstants.roomStatusMap().get(RoomStatus.CLEAN.toString())+":");
		as.setCleanValue(clean);

		as.addAtendantItemEventHandler(new AtendantItemEventHandler() {
			@Override
			public void onForwardClick(AtendantItemEvent event) {
				logger.log(Level.INFO, "onForwardClick()");
				getUiHandlers().goToAssignments();
			}
		});
		return as;
	}
}
