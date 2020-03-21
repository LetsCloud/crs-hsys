/**
 * 
 */
package io.crs.hsys.client.inf.gps.config;

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
public class GpsConfigPresenter extends PresenterWidget<GpsConfigPresenter.MyView> implements GpsConfigUiHandlers {
	private static Logger logger = Logger.getLogger(GpsConfigPresenter.class.getName());

	interface MyView extends View, HasUiHandlers<GpsConfigUiHandlers> {
		void initView();

		void openModal();
	}

	@Inject
	GpsConfigPresenter(EventBus eventBus, MyView view) {
		super(eventBus, view);
		logger.log(Level.INFO, "GpsPresenter()");
		getView().setUiHandlers(this);
	}

	@Override
	protected void onBind() {
		super.onBind();
		logger.log(Level.INFO, "GpsPresenter.onReveal()->in");
		getView().initView();
		logger.log(Level.INFO, "GpsPresenter.onReveal()->out");
	}

	public void open() {
		getView().openModal();
	}
}