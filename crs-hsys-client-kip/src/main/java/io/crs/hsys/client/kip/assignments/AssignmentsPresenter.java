/**
 * 
 */
package io.crs.hsys.client.kip.assignments;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.google.inject.Inject;
import com.google.web.bindery.event.shared.EventBus;
import com.gwtplatform.mvp.client.HasUiHandlers;
import com.gwtplatform.mvp.client.Presenter;
import com.gwtplatform.mvp.client.PresenterWidget;
import com.gwtplatform.mvp.client.View;
import com.gwtplatform.mvp.client.annotations.NameToken;
import com.gwtplatform.mvp.client.annotations.ProxyStandard;
import com.gwtplatform.mvp.client.annotations.UseGatekeeper;
import com.gwtplatform.mvp.client.presenter.slots.SingleSlot;
import com.gwtplatform.mvp.client.presenter.slots.Slot;
import com.gwtplatform.mvp.client.proxy.ProxyPlace;

import io.crs.hsys.client.core.event.SetPageTitleEvent;
import io.crs.hsys.client.core.security.LoggedInGatekeeper;
import io.crs.hsys.shared.cnst.AssignmentStatus;
import io.crs.hsys.shared.cnst.MenuItemType;
import io.crs.hsys.shared.dto.common.AppUserDto;
import io.crs.hsys.shared.dto.hk.CleanTypeDto;
import io.crs.hsys.shared.dto.hk.HkAssignmentDto;
import io.crs.hsys.shared.dto.hotel.RoomDto;
import io.crs.hsys.client.kip.KipAppPresenter;
import io.crs.hsys.client.kip.KipNameTokens;
import io.crs.hsys.client.kip.assignments.editor.AssignmentEditorFactory;
import io.crs.hsys.client.kip.assignments.editor.AssignmentEditorPresenter;
import io.crs.hsys.client.kip.assignments.widget.AssignmentWidgetFactory;
import io.crs.hsys.client.kip.assignments.widget.AssignmentWidgetPresenter;
import io.crs.hsys.client.kip.filter.KipFilterPresenterFactory;
import io.crs.hsys.client.kip.filter.assignment.AssignmentFilterPresenter;
import io.crs.hsys.client.kip.i18n.KipMessages;

/**
 * @author CR
 *
 */
public class AssignmentsPresenter extends Presenter<AssignmentsPresenter.MyView, AssignmentsPresenter.MyProxy>
		implements AssignmentsUiHandlers, AssignmentEditEvent.AssignmentEditEventHandler {
	private static Logger logger = Logger.getLogger(AssignmentsPresenter.class.getName());

	interface MyView extends View, HasUiHandlers<AssignmentsUiHandlers> {
	}


	public static final SingleSlot<PresenterWidget<?>> SLOT_FILTER = new SingleSlot<>();
	public static final SingleSlot<PresenterWidget<?>> SLOT_MODAL = new SingleSlot<>();
	public static final Slot<PresenterWidget<?>> SLOT_ASSIGNMENTS = new Slot<>();

	@ProxyStandard
	@NameToken(KipNameTokens.AREA_TASK_ASSIGNMENT)
	@UseGatekeeper(LoggedInGatekeeper.class)
	interface MyProxy extends ProxyPlace<AssignmentsPresenter> {
	}

	private final AssignmentFilterPresenter filterPresenter;
	private final AssignmentWidgetFactory assignmentWidgetFactory;
	private final AssignmentEditorPresenter assignmentEditPresenter;
	private final KipMessages i18n;

	@Inject
	AssignmentsPresenter(EventBus eventBus, MyView view, MyProxy proxy, AssignmentWidgetFactory assignmentWidgetFactory,
			AssignmentEditorFactory assignmentEditFactory, KipMessages i18n, KipFilterPresenterFactory filterPresenterFactory) {
		super(eventBus, view, proxy, KipAppPresenter.SLOT_MAIN);
		logger.log(Level.INFO, "AssignmentsPresenter()");

		this.filterPresenter = filterPresenterFactory.createAssignmentFilter();
		this.assignmentWidgetFactory = assignmentWidgetFactory;
		this.assignmentEditPresenter = assignmentEditFactory.createAssignmentEdit();
		this.i18n = i18n;

		getView().setUiHandlers(this);
	}

	@Override
	protected void onBind() {
		super.onBind();

		setInSlot(SLOT_FILTER, filterPresenter);
		setInSlot(SLOT_MODAL, assignmentEditPresenter);

		addRegisteredHandler(AssignmentEditEvent.TYPE, this);
	}

	@Override
	protected void onReveal() {
		super.onReveal();
		String description = i18n.assignmentsDescription();
		description = i18n.assignmentsTasksAssignedTo("Kiss Piroska");
		SetPageTitleEvent.fire(i18n.assignmentsTitle(), description, MenuItemType.MENU_ITEM, this);
		
		for (HkAssignmentDto data: getData()) {
			AssignmentWidgetPresenter widget = assignmentWidgetFactory.assignmentWidgetPresenter();
			widget.setData(data);
			addToSlot(SLOT_ASSIGNMENTS, widget);
		}
		
	}

	@Override
	public void onEditAssignment(AssignmentEditEvent event) {
		logger.log(Level.INFO, "AssignmentsPresenter.onEditAssignment()");
		assignmentEditPresenter.open();
		setInSlot(SLOT_MODAL, assignmentEditPresenter);
	}


	private List<HkAssignmentDto> getData() {
		List<HkAssignmentDto> result  = new ArrayList<HkAssignmentDto>();
		
		CleanTypeDto teljes = new CleanTypeDto();
		teljes.setDescription("Teljes");
		teljes.setTime(20);
		
		AppUserDto kissPiri = new AppUserDto();
		kissPiri.setUsername("Kiss Piroska");
		AppUserDto nagyMari = new AppUserDto();
		nagyMari.setUsername("Nagy Mária");
		
		RoomDto room101 = new RoomDto();
		room101.setCode("101");
		RoomDto room102 = new RoomDto();
		room102.setCode("102");
		RoomDto room103 = new RoomDto();
		room103.setCode("103");
		RoomDto room104 = new RoomDto();
		room104.setCode("104");
		RoomDto room105 = new RoomDto();
		room105.setCode("105");
		RoomDto room106 = new RoomDto();
		room106.setCode("106");
		
		HkAssignmentDto a1 = new HkAssignmentDto();
		a1.setRoomDto(room101);
		a1.setAttendantDto(kissPiri);
		a1.setInspectorDto(nagyMari);
		a1.setCleanTypeDto(teljes);
		a1.setNotice("Akármi");
		a1.setStatus(AssignmentStatus.OPEN);
		result.add(a1);
		
		HkAssignmentDto a2 = new HkAssignmentDto();
		a2.setRoomDto(room102);
		a2.setAttendantDto(kissPiri);
		a2.setInspectorDto(nagyMari);
		a2.setCleanTypeDto(teljes);
		a2.setNotice("Akármi");
		a2.setStatus(AssignmentStatus.OPEN);
		result.add(a2);
		
		return result;
	}

}
