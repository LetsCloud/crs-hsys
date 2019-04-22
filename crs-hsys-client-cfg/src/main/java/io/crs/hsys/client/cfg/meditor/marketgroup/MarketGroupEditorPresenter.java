/**
 * 
 */
package io.crs.hsys.client.cfg.meditor.marketgroup;

import java.util.logging.Logger;

import javax.inject.Inject;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.web.bindery.event.shared.EventBus;
import com.gwtplatform.dispatch.rest.delegates.client.ResourceDelegate;
import com.gwtplatform.mvp.client.HasUiHandlers;
import com.gwtplatform.mvp.client.proxy.PlaceManager;
import com.gwtplatform.mvp.shared.proxy.PlaceRequest;

import io.crs.hsys.client.core.CoreNameTokens;
import io.crs.hsys.client.core.config.AbstractConfigPresenter;
import io.crs.hsys.client.core.config.hotel.HotelConfigPresenter;
import io.crs.hsys.client.core.meditor.AbstractMeditorPresenter;
import io.crs.hsys.client.core.meditor.MeditorView;
import io.crs.hsys.client.core.security.CurrentUser;
import io.crs.hsys.shared.api.MarketGroupResource;
import io.crs.hsys.shared.dto.EntityPropertyCode;
import io.crs.hsys.shared.dto.hotel.MarketGroupDto;

/**
 * @author robi
 *
 */
public class MarketGroupEditorPresenter
		extends AbstractMeditorPresenter<MarketGroupDto, MarketGroupEditorPresenter.MyView>
		implements MarketGroupEditorUiHandlers {
	private static Logger logger = Logger.getLogger(MarketGroupEditorPresenter.class.getName());

	public interface MyView extends MeditorView<MarketGroupDto>, HasUiHandlers<MarketGroupEditorUiHandlers> {

		void displayError(EntityPropertyCode code, String message);
	}

	private final PlaceManager placeManager;
	private final ResourceDelegate<MarketGroupResource> resourceDelegate;
	private final CurrentUser currentUser;

	@Inject
	MarketGroupEditorPresenter(EventBus eventBus, PlaceManager placeManager, MyView view,
			ResourceDelegate<MarketGroupResource> resourceDelegate, CurrentUser currentUser) {
		super(eventBus, view);
		logger.info("MarketGroupEditorPresenter()");

		this.placeManager = placeManager;
		this.resourceDelegate = resourceDelegate;
		this.currentUser = currentUser;

		getView().setUiHandlers(this);
	}

	@Override
	protected MarketGroupDto createDto() {
		MarketGroupDto dto = new MarketGroupDto();
		dto.setHotel(currentUser.getAppUserDto().getDefaultHotel());
		return dto;
	}

	@Override
	public void saveDto(MarketGroupDto dto) {
		resourceDelegate.withCallback(new AsyncCallback<MarketGroupDto>() {
			@Override
			public void onSuccess(MarketGroupDto dto) {
				getView().close();
				PlaceRequest placeRequest = new PlaceRequest.Builder().nameToken(CoreNameTokens.HOTEL_CONFIG)
						.with(AbstractConfigPresenter.PLACE_PARAM, HotelConfigPresenter.MARKET_GROUPS).build();
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