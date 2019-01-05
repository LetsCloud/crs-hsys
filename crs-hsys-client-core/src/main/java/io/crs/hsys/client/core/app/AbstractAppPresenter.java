package io.crs.hsys.client.core.app;

import java.util.logging.Logger;

import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.web.bindery.event.shared.EventBus;

import com.gwtplatform.dispatch.rest.client.RestDispatch;
import com.gwtplatform.dispatch.rest.delegates.client.ResourceDelegate;
import com.gwtplatform.mvp.client.Presenter;
import com.gwtplatform.mvp.client.PresenterWidget;
import com.gwtplatform.mvp.client.View;
import com.gwtplatform.mvp.client.presenter.slots.NestedSlot;
import com.gwtplatform.mvp.client.presenter.slots.PermanentSlot;
import com.gwtplatform.mvp.client.presenter.slots.SingleSlot;
import com.gwtplatform.mvp.client.proxy.NavigationEvent;
import com.gwtplatform.mvp.client.proxy.NavigationHandler;
import com.gwtplatform.mvp.client.proxy.PlaceManager;
import com.gwtplatform.mvp.client.proxy.Proxy;

import io.crs.hsys.client.core.app.AbstractAppPresenter.MyView;
import io.crs.hsys.client.core.event.SetPageTitleEvent;
import io.crs.hsys.client.core.event.SetPageTitleEvent.SetPageTitleHandler;
import io.crs.hsys.client.core.firebase.messaging.MessagingManager;
import io.crs.hsys.client.core.menu.MenuPresenter;
import io.crs.hsys.client.core.security.CurrentUser;
import io.crs.hsys.shared.api.AuthResource;
import io.crs.hsys.shared.api.GlobalConfigResource;

public abstract class AbstractAppPresenter<Proxy_ extends Proxy<?>> extends Presenter<MyView, Proxy_>
		implements NavigationHandler, SetPageTitleHandler {
	private static Logger logger = Logger.getLogger(AbstractAppPresenter.class.getName());

	public interface MyView extends View {
		void setPageTitle(String title, String description);

		void displayUserName(String userName);
	}

	public static final PermanentSlot<MenuPresenter> SLOT_MENU = new PermanentSlot<>();
	public static final NestedSlot SLOT_MAIN = new NestedSlot();
	public static final SingleSlot<PresenterWidget<?>> SLOT_MODAL = new SingleSlot<>();

	private final RestDispatch dispatch;
	private final AuthResource authService;
	private final MenuPresenter menuPresenter;
	private final AppServiceWorkerManager swManager;

	protected AbstractAppPresenter(EventBus eventBus, MyView view, Proxy_ proxy, PlaceManager placeManager,
			RestDispatch dispatch, AuthResource authService,
			ResourceDelegate<GlobalConfigResource> globalConfigResource, MenuPresenter menuPresenter,
			CurrentUser currentUser, String appCode, AppServiceWorkerManager swManager,
			MessagingManager messagingManager) {
		super(eventBus, view, proxy, RevealType.Root);
		logger.info("AbstractAppPresenter()");

		this.dispatch = dispatch;
		this.authService = authService;
		this.menuPresenter = menuPresenter;
		this.swManager = swManager;
	}

	@Override
	protected void onBind() {
		super.onBind();
		logger.info("onBind()");
		setInSlot(SLOT_MENU, menuPresenter);

		addRegisteredHandler(NavigationEvent.getType(), this);
		addRegisteredHandler(SetPageTitleEvent.TYPE, this);
	}

	@Override
	protected void onReveal() {
		super.onReveal();
		logger.info("onReveal()");
		menuPresenter.referesh();
		DOM.getElementById("splashscreen").removeFromParent();
	}

	
	public void logout() {
		dispatch.execute(authService.logout(), new AsyncCallback<Void>() {

			@Override
			public void onSuccess(Void result) {
//				PlaceRequest placeRequest = new PlaceRequest.Builder().nameToken(CoreNameTokens.getLogin()).build();
//				currentUser.setLoggedIn(false);
//				placeManager.revealPlace(placeRequest);
			}

			@Override
			public void onFailure(Throwable caught) {
				// TODO Auto-generated method stub
			}
		});
	}

	@Override
	public void onNavigation(NavigationEvent navigationEvent) {
		Window.scrollTo(0, 0);
	}

	@Override
	public void onSetPageTitle(SetPageTitleEvent event) {
		getView().setPageTitle(event.getTitle(), event.getDescription());
		menuPresenter.adjustMenuItems(event.getMenuItemType());
	}

	public MenuPresenter getMenuPresenter() {
		return menuPresenter;
	}

	public AppServiceWorkerManager getServiceWorkerManager() {
		return swManager;
	}
}
