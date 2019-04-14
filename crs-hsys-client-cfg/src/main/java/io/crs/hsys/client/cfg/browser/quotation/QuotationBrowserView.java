/**
 * 
 */
package io.crs.hsys.client.cfg.browser.quotation;

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

import io.crs.hsys.client.cfg.browser.organization.OrganizationBrowserPresenter;
import io.crs.hsys.client.core.browser.AbstractBrowserView;
import io.crs.hsys.client.core.i18n.CoreMessages;
import io.crs.hsys.client.core.security.CurrentUser;
import io.crs.hsys.client.core.util.DateUtils;
import io.crs.hsys.shared.dto.doc.QuotationDto;

/**
 * @author robi
 *
 */
public class QuotationBrowserView extends ViewWithUiHandlers<QuotationBrowserUiHandlers>
		implements QuotationBrowserPresenter.MyView {
	private static Logger logger = Logger.getLogger(QuotationBrowserView.class.getName());

	private final AbstractBrowserView<QuotationDto> table;

	private final CurrentUser currentUser;
	private final CoreMessages i18nCore;

	/**
	* 
	*/
	@Inject
	QuotationBrowserView(AbstractBrowserView<QuotationDto> table, CurrentUser currentUser, CoreMessages i18nCore) {
		logger.info("QuotationBrowserView()");

		initWidget(table);

		this.table = table;
		this.currentUser = currentUser;
		this.i18nCore = i18nCore;

		bindSlot(OrganizationBrowserPresenter.SLOT_FILTER, table.getFilterPanel());

		init();
	}

	private void createCodeColumn(AbstractBrowserView<QuotationDto> table) {
		table.getTable().addColumn(i18nCore.quotationBrowserCode(), new TextColumn<QuotationDto>() {
			@Override
			public boolean sortable() {
				return true;
			}

			@Override
			public String getValue(QuotationDto object) {
				return object.getCode();
			}
		}.sortComparator((o1, o2) -> o1.getData().getCode().compareToIgnoreCase(o2.getData().getCode())));
	}

	private void createDescriptionColumn(AbstractBrowserView<QuotationDto> table) {
		table.getTable().addColumn(i18nCore.quotationBrowserDescription(), new TextColumn<QuotationDto>() {
			@Override
			public boolean sortable() {
				return false;
			}

			@Override
			public String getValue(QuotationDto object) {
				return object.getDescription();
			}
		});
	}

	private void createOrganizationColumn(AbstractBrowserView<QuotationDto> table) {
		table.getTable().addColumn(i18nCore.quotationBrowserOrganization(), new TextColumn<QuotationDto>() {
			@Override
			public boolean sortable() {
				return true;
			}

			@Override
			public String getValue(QuotationDto object) {
				if (object.getOrganization() == null)
					return null;
				return object.getOrganization().getCode();
			}
		}.sortComparator((o1, o2) -> (o1.getData().getOrganization() == null) ? null
				: o1.getData().getOrganization().getCode().compareToIgnoreCase(
						(o2.getData().getOrganization() == null) ? null : o2.getData().getOrganization().getCode())));
	}

	private void createIssuedByColumn(AbstractBrowserView<QuotationDto> table) {
		table.getTable().addColumn(i18nCore.quotationBrowserIssuedBy(), new TextColumn<QuotationDto>() {
			@Override
			public boolean sortable() {
				return true;
			}

			@Override
			public String getValue(QuotationDto object) {
				if (object.getIssuedBy() == null)
					return null;
				return object.getIssuedBy().getCode();
			}
		}.sortComparator((o1, o2) -> (o1.getData().getIssuedBy() == null) ? null
				: o1.getData().getIssuedBy().getCode().compareToIgnoreCase(
						(o2.getData().getIssuedBy() == null) ? null : o2.getData().getIssuedBy().getCode())));
	}

	private void createIssueDateColumn(AbstractBrowserView<QuotationDto> table) {
		table.getTable().addColumn(i18nCore.quotationBrowserIssueDate(), new TextColumn<QuotationDto>() {
			@Override
			public boolean sortable() {
				return true;
			}

			@Override
			public String getValue(QuotationDto object) {
				return DateUtils.formatDateTime(object.getIssueDate(), currentUser.getLocale());
			}
		}.sortComparator((o1, o2) -> o1.getData().getIssueDate().compareTo(o2.getData().getIssueDate())));
	}

	private void createEditActionColumn(AbstractBrowserView<QuotationDto> table) {
		table.getTable().addColumn(new WidgetColumn<QuotationDto, MaterialIcon>() {

			@Override
			public MaterialIcon getValue(QuotationDto object) {
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

	private void init() {

		table.setTableTitle(i18nCore.quotationBrowserTitle());

		table.getAddButton().addClickHandler(e -> {
			getUiHandlers().addNew();
		});

		createCodeColumn(table);
		createDescriptionColumn(table);
		createOrganizationColumn(table);
		createIssuedByColumn(table);
		createIssueDateColumn(table);
		createEditActionColumn(table);
	}

	@Override
	public void setData(List<QuotationDto> data) {
		table.setData(data);
	}
}