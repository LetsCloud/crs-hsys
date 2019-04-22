/**
 * 
 */
package io.crs.hsys.client.cfg.browser.quotation;

import java.util.List;
import java.util.logging.Logger;

import javax.inject.Inject;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.gwtplatform.mvp.client.ViewWithUiHandlers;

import gwt.material.design.client.constants.Color;
import gwt.material.design.client.constants.IconType;
import gwt.material.design.client.constants.TextAlign;
import gwt.material.design.client.constants.WavesType;
import gwt.material.design.client.ui.MaterialIcon;
import gwt.material.design.client.ui.table.cell.Column;
import gwt.material.design.client.ui.table.cell.TextColumn;
import gwt.material.design.client.ui.table.cell.WidgetColumn;

import io.crs.hsys.client.cfg.browser.organization.OrganizationBrowserPresenter;
import io.crs.hsys.client.core.browser.AbstractBrowserView;
import io.crs.hsys.client.core.browser.AbstractColumnConfig;
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

	private static final int COL_ORGANIZATION = 2;
//	private static final int COL_ISSUEDBY = 3;
	private static final int COL_ISSUEDATE = 4;

	public class ColumnConfig extends AbstractColumnConfig<QuotationDto> {

		public ColumnConfig(Column<QuotationDto, ?> column) {
			super(column);
		}

		public ColumnConfig(String header, Column<QuotationDto, ?> column) {
			super(header, column);
		}
	}

	private final AbstractBrowserView<QuotationDto> browserView;
	private final CurrentUser currentUser;
	private final CoreMessages i18nCore;

	/**
	* 
	*/
	@Inject
	QuotationBrowserView(AbstractBrowserView<QuotationDto> browserView, CurrentUser currentUser,
			CoreMessages i18nCore) {
		logger.info("QuotationBrowserView()");

		initWidget(browserView);

		this.browserView = browserView;
		this.currentUser = currentUser;
		this.i18nCore = i18nCore;
		init();

		bindSlot(OrganizationBrowserPresenter.SLOT_FILTER, browserView.getFilterPanel());
	}

	private void init() {
		browserView.setTableTitle(i18nCore.quotationBrowserTitle());

		browserView.getAddButton().addClickHandler(e -> {
			getUiHandlers().addNew();
		});

		browserView.clearColumnConfigs();
		// 0
		browserView.addColumnConfigs(new ColumnConfig(i18nCore.quotationBrowserCode(), createCodeColumn()));
		// 1
		browserView
				.addColumnConfigs(new ColumnConfig(i18nCore.quotationBrowserDescription(), createDescriptionColumn()));
		// 2 - COL_ORGANIZATION
		browserView.addColumnConfigs(
				new ColumnConfig(i18nCore.quotationBrowserOrganization(), createOrganizationColumn()));
		// 3 - COL_ISSUEDBY
		browserView.addColumnConfigs(new ColumnConfig(i18nCore.quotationBrowserIssuedBy(), createIssuedByColumn()));

		// 4 - COL_ISSUEDATE
		browserView.addColumnConfigs(new ColumnConfig(i18nCore.quotationBrowserIssueDate(), createIssueDateColumn()));

		// 5
		browserView.addColumnConfigs(new ColumnConfig(createEditActionColumn()));

		browserView.addAllColumns();
	}

	@Override
	public void reConfigColumns() {
		browserView.hideColumn(COL_ORGANIZATION, getUiHandlers().getOrganizationKey() != null);
		browserView.hideColumn(COL_ISSUEDATE, Window.getClientWidth() <= 1024);
	}

	private TextColumn<QuotationDto> createCodeColumn() {
		return (TextColumn<QuotationDto>) new TextColumn<QuotationDto>() {
			@Override
			public boolean sortable() {
				return true;
			}

			@Override
			public String getValue(QuotationDto object) {
				return object.getCode();
			}
		}.sortComparator((o1, o2) -> o1.getData().getCode().compareToIgnoreCase(o2.getData().getCode()));
	}

	private TextColumn<QuotationDto> createDescriptionColumn() {
		return new TextColumn<QuotationDto>() {
			@Override
			public boolean sortable() {
				return false;
			}

			@Override
			public String getValue(QuotationDto object) {
				return object.getDescription();
			}
		};
	}

	private TextColumn<QuotationDto> createOrganizationColumn() {
		return (TextColumn<QuotationDto>) new TextColumn<QuotationDto>() {
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
						(o2.getData().getOrganization() == null) ? null : o2.getData().getOrganization().getCode()));
	}

	private TextColumn<QuotationDto> createIssuedByColumn() {
		return (TextColumn<QuotationDto>) new TextColumn<QuotationDto>() {
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
						(o2.getData().getIssuedBy() == null) ? null : o2.getData().getIssuedBy().getCode()));
	}

	private TextColumn<QuotationDto> createIssueDateColumn() {
		return (TextColumn<QuotationDto>) new TextColumn<QuotationDto>() {
			@Override
			public boolean sortable() {
				return true;
			}

			@Override
			public String getValue(QuotationDto object) {
				return DateUtils.formatDateTime(object.getIssueDate(), currentUser.getLocale());
			}
		}.sortComparator((o1, o2) -> o1.getData().getIssueDate().compareTo(o2.getData().getIssueDate()));
	}

	private Column<QuotationDto, ?> createEditActionColumn() {
		return new WidgetColumn<QuotationDto, MaterialIcon>() {

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
		}.textAlign(TextAlign.RIGHT);
	}

	@Override
	public void setData(List<QuotationDto> data) {
		browserView.setData(data);
	}
}
