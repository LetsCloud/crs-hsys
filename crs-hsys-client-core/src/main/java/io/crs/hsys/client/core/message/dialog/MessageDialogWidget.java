/**
 * 
 */
package io.crs.hsys.client.core.message.dialog;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;

import gwt.material.design.client.ui.MaterialDialog;
import gwt.material.design.client.ui.MaterialTitle;
import io.crs.hsys.client.core.message.MessageData;

/**
 * @author robi
 *
 */
public class MessageDialogWidget extends Composite implements HasShowMessage {

	private static MessageDialogWidgetUiBinder uiBinder = GWT.create(MessageDialogWidgetUiBinder.class);

	interface MessageDialogWidgetUiBinder extends UiBinder<Widget, MessageDialogWidget> {
	}

	@UiField
	MaterialDialog dialog;

	@UiField
	MaterialTitle title;

	/**
	 * 
	 */
	public MessageDialogWidget() {
		initWidget(uiBinder.createAndBindUi(this));
	}

	@Override
	public void showMessage(MessageData messageData) {
		title.setTitle(messageData.getTitle());
		title.setDescription(messageData.getDescription());
		dialog.open();
	}

	@UiHandler("closeButton")
	public void onCloseClick(ClickEvent event) {
		dialog.close();
	}
}
