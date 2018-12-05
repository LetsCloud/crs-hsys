package io.crs.hsys.client.kip.browser.guestroom;

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
import com.gwtplatform.mvp.client.presenter.slots.SingleSlot;
import com.gwtplatform.mvp.client.presenter.slots.Slot;
import com.gwtplatform.mvp.client.proxy.ProxyPlace;

import io.crs.hsys.client.core.event.SetPageTitleEvent;
import io.crs.hsys.client.kip.KipAppPresenter;
import io.crs.hsys.client.kip.KipNameTokens;
import io.crs.hsys.client.kip.assignments.AssignmentEditEvent;
import io.crs.hsys.client.kip.assignments.editor.AssignmentEditorFactory;
import io.crs.hsys.client.kip.assignments.editor.AssignmentEditorPresenter;
import io.crs.hsys.client.kip.assignments.widget.AssignmentWidgetFactory;
import io.crs.hsys.client.kip.filter.FilterPresenterFactory;
import io.crs.hsys.client.kip.filter.assignment.AssignmentFilterPresenter;
import io.crs.hsys.client.kip.i18n.KipMessages;
import io.crs.hsys.shared.constans.MenuItemType;
import io.crs.hsys.shared.constans.RoomStatus;
import io.crs.hsys.shared.dto.hotel.RoomDto;
import io.crs.hsys.shared.dto.hotel.RoomTypeDtor;

public class GuestRoomBrowserPresenter
		extends Presenter<GuestRoomBrowserPresenter.MyView, GuestRoomBrowserPresenter.MyProxy>
		implements GuestRoomBrowserUiHandlers, AssignmentEditEvent.AssignmentEditEventHandler {
	private static Logger logger = Logger.getLogger(GuestRoomBrowserPresenter.class.getName());

	interface MyView extends View, HasUiHandlers<GuestRoomBrowserUiHandlers> {
		void addData(RoomDto data);
	}

	public static final SingleSlot<PresenterWidget<?>> SLOT_FILTER = new SingleSlot<>();
	public static final SingleSlot<PresenterWidget<?>> SLOT_MODAL = new SingleSlot<>();
	public static final Slot<PresenterWidget<?>> SLOT_ASSIGNMENTS = new Slot<>();

	@ProxyStandard
	@NameToken(KipNameTokens.GUEST_ROOMS2)
	interface MyProxy extends ProxyPlace<GuestRoomBrowserPresenter> {
	}

	private final AssignmentFilterPresenter filterPresenter;
//	private final AssignmentWidgetFactory assignmentWidgetFactory;
	private final AssignmentEditorPresenter assignmentEditPresenter;
	private final KipMessages i18n;

	@Inject
	GuestRoomBrowserPresenter(EventBus eventBus, MyView view, MyProxy proxy,
			AssignmentWidgetFactory assignmentWidgetFactory, AssignmentEditorFactory assignmentEditFactory,
			KipMessages i18n, FilterPresenterFactory filterPresenterFactory) {
		super(eventBus, view, proxy, KipAppPresenter.SLOT_MAIN);
		logger.log(Level.INFO, "GuestRoomBrowserPresenter()");

		this.filterPresenter = filterPresenterFactory.createAssignmentFilterPresenter();
//		this.assignmentWidgetFactory = assignmentWidgetFactory;
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

		for (RoomDto data : getData()) {
			getView().addData(data);
		}

	}

	@Override
	public void onEditAssignment(AssignmentEditEvent event) {
		logger.log(Level.INFO, "AssignmentsPresenter.onEditAssignment()");
		assignmentEditPresenter.open();
		setInSlot(SLOT_MODAL, assignmentEditPresenter);
	}

	private List<RoomDto> getData() {
		List<RoomDto> result = new ArrayList<RoomDto>();

		RoomTypeDtor dblbRTD = RoomTypeDtor.builder().code("DBLB").name("Double bed room").build();
		RoomTypeDtor twinRTD = RoomTypeDtor.builder().code("TWIN").name("Twin bed room").build();

		result.add(
				new RoomDto.Builder().code("101").description("asdsadasd").floor("1").roomStatus(RoomStatus.CLEAN).roomType(dblbRTD).build());
		result.add(
				new RoomDto.Builder().code("102").description("asdsadasd").floor("1").roomStatus(RoomStatus.CLEAN).roomType(dblbRTD).build());
		result.add(
				new RoomDto.Builder().code("103").description("asdsadasd").floor("1").roomStatus(RoomStatus.CLEAN).roomType(dblbRTD).build());
		result.add(
				new RoomDto.Builder().code("104").description("asdsadasd").floor("1").roomStatus(RoomStatus.CLEAN).roomType(twinRTD).build());
		result.add(
				new RoomDto.Builder().code("105").description("asdsadasd").floor("1").roomStatus(RoomStatus.CLEAN).roomType(twinRTD).build());
		result.add(
				new RoomDto.Builder().code("106").description("asdsadasd").floor("1").roomStatus(RoomStatus.CLEAN).roomType(twinRTD).build());

		return result;
	}

}
