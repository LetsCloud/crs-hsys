/**
 * 
 */
package io.crs.hsys.client.admin.meditor.firebase;

import java.util.logging.Logger;

import javax.inject.Inject;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.web.bindery.event.shared.EventBus;
import com.gwtplatform.dispatch.rest.delegates.client.ResourceDelegate;
import com.gwtplatform.mvp.client.HasUiHandlers;
import com.gwtplatform.mvp.client.proxy.PlaceManager;
import com.gwtplatform.mvp.shared.proxy.PlaceRequest;
import com.gwtplatform.mvp.shared.proxy.PlaceRequest.Builder;

import io.crs.hsys.client.admin.config.system.SystemConfigPresenter;
import io.crs.hsys.client.core.CoreNameTokens;
import io.crs.hsys.client.core.datasource.HotelDataSource;
import io.crs.hsys.client.core.i18n.CoreMessages;
import io.crs.hsys.client.core.security.CurrentUser;
import io.crs.hsys.client.core.ui.config.AbstractConfigPresenter;
import io.crs.hsys.client.core.ui.meditor.AbstractMeditorPresenter;
import io.crs.hsys.client.core.ui.meditor.MeditorView;
import io.crs.hsys.shared.api.GlobalConfigResource;
import io.crs.hsys.shared.api.MarketGroupResource;
import io.crs.hsys.shared.dto.EntityPropertyCode;
import io.crs.hsys.shared.dto.GlobalConfigDto;
import io.crs.hsys.shared.dto.hotel.MarketGroupDto;

/**
 * @author robi
 *
 */
public class FirebaseEditorPresenter
		extends AbstractMeditorPresenter<GlobalConfigDto, FirebaseEditorPresenter.MyView>
		implements FirebaseEditorUiHandlers {
	private static Logger logger = Logger.getLogger(FirebaseEditorPresenter.class.getName());

	public interface MyView extends MeditorView<GlobalConfigDto>, HasUiHandlers<FirebaseEditorUiHandlers> {

		void displayError(EntityPropertyCode code, String message);
	}

	private final PlaceManager placeManager;
	private final ResourceDelegate<GlobalConfigResource> resourceDelegate;
	private final HotelDataSource hotelDataSource;
	private final CurrentUser currentUser;
	private final CoreMessages i18n;

	@Inject
	FirebaseEditorPresenter(EventBus eventBus, PlaceManager placeManager, MyView view,
			ResourceDelegate<GlobalConfigResource> resourceDelegate, HotelDataSource hotelDataSource,
			CurrentUser currentUser, CoreMessages i18n) {
		super(eventBus, view);
		logger.info("MarketGroupEditorPresenter()");

		this.placeManager = placeManager;
		this.resourceDelegate = resourceDelegate;
		this.hotelDataSource = hotelDataSource;
		this.currentUser = currentUser;
		this.i18n = i18n;

		getView().setUiHandlers(this);
	}

	@Override
	protected GlobalConfigDto createDto() {
		GlobalConfigDto dto = new GlobalConfigDto();
		return dto;
	}

	@Override
	public void saveDto(GlobalConfigDto dto) {
		resourceDelegate.withCallback(new AsyncCallback<MarketGroupDto>() {
			@Override
			public void onSuccess(MarketGroupDto dto) {
				getView().close();
				PlaceRequest placeRequest = new PlaceRequest.Builder().nameToken(CoreNameTokens.HOTEL_CONFIG)
						.with(AbstractConfigPresenter.PLACE_PARAM, SystemConfigPresenter.MARKET_GROUPS).build();
				placeManager.revealPlace(placeRequest);
			}

			@Override
			public void onFailure(Throwable caught) {
				getView().displayError(EntityPropertyCode.NONE, caught.getMessage());
			}
		}).saveOrCreate(dto);
	}

	@Override
	public void cancel() {
		// TODO Auto-generated method stub

	}
}