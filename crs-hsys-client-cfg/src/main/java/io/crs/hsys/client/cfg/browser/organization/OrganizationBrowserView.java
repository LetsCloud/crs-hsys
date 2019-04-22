/**
 * 
 */
package io.crs.hsys.client.cfg.browser.organization;

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
import io.crs.hsys.client.core.browser.AbstractBrowserView;
import io.crs.hsys.client.core.i18n.CoreMessages;
import io.crs.hsys.shared.dto.profile.OrganizationDtor;

/**
 * @author robi
 *
 */
public class OrganizationBrowserView extends ViewWithUiHandlers<OrganizationBrowserUiHandlers>
		implements OrganizationBrowserPresenter.MyView {
	private static Logger logger = Logger.getLogger(OrganizationBrowserView.class.getName());

	private final AbstractBrowserView<OrganizationDtor> browserView;

	private final CoreMessages i18nCore;

	/**
	* 
	*/
	@Inject
	OrganizationBrowserView(AbstractBrowserView<OrganizationDtor> table, CoreMessages i18nCore) {
		logger.info("OrganizationBrowserView()");

		initWidget(table);

		this.browserView = table;
		this.i18nCore = i18nCore;

		bindSlot(OrganizationBrowserPresenter.SLOT_FILTER, table.getFilterPanel());

		init();
	}

	private void init() {

		browserView.setTableTitle(i18nCore.organizationBrowserTitle());

		browserView.getAddButton().addClickHandler(e -> {
			getUiHandlers().addNew();
		});

		/*
		 * CODE
		 */
		browserView.getTable().addColumn(i18nCore.organizationBrowserColCode(), new TextColumn<OrganizationDtor>() {
			@Override
			public boolean sortable() {
				return true;
			}

			@Override
			public String getValue(OrganizationDtor object) {
				return object.getCode();
			}
		}.sortComparator((o1, o2) -> o1.getData().getCode().compareToIgnoreCase(o2.getData().getCode())));

		/*
		 * NAME
		 */
		browserView.getTable().addColumn(i18nCore.organizationBrowserColName(), new TextColumn<OrganizationDtor>() {
			@Override
			public boolean sortable() {
				return true;
			}

			@Override
			public String getValue(OrganizationDtor object) {
				return object.getName();
			}
		}.sortComparator((o1, o2) -> o1.getData().getName().compareToIgnoreCase(o2.getData().getName())));

		//
		// EDIT ICON
		//
		browserView.getTable().addColumn(new WidgetColumn<OrganizationDtor, MaterialIcon>() {

			@Override
			public MaterialIcon getValue(OrganizationDtor object) {
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
	public void setData(List<OrganizationDtor> data) {
		logger.info("OrganizationBrowserView().setData()");
		if (data == null) {
			logger.info("OrganizationBrowserView().setData()->data==null");
			return;
		}
		browserView.setData(data);
	}
}
