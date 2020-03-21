/**
 * 
 */
package io.crs.hsys.client.inf.dashboard;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.inject.Inject;

import com.google.gwt.event.logical.shared.ResizeEvent;
import com.google.gwt.event.logical.shared.ResizeHandler;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Widget;
import com.gwtplatform.mvp.client.ViewWithUiHandlers;

import gwt.material.design.addins.client.masonry.MaterialMasonry;
import gwt.material.design.client.ui.MaterialPanel;
import io.crs.hsys.client.core.event.ContentPushEvent.MenuState;
import io.crs.hsys.client.inf.dashboard.widget.DataWidget;
import io.crs.hsys.client.inf.dashboard.widget.DataWidgetPresenter.DataWidgetView;

/**
 * @author CR
 *
 */
public class DashboardView extends ViewWithUiHandlers<DashboardUiHandlers> implements DashboardPresenter.MyView {
	private static Logger logger = Logger.getLogger(DashboardPresenter.class.getName());

	private static final String CW_1 = "8.3333333333%";
	private static final String CW_2 = "16.6666666667%";
	private static final String CW_3 = "25%";
	private static final String CW_4 = "33.3333333333%";
	private static final String CW_5 = "41.6666666667%";
	private static final String CW_6 = "50%";
	private static final String CW_7 = "58.3333333333%";
	private static final String CW_8 = "66.6666666667%";
	private static final String CW_9 = "75%";
	private static final String CW_10 = "83.3333333333%";
	private static final String CW_11 = "91.6666666667%";
	private static final String CW_12 = "100%";

	private Boolean visible = false;

	interface Binder extends UiBinder<Widget, DashboardView> {
	}

	private Map<Integer, DataWidget> dataWidgets = new HashMap<Integer, DataWidget>();

	@UiField
	MaterialPanel container;

	@UiField
	MaterialMasonry masonry;

	/*
	 * @UiField ResizeLayoutPanel resizeLayoutPanel;
	 */
	@Inject
	DashboardView(Binder uiBinder) {
		initWidget(uiBinder.createAndBindUi(this));
		logger.log(Level.INFO, "DashboardView");

		Window.addResizeHandler(new ResizeHandler() {
			@Override
			public void onResize(ResizeEvent event) {
				logger.log(Level.INFO, "rezizeEvent.getWidth()=" + event.getWidth());
				adjustMasonry(event.getWidth());
			}
		});
	}

	@Override
	public void setDataWidgets(Map<Integer, DataWidget> widgets) {
		this.dataWidgets = widgets;
		for (Map.Entry<Integer, DataWidget> entry : dataWidgets.entrySet()) {
			addDataWidget(entry.getValue().getView());
		}
		adjustMasonry(Window.getClientWidth());
	}

	@Override
	public void addDataWidget(DataWidgetView widget) {
		masonry.add(widget);
	}

	private void adjustMasonry(int clientWidth) {
		if (!visible)
			return;

		if (clientWidth < 480) {
			container.setWidth("320px");
			adjustCards(CW_6, CW_12, "145px", "300px");
			return;
		}

		if (clientWidth < 600) {
			container.setWidth("480px");
			adjustCards(CW_4, CW_8, "150px", "310px");
			return;
		}

		if (clientWidth < 740) {
			container.setWidth("480px");
			adjustCards(CW_4, CW_8, "150px", "310px");
			return;
		}

		if (clientWidth < 1120) {
			container.setWidth("620px");
			adjustCards(CW_3, CW_6, "150px", "310px");
			return;
		}

		if (clientWidth < 2080) {
			container.setWidth("1000px");
			adjustCards(CW_2, CW_4, "150px", "310px");
			return;
		}

		if (clientWidth >= 2080) {
			container.setWidth("1960px");
			adjustCards(CW_1, CW_2, "150px", "310px");
			return;
		}
	}

	private void adjustCards(String width1, String width2, String height1, String height2) {
		for (Map.Entry<Integer, DataWidget> entry : dataWidgets.entrySet()) {
			entry.getValue().getView().setWidth(width1, width2);
			entry.getValue().getView().setHeight(height1, height2);
		}
		masonry.reload();
	}

	@Override
	public void refresh(MenuState menuState) {
		int clientWidth = Window.getClientWidth();
		if (menuState.equals(MenuState.OPEN) && (clientWidth > 992))
			clientWidth -= 280;

		adjustMasonry(clientWidth);
	}

	@Override
	public void setVisible(Boolean visible) {
		this.visible = visible;
	}

}
