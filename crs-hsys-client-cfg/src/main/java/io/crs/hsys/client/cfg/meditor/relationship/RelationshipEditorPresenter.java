/**
 * 
 */
package io.crs.hsys.client.cfg.meditor.relationship;

import java.util.logging.Logger;

import javax.inject.Inject;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.web.bindery.event.shared.EventBus;
import com.gwtplatform.dispatch.rest.delegates.client.ResourceDelegate;
import com.gwtplatform.mvp.client.HasUiHandlers;
import com.gwtplatform.mvp.client.proxy.PlaceManager;
import com.gwtplatform.mvp.shared.proxy.PlaceRequest;

import io.crs.hsys.client.cfg.config.profile.ProfileConfigPresenter;
import io.crs.hsys.client.cfg.meditor.AbstractMeditorPresenter;
import io.crs.hsys.client.cfg.meditor.MeditorView;
import io.crs.hsys.client.core.CoreNameTokens;
import io.crs.hsys.client.core.security.CurrentUser;
import io.crs.hsys.client.core.ui.config.AbstractConfigPresenter;
import io.crs.hsys.shared.api.RelationshipResource;
import io.crs.hsys.shared.dto.EntityPropertyCode;
import io.crs.hsys.shared.dto.profile.RelationshipDto;

/**
 * @author robi
 *
 */
public class RelationshipEditorPresenter
		extends AbstractMeditorPresenter<RelationshipDto, RelationshipEditorPresenter.MyView>
		implements RelationshipEditorUiHandlers {
	private static Logger logger = Logger.getLogger(RelationshipEditorPresenter.class.getName());

	public interface MyView extends MeditorView<RelationshipDto>, HasUiHandlers<RelationshipEditorUiHandlers> {

		void displayError(EntityPropertyCode code, String message);
	}

	private final PlaceManager placeManager;
	private final ResourceDelegate<RelationshipResource> resourceDelegate;
	private final CurrentUser currentUser;

	@Inject
	RelationshipEditorPresenter(EventBus eventBus, PlaceManager placeManager, MyView view,
			ResourceDelegate<RelationshipResource> resourceDelegate, CurrentUser currentUser) {
		super(eventBus, view);
		logger.info("RelationshipEditorPresenter()");

		this.placeManager = placeManager;
		this.resourceDelegate = resourceDelegate;
		this.currentUser = currentUser;

		getView().setUiHandlers(this);
	}

	@Override
	protected RelationshipDto createDto() {
		RelationshipDto dto = new RelationshipDto();
		dto.setAccount(currentUser.getAppUserDto().getAccount());
		return dto;
	}

	@Override
	public void saveDto(RelationshipDto dto) {
		logger.info("RelationshipEditorPresenter().saveDto()");
		resourceDelegate.withCallback(new AsyncCallback<RelationshipDto>() {
			@Override
			public void onSuccess(RelationshipDto dto) {
				logger.info("RelationshipEditorPresenter().saveDto().onSuccess()");
				getView().close();
				PlaceRequest placeRequest = new PlaceRequest.Builder().nameToken(CoreNameTokens.PROFILE_CONFIG)
						.with(AbstractConfigPresenter.PLACE_PARAM, ProfileConfigPresenter.RELATIONSHIPS).build();
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
		getView().close();
	}
}