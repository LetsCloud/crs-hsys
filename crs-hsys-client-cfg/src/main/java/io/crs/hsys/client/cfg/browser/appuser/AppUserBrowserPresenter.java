/**
 * 
 */
package io.crs.hsys.client.cfg.browser.appuser;

import java.util.List;
import java.util.logging.Logger;

import javax.inject.Inject;

import com.google.web.bindery.event.shared.EventBus;
import com.gwtplatform.dispatch.rest.delegates.client.ResourceDelegate;
import com.gwtplatform.mvp.client.HasUiHandlers;
import com.gwtplatform.mvp.client.PresenterWidget;
import com.gwtplatform.mvp.client.View;
import com.gwtplatform.mvp.client.presenter.slots.SingleSlot;
import com.gwtplatform.mvp.client.proxy.PlaceManager;

import io.crs.hsys.client.cfg.browser.AbstractBrowserPresenter;
import io.crs.hsys.client.core.CoreNameTokens;
import io.crs.hsys.client.core.security.CurrentUser;
import io.crs.hsys.client.core.util.AbstractAsyncCallback;
import io.crs.hsys.client.core.util.ErrorHandlerAsyncCallback;
import io.crs.hsys.shared.api.AppUserResource;
import io.crs.hsys.shared.dto.common.AppUserDto;

/**
 * @author robi
 *
 */
public class AppUserBrowserPresenter extends AbstractBrowserPresenter<AppUserDto, AppUserBrowserPresenter.MyView>
		implements AppUserBrowserUiHandlers {
	private static Logger logger = Logger.getLogger(AppUserBrowserPresenter.class.getName());

	public interface MyView extends View, HasUiHandlers<AppUserBrowserUiHandlers> {
		void setData(List<AppUserDto> data);
	}

	public static final SingleSlot<PresenterWidget<?>> SLOT_EDITOR = new SingleSlot<>();

	private final ResourceDelegate<AppUserResource> resourceDelegate;

	@Inject
	AppUserBrowserPresenter(EventBus eventBus, PlaceManager placeManager, MyView view,
			ResourceDelegate<AppUserResource> resourceDelegate, CurrentUser currentUser) {
		super(eventBus, view, placeManager);
		logger.info("AppUserBrowserPresenter()");

		this.resourceDelegate = resourceDelegate;

		getView().setUiHandlers(this);
	}

	@Override
	protected void loadData() {
		resourceDelegate.withCallback(new AbstractAsyncCallback<List<AppUserDto>>() {
			@Override
			public void onSuccess(List<AppUserDto> result) {
				getView().setData(result);
			}
		}).list();
	}

	@Override
	protected String getCreatorNameToken() {
		return CoreNameTokens.USER_EDITOR;
	}

	@Override
	protected String getEditorNameToken() {
		return CoreNameTokens.USER_EDITOR;
	}

	@Override
	protected void deleteData(String webSafeKey) {
		resourceDelegate.withCallback(new AbstractAsyncCallback<Void>() {
			@Override
			public void onSuccess(Void result) {
				loadData();
			}
		}).delete(webSafeKey);
	}

	@Override
	public void inviteItem(List<AppUserDto> dtos) {
		for (AppUserDto dto : dtos) {
			resourceDelegate.withCallback(new ErrorHandlerAsyncCallback<AppUserDto>(this) {
				@Override
				public void onSuccess(AppUserDto userDto) {
				}

				@Override
				public void onFailure(Throwable caught) {
				}
			}).invite(dto);
		}
	}

	@Override
	public void clearFcmTokens(List<AppUserDto> dtos) {
		for (AppUserDto dto : dtos) {
			dto.getFcmTokens().clear();
			resourceDelegate.withCallback(new ErrorHandlerAsyncCallback<AppUserDto>(this) {
				@Override
				public void onSuccess(AppUserDto userDto) {
				}

				@Override
				public void onFailure(Throwable caught) {
				}
			}).saveOrCreate(dto);
		}
	}
}