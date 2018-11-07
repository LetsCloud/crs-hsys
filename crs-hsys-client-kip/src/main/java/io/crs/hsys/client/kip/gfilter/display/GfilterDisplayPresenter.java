/**
 * 
 */
package io.crs.hsys.client.kip.gfilter.display;

import java.util.logging.Level;
import java.util.logging.Logger;

import javax.inject.Inject;

import com.google.web.bindery.event.shared.EventBus;
import com.gwtplatform.mvp.client.HasUiHandlers;
import com.gwtplatform.mvp.client.PresenterWidget;
import com.gwtplatform.mvp.client.View;

import io.crs.hsys.client.kip.gfilter.config.GfilterConfigPresenter;

/**
 * @author CR
 *
 */
public class GfilterDisplayPresenter extends PresenterWidget<GfilterDisplayPresenter.MyView> implements GfilterDisplayUiHandlers {
	private static Logger logger = Logger.getLogger(GfilterDisplayPresenter.class.getName());

	interface MyView extends View, HasUiHandlers<GfilterDisplayUiHandlers> {
	}

	private GfilterConfigPresenter gfilterConfigPresenter;

	@Inject
	GfilterDisplayPresenter(EventBus eventBus, MyView view) {
		super(eventBus, view);
		logger.log(Level.INFO, "GpsDisplayPresenter()");
		getView().setUiHandlers(this);

	}

	@Override
	protected void onBind() {
		super.onBind();
	}

	@Override
	public void onConfig() {
		gfilterConfigPresenter.open();
	}

	public void setGfilterConfigPresenter(GfilterConfigPresenter gfilterConfigPresenter) {
		this.gfilterConfigPresenter = gfilterConfigPresenter;
	}
}