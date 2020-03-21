/**
 * 
 */
package io.crs.hsys.client.inf.analytics;

import java.util.List;
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

import gwt.material.design.client.ui.MaterialPanel;
import io.crs.hsys.client.core.event.ContentPushEvent.MenuState;
import io.crs.hsys.client.inf.ana.ChartPanel;
import io.crs.hsys.client.inf.analytics.table.AnalyticsTable;
import io.crs.hsys.shared.dto.cube.D3m6Dto;

/**
 * @author CR
 *
 */
public class AnalyticsView extends ViewWithUiHandlers<AnalyticsUiHandlers> implements AnalyticsPresenter.MyView {
	private static final Logger LOGGER = Logger.getLogger(AnalyticsView.class.getName());

	interface Binder extends UiBinder<Widget, AnalyticsView> {
	}

	private ChartPanel chartPanel;

	private AnalyticsTable analyticsTable;

	@UiField
	MaterialPanel container, chartContainer, tableContainer;

	/**
	 */
	@Inject
	AnalyticsView(Binder uiBinder) {
		LOGGER.log(Level.INFO, "AnalyticsView()");
		initWidget(uiBinder.createAndBindUi(this));

		Window.addResizeHandler(new ResizeHandler() {
			@Override
			public void onResize(ResizeEvent event) {
				LOGGER.log(Level.INFO, "rezizeEvent.getWidth()=" + event.getWidth());
				adjustContainer(event.getWidth());
			}
		});

		analyticsTable = new AnalyticsTable();
		tableContainer.add(analyticsTable);

		chartPanel = new ComboChartPanel();
//		chartPanel.setTitle("Szobaéj teljesítmény (2017.07.01.-31. vs 2016.07.01.-31.)");
		chartContainer.add(chartPanel);
	}

	@Override
	public void displayData(List<D3m6Dto> data) {
		analyticsTable.setData(data);
	}

	@Override
	public void setDataSeries(List<Double> actualSeries) {
		chartPanel.setDataSeries(actualSeries);
		adjustContainer(Window.getClientWidth());
	}

	private void adjustContainer(int clientWidth) {
		if (!this.asWidget().isVisible()) return;

		LOGGER.log(Level.INFO, "adjustContainer");

		if (clientWidth < 480) {
//			container.setWidth("320px");
			chartPanel.setHeight(220);
			chartPanel.setSimpleView(true);
			chartPanel.redrawChart();
			return;
		}

		if (clientWidth < 600) {
//			container.setWidth("480px");
			chartPanel.setHeight(350);
			chartPanel.setSimpleView(true);
			chartPanel.redrawChart();
			return;
		}

		if (clientWidth < 740) {
//			container.setWidth("480px");
			chartPanel.setHeight(350);
			chartPanel.setSimpleView(false);
			chartPanel.redrawChart();
			return;
		}

		if (clientWidth < 1120) {
//			container.setWidth("620px");
			chartPanel.setHeight(405);
			chartPanel.setSimpleView(false);
			chartPanel.redrawChart();
			return;
		}

		if (clientWidth < 2080) {
//			container.setWidth("1000px");
			chartPanel.setHeight(660);
			chartPanel.setSimpleView(false);
			chartPanel.redrawChart();
			return;
		}

		if (clientWidth >= 2080) {
//			container.setWidth("1960px");
			chartPanel.setHeight(1320);
			chartPanel.setSimpleView(false);
			chartPanel.redrawChart();
			return;
		}
	}

	@Override
	public void refresh(MenuState menuState) {
		LOGGER.log(Level.INFO, "refresh()");
		int clientWidth = Window.getClientWidth();
		if (menuState.equals(MenuState.OPEN) && (clientWidth > 992))
			clientWidth -= 280;

		LOGGER.log(Level.INFO, "refresh->clientWidth=" + clientWidth);

		adjustContainer(clientWidth);
	}

	@Override
	public void setDataSeries(List<Double> actualSeries, List<Double> baseSeries) {
		chartPanel.setDataSeries(actualSeries, baseSeries);
		adjustContainer(Window.getClientWidth());
	}
}
