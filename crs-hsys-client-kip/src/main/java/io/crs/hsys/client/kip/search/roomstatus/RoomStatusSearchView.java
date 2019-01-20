/**
 * 
 */
package io.crs.hsys.client.kip.search.roomstatus;

import java.util.logging.Logger;

import javax.inject.Inject;

import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Widget;
import com.gwtplatform.mvp.client.ViewWithUiHandlers;

import gwt.material.design.client.ui.MaterialSearch;

/**
 * @author robi
 *
 */
public class RoomStatusSearchView extends ViewWithUiHandlers<RoomStatusSearchUiHandlers>
		implements RoomStatusSearchPresenter.MyView {
	private static Logger logger = Logger.getLogger(RoomStatusSearchView.class.getName());

	interface Binder extends UiBinder<Widget, RoomStatusSearchView> {
	}

	@UiField
	MaterialSearch searchField;
	
	/**
	 */
	@Inject
	RoomStatusSearchView(Binder uiBinder) {
		logger.info("RoomStatusSearchView()");
		initWidget(uiBinder.createAndBindUi(this));
	}

}
