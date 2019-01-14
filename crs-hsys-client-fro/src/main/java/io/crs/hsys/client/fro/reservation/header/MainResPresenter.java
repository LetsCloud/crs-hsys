/**
 * 
 */
package io.crs.hsys.client.fro.reservation.header;

import java.util.logging.Logger;

import javax.inject.Inject;

import com.google.web.bindery.event.shared.EventBus;
import com.gwtplatform.mvp.client.HasUiHandlers;
import com.gwtplatform.mvp.client.View;

import io.crs.hsys.client.fro.reservation.AbstractResWidget;

/**
 * @author robi
 *
 */
public class MainResPresenter extends AbstractResWidget<MainResPresenter.MyView> implements MainResUiHandlers {
	private static Logger logger = Logger.getLogger(MainResPresenter.class.getName());

	interface MyView extends View, HasUiHandlers<MainResUiHandlers> {
		void open();
	}

	@Inject
	MainResPresenter(EventBus eventBus, MyView view) {
		super(eventBus, view);
		logger.info("MainResPresenter()");
		this.setTitle("Főszámla");
		getView().setUiHandlers(this);
	}

	@Override
	protected void onBind() {
		super.onBind();
		logger.info("MainResPresenter().onBind()");
	}

	@Override
	protected void onReveal() {
		super.onReveal();
		logger.info("MainResPresenter().onReveal()");
		getView().open();
	}
}
