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

import io.crs.hsys.client.core.security.CurrentUser;
import io.crs.hsys.shared.dto.common.AppUserDto;
import io.crs.hsys.shared.dto.hk.RoomStatusDto;

/**
 * @author robi
 *
 */
public class RoomStatusControllPresenter extends PresenterWidget<RoomStatusControllPresenter.MyView>
		implements RoomStatusControllUiHandlers {
	private static Logger logger = Logger.getLogger(RoomStatusControllPresenter.class.getName());

	interface MyView extends View, HasUiHandlers<RoomStatusControllUiHandlers> {
		void open(RoomStatusDto dto, AppUserDto admin);

		void close();
	}

	private final CurrentUser currentUser;
	
	@Inject
	RoomStatusControllPresenter(EventBus eventBus, MyView view, CurrentUser currentUser) {
		super(eventBus, view);
		logger.log(Level.INFO, "RoomStatusControllPresenter()");
		this.currentUser = currentUser;
		getView().setUiHandlers(this);
	}

	@Override
	protected void onBind() {
		super.onBind();
	}

	public void open(RoomStatusDto dto) {
		logger.log(Level.INFO, "RoomStatusControllPresenter().open()");
		getView().open(dto, currentUser.getAppUserDto());
	}
}