/**
 * 
 */
package io.crs.hsys.client.cfg.browser.quotation;

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

import io.crs.hsys.client.core.CoreNameTokens;
import io.crs.hsys.client.core.browser.AbstractBrowserPresenter;
import io.crs.hsys.client.core.event.RefreshTableEvent.TableType;
import io.crs.hsys.client.core.filter.FilterPresenterFactory;
import io.crs.hsys.client.core.filter.profile.ProfileFilterPresenter;
import io.crs.hsys.client.core.message.callback.AbstractAsyncCallback;
import io.crs.hsys.client.core.security.CurrentUser;
import io.crs.hsys.client.core.ui.filter.FilterChangeEvent;
import io.crs.hsys.shared.api.QuotationResource;
import io.crs.hsys.shared.dto.doc.QuotationDto;

/**
 * @author robi
 *
 */
public class QuotationBrowserPresenter
		extends AbstractBrowserPresenter<QuotationDto, QuotationBrowserPresenter.MyView>
		implements QuotationBrowserUiHandlers, FilterChangeEvent.FilterChangeHandler {
	private static Logger logger = Logger.getLogger(QuotationBrowserPresenter.class.getName());

	public interface MyView extends View, HasUiHandlers<QuotationBrowserUiHandlers> {
		void setData(List<QuotationDto> data);
	}

	public static final SingleSlot<PresenterWidget<?>> SLOT_FILTER = new SingleSlot<>();
	public static final SingleSlot<PresenterWidget<?>> SLOT_EDITOR = new SingleSlot<>();

	private final ResourceDelegate<QuotationResource> resourceDelegate;
	private final ProfileFilterPresenter filter;

	@Inject
	QuotationBrowserPresenter(EventBus eventBus, PlaceManager placeManager, MyView view,
			ResourceDelegate<QuotationResource> resourceDelegate, CurrentUser currentUser,
			FilterPresenterFactory filterFactory) {
		super(eventBus, view, placeManager);
		logger.info("QuotationBrowserPresenter()");

		this.resourceDelegate = resourceDelegate;
		this.filter = filterFactory.createProfileFilterPresenter();

		addVisibleHandler(FilterChangeEvent.TYPE, this);

		getView().setUiHandlers(this);
	}

	@Override
	protected void onBind() {
		super.onBind();
		setInSlot(SLOT_FILTER, filter);
	}

	@Override
	protected void loadData() {
		resourceDelegate.withCallback(new AbstractAsyncCallback<List<QuotationDto>>() {
			@Override
			public void onSuccess(List<QuotationDto> result) {
/*				
				if ((filter.getCode() != null) && (!filter.getCode().isEmpty()))
					result = result.stream().filter(org -> org.getCode().contains(filter.getCode()))
							.collect(Collectors.toList());
				if ((filter.getName() != null) && (!filter.getName().isEmpty()))
					result = result.stream().filter(org -> org.getName().contains(filter.getName()))
							.collect(Collectors.toList());
				if (!filter.getProfileGroupKeys().isEmpty())
					result = result.stream()
							.filter(org -> filter.getProfileGroupKeys().contains(org.getProfileGroup().getWebSafeKey()))
							.collect(Collectors.toList());
*/
				getView().setData(result);
			}
		}).getAll(null);
	}

	@Override
	protected String getCreatorNameToken() {
		return CoreNameTokens.QUOTATION_CREATOR;
	}

	@Override
	protected String getEditorNameToken() {
		return CoreNameTokens.QUOTATION_CREATOR;
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
	public void onFilterChange(FilterChangeEvent event) {
		loadData();
	}

	@Override
	protected TableType getTableType() {
		return TableType.QUOTATION;
	}
}