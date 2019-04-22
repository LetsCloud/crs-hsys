/**
 * 
 */
package io.crs.hsys.client.cfg.meditor.qtnstatus;

import java.util.logging.Logger;

import javax.inject.Inject;

import com.google.web.bindery.event.shared.EventBus;
import com.gwtplatform.dispatch.rest.delegates.client.ResourceDelegate;
import com.gwtplatform.mvp.client.HasUiHandlers;
import com.gwtplatform.mvp.client.proxy.PlaceManager;
import com.gwtplatform.mvp.shared.proxy.PlaceRequest;

import io.crs.hsys.client.cfg.config.doc.DocConfigPresenter;
import io.crs.hsys.client.core.CoreNameTokens;
import io.crs.hsys.client.core.config.AbstractConfigPresenter;
import io.crs.hsys.client.core.event.DisplayMessageEvent;
import io.crs.hsys.client.core.i18n.CoreMessages;
import io.crs.hsys.client.core.meditor.AbstractMeditorPresenter;
import io.crs.hsys.client.core.meditor.MeditorView;
import io.crs.hsys.client.core.message.callback.ErrorHandlerAsyncCallback;
import io.crs.hsys.client.core.security.CurrentUser;
import io.crs.hsys.shared.api.QuotationStatusResource;
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
	}

	private final PlaceManager placeManager;
	private final ResourceDelegate<QuotationStatusResource> resourceDelegate;
	private final CurrentUser currentUser;
	private final CoreMessages i18nCore;

	@Inject
	QuotationStatusEditorPresenter(EventBus eventBus, PlaceManager placeManager, MyView view,
			ResourceDelegate<QuotationStatusResource> resourceDelegate, CurrentUser currentUser,
			CoreMessages i18nCore) {
		super(eventBus, view);
		logger.info("QuotationStatusEditorPresenter()");

		this.placeManager = placeManager;
		this.resourceDelegate = resourceDelegate;
		this.currentUser = currentUser;
		this.i18nCore = i18nCore;

		getView().setUiHandlers(this);
	}

	@Override
	protected void onBind() {
		super.onBind();
		addRegisteredHandler(DisplayMessageEvent.TYPE, this);
	}

	@Override
	protected QuotationStatusDto createDto() {
		QuotationStatusDto dto = new QuotationStatusDto();
		dto.setAccount(currentUser.getAppUserDto().getAccount());
		dto.setActive(true);
		return dto;
	}

	@Override
	public void saveDto(QuotationStatusDto dto) {
		resourceDelegate.withCallback(new ErrorHandlerAsyncCallback<QuotationStatusDto>(this, i18nCore) {
			@Override
			public void onSuccess(QuotationStatusDto dto) {
				getView().close();
				PlaceRequest placeRequest = new PlaceRequest.Builder().nameToken(CoreNameTokens.DOC_CONFIG)
						.with(AbstractConfigPresenter.PLACE_PARAM, DocConfigPresenter.QUOTATION_STATUS).build();
				placeManager.revealPlace(placeRequest);
			}
		}).saveOrCreate(dto);
	}
}