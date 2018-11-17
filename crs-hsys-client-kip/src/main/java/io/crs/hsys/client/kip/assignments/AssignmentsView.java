/**
 * 
 */
package io.crs.hsys.client.kip.assignments;

import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;
import com.gwtplatform.mvp.client.ViewWithUiHandlers;

import gwt.material.design.client.ui.MaterialCollection;

/**
 * @author CR
 *
 */
public class AssignmentsView extends ViewWithUiHandlers<AssignmentsUiHandlers>
		implements AssignmentsPresenter.MyView {
	interface Binder extends UiBinder<Widget, AssignmentsView> {
	}

	@UiField
	MaterialCollection materialCollection;

	@UiField
	SimplePanel modalSlot, filterSlot;

	/**
	 */
	@Inject
	AssignmentsView(Binder uiBinder) {
		initWidget(uiBinder.createAndBindUi(this));

		bindSlot(AssignmentsPresenter.SLOT_FILTER, filterSlot);		
		bindSlot(AssignmentsPresenter.SLOT_ASSIGNMENTS, materialCollection);		
		bindSlot(AssignmentsPresenter.SLOT_MODAL, modalSlot);		
	}
}
