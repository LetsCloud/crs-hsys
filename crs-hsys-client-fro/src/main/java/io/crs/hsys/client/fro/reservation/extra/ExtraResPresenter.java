/**
 * 
 */
package io.crs.hsys.client.fro.reservation.extra;

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
public class ExtraResPresenter extends AbstractResWidget<ExtraResPresenter.MyView> implements ExtraResUiHandlers {
	private static Logger logger = Logger.getLogger(ExtraResPresenter.class.getName());

	interface MyView extends View, HasUiHandlers<ExtraResUiHandlers> {
		void open();
	}

	@Inject
	ExtraResPresenter(EventBus eventBus, MyView view) {
		super(eventBus, view);
		logger.info("ExtraResPresenter()");
		this.setTitle("Extra");
		getView().setUiHandlers(this);
	}

	@Override
	protected void onBind() {
		super.onBind();
		logger.info("ExtraResPresenter().onBind()");
	}

	@Override
	protected void onReveal() {
		super.onReveal();
		logger.info("ExtraResPresenter().onReveal()");
		getView().open();
	}
}
