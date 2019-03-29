/**
 * 
 */
package io.crs.hsys.client.kip.atendants;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.google.inject.Inject;
import com.google.web.bindery.event.shared.EventBus;
import com.gwtplatform.mvp.client.HasUiHandlers;
import com.gwtplatform.mvp.client.Presenter;
import com.gwtplatform.mvp.client.View;
import com.gwtplatform.mvp.client.annotations.NameToken;
import com.gwtplatform.mvp.client.annotations.ProxyStandard;
import com.gwtplatform.mvp.client.annotations.UseGatekeeper;
import com.gwtplatform.mvp.client.proxy.PlaceManager;
import com.gwtplatform.mvp.client.proxy.ProxyPlace;
import com.gwtplatform.mvp.shared.proxy.PlaceRequest;

import io.crs.hsys.client.core.event.SetPageTitleEvent;
import io.crs.hsys.client.core.security.LoggedInGatekeeper;
import io.crs.hsys.shared.cnst.MenuItemType;
import io.crs.hsys.shared.cnst.RoomStatus;
import io.crs.hsys.shared.dto.common.AppUserDto;
import io.crs.hsys.shared.dto.hk.AssignmentSummaryDto;
import io.crs.hsys.client.kip.KipAppPresenter;
import io.crs.hsys.client.kip.KipNameTokens;
import io.crs.hsys.client.kip.i18n.KipMessages;

/**
 * @author CR
 *
 */
public class AtendantsPresenter extends Presenter<AtendantsPresenter.MyView, AtendantsPresenter.MyProxy>
		implements AtendantsUiHandlers {
	private static Logger logger = Logger.getLogger(AtendantsPresenter.class.getName());

	interface MyView extends View, HasUiHandlers<AtendantsUiHandlers> {
		void setData(List<AssignmentSummaryDto> assignmentSummary);
	}

	@ProxyStandard
	@NameToken(KipNameTokens.ROOM_TASK_ASSIGNMENT)
	@UseGatekeeper(LoggedInGatekeeper.class)
	interface MyProxy extends ProxyPlace<AtendantsPresenter> {
	}

	private final PlaceManager placeManager;
	private final KipMessages i18n;

	@Inject
	AtendantsPresenter(EventBus eventBus, MyView view, MyProxy proxy, PlaceManager placeManager, KipMessages i18n) {
		super(eventBus, view, proxy, KipAppPresenter.SLOT_MAIN);

		this.placeManager = placeManager;
		this.i18n = i18n;

		getView().setUiHandlers(this);
	}

	@Override
	protected void onReveal() {
		super.onReveal();
		SetPageTitleEvent.fire(i18n.attendantsTitle(), i18n.attendantsDescription(), MenuItemType.MENU_ITEM, this);
		loadData();
	}

	@Override
	public void goToAssignments() {
		logger.log(Level.INFO, "goToAssignments()");
		PlaceRequest placeRequest = new PlaceRequest.Builder().nameToken(KipNameTokens.AREA_TASK_ASSIGNMENT).build();
		placeManager.revealPlace(placeRequest);
	}

	private void loadData() {
		getView().setData(createData());
	}

	private List<AssignmentSummaryDto> createData() {
		List<AssignmentSummaryDto> result = new ArrayList<AssignmentSummaryDto>();

		result.add(createAssignment("Kis Beáta", 5, 6));
		result.add(createAssignment("Nagy Karolina", 2, 3));
		result.add(createAssignment("Barna Éva", 3, 4));
		result.add(createAssignment("Kis Darinka", 5, 1));
		result.add(createAssignment("Pöttyös Panni", 1, 5));

		return result;
	}

	private AssignmentSummaryDto createAssignment(String userName, Integer clean, Integer dirty) {

		AppUserDto au1 = new AppUserDto();
		au1.setName(userName);

		AssignmentSummaryDto result = new AssignmentSummaryDto();
		result.setAttendantDto(au1);

		Map<RoomStatus, Integer> statusMap1 = new HashMap<RoomStatus, Integer>();
		statusMap1.put(RoomStatus.DIRTY, clean);
		statusMap1.put(RoomStatus.CLEAN, dirty);
		result.setRoomSummary(statusMap1);

		return result;
	}
}
