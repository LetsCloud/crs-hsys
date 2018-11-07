/**
 * 
 */
package io.crs.hsys.client.kip.chat.editor;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.DivElement;
import com.google.gwt.dom.client.Style;
import com.google.gwt.dom.client.Style.TextAlign;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;

/**
 * @author robi
 *
 */
public class PostWidget extends Composite {

	private static ChatItemUiBinder uiBinder = GWT.create(ChatItemUiBinder.class);

	interface ChatItemUiBinder extends UiBinder<Widget, PostWidget> {
	}

	@UiField
	DivElement avatarDiv, contentDiv, bodyDiv;
	
	@UiField
	Image image;
	
	@UiField
	Label messageLabel;

	@UiField
	Label timeLabel;

	/**
	 */
	public PostWidget() {
		initWidget(uiBinder.createAndBindUi(this));
	}

	public PostWidget(String imageUrl, String message, String time, Boolean own) {
		this();
		image.setUrl(imageUrl);
		image.setPixelSize(42, 42);
		messageLabel.setText(message);
		timeLabel.setText(time);
		
		if (own) {
			avatarDiv.getStyle().setFloat(Style.Float.RIGHT);
//			avatarDiv.getStyle().setDisplay(Display.NONE);
			bodyDiv.getStyle().setMarginTop(10, Unit.PX);
			bodyDiv.getStyle().setMarginLeft(0, Unit.PX);
			bodyDiv.getStyle().setMarginRight(0, Unit.PX);
			bodyDiv.getStyle().setMarginBottom(0, Unit.PX);
			
			contentDiv.getStyle().setTextAlign(TextAlign.RIGHT);
			contentDiv.getStyle().setFloat(Style.Float.RIGHT);
			contentDiv.getStyle().setMarginTop(0, Unit.PX);
			contentDiv.getStyle().setMarginLeft(0, Unit.PX);
			contentDiv.getStyle().setMarginRight(0, Unit.PX);
			contentDiv.getStyle().setMarginBottom(10, Unit.PX);
			contentDiv.getStyle().setBackgroundColor("#EDEEF0");
			contentDiv.getStyle().setColor("#404E67");
		} else {
			avatarDiv.getStyle().setFloat(Style.Float.LEFT);
			
			bodyDiv.getStyle().setMarginTop(10, Unit.PX);
			bodyDiv.getStyle().setMarginLeft(30, Unit.PX);
			bodyDiv.getStyle().setMarginRight(0, Unit.PX);
			bodyDiv.getStyle().setMarginBottom(0, Unit.PX);
			
			contentDiv.getStyle().setTextAlign(TextAlign.LEFT);
			contentDiv.getStyle().setFloat(Style.Float.LEFT);
			contentDiv.getStyle().setMarginTop(0, Unit.PX);
			contentDiv.getStyle().setMarginLeft(20, Unit.PX);
			contentDiv.getStyle().setMarginRight(0, Unit.PX);
			contentDiv.getStyle().setMarginBottom(10, Unit.PX);
			contentDiv.getStyle().setBackgroundColor("#00B5B8");
			contentDiv.getStyle().setColor("#FFFFFF");
		}
	}

}
