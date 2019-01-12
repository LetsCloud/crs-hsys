/**
 * 
 */
package io.crs.hsys.client.fro.reservation.guest;

import java.util.logging.Level;
import java.util.logging.Logger;

import javax.inject.Inject;

import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.user.client.ui.Widget;
import com.gwtplatform.mvp.client.ViewWithUiHandlers;

/**
 * @author robi
 *
 */
public class GuestResView extends ViewWithUiHandlers<GuestResUiHandlers> implements GuestResPresenter.MyView {
	private static Logger logger = Logger.getLogger(GuestResView.class.getName());

	interface Binder extends UiBinder<Widget, GuestResView> {
	}

	/**
	*/
	@Inject
	GuestResView(Binder binder) {
		logger.log(Level.INFO, "GuestResView()");
		initWidget(binder.createAndBindUi(this));
	}

	@Override
	public void open() {
	}
}
