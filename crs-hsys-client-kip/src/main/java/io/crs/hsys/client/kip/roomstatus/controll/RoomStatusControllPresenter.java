/**
 * 
 */
package io.crs.hsys.client.kip.roomstatus.controll;

import java.util.logging.Level;
import java.util.logging.Logger;

import com.google.inject.Inject;
import com.google.web.bindery.event.shared.EventBus;
import com.gwtplatform.mvp.client.HasUiHandlers;
import com.gwtplatform.mvp.client.PresenterWidget;
import com.gwtplatform.mvp.client.View;

import io.crs.hsys.shared.dto.hk.RoomStatusDto;

/**
 * @author robi
 *
 */
public class RoomStatusControllPresenter extends PresenterWidget<RoomStatusControllPresenter.MyView>
		implements RoomStatusControllUiHandlers {
	private static Logger logger = Logger.getLogger(RoomStatusControllPresenter.class.getName());

	interface MyView extends View, HasUiHandlers<RoomStatusControllUiHandlers> {
		void open(RoomStatusDto dto);

		void close();
	}

	@Inject
	RoomStatusControllPresenter(EventBus eventBus, MyView view) {
		super(eventBus, view);
		logger.log(Level.INFO, "RoomStatusControllPresenter()");
		getView().setUiHandlers(this);
	}

	@Override
	protected void onBind() {
		super.onBind();
	}

	public void open(RoomStatusDto dto) {
		getView().open(dto);
	}
}