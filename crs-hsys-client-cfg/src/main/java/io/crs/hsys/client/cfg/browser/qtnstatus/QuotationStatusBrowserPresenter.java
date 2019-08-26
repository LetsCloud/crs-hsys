/**
 * 
 */
package io.crs.hsys.client.cfg.browser.qtnstatus;

import java.util.List;
import java.util.logging.Logger;

import javax.inject.Inject;

import com.google.web.bindery.event.shared.EventBus;
import com.gwtplatform.dispatch.rest.delegates.client.ResourceDelegate;
import com.gwtplatform.mvp.client.HasUiHandlers;
import com.gwtplatform.mvp.client.PresenterWidget;
import com.gwtplatform.mvp.client.View;
import com.gwtplatform.mvp.client.presenter.slots.SingleSlot;
import com.gwtplatform.mvp.client.proxy.PlaceManager;

import io.crs.hsys.client.cfg.meditor.qtnstatus.QuotationStatusEditorFactory;
import io.crs.hsys.client.cfg.meditor.qtnstatus.QuotationStatusEditorPresenter;
import io.crs.hsys.client.core.browser.AbstractBrowserPresenter;
import io.crs.hsys.client.core.event.RefreshTableEvent.TableType;
import io.crs.hsys.client.core.filter.hotelchild.HotelChildFilterPresenter;
import io.crs.hsys.client.core.filter.widget.FilterPresenterFactory;
import io.crs.hsys.client.core.message.callback.AbstractAsyncCallback;
import io.crs.hsys.shared.api.QuotationStatusResource;
import io.crs.hsys.shared.dto.doc.QuotationStatusDto;

/**
 * @author robi
 *
 */
public class QuotationStatusBrowserPresenter
		extends AbstractBrowserPresenter<QuotationStatusDto, QuotationStatusBrowserPresenter.MyView>
		implements QuotationStatusBrowserUiHandlers {
	private static Logger logger = Logger.getLogger(QuotationStatusBrowserPresenter.class.getName());

	public interface MyView extends View, HasUiHandlers<QuotationStatusBrowserUiHandlers> {
		void setData(List<QuotationStatusDto> data);
	}

	public static final SingleSlot<PresenterWidget<?>> SLOT_FILTER = new SingleSlot<>();
	public static final SingleSlot<PresenterWidget<?>> SLOT_EDITOR = new SingleSlot<>();

	private final ResourceDelegate<QuotationStatusResource> resourceDelegate;
	private final HotelChildFilterPresenter filter;
	private final QuotationStatusEditorPresenter editor;

	@Inject
	QuotationStatusBrowserPresenter(EventBus eventBus, PlaceManager placeManager, MyView view,
			ResourceDelegate<QuotationStatusResource> resourceDelegate, FilterPresenterFactory filterFactory,
			QuotationStatusEditorFactory editorFactory) {
		super(eventBus, view, placeManager);
		logger.info("QuotationStatusBrowserPresenter()");

		this.resourceDelegate = resourceDelegate;
		this.filter = filterFactory.createHotelChildFilter();
		this.editor = editorFactory.createQuotationStatusEditor();

		getView().setUiHandlers(this);
	}

	@Override
	protected void onBind() {
		super.onBind();
		logger.info("QuotationStatusBrowserPresenter().onBind()");
		setInSlot(SLOT_FILTER, filter);
		setInSlot(SLOT_EDITOR, editor);
	}

	@Override
	protected void loadData() {
		logger.info("QuotationStatusBrowserPresenter().loadData()");
		resourceDelegate.withCallback(new AbstractAsyncCallback<List<QuotationStatusDto>>() {
			@Override
			public void onSuccess(List<QuotationStatusDto> result) {
				logger.info("QuotationStatusBrowserPresenter().loadData().onSuccess()");
				getView().setData(result);
			}
		}).getAll(false);
	}

	@Override
	public void addNew() {
		logger.info("QuotationStatusBrowserPresenter().addNew()");
		editor.create();
	}

	@Override
	public void edit(QuotationStatusDto dto) {
		editor.edit(dto);
	}

	@Override
	protected void deleteData(String webSafeKey) {
		resourceDelegate.withCallback(new AbstractAsyncCallback<Void>() {
			@Override
			public void onSuccess(Void result) {
				loadData();
			}
		}).delete(webSafeKey);
	}

	@Override
	protected String getCreatorNameToken() {
// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected String getEditorNameToken() {
//TODO Auto-generated method stub
		return null;
	}

	@Override
	protected TableType getTableType() {
		return TableType.QUOTATION_STATUS;
	}
}