/**
 * 
 */
package io.crs.hsys.client.cfg.browser.profilegroup;

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

import io.crs.hsys.client.cfg.meditor.profilegroup.ProfileGroupEditorFactory;
import io.crs.hsys.client.cfg.meditor.profilegroup.ProfileGroupEditorPresenter;
import io.crs.hsys.client.core.browser.AbstractBrowserPresenter;
import io.crs.hsys.client.core.event.RefreshTableEvent.TableType;
import io.crs.hsys.client.core.filter.FilterPresenterFactory;
import io.crs.hsys.client.core.filter.accountchild.AccountChildFilterPresenter;
import io.crs.hsys.client.core.message.callback.AbstractAsyncCallback;
import io.crs.hsys.shared.api.ProfileGroupResource;
import io.crs.hsys.shared.dto.profile.ProfileGroupDto;

/**
 * @author robi
 *
 */
public class ProfileGroupBrowserPresenter
		extends AbstractBrowserPresenter<ProfileGroupDto, ProfileGroupBrowserPresenter.MyView>
		implements ProfileGroupBrowserUiHandlers {
	private static Logger logger = Logger.getLogger(ProfileGroupBrowserPresenter.class.getName());

	public interface MyView extends View, HasUiHandlers<ProfileGroupBrowserUiHandlers> {
		void setData(List<ProfileGroupDto> data);
	}

	public static final SingleSlot<PresenterWidget<?>> SLOT_FILTER = new SingleSlot<>();
	public static final SingleSlot<PresenterWidget<?>> SLOT_EDITOR = new SingleSlot<>();

	private final ResourceDelegate<ProfileGroupResource> resourceDelegate;

	private final AccountChildFilterPresenter filter;

	private final ProfileGroupEditorPresenter editor;

	@Inject
	ProfileGroupBrowserPresenter(EventBus eventBus, PlaceManager placeManager, MyView view,
			ResourceDelegate<ProfileGroupResource> resourceDelegate, FilterPresenterFactory filterFactory,
			ProfileGroupEditorFactory editorFactory) {
		super(eventBus, view, placeManager);
		logger.info("ProfileGroupBrowserPresenter()");

		this.resourceDelegate = resourceDelegate;
		this.filter = filterFactory.createAccountChildFilter();
		this.editor = editorFactory.createProfileGroupEditor();

		getView().setUiHandlers(this);
	}

	@Override
	protected void onBind() {
		super.onBind();
		logger.info("ProfileGroupBrowserPresenter().onBind()");
		setInSlot(SLOT_FILTER, filter);
		setInSlot(SLOT_EDITOR, editor);
	}

	@Override
	protected void loadData() {
		logger.info("ProfileGroupBrowserPresenter().loadData()");
		resourceDelegate.withCallback(new AbstractAsyncCallback<List<ProfileGroupDto>>() {
			@Override
			public void onSuccess(List<ProfileGroupDto> result) {
				getView().setData(result);
			}
		}).getAll(false);
	}

	@Override
	public void addNew() {
		editor.create();
	}

	@Override
	public void edit(ProfileGroupDto dto) {
		editor.edit(dto);
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
	protected String getCreatorNameToken() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected String getEditorNameToken() {
//TODO Auto-generated method stub
		return null;
	}
	@Override
	protected TableType getTableType() {
		return TableType.PROFILE_GROUP;
	}
}