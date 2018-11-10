/**
 * 
 */
package io.crs.hsys.client.kip.browser.guestroom;

import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;
import com.gwtplatform.mvp.client.ViewWithUiHandlers;

import gwt.material.design.client.ui.MaterialCollection;

/**
 * @author robi
 *
 */
public class GuestRoomBrowserView extends ViewWithUiHandlers<GuestRoomBrowserUiHandlers>
		implements GuestRoomBrowserPresenter.MyView {
	interface Binder extends UiBinder<Widget, GuestRoomBrowserView> {
	}

	@UiField
	MaterialCollection materialCollection;

	@UiField
	SimplePanel modalSlot, filterSlot;

	/**
	*/
	@Inject
	GuestRoomBrowserView(Binder uiBinder) {
		initWidget(uiBinder.createAndBindUi(this));

		bindSlot(GuestRoomBrowserPresenter.SLOT_FILTER, filterSlot);
		bindSlot(GuestRoomBrowserPresenter.SLOT_ASSIGNMENTS, materialCollection);
		bindSlot(GuestRoomBrowserPresenter.SLOT_MODAL, modalSlot);
	}
}
