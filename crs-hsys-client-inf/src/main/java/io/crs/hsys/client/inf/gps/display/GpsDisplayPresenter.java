/**
 * 
 */
package io.crs.hsys.client.inf.gps.display;

import java.util.logging.Level;
import java.util.logging.Logger;

import javax.inject.Inject;

import com.google.web.bindery.event.shared.EventBus;
import com.gwtplatform.mvp.client.HasUiHandlers;
import com.gwtplatform.mvp.client.PresenterWidget;
import com.gwtplatform.mvp.client.View;

import io.crs.hsys.client.inf.gps.config.GpsConfigPresenter;

/**
 * @author CR
 *
 */
public class GpsDisplayPresenter extends PresenterWidget<GpsDisplayPresenter.MyView> implements GpsDisplayUiHandlers {
	private static Logger logger = Logger.getLogger(GpsDisplayPresenter.class.getName());

	interface MyView extends View, HasUiHandlers<GpsDisplayUiHandlers> {
	}

	private GpsConfigPresenter gpsConfigPresenter;

	@Inject
	GpsDisplayPresenter(EventBus eventBus, MyView view) {
		super(eventBus, view);
		logger.log(Level.INFO, "GpsDisplayPresenter()");
		getView().setUiHandlers(this);

	}

	@Override
	protected void onBind() {
		super.onBind();
	}

	@Override
	public void gpsSetup() {
		gpsConfigPresenter.open();
	}

	public void setGpsConfigPresenter(GpsConfigPresenter gpsConfigPresenter) {
		this.gpsConfigPresenter = gpsConfigPresenter;
	}
}