/**
 * 
 */
package io.crs.hsys.client.core.message.dialog;

import java.util.logging.Logger;

import com.google.gwt.core.client.GWT;
import com.google.gwt.core.client.Scheduler;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;

import gwt.material.design.client.ui.MaterialButton;
import gwt.material.design.client.ui.MaterialDialog;
import gwt.material.design.client.ui.MaterialDialogFooter;
import gwt.material.design.client.ui.MaterialTitle;
import io.crs.hsys.client.core.message.MessageButtonConfig;
import io.crs.hsys.client.core.message.MessageData;
import io.crs.hsys.client.core.promise.xgwt.Fn;

/**
 * @author robi
 *
 */
public class MessageDialogWidget extends Composite implements HasShowMessage {
	private static Logger logger = Logger.getLogger(MessageDialogWidget.class.getName());

	private static MessageDialogWidgetUiBinder uiBinder = GWT.create(MessageDialogWidgetUiBinder.class);

	interface MessageDialogWidgetUiBinder extends UiBinder<Widget, MessageDialogWidget> {
	}

	@UiField
	MaterialDialog dialog;

	@UiField
	MaterialTitle title;

	@UiField
	MaterialDialogFooter footer;

	/**
	 * 
	 */
	public MessageDialogWidget() {
		logger.info("MessageDialogWidget()");
		initWidget(uiBinder.createAndBindUi(this));
	}

	private MaterialButton createCloseButton() {
		MaterialButton b = new MaterialButton("Close");
		b.addClickHandler(e -> dialog.close());
		return b;
	}

	private MaterialButton createButton(String label, Fn.Arg<ClickEvent> callback) {
		MaterialButton b = new MaterialButton(label);
		b.addClickHandler(e -> {
			dialog.close();
			Timer timer = new Timer() {
				public void run() {
					callback.call(e);
				}
			};
			timer.schedule(500);
		});
		return b;
	}

	@Override
	public void showMessage(MessageData messageData) {
		title.setTitle(messageData.getTitle());
		title.setDescription(messageData.getDescription());
		footer.clear();
		if (messageData.getBttonConfigs().isEmpty()) {
			footer.add(createCloseButton());
		} else {
			for (MessageButtonConfig buttonConfig : messageData.getBttonConfigs()) {
				footer.add(createButton(buttonConfig.getType().getLabel(), buttonConfig.getCallback()));
			}
		}
		dialog.open();
	}
}
