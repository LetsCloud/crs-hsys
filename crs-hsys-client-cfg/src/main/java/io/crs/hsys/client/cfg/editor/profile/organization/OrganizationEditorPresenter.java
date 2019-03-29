/**
 * 
 */
package io.crs.hsys.client.cfg.editor.profile.organization;

import java.util.List;
import java.util.logging.Logger;

import javax.inject.Inject;

import com.google.common.base.Strings;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.web.bindery.event.shared.EventBus;
import com.gwtplatform.dispatch.rest.delegates.client.ResourceDelegate;
import com.gwtplatform.mvp.client.HasUiHandlers;
import com.gwtplatform.mvp.client.proxy.PlaceManager;
import com.gwtplatform.mvp.shared.proxy.PlaceRequest.Builder;

import gwt.material.design.client.data.loader.LoadCallback;
import gwt.material.design.client.data.loader.LoadConfig;
import gwt.material.design.client.data.loader.LoadResult;

import io.crs.hsys.client.cfg.CfgNameTokens;
import io.crs.hsys.client.core.datasource.ProfileGroupDataSource;
import io.crs.hsys.client.core.editor.AbstractEditorPresenterWidget;
import io.crs.hsys.client.core.editor.AbstractEditorView;
import io.crs.hsys.client.core.event.SetPageTitleEvent;
import io.crs.hsys.client.core.security.CurrentUser;
import io.crs.hsys.shared.api.ApiParameters;
import io.crs.hsys.shared.api.OrganizationResource;
import io.crs.hsys.shared.cnst.CommMode;
import io.crs.hsys.shared.cnst.MenuItemType;
import io.crs.hsys.shared.dto.EntityPropertyCode;
import io.crs.hsys.shared.dto.profile.AddressDto;
import io.crs.hsys.shared.dto.profile.CommunicationDto;
import io.crs.hsys.shared.dto.profile.OrganizationDto;
import io.crs.hsys.shared.dto.profile.ProfileGroupDto;

/**
 * @author robi
 *
 */
public final class OrganizationEditorPresenter
		extends AbstractEditorPresenterWidget<OrganizationDto, OrganizationEditorPresenter.MyView>
		implements OrganizationEditorUiHandlers {
	private static Logger logger = Logger.getLogger(OrganizationEditorPresenter.class.getName());

	interface MyView extends AbstractEditorView<OrganizationDto>, HasUiHandlers<OrganizationEditorUiHandlers> {
		void setProfileGroupData(List<ProfileGroupDto> profileGroupData);

		void displayError(EntityPropertyCode code, String message);

		void setReadOnly(Boolean readOnly, Boolean createMode);
	}

	private final PlaceManager placeManager;
	private final ResourceDelegate<OrganizationResource> resourceDelegate;
	private final ProfileGroupDataSource profileGroupDataSource;
	private final CurrentUser currentUser;

	@Inject
	OrganizationEditorPresenter(EventBus eventBus, PlaceManager placeManager, MyView view,
			ResourceDelegate<OrganizationResource> resourceDelegate, ProfileGroupDataSource profileGroupDataSource,
			CurrentUser currentUser) {
		super(eventBus, placeManager, view);
		logger.info("OrganizationEditorPresenter()");

		this.placeManager = placeManager;
		this.resourceDelegate = resourceDelegate;
		this.profileGroupDataSource = profileGroupDataSource;
		this.currentUser = currentUser;

		getView().setUiHandlers(this);
	}

	@Override
	protected void loadData() {
		profileGroupDataSource.setOnlyActive(true);

		LoadCallback<ProfileGroupDto> profileGroupLoadCallback = new LoadCallback<ProfileGroupDto>() {
			@Override
			public void onSuccess(LoadResult<ProfileGroupDto> loadResult) {
				getView().setProfileGroupData(loadResult.getData());
				if (Strings.isNullOrEmpty(getWebSafeKey())) {
					create();
				} else {
					showOrEdit(getWebSafeKey());
				}
			}

			@Override
			public void onFailure(Throwable caught) {
				// TODO Auto-generated method stub
			}
		};
		profileGroupDataSource.load(new LoadConfig<ProfileGroupDto>(0, 0, null, null), profileGroupLoadCallback);
	}

	@Override
	protected OrganizationDto createDto() {
		logger.info("createDto()->currentUser=" + currentUser);
		OrganizationDto dto = new OrganizationDto();
		dto.setAccount(currentUser.getAppUserDto().getAccount());
		dto.getCommunications().add(new CommunicationDto(true, CommMode.MOBILE));
		dto.getAddresses().add(new AddressDto());
		return dto;
	}

	private void showOrEdit(String webSafeKey) {
		resourceDelegate.withCallback(new AsyncCallback<OrganizationDto>() {
			@Override
			public void onSuccess(OrganizationDto dto) {
				SetPageTitleEvent.fire(dto.getCode(), dto.getName(), MenuItemType.MENU_ITEM,
						OrganizationEditorPresenter.this);

				if (getReadOnly()) {
					getView().show(dto);
				} else {
					getView().edit(dto);
				}
			}

			@Override
			public void onFailure(Throwable caught) {
				getView().displayError(EntityPropertyCode.NONE, caught.getMessage());
			}
		}).read(webSafeKey);
	}

	@Override
	public void save(OrganizationDto dto) {
		resourceDelegate.withCallback(new AsyncCallback<OrganizationDto>() {
			@Override
			public void onSuccess(OrganizationDto dto) {
//				loadData();
//				setReadOnly(true);

				// placeManager.navigateBack();
				
//				eventBus.fireEvent(new CommunicationActionEvent(CommunicationActionEvent.Action.CLOSE, -1));
				
				Builder placeBuilder = new Builder().nameToken(CfgNameTokens.ORGANIZATION_DISPLAY);
				placeBuilder.with(ApiParameters.WEBSAFEKEY, String.valueOf(dto.getWebSafeKey()));
				placeManager.revealPlace(placeBuilder.build());
				
//				PlaceRequest placeRequest = new PlaceRequest.Builder().nameToken(CoreNameTokens.PROFILE_CONFIG)
//				.with(AbstractConfigPresenter.PLACE_PARAM, ProfileConfigPresenter.ORGANIZATIONS).build();
//		placeManager.revealPlace(placeRequest);

			}

			@Override
			public void onFailure(Throwable caught) {
				getView().displayError(EntityPropertyCode.NONE, caught.getMessage());
			}
		}).saveOrCreate(dto);
	}

	public void setReadOnly(Boolean readOnly) {
		getView().setReadOnly(readOnly, true);
	}
}