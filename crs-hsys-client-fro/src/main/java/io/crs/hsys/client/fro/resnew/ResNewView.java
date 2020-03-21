/**
 * 
 */
package io.crs.hsys.client.fro.resnew;

import java.util.logging.Level;
import java.util.logging.Logger;

import javax.inject.Inject;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.Widget;
import com.gwtplatform.mvp.client.ViewWithUiHandlers;

/**
 * @author robi
 *
 */
public class ResNewView extends ViewWithUiHandlers<ResNewUiHandlers> implements ResNewPresenter.MyView {
	private static Logger logger = Logger.getLogger(ResNewView.class.getName());

	private static Binder uiBinder = GWT.create(Binder.class);

	interface Binder extends UiBinder<Widget, ResNewView> {
	}

	@UiField
	SimplePanel filterPanel;
	
	@Inject
	ResNewView() {
		logger.log(Level.INFO, "ResNewView()");
		initWidget(uiBinder.createAndBindUi(this));
		bindSlot(ResNewPresenter.SLOT_FILTER, filterPanel);				
		
	}
}
