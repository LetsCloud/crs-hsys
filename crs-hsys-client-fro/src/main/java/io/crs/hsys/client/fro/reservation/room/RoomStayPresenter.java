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
import io.crs.hsys.shared.dto.reservation.ReservationDto;

/**
 * @author robi
 *
 */
public class RoomStayPresenter extends AbstractResWidget<RoomStayPresenter.MyView> implements RoomResUiHandlers {
	private static Logger logger = Logger.getLogger(RoomStayPresenter.class.getName());

	interface MyView extends View, HasUiHandlers<RoomResUiHandlers> {
		void setData(ReservationDto reservation);
	}

	@Inject
	RoomStayPresenter(EventBus eventBus, MyView view) {
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
	}
	
	public void setData(ReservationDto reservation) {
		getView().setData(reservation);
	}
}
