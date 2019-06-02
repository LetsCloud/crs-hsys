/**
 * 
 */
package io.crs.hsys.client.admin.meditor.globalconfig;

import java.util.logging.Logger;

import javax.inject.Inject;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.web.bindery.event.shared.EventBus;
import com.gwtplatform.dispatch.rest.delegates.client.ResourceDelegate;
import com.gwtplatform.mvp.client.HasUiHandlers;
import com.gwtplatform.mvp.client.proxy.PlaceManager;
import com.gwtplatform.mvp.shared.proxy.PlaceRequest;

import io.crs.hsys.client.admin.AdminNameTokens;
import io.crs.hsys.client.admin.config.system.SystemConfigPresenter;
import io.crs.hsys.client.core.config.AbstractConfigPresenter;
import io.crs.hsys.client.core.meditor.AbstractMeditorPresenter;
import io.crs.hsys.client.core.meditor.MeditorView;
import io.crs.hsys.client.core.security.CurrentUser;
import io.crs.hsys.shared.api.GlobalConfigResource;
import io.crs.hsys.shared.dto.EntityPropertyCode;
import io.crs.hsys.shared.dto.GlobalConfigDto;

/**
 * @author robi
 *
 */
public class GlobalConfigEditorPresenter
		extends AbstractMeditorPresenter<GlobalConfigDto, GlobalConfigEditorPresenter.MyView>
		implements GlobalConfigEditorUiHandlers {
	private static Logger logger = Logger.getLogger(GlobalConfigEditorPresenter.class.getName());

	public interface MyView extends MeditorView<GlobalConfigDto>, HasUiHandlers<GlobalConfigEditorUiHandlers> {

		void displayError(EntityPropertyCode code, String message);
	}

	private final PlaceManager placeManager;
	private final ResourceDelegate<GlobalConfigResource> resourceDelegate;
	private final CurrentUser currentUser;

	@Inject
	GlobalConfigEditorPresenter(EventBus eventBus, PlaceManager placeManager, MyView view,
			ResourceDelegate<GlobalConfigResource> resourceDelegate,
			CurrentUser currentUser) {
		super(eventBus, view);
		logger.info("GlobalConfigEditorPresenter()");

		this.placeManager = placeManager;
		this.resourceDelegate = resourceDelegate;
		this.currentUser = currentUser;

		getView().setUiHandlers(this);
	}

	@Override
	protected GlobalConfigDto createDto() {
		GlobalConfigDto dto = new GlobalConfigDto();
		return dto;
	}

	@Override
	public void saveDto(GlobalConfigDto dto) {
		resourceDelegate.withCallback(new AsyncCallback<GlobalConfigDto>() {
			@Override
			public void onSuccess(GlobalConfigDto dto) {
				getView().close();
				PlaceRequest placeRequest = new PlaceRequest.Builder().nameToken(AdminNameTokens.SYSTEM_CONFIG)
						.with(AbstractConfigPresenter.PLACE_PARAM, SystemConfigPresenter.GLOBAL_CONFIGS).build();
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