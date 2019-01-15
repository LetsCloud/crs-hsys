/**
 * 
 */
package io.crs.hsys.client.kip.ui;

import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.user.client.ui.Composite;

import gwt.material.design.client.constants.Color;
import gwt.material.design.client.constants.IconPosition;
import gwt.material.design.client.constants.IconType;
import gwt.material.design.client.constants.TextAlign;
import gwt.material.design.client.ui.MaterialCard;
import gwt.material.design.client.ui.MaterialCardContent;
import gwt.material.design.client.ui.MaterialCardTitle;
import gwt.material.design.client.ui.MaterialLabel;
import gwt.material.design.client.ui.MaterialPanel;
import gwt.material.design.client.ui.MaterialRow;

/**
 * @author robi
 *
 */
public class CardDisplay extends Composite {

	private MaterialCard card;
	private MaterialCardContent content;
	private MaterialCardTitle title;
	private MaterialRow panel;

	public CardDisplay() {
		card = new MaterialCard();
		
		content = new MaterialCardContent();
		content.getElement().getStyle().setPadding(0, Unit.PX);
		content.setTextAlign(TextAlign.CENTER);
		card.add(content);

		title = new MaterialCardTitle();
		title.setTruncate(true);
		title.setIconPosition(IconPosition.LEFT);
		title.getIcon().setMarginTop(10);
		title.getIcon().setMarginLeft(5);
		content.add(title);

		panel = new MaterialRow();
		panel.setPaddingTop(10);
		content.add(panel);
		
		initWidget(card);
	}

	public void setTitleIconType(IconType type) {
		title.setIconType(type);
	}

	public void setTitleIconColor(Color color) {
		title.setIconColor(color);
	}
	
	public void setBackgroundColor(Color color) {
		card.setBackgroundColor(color);
	}

	public void setTextColor(Color color) {
		content.setTextColor(color);
	}

	public void setTitleTextColor(Color color) {
		title.setTextColor(color);
	}

	public void setTitleText(String text) {
		title.setText(text);
	}

	public void setTitleBackgroundColor(Color color) {
		title.setBackgroundColor(color);
	}

	public void setIconType(IconType iconType) {
		title.setIconType(iconType);
	}

	public void setIconPosition(IconPosition iconPosition) {
		title.setIconPosition(iconPosition);
	}

	public void addItem(String text, String value) {

		panel.add(createPanel("s12", text, value));
	}

	public void addItem(String text1, String value1, String text2, String value2) {

		panel.add(createPanel("s6", text1, value1));
		panel.add(createPanel("s6", text2, value2));
	}

	public void addItem(String text1, String value1, String text2, String value2, String text3, String value3) {

		panel.add(createPanel("s4", text1, value1));
		panel.add(createPanel("s4", text2, value2));
		panel.add(createPanel("s4", text3, value3));
	}
	
	private MaterialPanel createPanel(String grid, String text, String value) {
		MaterialPanel panel = new MaterialPanel();
		panel.setTextAlign(TextAlign.CENTER);
		panel.setGrid(grid);

		MaterialLabel textLabel = new MaterialLabel(text);
		textLabel.setFontSize(1, Unit.EM);
		textLabel.setTruncate(true);
		panel.add(textLabel);

		MaterialLabel valueLabel = new MaterialLabel(value);
		valueLabel.setFontSize(2, Unit.EM);
		panel.add(valueLabel);		

		return panel;
	}
}
