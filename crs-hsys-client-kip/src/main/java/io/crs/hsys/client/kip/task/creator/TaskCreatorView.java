/**
 * 
 */
package io.crs.hsys.client.kip.task.creator;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import javax.inject.Inject;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.Widget;
import com.gwtplatform.mvp.client.ViewWithUiHandlers;

import gwt.material.design.addins.client.overlay.MaterialOverlay;
import gwt.material.design.client.base.MaterialWidget;
import gwt.material.design.client.ui.MaterialButton;
import gwt.material.design.client.ui.MaterialCollection;

import io.crs.hsys.client.core.i18n.CoreMessages;
import io.crs.hsys.client.kip.chat.creator.ReceiverItem;
import io.crs.hsys.client.kip.chat.editor.SendMessageWidget;
import io.crs.hsys.shared.dto.common.AppUserDto;
import io.crs.hsys.shared.dto.common.UserGroupDto;

/**
 * @author robi
 *
 */
public class TaskCreatorView extends ViewWithUiHandlers<TaskCreatorUiHandlers> implements TaskCreatorPresenter.MyView {
	private static Logger logger = Logger.getLogger(TaskCreatorView.class.getName());

	interface Binder extends UiBinder<Widget, TaskCreatorView> {
	}

	@UiField
	MaterialOverlay overlay;

	@UiField
	SendMessageWidget sendMessageWidget;

	@UiField
	MaterialButton btnCloseOverlay;
	
	@UiField
	MaterialCollection receiverCollection;

	private final CoreMessages i18n;

	/**
	 */
	@Inject
	TaskCreatorView(Binder uiBinder, CoreMessages i18n) {
		logger.info("ChatCreatorView()");
		this.i18n = i18n;
		initWidget(uiBinder.createAndBindUi(this));
		sendMessageWidget.addSendIconClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				saveMessage();
			}
		});
	}

	@Override
	public void open(MaterialWidget widget) {
		overlay.open(widget);
	}

	@Override
	public void clearReceiverList() {
		receiverCollection.clear();
	}

	@Override
	public void addUserGroupList(List<UserGroupDto> userGroups) {
		for (UserGroupDto dto : userGroups) {
			receiverCollection.add(new ReceiverItem(dto));
		}
	}

	@Override
	public void addAppUserList(List<UserGroupDto> appUsers) {
		for (UserGroupDto dto : appUsers) {
			receiverCollection.add(new ReceiverItem(dto));
		}
	}

	private void saveMessage() {
		logger.info("saveMessage()");
		List<AppUserDto> receivers = new ArrayList<AppUserDto>();
		
		for (Widget w : receiverCollection) {
			ReceiverItem ri = (ReceiverItem)w;
			if (ri.isSelected()) {
				for (AppUserDto receiver : ri.getValue().getMembers()) {
					receiver.getUserGroups().clear();
					receivers.add(receiver);
				}
			}
		}
		getUiHandlers().saveChat(receivers, sendMessageWidget.getText());
		sendMessageWidget.clearText();
	}

	@Override
	public void close() {
		overlay.close();
	}

	@UiHandler("btnCloseOverlay")
	public void onCloseOverlay(ClickEvent event) {
		close();
	}
}
