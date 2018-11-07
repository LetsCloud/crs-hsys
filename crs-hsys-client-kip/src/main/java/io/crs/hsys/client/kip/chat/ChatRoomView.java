/**
 * 
 */
package io.crs.hsys.client.kip.chat;

import java.util.logging.Level;
import java.util.logging.Logger;

import javax.inject.Inject;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.Widget;
import com.gwtplatform.mvp.client.ViewWithUiHandlers;

import gwt.material.design.client.ui.MaterialButton;
import gwt.material.design.client.ui.MaterialToast;

/**
 * @author robi
 *
 */
public class ChatRoomView extends ViewWithUiHandlers<ChatRoomUiHandlers> implements ChatRoomPresenter.MyView {
	private static Logger logger = Logger.getLogger(ChatRoomView.class.getName());

	interface Binder extends UiBinder<Widget, ChatRoomView> {
	}

	@UiField
	MaterialButton btnAdd;

	@UiField
	SimplePanel modalSlot, listPanel;

	@Inject
	ChatRoomView(Binder uiBinder) {
		initWidget(uiBinder.createAndBindUi(this));

		bindSlot(ChatRoomPresenter.LIST_SLOT, listPanel);
		bindSlot(ChatRoomPresenter.CREATOR_SLOT, modalSlot);

		// ViewPort.when(Resolution.MOBILE_SMALL).then(viewPortChange -> {
		// rightColumn.setVisible(false);
		// });

	}

	@Override
	protected void onAttach() {
		super.onAttach();
	}

	@UiHandler("btnAdd")
	void onAdd(ClickEvent e) {
//		getUiHandlers().createNotification();
		getUiHandlers().createChat(btnAdd);
	}

	void enablePushNotification(ValueChangeEvent<Boolean> event) {
		logger.log(Level.INFO, "NotificationsView.enablePushNotification()");
		/*
		 * if (event.getValue()) {
		 * getUiHandlers().getServiceWorkerManager().subscribe(() -> updateSwitch()); }
		 * else { getUiHandlers().getServiceWorkerManager().unsubscribe(() ->
		 * updateSwitch()); }
		 */
		getUiHandlers().getMessagingManager().requestPermission(() -> reqPerm());

		getUiHandlers().getMessagingManager().getToken(token -> showToken(token));
	}

	protected void reqPerm() {
		logger.log(Level.INFO, "NotificationsView.reqPerm()");
		MaterialToast.fireToast("requestPermission");
	}

	protected void showToken(String token) {
		logger.log(Level.INFO, "NotificationsView.showToken().token=" + token);
		if (token != null) {
			// tokenLabel.setText(token);
			getUiHandlers().subToServer(token);
		}
	}
}
