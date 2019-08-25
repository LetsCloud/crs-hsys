/**
 * 
 */
package io.crs.hsys.client.fro.editor.ratecode;

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

import gwt.material.design.client.data.loader.LoadCallback;
import gwt.material.design.client.data.loader.LoadConfig;
import gwt.material.design.client.data.loader.LoadResult;

import io.crs.hsys.client.core.app.AbstractAppPresenter;
import io.crs.hsys.client.core.datasource.HotelDataSource;
import io.crs.hsys.client.core.datasource.RoomTypeDataSource2;
import io.crs.hsys.client.core.editor.AbstractEditorPresenter;
import io.crs.hsys.client.core.editor.AbstractEditorView;
import io.crs.hsys.client.core.event.SetPageTitleEvent;
import io.crs.hsys.client.core.i18n.CoreMessages;
import io.crs.hsys.client.core.security.CurrentUser;
import io.crs.hsys.client.fro.NameTokens;
import io.crs.hsys.shared.api.RateCodeResource;
import io.crs.hsys.shared.cnst.MenuItemType;
import io.crs.hsys.shared.dto.hotel.HotelDto;
import io.crs.hsys.shared.dto.hotel.RateCodeDto;

import static io.crs.hsys.shared.api.ApiParameters.WEBSAFEKEY;

/**
 * @author robi
 *
 */
public class RateCodeEditorPresenter
		extends AbstractEditorPresenter<RateCodeDto, RateCodeEditorPresenter.MyView, RateCodeEditorPresenter.MyProxy>
		implements RateCodeEditorUiHandlers {
	private static Logger logger = Logger.getLogger(RateCodeEditorPresenter.class.getName());

	public interface MyView extends AbstractEditorView<RateCodeDto>, HasUiHandlers<RateCodeEditorUiHandlers> {
	}

	@ProxyCodeSplit
	@NameToken(NameTokens.RATECODE_EDITOR)
	interface MyProxy extends ProxyPlace<RateCodeEditorPresenter> {
	}

	private final PlaceManager placeManager;
	private final ResourceDelegate<RateCodeResource> resourceDelegate;
//	private final HotelDataSource hotelDataSource;
	private final CurrentUser currentUser;
	private final CoreMessages i18nCore;

	@Inject
	RateCodeEditorPresenter(EventBus eventBus, PlaceManager placeManager, MyView view, MyProxy proxy,
			ResourceDelegate<RateCodeResource> resourceDelegate, RoomTypeDataSource2 roomTypeDataSource,
			HotelDataSource hotelDataSource, CurrentUser currentUser, CoreMessages i18nCore) {
		super(eventBus, placeManager, view, proxy, AbstractAppPresenter.SLOT_MAIN);
		logger.info("RateCodeEditorPresenter()");

		this.placeManager = placeManager;
		this.resourceDelegate = resourceDelegate;
//		this.hotelDataSource = hotelDataSource;
		this.currentUser = currentUser;
		this.i18nCore = i18nCore;

		getView().setUiHandlers(this);
	}

	@Override
	protected void loadData() {
		if (isNew()) {
/*			
			LoadCallback<HotelDto> hotelLoadCallback = new LoadCallback<HotelDto>() {
				@Override
				public void onSuccess(LoadResult<HotelDto> loadResult) {
*/					
					SetPageTitleEvent.fire(i18nCore.roomEditorCreateTitle(), "loadResult.getData().get(0).getName()",
							MenuItemType.MENU_ITEM, RateCodeEditorPresenter.this);
					create();
/*				}

				@Override
				public void onFailure(Throwable caught) {
					// TODO Auto-generated method stub
				}
			};
			hotelDataSource.get(new LoadConfig<HotelDto>(0, 0, null, null), hotelLoadCallback);
	*/
		} else { 
			edit(filters.get(WEBSAFEKEY));
		}
	}

	@Override
	protected RateCodeDto createDto() {
		RateCodeDto dto = new RateCodeDto();
		dto.setHotel(currentUser.getAppUserDto().getDefaultHotel());
		return dto;
	}

	private void edit(String webSafeKey) {
		resourceDelegate.withCallback(new AsyncCallback<RateCodeDto>() {
			@Override
			public void onSuccess(RateCodeDto dto) {
				SetPageTitleEvent.fire(i18nCore.roomEditorModifyTitle(), dto.getHotel().getName(),
						MenuItemType.MENU_ITEM, RateCodeEditorPresenter.this);

				getView().edit(dto);
			}

			@Override
			public void onFailure(Throwable caught) {
			}
		}).get(webSafeKey);
	}

	@Override
	public void save(RateCodeDto dto) {
		resourceDelegate.withCallback(new AsyncCallback<RateCodeDto>() {
			@Override
			public void onSuccess(RateCodeDto dto) {
				placeManager.navigateBack();
			}

			@Override
			public void onFailure(Throwable caught) {
			}
		}).saveOrCreate(dto);
	}
}