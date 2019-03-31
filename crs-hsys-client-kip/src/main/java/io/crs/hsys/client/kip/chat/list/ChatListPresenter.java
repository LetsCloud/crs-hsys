/**
 * 
 */
package io.crs.hsys.client.kip.chat.list;

import java.util.List;
import java.util.logging.Logger;

import javax.inject.Inject;

import com.google.common.base.Strings;
import com.google.web.bindery.event.shared.EventBus;
import com.gwtplatform.dispatch.rest.delegates.client.ResourceDelegate;
import com.gwtplatform.mvp.client.HasUiHandlers;
import com.gwtplatform.mvp.client.PresenterWidget;
import com.gwtplatform.mvp.client.View;
import com.gwtplatform.mvp.client.presenter.slots.SingleSlot;
import com.gwtplatform.mvp.client.proxy.PlaceManager;
import com.gwtplatform.mvp.shared.proxy.PlaceRequest;
import com.gwtplatform.mvp.shared.proxy.PlaceRequest.Builder;

import io.crs.hsys.shared.api.ChatResource;
import io.crs.hsys.shared.dto.chat.ChatDto;
import io.crs.hsys.client.core.message.callback.AbstractAsyncCallback;
import io.crs.hsys.client.kip.KipNameTokens;
import io.crs.hsys.client.kip.chat.editor.ChatEditorFactory;
import io.crs.hsys.client.kip.chat.editor.ChatEditorPresenter;

/**
 * @author robi
 *
 */
public class ChatListPresenter extends PresenterWidget<ChatListPresenter.MyView> implements ChatListUiHandlers {
	private static Logger logger = Logger.getLogger(ChatListPresenter.class.getName());

	public static final SingleSlot<PresenterWidget<?>> EDITOR_SLOT = new SingleSlot<>();

	public interface MyView extends View, HasUiHandlers<ChatListUiHandlers> {
		void setData(List<ChatDto> data, String chatWebSafeKey);

		void refreshPanel(Boolean showEditor);
	}

	private final PlaceManager placeManager;
	private final ChatEditorPresenter chatEditorPresenter;
	private final ResourceDelegate<ChatResource> resourceDelegate;

	@Inject
	ChatListPresenter(EventBus eventBus, PlaceManager placeManager, MyView view,
			ChatEditorFactory chatEditorPresenterFactory, ResourceDelegate<ChatResource> resourceDelegate) {
		super(eventBus, view);
		logger.info("ChatListPresenter()");

		this.placeManager = placeManager;
		this.chatEditorPresenter = chatEditorPresenterFactory.createChatEditorPresenter();
		this.resourceDelegate = resourceDelegate;

		getView().setUiHandlers(this);
	}

	@Override
	protected void onBind() {
		super.onBind();
		setInSlot(EDITOR_SLOT, chatEditorPresenter);
	}

	@Override
	public void loadData(String chatWebSafeKey) {
		getView().refreshPanel(!Strings.isNullOrEmpty(chatWebSafeKey));

		resourceDelegate.withCallback(new AbstractAsyncCallback<List<ChatDto>>() {
			@Override
			public void onSuccess(List<ChatDto> result) {
				result.sort((ChatDto c1, ChatDto c2) -> c2.getUpdated().compareTo(c1.getUpdated()));

				if (Strings.isNullOrEmpty(chatWebSafeKey) && result.size() > 0) {
					getView().setData(result, result.get(0).getWebSafeKey());
					chatEditorPresenter.loadData(result.get(0).getWebSafeKey());
				} else {
					getView().setData(result, chatWebSafeKey);
					chatEditorPresenter.loadData(chatWebSafeKey);
				}
			}
		}).list();
	}

	@Override
	public void onSelectChat(String chatWebSafeKey) {
		PlaceRequest placeRequest = new Builder().nameToken(KipNameTokens.CHAT_ROOM)
				.with("id", String.valueOf(chatWebSafeKey)).build();

		placeManager.revealPlace(placeRequest);
	}
}
