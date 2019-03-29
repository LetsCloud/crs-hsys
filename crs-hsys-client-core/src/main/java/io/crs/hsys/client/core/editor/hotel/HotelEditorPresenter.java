/**
 * 
 */
package io.crs.hsys.client.core.editor.hotel;

import java.util.logging.Logger;

import javax.inject.Inject;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.web.bindery.event.shared.EventBus;
import com.gwtplatform.dispatch.rest.delegates.client.ResourceDelegate;
import com.gwtplatform.mvp.client.HasUiHandlers;
import com.gwtplatform.mvp.client.annotations.NameToken;
import com.gwtplatform.mvp.client.annotations.ProxyCodeSplit;
import com.gwtplatform.mvp.client.proxy.PlaceManager;
import com.gwtplatform.mvp.client.proxy.ProxyPlace;

import io.crs.hsys.client.core.CoreNameTokens;
import io.crs.hsys.client.core.app.AbstractAppPresenter;
import io.crs.hsys.client.core.editor.AbstractEditorPresenter;
import io.crs.hsys.client.core.editor.AbstractEditorView;
import io.crs.hsys.client.core.event.SetPageTitleEvent;
import io.crs.hsys.client.core.i18n.CoreMessages;
import io.crs.hsys.client.core.security.CurrentUser;
import io.crs.hsys.client.core.util.ErrorHandlerAsyncCallback;
import io.crs.hsys.shared.api.HotelResource;
import io.crs.hsys.shared.cnst.MenuItemType;
import io.crs.hsys.shared.dto.EntityPropertyCode;
import io.crs.hsys.shared.dto.hotel.HotelDto;

import static io.crs.hsys.shared.api.ApiParameters.WEBSAFEKEY;

/**
 * @author robi
 *
 */
public class HotelEditorPresenter
		extends AbstractEditorPresenter<HotelDto, HotelEditorPresenter.MyView, HotelEditorPresenter.MyProxy>
		implements HotelEditorUiHandlers {
	private static Logger logger = Logger.getLogger(HotelEditorPresenter.class.getName());

	public interface MyView extends AbstractEditorView<HotelDto>, HasUiHandlers<HotelEditorUiHandlers> {

		void displayError(EntityPropertyCode code, String message);
	}

	@ProxyCodeSplit
	@NameToken(CoreNameTokens.HOTEL_EDITOR)
	interface MyProxy extends ProxyPlace<HotelEditorPresenter> {
	}

	private final PlaceManager placeManager;
	private final ResourceDelegate<HotelResource> resourceDelegate;
	private final CurrentUser currentUser;
	private final CoreMessages i18n;

	@Inject
	HotelEditorPresenter(EventBus eventBus, PlaceManager placeManager, MyView view, MyProxy proxy,
			ResourceDelegate<HotelResource> resourceDelegate, CurrentUser currentUser, CoreMessages i18n) {
		super(eventBus, placeManager, view, proxy, AbstractAppPresenter.SLOT_MAIN);
		logger.info("HotelEditorPresenter()");

		this.placeManager = placeManager;
		this.resourceDelegate = resourceDelegate;
		this.currentUser = currentUser;
		this.i18n = i18n;

		getView().setUiHandlers(this);
	}

	@Override
	protected void loadData() {
		if (isNew()) {
			SetPageTitleEvent.fire(i18n.hotelEditorCreateTitle(), "", MenuItemType.MENU_ITEM, HotelEditorPresenter.this);
			create();
		} else {
			SetPageTitleEvent.fire(i18n.hotelEditorModifyTitle(), "", MenuItemType.MENU_ITEM, HotelEditorPresenter.this);
			edit(filters.get(WEBSAFEKEY));
		}
	}

	@Override
	protected HotelDto createDto() {
		HotelDto dto = new HotelDto();
		dto.setAccount(currentUser.getAppUserDto().getAccount());
		return dto;
	}

	private void edit(String webSafeKey) {
		resourceDelegate.withCallback(new AsyncCallback<HotelDto>() {
			@Override
			public void onSuccess(HotelDto dto) {
				logger.info("AppUserEditorPresenter().edit().onSuccess()->dto=" + dto);
				getView().edit(dto);
			}

			@Override
			public void onFailure(Throwable caught) {
				getView().displayError(EntityPropertyCode.NONE, caught.getMessage());
			}
		}).read(webSafeKey);
	}

	@Override
	public void save(HotelDto userDto) {
		resourceDelegate.withCallback(new ErrorHandlerAsyncCallback<HotelDto>(this) {
			@Override
			public void onSuccess(HotelDto userDto) {
				placeManager.navigateBack();
			}

			@Override
			public void onFailure(Throwable caught) {
				getView().displayError(EntityPropertyCode.NONE, caught.getMessage());
			}
		}).saveOrCreate(userDto);
	}
}