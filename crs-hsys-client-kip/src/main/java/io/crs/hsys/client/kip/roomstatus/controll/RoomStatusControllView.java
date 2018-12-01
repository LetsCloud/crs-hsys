/**
 * 
 */
package io.crs.hsys.client.kip.roomstatus.controll;

import java.util.logging.Level;
import java.util.logging.Logger;

import javax.inject.Inject;

import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.HTMLPanel;

import com.gwtplatform.mvp.client.ViewWithUiHandlers;

import gwt.material.design.addins.client.overlay.MaterialOverlay;
import io.crs.hsys.shared.dto.hk.RoomStatusDto;

/**
 * @author robi
 *
 */
public class RoomStatusControllView extends ViewWithUiHandlers<RoomStatusControllUiHandlers>
		implements RoomStatusControllPresenter.MyView {
	private static Logger logger = Logger.getLogger(RoomStatusControllView.class.getName());

	interface Binder extends UiBinder<HTMLPanel, RoomStatusControllView> {
	}

	@UiField
	MaterialOverlay overlay;

	@Inject
	RoomStatusControllView(Binder binder) {
		logger.log(Level.INFO, "RoomStatusControllView()");
		initWidget(binder.createAndBindUi(this));
	}

	@Override
	public void open(RoomStatusDto dto) {
		overlay.open();
	}

	@Override
	public void close() {
		overlay.close();
	}
}
