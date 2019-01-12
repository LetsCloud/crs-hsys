/**
 * 
 */
package io.crs.hsys.client.cfg.browser.organization;

import java.util.Comparator;
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
import gwt.material.design.client.data.component.RowComponent;
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

	private final AbstractBrowserView<OrganizationDtor> table;

	private final CoreMessages i18nCore;

	/**
	* 
	*/
	@Inject
	OrganizationBrowserView(AbstractBrowserView<OrganizationDtor> table, CoreMessages i18nCore) {
		logger.info("CustomerBrowserView()");

		initWidget(table);

		this.table = table;
		this.i18nCore = i18nCore;

		bindSlot(OrganizationBrowserPresenter.SLOT_FILTER, table.getFilterPanel());

		init();
	}

	private void init() {

		table.setTableTitle(i18nCore.organizationBrowserTitle());

		table.getAddButton().addClickHandler(e -> {
			getUiHandlers().addNew();
		});

		/*
		 * CODE
		 */
		table.getTable().addColumn(new TextColumn<OrganizationDtor>() {
			@Override
			public boolean isSortable() {
				return true;
			}

			@Override
			public Comparator<? super RowComponent<OrganizationDtor>> sortComparator() {
				return (o1, o2) -> o1.getData().getCode().compareToIgnoreCase(o2.getData().getCode());
			}

			@Override
			public String getValue(OrganizationDtor object) {
				return object.getCode();
			}
		}, i18nCore.organizationBrowserColCode());

		/*
		 * NAME
		 */
		table.getTable().addColumn(new TextColumn<OrganizationDtor>() {
			@Override
			public boolean isSortable() {
				return true;
			}

			@Override
			public Comparator<? super RowComponent<OrganizationDtor>> sortComparator() {
				return (o1, o2) -> o1.getData().getName().compareToIgnoreCase(o2.getData().getName());
			}

			@Override
			public String getValue(OrganizationDtor object) {
				return object.getName();
			}
		}, i18nCore.organizationBrowserColName());

		//
		// EDIT ICON
		//
		table.getTable().addColumn(new WidgetColumn<OrganizationDtor, MaterialIcon>() {
			@Override
			public TextAlign textAlign() {
				return TextAlign.RIGHT;
			}

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
		});
	}

	@Override
	public void setData(List<OrganizationDtor> data) {
		table.setData(data);
	}
}
