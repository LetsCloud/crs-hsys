/**
 * 
 */
package io.crs.hsys.client.fro.rate.updater;

import java.util.logging.Logger;

import javax.inject.Inject;

import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Widget;
import com.gwtplatform.mvp.client.ViewWithUiHandlers;

import gwt.material.design.client.ui.MaterialPanel;
import gwt.material.design.client.ui.MaterialRow;

/**
 * @author robi
 *
 */
public class RateUpdaterView extends ViewWithUiHandlers<RateUpdaterUiHandlers> implements RateUpdaterPresenter.MyView {
	private static Logger logger = Logger.getLogger(RateUpdaterView.class.getName());

	interface Binder extends UiBinder<Widget, RateUpdaterView> {
	}

	@UiField
	MaterialRow contentPanel;

	/**
	* 
	*/
	@Inject
	RateUpdaterView(Binder uiBinder) {
		logger.info("RateUpdaterView()");
		initWidget(uiBinder.createAndBindUi(this));
	}
}
