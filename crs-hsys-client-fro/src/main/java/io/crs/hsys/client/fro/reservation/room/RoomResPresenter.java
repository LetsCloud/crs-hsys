/**
 * 
 */
package io.crs.hsys.client.fro.reservation.room;

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
public class RoomResPresenter extends AbstractResWidget<RoomResPresenter.MyView> implements RoomResUiHandlers {
	private static Logger logger = Logger.getLogger(RoomResPresenter.class.getName());

	interface MyView extends View, HasUiHandlers<RoomResUiHandlers> {
		void open();
	}

	@Inject
	RoomResPresenter(EventBus eventBus, MyView view) {
		super(eventBus, view);
		logger.info("RoomResPresenter()");
		this.setTitle("Szobafoglalás");
		getView().setUiHandlers(this);
	}

	@Override
	protected void onBind() {
		super.onBind();
		logger.info("RoomResPresenter().onBind()");
	}

	@Override
	protected void onReveal() {
		super.onReveal();
		logger.info("RoomResPresenter().onReveal()");
		getView().open();
	}
}
