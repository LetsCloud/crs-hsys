/**
 * 
 */
package io.crs.hsys.client.kip.gfilter.config;

import java.util.logging.Level;
import java.util.logging.Logger;

import javax.inject.Inject;

import com.google.web.bindery.event.shared.EventBus;
import com.gwtplatform.mvp.client.HasUiHandlers;
import com.gwtplatform.mvp.client.PresenterWidget;
import com.gwtplatform.mvp.client.View;

/**
 * @author CR
 *
 */
public class GfilterConfigPresenter extends PresenterWidget<GfilterConfigPresenter.MyView>
		implements GfilterConfigUiHandlers {
	private static Logger logger = Logger.getLogger(GfilterConfigPresenter.class.getName());

	interface MyView extends View, HasUiHandlers<GfilterConfigUiHandlers> {
		void initView();

		void openModal();
	}

	@Inject
	GfilterConfigPresenter(EventBus eventBus, MyView view) {
		super(eventBus, view);
		logger.log(Level.INFO, "GfilterConfigPresenter()");
		getView().setUiHandlers(this);
	}

	@Override
	protected void onBind() {
		super.onBind();
		getView().initView();
	}

	public void open() {
		getView().openModal();
	}
}