/**
 * 
 */
package io.crs.hsys.client.fro.reservation.guest;

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
public class GuestResPresenter extends AbstractResWidget<GuestResPresenter.MyView> implements GuestResUiHandlers {
	private static Logger logger = Logger.getLogger(GuestResPresenter.class.getName());

	interface MyView extends View, HasUiHandlers<GuestResUiHandlers> {
		void open();
	}

	@Inject
	GuestResPresenter(EventBus eventBus, MyView view) {
		super(eventBus, view);
		logger.info("GuestResPresenter()");
		this.setTitle("Vendégek");
		getView().setUiHandlers(this);
	}

	@Override
	protected void onBind() {
		super.onBind();
		logger.info("GuestResPresenter().onBind()");
	}

	@Override
	protected void onReveal() {
		super.onReveal();
		logger.info("GuestResPresenter().onReveal()");
		getView().open();
	}
}
