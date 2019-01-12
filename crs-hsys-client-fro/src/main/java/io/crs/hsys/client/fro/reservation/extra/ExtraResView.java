/**
 * 
 */
package io.crs.hsys.client.fro.reservation.extra;

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
public class ExtraResView extends ViewWithUiHandlers<ExtraResUiHandlers> implements ExtraResPresenter.MyView {
	private static Logger logger = Logger.getLogger(ExtraResView.class.getName());

	interface Binder extends UiBinder<Widget, ExtraResView> {
	}

	/**
	*/
	@Inject
	ExtraResView(Binder binder) {
		logger.log(Level.INFO, "ExtraResView()");
		initWidget(binder.createAndBindUi(this));
	}

	@Override
	public void open() {
	}
}
