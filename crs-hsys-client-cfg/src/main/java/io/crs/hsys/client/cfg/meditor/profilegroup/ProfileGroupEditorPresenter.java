/**
 * 
 */
package io.crs.hsys.client.cfg.meditor.profilegroup;

import java.util.logging.Logger;

import javax.inject.Inject;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.web.bindery.event.shared.EventBus;
import com.gwtplatform.dispatch.rest.delegates.client.ResourceDelegate;
import com.gwtplatform.mvp.client.HasUiHandlers;
import com.gwtplatform.mvp.client.proxy.PlaceManager;
import com.gwtplatform.mvp.shared.proxy.PlaceRequest;

import io.crs.hsys.client.cfg.config.profile.ProfileConfigPresenter;
import io.crs.hsys.client.core.CoreNameTokens;
import io.crs.hsys.client.core.meditor.AbstractMeditorPresenter;
import io.crs.hsys.client.core.meditor.MeditorView;
import io.crs.hsys.client.core.security.CurrentUser;
import io.crs.hsys.client.core.ui.config.AbstractConfigPresenter;
import io.crs.hsys.shared.api.ProfileGroupResource;
import io.crs.hsys.shared.dto.EntityPropertyCode;
import io.crs.hsys.shared.dto.common.AccountDtor;
import io.crs.hsys.shared.dto.profile.ProfileGroupDto;

/**
 * @author robi
 *
 */
public class ProfileGroupEditorPresenter
		extends AbstractMeditorPresenter<ProfileGroupDto, ProfileGroupEditorPresenter.MyView>
		implements ProfileGroupEditorUiHandlers {
	private static Logger logger = Logger.getLogger(ProfileGroupEditorPresenter.class.getName());

	public interface MyView extends MeditorView<ProfileGroupDto>, HasUiHandlers<ProfileGroupEditorUiHandlers> {

		void displayError(EntityPropertyCode code, String message);
	}

	private final PlaceManager placeManager;
	private final ResourceDelegate<ProfileGroupResource> resourceDelegate;
	private final CurrentUser currentUser;

	@Inject
	ProfileGroupEditorPresenter(EventBus eventBus, PlaceManager placeManager, MyView view,
			ResourceDelegate<ProfileGroupResource> resourceDelegate, CurrentUser currentUser) {
		super(eventBus, view);
		logger.info("ProfileGroupEditorPresenter()");

		this.placeManager = placeManager;
		this.resourceDelegate = resourceDelegate;
		this.currentUser = currentUser;

		getView().setUiHandlers(this);
	}

	@Override
	protected ProfileGroupDto createDto() {
		logger.info("ProfileGroupEditorPresenter().createDto");
		ProfileGroupDto dto = new ProfileGroupDto();
		dto.setAccount(new AccountDtor());
		dto.getAccount().setId(currentUser.getAppUserDto().getAccount().getId());
		dto.setActive(true);
		return dto;
	}

	@Override
	public void saveDto(ProfileGroupDto dto) {
		resourceDelegate.withCallback(new AsyncCallback<ProfileGroupDto>() {
			@Override
			public void onSuccess(ProfileGroupDto dto) {
				getView().close();
				PlaceRequest placeRequest = new PlaceRequest.Builder().nameToken(CoreNameTokens.PROFILE_CONFIG)
						.with(AbstractConfigPresenter.PLACE_PARAM, ProfileConfigPresenter.PROFILE_GROUPS).build();
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