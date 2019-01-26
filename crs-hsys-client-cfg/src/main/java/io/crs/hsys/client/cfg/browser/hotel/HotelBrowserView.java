/**
 * 
 */
package io.crs.hsys.client.cfg.browser.hotel;

import java.util.List;
import java.util.logging.Logger;

import javax.inject.Inject;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.gwtplatform.mvp.client.ViewWithUiHandlers;

import gwt.material.design.client.constants.Color;
import gwt.material.design.client.constants.IconType;
import gwt.material.design.client.constants.TextAlign;
import gwt.material.design.client.constants.WavesType;
import gwt.material.design.client.ui.MaterialIcon;
import gwt.material.design.client.ui.table.cell.TextColumn;
import gwt.material.design.client.ui.table.cell.WidgetColumn;

import io.crs.hsys.client.core.i18n.CoreMessages;
import io.crs.hsys.client.core.ui.browser.AbstractBrowserView;
import io.crs.hsys.shared.dto.hotel.HotelDto;

/**
 * @author robi
 *
 */
public class HotelBrowserView extends ViewWithUiHandlers<HotelBrowserUiHandlers>
		implements HotelBrowserPresenter.MyView {
	private static Logger logger = Logger.getLogger(HotelBrowserView.class.getName());

	private final AbstractBrowserView<HotelDto> table;

	private final CoreMessages i18nCore;

	/**
	* 
	*/
	@Inject
	HotelBrowserView(AbstractBrowserView<HotelDto> table, CoreMessages i18nCore) {
		logger.info("HotelBrowserView()");

		initWidget(table);

		this.table = table;
		this.i18nCore = i18nCore;

		init();
	}

	private void init() {

		table.setTableTitle(i18nCore.hotelBrowserTitle());

		table.getAddButton().addClickHandler(e -> {
			getUiHandlers().addNew();
		});

		/*
		 * CODE
		 */
		table.getTable().addColumn(i18nCore.hotelsTableCode(), new TextColumn<HotelDto>() {
			@Override
			public boolean sortable() {
				return true;
			}

			@Override
			public String getValue(HotelDto object) {
				return object.getCode();
			}
		}.sortComparator((o1, o2) -> o1.getData().getCode().compareToIgnoreCase(o2.getData().getCode())));

		/*
		 * NAME
		 */
		table.getTable().addColumn(i18nCore.hotelsTableName(), new TextColumn<HotelDto>() {
			@Override
			public boolean sortable() {
				return true;
			}

			@Override
			public String getValue(HotelDto object) {
				return object.getName();
			}
		}.sortComparator((o1, o2) -> o1.getData().getName().compareToIgnoreCase(o2.getData().getName())));

		//
		// EDIT ICON
		//
		table.getTable().addColumn(new WidgetColumn<HotelDto, MaterialIcon>() {

			@Override
			public MaterialIcon getValue(HotelDto object) {
				MaterialIcon icon = new MaterialIcon();
				icon.addClickHandler(new ClickHandler() {
					@Override
					public void onClick(ClickEvent event) {
						getUiHandlers().edit(object);
					}
				});

				icon.setWaves(WavesType.DEFAULT);
				icon.setIconType(IconType.EDIT);
				icon.setBackgroundColor(Color.AMBER);
				icon.setCircle(true);
				icon.setTextColor(Color.WHITE);
				return icon;
			}
		}.textAlign(TextAlign.RIGHT));
	}

	@Override
	public void setData(List<HotelDto> data) {
		logger.info("RoomTypeTableView().setData()");
		table.setData(data);
	}
}
