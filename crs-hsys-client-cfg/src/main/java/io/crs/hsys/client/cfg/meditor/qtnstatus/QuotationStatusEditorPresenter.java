/**
 * 
 */
package io.crs.hsys.client.cfg.meditor.qtnstatus;

import java.util.logging.Logger;

import javax.inject.Inject;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.web.bindery.event.shared.EventBus;
import com.gwtplatform.dispatch.rest.delegates.client.ResourceDelegate;
import com.gwtplatform.mvp.client.HasUiHandlers;
import com.gwtplatform.mvp.client.proxy.PlaceManager;
import com.gwtplatform.mvp.shared.proxy.PlaceRequest;

import io.crs.hsys.client.core.CoreNameTokens;
import io.crs.hsys.client.core.config.hotel.HotelConfigPresenter;
import io.crs.hsys.client.core.meditor.AbstractMeditorPresenter;
import io.crs.hsys.client.core.meditor.MeditorView;
import io.crs.hsys.client.core.ui.config.AbstractConfigPresenter;
import io.crs.hsys.shared.api.QuotationStatusResource;
import io.crs.hsys.shared.dto.EntityPropertyCode;
import io.crs.hsys.shared.dto.doc.QuotationStatusDto;

/**
 * @author robi
 *
 */
public class QuotationStatusEditorPresenter
		extends AbstractMeditorPresenter<QuotationStatusDto, QuotationStatusEditorPresenter.MyView>
		implements QuotationStatusEditorUiHandlers {
	private static Logger logger = Logger.getLogger(QuotationStatusEditorPresenter.class.getName());

	public interface MyView extends MeditorView<QuotationStatusDto>, HasUiHandlers<QuotationStatusEditorUiHandlers> {

		void displayError(EntityPropertyCode code, String message);
	}

	private final PlaceManager placeManager;
	private final ResourceDelegate<QuotationStatusResource> resourceDelegate;

	@Inject
	QuotationStatusEditorPresenter(EventBus eventBus, PlaceManager placeManager, MyView view,
			ResourceDelegate<QuotationStatusResource> resourceDelegate) {
		super(eventBus, view);
		logger.info("QuotationStatusEditorPresenter()");

		this.placeManager = placeManager;
		this.resourceDelegate = resourceDelegate;

		getView().setUiHandlers(this);
	}

	@Override
	protected QuotationStatusDto createDto() {
		QuotationStatusDto dto = new QuotationStatusDto();
		return dto;
	}

	@Override
	public void saveDto(QuotationStatusDto dto) {
		resourceDelegate.withCallback(new AsyncCallback<QuotationStatusDto>() {
			@Override
			public void onSuccess(QuotationStatusDto dto) {
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