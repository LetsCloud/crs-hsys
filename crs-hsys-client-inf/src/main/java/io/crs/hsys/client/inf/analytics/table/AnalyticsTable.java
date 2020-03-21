/**
 * 
 */
package io.crs.hsys.client.inf.analytics.table;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.google.gwt.core.client.GWT;
import com.google.gwt.core.client.Scheduler;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;

import gwt.material.design.client.constants.IconType;
import gwt.material.design.client.ui.MaterialIcon;
import gwt.material.design.client.ui.table.MaterialDataTable;
import gwt.material.design.client.ui.table.cell.TextColumn;

import io.crs.hsys.client.inf.analytics.factory.AnalyticTableConfig;
import io.crs.hsys.client.inf.analytics.factory.DimensionConfig;
import io.crs.hsys.client.inf.analytics.factory.MeasureConfig;
import io.crs.hsys.shared.dto.cube.D3m6Dto;

/**
 * @author CR
 *
 */
public class AnalyticsTable extends Composite {
	private static Logger logger = Logger.getLogger(AnalyticsTable.class.getName());

	interface AnalyticsTableUiBinder extends UiBinder<Widget, AnalyticsTable> {
	}
	private static AnalyticsTableUiBinder uiBinder = GWT.create(AnalyticsTableUiBinder.class);

	@UiField
	MaterialDataTable<D3m6Dto> table;

	private MaterialIcon favoriteMI;
	private TextColumn<D3m6Dto> dim2Column;

	private AnalyticTableConfig config;

	/**
	 * 
	 */
	public AnalyticsTable() {

		initWidget(uiBinder.createAndBindUi(this));

		favoriteMI = new MaterialIcon(IconType.FAVORITE);
		favoriteMI.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				table.removeColumn(2);
			}
		});

		Scheduler.get().scheduleDeferred(() -> {
			init();
		});
	}

	@Override
	protected void onLoad() {
		super.onLoad();
		logger.log(Level.INFO, "onLoad()");
	}

	private void setConfig() {
		DimensionConfig dc1 = new DimensionConfig();
		// dc1.setFieldName(CubePerfActDto.D_MARKET);
		dc1.setCaption("Market");

		DimensionConfig dc2 = new DimensionConfig();
		// dc2.setFieldName(CubePerfActDto.D_CHANNEL);
		dc2.setCaption("Channel");

		MeasureConfig mc1 = new MeasureConfig();
		// mc1.setDataSource(DataSources.DS_ACTUAL);
		// mc1.setFieldName(CubePerfActDto.D_MARKET);
		mc1.setCaption("Market");

	}

	public void setData(List<D3m6Dto> data) {
		table.setRowData(0, data);
	}

	private void init() {

//		Panel panel = table.getScaffolding().getToolPanel();
//		panel.clear();
		// Add two buttons
//		panel.add(favoriteMI);
//		panel.add(new MaterialIcon(IconType.DELETE));
//		panel.add(new MaterialIcon(IconType.MESSAGE));

		table.addColumn("Dimension1", new TextColumn<D3m6Dto>() {
			@Override
			public String getValue(D3m6Dto object) {
				return object.getD1();
			}
		});

		dim2Column = new TextColumn<D3m6Dto>() {
			@Override
			public String getValue(D3m6Dto object) {
				return object.getD2();
			}
		};
		
		table.addColumn("Dimension2", dim2Column);

		table.addColumn("Measure1", new TextColumn<D3m6Dto>() {
			@Override
			public String getValue(D3m6Dto object) {
				return object.getM1().toString();
			}
		});

		// table.setVisibleRange(0, 2001);
	}
}
