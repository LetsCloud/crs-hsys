/**
 * 
 */
package io.crs.hsys.client.kip.chat.list;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;

import gwt.material.design.client.ui.MaterialCollectionItem;
import gwt.material.design.client.ui.MaterialImage;
import gwt.material.design.client.ui.MaterialLabel;
import io.crs.hsys.shared.dto.chat.ChatDto;

/**
 * @author robi
 *
 */
public class ChatListItem extends Composite {

	private static ChatListItemUiBinder uiBinder = GWT.create(ChatListItemUiBinder.class);

	interface ChatListItemUiBinder extends UiBinder<Widget, ChatListItem> {
	}

	@UiField
	MaterialCollectionItem collectionItem;

	@UiField
	MaterialImage senderImage;

	@UiField
	MaterialLabel senderLabel, createdLabel, messageLabel;

	/**
	 * 
	 */
	public ChatListItem() {
		initWidget(uiBinder.createAndBindUi(this));
	}

	public ChatListItem(ChatDto chat) {
		this();
		senderImage.setUrl(chat.getSender().getPicture());
		senderLabel.setText(chat.getSender().getName());
		createdLabel.setText(chat.getCreated().toString());
		messageLabel.setText(chat.getMessage());
	}

	public ChatListItem(ChatDto chat, Boolean selected) {
		this(chat);
		setSelected(selected);
	}

	@UiHandler("chatIcon")
	public void onChatIconClick(ClickEvent event) {
	}

	public void setSelected(Boolean selected) {
		if (selected) {
			this.getElement().getStyle().setBackgroundColor("silver");
		} else {
			this.getElement().getStyle().setBackgroundColor("white");
		}
	}
}
