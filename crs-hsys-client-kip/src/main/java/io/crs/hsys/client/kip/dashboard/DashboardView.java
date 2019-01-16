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
import gwt.material.design.client.constants.IconType;
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
		dirtyRooms.setTitleTextColor(Color.WHITE);
		dirtyRooms.setTitleBackgroundColor(Color.RED);
		dirtyRooms.setTitleIconType(IconType.DELETE);
		dirtyRooms.setTitleIconColor(Color.RED_LIGHTEN_4);
		dirtyRooms.setTextColor(Color.GREY_DARKEN_3);
		dirtyRooms.setBackgroundColor(Color.GREY_LIGHTEN_4);
		dirtyRooms.setTitleText("PISZKOS SZOBÁK");
		dirtyRooms.addItem("Érkező", "50", "Lakó", "50", "Üres", "50");
		dirtyRooms.addItem("ÖSSZES", "150");
		addCard(dirtyRooms);

		CardDisplay cleanRooms = new CardDisplay();
		cleanRooms.setBackgroundColor(Color.AMBER_LIGHTEN_2);
		cleanRooms.setTitleBackgroundColor(Color.AMBER);
		cleanRooms.setTextColor(Color.WHITE);
		cleanRooms.setTitleText("TISZTA SZOBÁK");
		cleanRooms.addItem("Érkező", "50", "Lakó", "50", "Üres", "50");
		cleanRooms.addItem("ÖSSZES", "150");
		addCard(cleanRooms);

		CardDisplay checkedRooms = new CardDisplay();
		checkedRooms.setBackgroundColor(Color.GREEN_DARKEN_2);
		checkedRooms.setTitleBackgroundColor(Color.GREEN_DARKEN_4);
		checkedRooms.setTextColor(Color.WHITE);
		checkedRooms.setTitleText("ELLENŐRZÖTT SZOBÁK");
		checkedRooms.addItem("Érkező", "50", "Lakó", "50", "Üres", "50");
		checkedRooms.addItem("ÖSSZES", "150");
		addCard(checkedRooms);

		CardDisplay publicAreas = new CardDisplay();
		publicAreas.setBackgroundColor(Color.PURPLE_DARKEN_2);
		publicAreas.setTitleBackgroundColor(Color.PURPLE_DARKEN_4);
		publicAreas.setTextColor(Color.WHITE);
		publicAreas.setTitleText("KÖZÖSSÉGI TEREK");
		publicAreas.addItem("Piszkos", "50", "Tiszta", "50", "Ellenőrzött", "50");
		publicAreas.addItem("", "");
		addCard(publicAreas);

		CardDisplay tasks = new CardDisplay();
		tasks.setBackgroundColor(Color.BLUE_DARKEN_2);
		tasks.setTitleBackgroundColor(Color.BLUE_DARKEN_4);
		tasks.setTextColor(Color.WHITE);
		tasks.setTitleText("TAKARÍTÁSI FELADATOK");
		tasks.addItem("Szoba", "50", "Közösségi tér", "50", "Bekészítés", "50");
		tasks.addItem("", "");
		addCard(tasks);

		CardDisplay oooRooms = new CardDisplay();
		oooRooms.setBackgroundColor(Color.GREY_DARKEN_2);
		oooRooms.setTitleBackgroundColor(Color.GREY_DARKEN_4);
		oooRooms.setTextColor(Color.WHITE);
		oooRooms.setTitleText("OOO SZOBÁK");
		oooRooms.addItem("Visszaadandó", "50", "Marad", "50");
		tasks.addItem("", "");
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
