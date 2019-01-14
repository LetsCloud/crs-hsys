/**
 * 
 */
package io.crs.hsys.client.cfg.browser.relationship;

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

import io.crs.hsys.client.cfg.filter.FilterPresenterFactory;
import io.crs.hsys.client.cfg.filter.accountchild.AccountChildFilterPresenter;
import io.crs.hsys.client.cfg.meditor.relationship.RelationshipEditorFactory;
import io.crs.hsys.client.cfg.meditor.relationship.RelationshipEditorPresenter;
import io.crs.hsys.client.core.browser.AbstractBrowserPresenter;
import io.crs.hsys.client.core.util.AbstractAsyncCallback;
import io.crs.hsys.shared.api.RelationshipResource;
import io.crs.hsys.shared.dto.profile.RelationshipDto;

/**
 * @author robi
 *
 */
public class RelationshipBrowserPresenter
		extends AbstractBrowserPresenter<RelationshipDto, RelationshipBrowserPresenter.MyView>
		implements RelationshipBrowserUiHandlers {
	private static Logger logger = Logger.getLogger(RelationshipBrowserPresenter.class.getName());

	public interface MyView extends View, HasUiHandlers<RelationshipBrowserUiHandlers> {
		void setData(List<RelationshipDto> data);
	}

	public static final SingleSlot<PresenterWidget<?>> SLOT_FILTER = new SingleSlot<>();
	public static final SingleSlot<PresenterWidget<?>> SLOT_EDITOR = new SingleSlot<>();

	private final ResourceDelegate<RelationshipResource> resourceDelegate;

	private final AccountChildFilterPresenter filter;

	private final RelationshipEditorPresenter editor;

	@Inject
	RelationshipBrowserPresenter(EventBus eventBus, PlaceManager placeManager, MyView view,
			ResourceDelegate<RelationshipResource> resourceDelegate, FilterPresenterFactory filterFactory,
			RelationshipEditorFactory editorFactory) {
		super(eventBus, view, placeManager);
		logger.info("RelationshipBrowserPresenter()");

		this.resourceDelegate = resourceDelegate;
		this.filter = filterFactory.createAccountChildFilter();
		this.editor = editorFactory.createRelationshipEditor();

		getView().setUiHandlers(this);
	}

	@Override
	protected void onBind() {
		super.onBind();
		setInSlot(SLOT_FILTER, filter);
		setInSlot(SLOT_EDITOR, editor);
	}

	@Override
	protected void loadData() {
		resourceDelegate.withCallback(new AbstractAsyncCallback<List<RelationshipDto>>() {
			@Override
			public void onSuccess(List<RelationshipDto> result) {
				getView().setData(result);
			}
		}).getAll(false);
	}

	@Override
	public void addNew() {
		editor.create();
	}

	@Override
	public void edit(RelationshipDto dto) {
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
}