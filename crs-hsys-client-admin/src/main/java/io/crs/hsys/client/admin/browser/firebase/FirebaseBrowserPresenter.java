/**
 * 
 */
package io.crs.hsys.client.admin.browser.firebase;

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

import io.crs.hsys.client.admin.meditor.firebase.FirebaseEditorFactory;
import io.crs.hsys.client.admin.meditor.firebase.FirebaseEditorPresenter;
import io.crs.hsys.client.core.security.CurrentUser;
import io.crs.hsys.client.core.ui.browser.AbstractBrowserPresenter;
import io.crs.hsys.client.core.util.AbstractAsyncCallback;
import io.crs.hsys.shared.api.GlobalConfigResource;
import io.crs.hsys.shared.dto.GlobalConfigDto;

/**
 * @author robi
 *
 */
public class FirebaseBrowserPresenter extends AbstractBrowserPresenter<GlobalConfigDto, FirebaseBrowserPresenter.MyView>
		implements FirebaseBrowserUiHandlers {
	private static Logger logger = Logger.getLogger(FirebaseBrowserPresenter.class.getName());

	public interface MyView extends View, HasUiHandlers<FirebaseBrowserUiHandlers> {
		void setData(List<GlobalConfigDto> data);
	}

	public static final SingleSlot<PresenterWidget<?>> SLOT_FILTER = new SingleSlot<>();
	public static final SingleSlot<PresenterWidget<?>> SLOT_EDITOR = new SingleSlot<>();

	private final ResourceDelegate<GlobalConfigResource> resourceDelegate;

	private final FirebaseEditorPresenter editor;

	private final CurrentUser currentUser;

	@Inject
	FirebaseBrowserPresenter(EventBus eventBus, PlaceManager placeManager, MyView view,
			ResourceDelegate<GlobalConfigResource> resourceDelegate, CurrentUser currentUser,
			FirebaseEditorFactory editorFactory) {
		super(eventBus, view, placeManager);

		this.resourceDelegate = resourceDelegate;
		this.editor = editorFactory.createMarketGroupEditor();
		this.currentUser = currentUser;

		getView().setUiHandlers(this);
	}

	@Override
	protected void onBind() {
		super.onBind();
		setInSlot(SLOT_EDITOR, editor);
	}

	@Override
	protected void loadData() {
		logger.info("MarketGroupTablePresenter().loadData()");
		resourceDelegate.withCallback(new AbstractAsyncCallback<List<GlobalConfigDto>>() {
			@Override
			public void onSuccess(List<GlobalConfigDto> result) {
				getView().setData(result);
			}
		}).getAll();
	}

	@Override
	public void addNew() {
		editor.create();
	}

	@Override
	public void edit(GlobalConfigDto dto) {
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
// TODO Auto-generated method stub
		return null;
	}
}