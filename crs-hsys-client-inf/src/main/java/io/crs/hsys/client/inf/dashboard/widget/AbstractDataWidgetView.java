/**
 * 
 */
package io.crs.hsys.client.inf.dashboard.widget;

import java.util.List;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Widget;
import com.gwtplatform.mvp.client.ViewImpl;

import gwt.material.design.client.ui.MaterialCard;
import gwt.material.design.client.ui.MaterialCardContent;
import gwt.material.design.client.ui.MaterialColumn;
import gwt.material.design.client.ui.MaterialLabel;

import io.crs.hsys.shared.dto.cube.DataWidgetValueM1Dto;
import io.crs.hsys.shared.dto.cube.dw.DataWidgetConfigDto;

/**
 * @author CR
 *
 */
public abstract class AbstractDataWidgetView extends ViewImpl implements DataWidgetPresenter.DataWidgetView {

	interface Binder extends UiBinder<Widget, AbstractDataWidgetView> {
	}

	private static Binder uiBinder = GWT.create(Binder.class);

	protected static final String WIDGET_WIDTHx1 = "l2 m3 s6";
	protected static final String WIDGET_WIDTHx2 = "l4 m6 s12";
	protected static final String CARD_H1X = "card-h1x";
	protected static final String CARD_H2X = "card-h2x";

	@UiField
	MaterialColumn materialColumn;

	@UiField
	MaterialCard materialCard;

	@UiField
	MaterialLabel cardTitle;

	@UiField
	MaterialCardContent cardContent;

	/**
	 */
	public AbstractDataWidgetView() {
		initWidget(uiBinder.createAndBindUi(this));

		init();
	}

	protected void init() {
		setWidgetWidth(WIDGET_WIDTHx1);
		// setWidgetHeight(WIDGET_HEIGHTx1);
	}

	public void setCardTitleText(String text) {
		cardTitle.setText(text);
	}

	public void setRevealTitleText(String text) {
		// revealTitle.setText(text);
	}

	public void addContent(Widget widget) {
		cardContent.add(widget);
	}

	public void setHeight(String height) {
		materialCard.setHeight(height);
	}

	public void setWidth(String width) {
		materialColumn.setWidth(width);
	}

	@Override
	public void setConfig(DataWidgetConfigDto config) {
		cardTitle.setText(config.getCaption());
	}

	@Override
	public void setValues(List<DataWidgetValueM1Dto> values) {
	}

	public void setWidgetWidth(String grid) {
		// materialColumn.setGrid(grid);
	}

	public void setWidgetHeight(String cellHeight) {
		materialCard.addStyleName(cellHeight);
	}

	@Override
	public void removeFromParent() {
		// materialColumn.removeFromParent();
	}

	public void setCardTitleHeight(String height) {
		cardTitle.setHeight(height);
	}
}
