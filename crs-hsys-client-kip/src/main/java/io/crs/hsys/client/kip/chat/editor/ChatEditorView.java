/**
 * 
 */
package io.crs.hsys.client.kip.chat.editor;

import java.util.List;
import java.util.logging.Logger;

import javax.inject.Inject;

import com.google.gwt.editor.client.Editor;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Widget;
import com.gwtplatform.mvp.client.ViewWithUiHandlers;

import gwt.material.design.client.ui.MaterialCardTitle;
import io.crs.hsys.client.core.i18n.CoreMessages;
import io.crs.hsys.shared.dto.chat.ChatDto;
import io.crs.hsys.shared.dto.chat.ChatPostDto;
import io.crs.hsys.shared.dto.common.AppUserDto;

/**
 * @author robi
 *
 */
public class ChatEditorView extends ViewWithUiHandlers<ChatEditorUiHandlers>
		implements ChatEditorPresenter.MyView, Editor<ChatDto> {
	private static Logger logger = Logger.getLogger(ChatEditorView.class.getName());

	interface Binder extends UiBinder<Widget, ChatEditorView> {
	}

	private final CoreMessages i18n;

	@UiField
	MaterialCardTitle chatTitle;
	
	@UiField
	SendMessageWidget sendMessageWidget;
	
	@UiField
	PostPanelWidget chatPostsWidget;
	
	/**
	 * 
	 */
	@Inject
	ChatEditorView(Binder uiBinder, CoreMessages i18n) {
		logger.info("ChatEditorView()");

		initWidget(uiBinder.createAndBindUi(this));

		this.i18n = i18n;

		sendMessageWidget.addSendIconClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				addPost(sendMessageWidget.getText());
				sendMessageWidget.clearText();
			}
		});

	}

	@Override
	public void setReceivers(List<AppUserDto> receivers) {
		// TODO Auto-generated method stub

	}

	@Override
	public void setPosts(List<ChatPostDto> posts, String currentUserKey) {
		chatPostsWidget.setPosts(posts, currentUserKey);
	}

	@Override
	public void addPost(String message) {
		getUiHandlers().addPost(message);
	}

}
