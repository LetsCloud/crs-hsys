/**
 * 
 */
package io.crs.hsys.client.fro.dashboard;

import java.util.logging.Level;
import java.util.logging.Logger;

import javax.inject.Inject;

import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Widget;
import com.gwtplatform.mvp.client.ViewWithUiHandlers;

import gwt.material.design.client.ui.MaterialColumn;
import gwt.material.design.client.ui.MaterialRow;
import gwt.material.design.client.ui.animate.MaterialAnimation;
import gwt.material.design.client.ui.animate.Transition;

/**
 * @author CR
 *
 */
public class DashboardView extends ViewWithUiHandlers<DashboardUiHandlers> implements DashboardPresenter.MyView {
	private static Logger logger = Logger.getLogger(DashboardView.class.getName());

	interface Binder extends UiBinder<Widget, DashboardView> {
	}

	@UiField
	MaterialRow rowCards;

	@Inject
	DashboardView(Binder uiBinder) {
		initWidget(uiBinder.createAndBindUi(this));
		logger.log(Level.INFO, "DashboardView");

	}

	public void addCard(Widget card) {
		MaterialColumn column = new MaterialColumn(12, 6, 4);
		column.add(card);
		rowCards.add(column);
	}

	@Override
	public void showCards() {
		new MaterialAnimation().transition(Transition.SHOW_GRID).animate(rowCards);
	}
}
