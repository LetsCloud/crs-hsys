/**
 * 
 */
package io.crs.hsys.client.kip.chat;

import java.util.logging.Logger;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.inject.Inject;
import com.google.web.bindery.event.shared.EventBus;
import com.gwtplatform.dispatch.rest.client.RestDispatch;
import com.gwtplatform.mvp.client.HasUiHandlers;
import com.gwtplatform.mvp.client.Presenter;
import com.gwtplatform.mvp.client.PresenterWidget;
import com.gwtplatform.mvp.client.View;
import com.gwtplatform.mvp.client.annotations.NameToken;
import com.gwtplatform.mvp.client.annotations.ProxyStandard;
import com.gwtplatform.mvp.client.presenter.slots.SingleSlot;
import com.gwtplatform.mvp.client.proxy.ProxyPlace;
import com.gwtplatform.mvp.shared.proxy.PlaceRequest;

import gwt.material.design.client.base.MaterialWidget;
import gwt.material.design.client.pwa.PwaManager;
import gwt.material.design.client.ui.MaterialToast;

import io.crs.hsys.client.core.app.AppServiceWorkerManager;
import io.crs.hsys.client.core.event.SetPageTitleEvent;
import io.crs.hsys.client.core.firebase.messaging.MessagingManager;
import io.crs.hsys.client.core.util.Base64Utils;
import io.crs.hsys.shared.api.FcmResource;
import io.crs.hsys.shared.constans.MenuItemType;
import io.crs.hsys.client.kip.KipAppPresenter;
import io.crs.hsys.client.kip.KipNameTokens;
import io.crs.hsys.client.kip.chat.creator.ChatCreatorFactory;
import io.crs.hsys.client.kip.chat.creator.ChatCreatorPresenter;
import io.crs.hsys.client.kip.chat.list.ChatListFactory;
import io.crs.hsys.client.kip.chat.list.ChatListPresenter;
import io.crs.hsys.client.kip.i18n.KipMessages;

/**
 * @author robi
 *
 */
public class ChatRoomPresenter extends Presenter<ChatRoomPresenter.MyView, ChatRoomPresenter.MyProxy>
		implements ChatRoomUiHandlers {
	private static Logger logger = Logger.getLogger(ChatRoomPresenter.class.getName());

	interface MyView extends View, HasUiHandlers<ChatRoomUiHandlers> {
	}

	public static final SingleSlot<PresenterWidget<?>> LIST_SLOT = new SingleSlot<>();
	public static final SingleSlot<PresenterWidget<?>> CREATOR_SLOT = new SingleSlot<>();

	PwaManager manager = PwaManager.getInstance();

	private final ChatListPresenter chatListPresenter;
	private final ChatCreatorPresenter chatCreatorPresenter;
	private final RestDispatch dispatcher;
	private final FcmResource fcmService;
	private final MessagingManager messagingManager;
	private final KipMessages i18n;

	@ProxyStandard
	@NameToken(KipNameTokens.CHAT_ROOM)
	interface MyProxy extends ProxyPlace<ChatRoomPresenter> {
	}

	@Inject
	ChatRoomPresenter(EventBus eventBus, MyView view, MyProxy proxy, ChatListFactory chatListFactory,
			ChatCreatorFactory chatCreatorFactory, RestDispatch dispatcher, FcmResource fcmService,
			MessagingManager messagingManager, KipMessages i18n) {
		super(eventBus, view, proxy, KipAppPresenter.SLOT_MAIN);
		logger.info("ChatRoomPresenter()");

		this.chatListPresenter = chatListFactory.createChatListPresenter();
		this.chatCreatorPresenter = chatCreatorFactory.createChatCreatorPresenter();
		this.fcmService = fcmService;
		this.dispatcher = dispatcher;
		this.messagingManager = messagingManager;
		this.i18n = i18n;

		getView().setUiHandlers(this);
	}

	@Override
	protected void onBind() {
		super.onBind();

		setInSlot(LIST_SLOT, chatListPresenter);
		setInSlot(CREATOR_SLOT, chatCreatorPresenter);
	}

	@Override
	public void prepareFromRequest(PlaceRequest request) {
		String param = request.getParameter("id", null);
		chatListPresenter.loadData(param);
	}

	@Override
	protected void onReveal() {
		super.onReveal();
		SetPageTitleEvent.fire(i18n.chatRoomTitle(), i18n.chatRoomDescription(), MenuItemType.MENU_ITEM, this);
	}

	@Override
	public AppServiceWorkerManager getServiceWorkerManager() {

		if (manager.getServiceWorkerManager() instanceof AppServiceWorkerManager) {
			return (AppServiceWorkerManager) manager.getServiceWorkerManager();
		}
		GWT.log("Push Notification Manager is not yet registered");

		return null;
	}

	@Override
	public MessagingManager getMessagingManager() {
		return messagingManager;
	}

	@Override
	public void subToServer(String iidToken) {
		logger.info("subToServer()");
		String userAgent = Base64Utils.toBase64(getUserAgent().getBytes());
		logger.info("subToServer()->userAgent=" + userAgent);
		dispatcher.execute(fcmService.subscribe(iidToken, userAgent), new AsyncCallback<Void>() {

			@Override
			public void onSuccess(Void response) {
				MaterialToast.fireToast("Sussecfull subscription!");
			}

			@Override
			public void onFailure(Throwable throwable) {
				MaterialToast.fireToast(throwable.getMessage());
			}
		});
	}

	@Override
	public void createChat(MaterialWidget source) {
		chatCreatorPresenter.open(source);
	}

	/**
	 * 
	 * @return
	 */
	public static native String getUserAgent() /*-{
		return $wnd.navigator.userAgent.toLowerCase();
	}-*/;

	private static native String b64decode(String a) /*-{
		return window.atob(a);
	}-*/;

}
