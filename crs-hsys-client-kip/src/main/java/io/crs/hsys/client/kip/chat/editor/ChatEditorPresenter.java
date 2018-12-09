/**
 * 
 */
package io.crs.hsys.client.kip.chat.editor;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.inject.Inject;

import com.google.common.base.Strings;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.web.bindery.event.shared.EventBus;
import com.gwtplatform.dispatch.rest.delegates.client.ResourceDelegate;
import com.gwtplatform.mvp.client.HasUiHandlers;
import com.gwtplatform.mvp.client.PresenterWidget;
import com.gwtplatform.mvp.client.View;

import io.crs.hsys.client.core.security.CurrentUser;
import io.crs.hsys.shared.api.ChatResource;
import io.crs.hsys.shared.dto.chat.AddPostDto;
import io.crs.hsys.shared.dto.chat.ChatDto;
import io.crs.hsys.shared.dto.chat.ChatPostDto;
import io.crs.hsys.shared.dto.common.AppUserDto;

/**
 * @author robi
 *
 */
public class ChatEditorPresenter extends PresenterWidget<ChatEditorPresenter.MyView> implements ChatEditorUiHandlers {
	private static Logger logger = Logger.getLogger(ChatEditorPresenter.class.getName());

	public interface MyView extends View, HasUiHandlers<ChatEditorUiHandlers> {
		void setReceivers(List<AppUserDto> receivers);

		void setPosts(List<ChatPostDto> posts, String currentUserKey);

		void addPost(String message);
	}

	private final ResourceDelegate<ChatResource> chatResource;
	private final CurrentUser currentUser;
	private String chatWebSafeKey;

	@Inject
	ChatEditorPresenter(EventBus eventBus, MyView view, ResourceDelegate<ChatResource> chatResource,
			CurrentUser currentUser) {
		super(eventBus, view);
		this.chatResource = chatResource;
		this.currentUser = currentUser;
		getView().setUiHandlers(this);
	}

	@Override
	public void loadData(String chatWebSafeKey) {
		this.chatWebSafeKey = chatWebSafeKey;

		if (!Strings.isNullOrEmpty(chatWebSafeKey))
			chatResource.withCallback(new AsyncCallback<ChatDto>() {
				@Override
				public void onSuccess(ChatDto result) {
					List<ChatPostDto> sortedResult = result.getPosts();
					sortedResult.sort((ChatPostDto c1, ChatPostDto c2) -> c2.getCreated().compareTo(c1.getCreated()));
					getView().setPosts(sortedResult, currentUser.getAppUserDto().getWebSafeKey());
				}

				@Override
				public void onFailure(Throwable caught) {
				}
			}).getByKey(chatWebSafeKey);
	}

	@Override
	public void addPost(String message) {

		AddPostDto dto = new AddPostDto();
		dto.setChatWebSafeKey(chatWebSafeKey);
		dto.setSender(currentUser.getAppUserDto());
		dto.setMessage(message);

		modifyEntity(dto);
	}

	private void modifyEntity(AddPostDto dto) {
		logger.log(Level.INFO, "modifyEntity");
		chatResource.withCallback(new AsyncCallback<ChatDto>() {
			@Override
			public void onSuccess(ChatDto result) {
				logger.log(Level.INFO, "modifyEntity().onSuccess");
				List<ChatPostDto> sortedResult = result.getPosts();
				sortedResult.sort((ChatPostDto c1, ChatPostDto c2) -> c2.getCreated().compareTo(c1.getCreated()));
				getView().setPosts(sortedResult, currentUser.getAppUserDto().getWebSafeKey());
			}

			@Override
			public void onFailure(Throwable caught) {
			}
		}).addPost(dto);
	}
}
