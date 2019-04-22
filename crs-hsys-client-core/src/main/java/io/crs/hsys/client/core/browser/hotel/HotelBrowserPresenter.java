/**
 * 
 */
package io.crs.hsys.client.core.browser.hotel;

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
import io.crs.hsys.client.core.message.callback.AbstractAsyncCallback;
import io.crs.hsys.client.core.security.CurrentUser;
import io.crs.hsys.shared.api.HotelResource;
import io.crs.hsys.shared.dto.hotel.HotelDto;

/**
 * @author robi
 *
 */
public class HotelBrowserPresenter extends AbstractBrowserPresenter<HotelDto, HotelBrowserPresenter.MyView>
		implements HotelBrowserUiHandlers {
	private static Logger logger = Logger.getLogger(HotelBrowserPresenter.class.getName());

	public interface MyView extends View, HasUiHandlers<HotelBrowserUiHandlers> {
		void setData(List<HotelDto> data);
	}

	public static final SingleSlot<PresenterWidget<?>> SLOT_EDITOR = new SingleSlot<>();

	private final ResourceDelegate<HotelResource> resourceDelegate;

	@Inject
	HotelBrowserPresenter(EventBus eventBus, PlaceManager placeManager, MyView view,
			ResourceDelegate<HotelResource> resourceDelegate, CurrentUser currentUser) {
		super(eventBus, view, placeManager);
		logger.info("HotelBrowserPresenter()");

		this.resourceDelegate = resourceDelegate;

		getView().setUiHandlers(this);
	}

	@Override
	protected void loadData() {
		resourceDelegate.withCallback(new AbstractAsyncCallback<List<HotelDto>>() {
			@Override
			public void onSuccess(List<HotelDto> result) {
				getView().setData(result);
			}
		}).list();
	}

	@Override
	protected String getCreatorNameToken() {
		return CoreNameTokens.HOTEL_EDITOR;
	}

	@Override
	protected String getEditorNameToken() {
		return CoreNameTokens.HOTEL_EDITOR;
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
	protected TableType getTableType() {
		return TableType.HOTEL;
	}

}