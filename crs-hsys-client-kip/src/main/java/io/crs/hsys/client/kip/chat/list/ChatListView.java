/**
 * 
 */
package io.crs.hsys.client.kip.chat.list;

import java.util.List;
import java.util.logging.Logger;

import javax.inject.Inject;

import com.google.gwt.dom.client.Style.Display;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.Widget;
import com.gwtplatform.mvp.client.ViewWithUiHandlers;

import gwt.material.design.client.constants.RadioButtonType;
import gwt.material.design.client.ui.MaterialCollection;
import gwt.material.design.client.ui.MaterialColumn;
import gwt.material.design.client.ui.MaterialRadioButton;
import gwt.material.design.client.ui.MaterialSwitch;

import io.crs.hsys.client.core.i18n.CoreMessages;
import io.crs.hsys.shared.dto.chat.ChatDto;

/**
 * @author robi
 *
 */
public class ChatListView extends ViewWithUiHandlers<ChatListUiHandlers> implements ChatListPresenter.MyView {
	private static Logger logger = Logger.getLogger(ChatListView.class.getName());

	interface Binder extends UiBinder<Widget, ChatListView> {
	}

	private final CoreMessages i18n;

	@UiField
	SimplePanel editorPanel;

	@UiField
	MaterialCollection materialCollection;

	@UiField
	MaterialSwitch openChats;

	@UiField
	MaterialRadioButton openChats2;

	@UiField
	MaterialColumn leftColumn, rightColumn;

	/**
	* 
	*/
	@Inject
	ChatListView(Binder uiBinder, CoreMessages i18n) {
		logger.info("ChatListView()");

		initWidget(uiBinder.createAndBindUi(this));

		this.i18n = i18n;

		openChats2.setType(RadioButtonType.GAP);
		openChats2.setValue(true);

		openChats.setTitle("Only open chats");

		bindSlot(ChatListPresenter.EDITOR_SLOT, editorPanel);
	}

	/*
	 * private void resizePanel(Integer height) { Integer h = height - 280;
	 * materialCollection.setHeight(h.toString() + "px"); }
	 */
	@Override
	public void refreshPanel(Boolean showEditor) {
		if (Window.getClientWidth() <= 600) {
			if (showEditor) {
				leftColumn.getElement().getStyle().setDisplay(Display.NONE);
				rightColumn.getElement().getStyle().setDisplay(Display.BLOCK);
			} else {
				leftColumn.getElement().getStyle().setDisplay(Display.BLOCK);
				rightColumn.getElement().getStyle().setDisplay(Display.NONE);
			}

		}

		// resizePanel(Window.getClientHeight());
		// adjustPanel(Window.getClientWidth());
	}

	@Override
	public void setData(List<ChatDto> data, String chatWebSafeKey) {
		materialCollection.clear();
		for (ChatDto chat : data) {
			materialCollection.add(new ChatListItem(chat, chatWebSafeKey.equals(chat.getWebSafeKey())) {
				@Override
				public void onChatIconClick(ClickEvent event) {
					selectListItem(this);
					getUiHandlers().onSelectChat(chat.getWebSafeKey());
				}
			});
		}
	}

	private void selectListItem(ChatListItem selected) {
		for (Widget child : materialCollection.getChildren()) {
			ChatListItem item = (ChatListItem) child;
			item.setSelected(selected.equals(item));
		}
	}
	/*
	 * private void adjustPanel(Integer width) { if (width < 600) {
	 * leftColumn.setVisible(false); } else { leftColumn.setVisible(true); } }
	 */
}
