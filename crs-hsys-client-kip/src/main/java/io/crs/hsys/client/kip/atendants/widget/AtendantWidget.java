/**
 * 
 */
package io.crs.hsys.client.kip.atendants.widget;

import java.util.logging.Level;
import java.util.logging.Logger;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;

import gwt.material.design.addins.client.avatar.MaterialAvatar;
import gwt.material.design.client.ui.MaterialIcon;
import gwt.material.design.client.ui.MaterialLabel;
import io.crs.hsys.client.kip.atendants.AtendantItemEvent;
import io.crs.hsys.client.kip.atendants.AtendantItemEventHandler;
import io.crs.hsys.client.kip.atendants.HasAtendantItemEventHandlers;

/**
 * @author CR
 *
 */
public class AtendantWidget extends Composite implements HasAtendantItemEventHandlers {
	private static final Logger logger = Logger.getLogger(AtendantWidget.class.getName());

	private static AttendantSumUiBinder uiBinder = GWT.create(AttendantSumUiBinder.class);

	interface AttendantSumUiBinder extends UiBinder<Widget, AtendantWidget> {
	}

	@UiField
	MaterialAvatar materialAvatar;

	@UiField
	MaterialLabel attendantLabel, cleanLabel, cleanValue, dirtyLabel, dirtyValue;

	@UiField
	MaterialIcon forwardIcon;

	/**
	 */
	public AtendantWidget() {
		initWidget(uiBinder.createAndBindUi(this));
	}

	public void setAttendantName(String name) {
		attendantLabel.setText(name);
		materialAvatar.setName(name);
		// materialAvatar.initialize();
	}

	public void setDirtyLabel(String text) {
		dirtyLabel.setText(text);
	}

	public void setDirtyValue(Integer value) {
		dirtyValue.setText(value.toString());
	}

	public void setCleanLabel(String text) {
		cleanLabel.setText(text);
	}

	public void setCleanValue(Integer value) {
		cleanValue.setText(value.toString());
	}

	@UiHandler("forwardIcon")
	public void onForwardClick(ClickEvent event) {
		logger.log(Level.INFO, "onForwardClick()");
		fireEvent(new AtendantItemEvent());
	}

	@Override
	public HandlerRegistration addAtendantItemEventHandler(AtendantItemEventHandler handler) {
		return addHandler(handler, AtendantItemEvent.getType());
	}
}
