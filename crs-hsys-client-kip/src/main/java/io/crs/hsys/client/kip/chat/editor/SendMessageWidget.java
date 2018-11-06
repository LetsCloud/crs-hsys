/**
 * 
 */
package io.crs.hsys.client.kip.chat.editor;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;

import gwt.material.design.client.ui.MaterialIcon;
import gwt.material.design.client.ui.MaterialTextBox;

/**
 * @author robi
 *
 */
public class SendMessageWidget extends Composite {

	private static SendMessagePanelUiBinder uiBinder = GWT.create(SendMessagePanelUiBinder.class);

	interface SendMessagePanelUiBinder extends UiBinder<Widget, SendMessageWidget> {
	}

	@UiField
	MaterialIcon sendIcon;

	@UiField
	MaterialTextBox textBox;

	/**
	 */
	public SendMessageWidget() {
		initWidget(uiBinder.createAndBindUi(this));
	}

	public String getText() {
		return textBox.getText();
	}

	public void clearText() {
		textBox.clear();
	}

	@UiHandler("sendIcon")
	public void onSendIconClick(ClickEvent event) {}
	
	public void addSendIconClickHandler(ClickHandler handler) {
		sendIcon.addClickHandler(handler);
	}
}
