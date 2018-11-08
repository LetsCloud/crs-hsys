/**
 * 
 */
package io.crs.hsys.client.kip.dashboard;

import java.util.logging.Level;
import java.util.logging.Logger;

import javax.inject.Inject;

import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Widget;
import com.gwtplatform.mvp.client.ViewWithUiHandlers;

import gwt.material.design.client.constants.Color;
import gwt.material.design.client.ui.MaterialColumn;
import gwt.material.design.client.ui.MaterialRow;
import gwt.material.design.client.ui.animate.MaterialAnimation;
import gwt.material.design.client.ui.animate.Transition;
import io.crs.hsys.client.kip.ui.CardDisplay;

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

		CardDisplay dirtyRooms = new CardDisplay();
		dirtyRooms.setBackgroundColor(Color.RED_DARKEN_2);
		dirtyRooms.setTextColor(Color.WHITE);
		dirtyRooms.setTitleText("PISZKOS SZOBÁK");
		dirtyRooms.addItem("Lakó", "50");
		dirtyRooms.addItem("Érkező", "50");
		dirtyRooms.addItem("Üres", "50");
		dirtyRooms.addItem("ÖSSZESEN", "150");
		addCard(dirtyRooms);

		CardDisplay cleanRooms = new CardDisplay();
		cleanRooms.setBackgroundColor(Color.AMBER_DARKEN_2);
		cleanRooms.setTextColor(Color.WHITE);
		cleanRooms.setTitleText("TISZTA SZOBÁK");
		cleanRooms.addItem("Lakó", "50");
		cleanRooms.addItem("Érkező", "50");
		cleanRooms.addItem("Üres", "50");
		cleanRooms.addItem("ÖSSZESEN", "150");
		addCard(cleanRooms);

		CardDisplay checkedRooms = new CardDisplay();
		checkedRooms.setBackgroundColor(Color.GREEN_DARKEN_2);
		checkedRooms.setTextColor(Color.WHITE);
		checkedRooms.setTitleText("ELLENŐRZÖTT SZOBÁK");
		checkedRooms.addItem("Lakó", "50");
		checkedRooms.addItem("Érkező", "50");
		checkedRooms.addItem("Üres", "50");
		checkedRooms.addItem("ÖSSZESEN", "150");
		addCard(checkedRooms);

		CardDisplay publicAreas = new CardDisplay();
		publicAreas.setBackgroundColor(Color.PURPLE_DARKEN_2);
		publicAreas.setTextColor(Color.WHITE);
		publicAreas.setTitleText("KÖZÖSSÉGI TEREK");
		publicAreas.addItem("Piszkos", "50");
		publicAreas.addItem("Tiszta", "50");
		publicAreas.addItem("Ellenőrzött", "50");
		publicAreas.addItem(".", ".");
		addCard(publicAreas);

		CardDisplay tasks = new CardDisplay();
		tasks.setBackgroundColor(Color.BLUE_DARKEN_2);
		tasks.setTextColor(Color.WHITE);
		tasks.setTitleText("TAKARÍTÁSI FELADATOK");
		tasks.addItem("Vendégszoba", "50");
		tasks.addItem("Közösségi tér", "50");
		tasks.addItem("Bekészítés", "50");
		tasks.addItem(".", ".");
		addCard(tasks);

		CardDisplay oooRooms = new CardDisplay();
		oooRooms.setBackgroundColor(Color.GREY_DARKEN_2);
		oooRooms.setTextColor(Color.WHITE);
		oooRooms.setTitleText("OOO SZOBÁK");
		oooRooms.addItem(".", ".");
		oooRooms.addItem("Visszaadandó", "50");
		oooRooms.addItem("Marad", "50");
		oooRooms.addItem(".", ".");
		addCard(oooRooms);
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
