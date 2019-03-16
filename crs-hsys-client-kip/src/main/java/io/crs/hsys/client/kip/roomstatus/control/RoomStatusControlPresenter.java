/**
 * 
 */
package io.crs.hsys.client.kip.roomstatus.control;

import java.util.logging.Level;
import java.util.logging.Logger;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.inject.Inject;
import com.google.web.bindery.event.shared.EventBus;
import com.gwtplatform.dispatch.rest.delegates.client.ResourceDelegate;
import com.gwtplatform.mvp.client.HasUiHandlers;
import com.gwtplatform.mvp.client.Presenter;
import com.gwtplatform.mvp.client.View;
import com.gwtplatform.mvp.client.annotations.NameToken;
import com.gwtplatform.mvp.client.annotations.ProxyCodeSplit;
import com.gwtplatform.mvp.client.proxy.PlaceManager;
import com.gwtplatform.mvp.client.proxy.ProxyPlace;
import com.gwtplatform.mvp.shared.proxy.PlaceRequest;

import io.crs.hsys.client.core.security.CurrentUser;
import io.crs.hsys.client.kip.KipAppPresenter;
import io.crs.hsys.client.kip.KipNameTokens;
import io.crs.hsys.shared.api.RoomResource;
import io.crs.hsys.shared.dto.common.AppUserDto;
import io.crs.hsys.shared.dto.hk.RoomStatusDto;

import static io.crs.hsys.shared.api.ApiParameters.WEBSAFEKEY;

/**
 * @author robi
 *
 */
public class RoomStatusControlPresenter
		extends Presenter<RoomStatusControlPresenter.MyView, RoomStatusControlPresenter.MyProxy>
		implements RoomStatusControlUiHandlers {
	private static Logger logger = Logger.getLogger(RoomStatusControlPresenter.class.getName());

	interface MyView extends View, HasUiHandlers<RoomStatusControlUiHandlers> {
		void open(RoomStatusDto dto, AppUserDto admin);

		void close();
	}

	@ProxyCodeSplit
	@NameToken(KipNameTokens.ROOM_CONTROL)
	interface MyProxy extends ProxyPlace<RoomStatusControlPresenter> {
	}

	private String webSafeKey;

	private final PlaceManager placeManager;
	private final ResourceDelegate<RoomResource> resourceDelegate;
	private final CurrentUser currentUser;

	@Inject
	RoomStatusControlPresenter(EventBus eventBus, MyView view, MyProxy proxy, PlaceManager placeManager,
			ResourceDelegate<RoomResource> resourceDelegate, CurrentUser currentUser) {
		super(eventBus, view, proxy, KipAppPresenter.SLOT_MAIN);
		logger.log(Level.INFO, "RoomStatusControllPresenter()");
		this.placeManager = placeManager;
		this.resourceDelegate = resourceDelegate;
		this.currentUser = currentUser;
		getView().setUiHandlers(this);
	}

	@Override
	protected void onBind() {
		super.onBind();
	}

	@Override
	public void prepareFromRequest(PlaceRequest request) {
		webSafeKey = request.getParameter(WEBSAFEKEY, null);
	}

	@Override
	protected void onReveal() {
		super.onReveal();
		logger.log(Level.INFO, "RoomStatusControllPresenter().onReveal()");

		resourceDelegate.withCallback(new AsyncCallback<RoomStatusDto>() {
			@Override
			public void onSuccess(RoomStatusDto dto) {
				logger.log(Level.INFO, "RoomStatusControllPresenter().onReveal().onSuccess()");
				getView().open(dto, currentUser.getAppUserDto());
			}

			@Override
			public void onFailure(Throwable caught) {
			}
		}).getRoomStatus(webSafeKey);

	}

	@Override
	public void navBack() {
		getView().close();
		placeManager.navigateBack();
	}
}