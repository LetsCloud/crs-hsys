/**
 * 
 */
package io.crs.hsys.client.fro.rate.manager;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Style.TextAlign;
import com.google.gwt.dom.client.Style.TextDecoration;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.i18n.client.NumberFormat;
import com.google.gwt.resources.client.CssResource;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.InlineLabel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;

import gwt.material.design.client.constants.Color;
import gwt.material.design.client.ui.MaterialPanel;
import io.crs.hsys.shared.cnst.RatePriceType;
import io.crs.hsys.shared.dto.rate.query.RateByDateDto;

/**
 * @author robi
 *
 */
public class RateWidget extends Composite {

	private static RateWidgetUiBinder uiBinder = GWT.create(RateWidgetUiBinder.class);

	interface RateWidgetUiBinder extends UiBinder<Widget, RateWidget> {
	}

	interface MyStyle extends CssResource {
		String cta();

		String ctd();
	}

	@UiField
	MyStyle style;

	@UiField
	MaterialPanel wrapPanel;

	@UiField
	FlowPanel ratePanel;

	@UiField
	Label restrictionLabel;

	@UiField
	InlineLabel priceLabel, currencyLabel;

	/**
	 */
	public RateWidget() {
		initWidget(uiBinder.createAndBindUi(this));

	}

	public RateWidget(String price) {
		this();
		priceLabel.setText(price);
	}

	public RateWidget(RateByDateDto price) {
		this();
		String formatted = NumberFormat.getFormat("###,##0").format(price.getRates().get(RatePriceType.DOUBLE));
		priceLabel.setText(formatted);
		currencyLabel.setText(price.getCurrency().getCode());

		wrapPanel.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				wrapPanel.setBorder("2px solid yellow");
//				Window.alert("Date=" + price.getDate());
			}
		});

		switch (price.getRestriction().getType()) {
		case CLOSED:
			wrapPanel.setBackgroundColor(Color.RED_LIGHTEN_4);
			restrictionLabel.setText("CLOSED");
			restrictionLabel.getElement().getStyle().setColor("#b71c1c");
			restrictionLabel.getElement().getStyle().setTextAlign(TextAlign.CENTER);
			ratePanel.getElement().getStyle().setPaddingTop(0, Unit.PX);
			ratePanel.getElement().getStyle().setTextDecoration(TextDecoration.LINE_THROUGH);
			break;
		case CTA:
			wrapPanel.addStyleName(style.cta());
			restrictionLabel.setText("CTA");
			restrictionLabel.getElement().getStyle().setTextAlign(TextAlign.LEFT);
			ratePanel.getElement().getStyle().setPaddingTop(0, Unit.PX);
			break;
		case CTD:
			wrapPanel.addStyleName(style.ctd());
			restrictionLabel.setText("CTD");
			restrictionLabel.getElement().getStyle().setTextAlign(TextAlign.RIGHT);
			ratePanel.getElement().getStyle().setPaddingTop(0, Unit.PX);
			break;
		case MIN_LOS:
			restrictionLabel.setText("MinLOS " + price.getRestriction().getValue());
			restrictionLabel.getElement().getStyle().setTextAlign(TextAlign.CENTER);
			restrictionLabel.getElement().getStyle().setBackgroundColor("#e1bee7");
			restrictionLabel.getElement().getStyle().setColor("#4a148c");
			ratePanel.getElement().getStyle().setPaddingTop(0, Unit.PX);
			break;
		case MAX_LOS:
			restrictionLabel.setText("MaxLOS " + price.getRestriction().getValue());
			restrictionLabel.getElement().getStyle().setTextAlign(TextAlign.CENTER);
			restrictionLabel.getElement().getStyle().setBackgroundColor("#bbdefb");
			ratePanel.getElement().getStyle().setPaddingTop(0, Unit.PX);
			break;
		case MIN_ST:
			restrictionLabel.setText("MinST " + price.getRestriction().getValue());
			restrictionLabel.getElement().getStyle().setTextAlign(TextAlign.CENTER);
			restrictionLabel.getElement().getStyle().setBackgroundColor("#ffe0b2");
			ratePanel.getElement().getStyle().setPaddingTop(0, Unit.PX);
			break;
		default:
			break;
		}
	}

	public void setPrice(String price) {
		priceLabel.setText(price);
	}
}
