/**
 * 
 */
package io.crs.hsys.client.kip.dashboard;

import java.util.List;
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
import io.crs.hsys.client.kip.dashboard.widget.ListMeasureCard;
import io.crs.hsys.client.kip.dashboard.widget.MultiMeasureCard;
import io.crs.hsys.shared.dto.hk.MaintenanceSumDto;

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
		rowCards.clear();
		
		MultiMeasureCard dirtyRooms2 = new MultiMeasureCard();
		dirtyRooms2.setTitleText("Piszkos szobák");
		dirtyRooms2.setIconType(IconType.DELETE);
		dirtyRooms2.setIconColor(Color.RED_LIGHTEN_2);
		dirtyRooms2.setSumValue("299");
		dirtyRooms2.setPartTitle1("Érkező");
		dirtyRooms2.setPartValue1("89");
		dirtyRooms2.setPartTitle2("Lakó");
		dirtyRooms2.setPartValue2("140");
		dirtyRooms2.setPartTitle3("Üres");
		dirtyRooms2.setPartValue3("70");
		addCard(dirtyRooms2);

		MultiMeasureCard cleanRooms2 = new MultiMeasureCard();
		cleanRooms2.setTitleText("Tiszta szobák");
		cleanRooms2.setIconType(IconType.STAR);
		cleanRooms2.setIconColor(Color.BLUE_LIGHTEN_2);
		cleanRooms2.setSumValue("11");
		cleanRooms2.setPartTitle1("Érkező");
		cleanRooms2.setPartValue1("11");
		cleanRooms2.setPartTitle2("Lakó");
		cleanRooms2.setPartValue2("0");
		cleanRooms2.setPartTitle3("Üres");
		cleanRooms2.setPartValue3("0");
		addCard(cleanRooms2);

		MultiMeasureCard checkedRooms2 = new MultiMeasureCard();
		checkedRooms2.setTitleText("Ellenőrzött szobák");
		checkedRooms2.setIconType(IconType.DONE_ALL);
		checkedRooms2.setIconColor(Color.GREEN_LIGHTEN_2);
		checkedRooms2.setSumValue("11");
		checkedRooms2.setPartTitle1("Érkező");
		checkedRooms2.setPartValue1("11");
		checkedRooms2.setPartTitle2("Lakó");
		checkedRooms2.setPartValue2("0");
		checkedRooms2.setPartTitle3("Üres");
		checkedRooms2.setPartValue3("0");
		addCard(checkedRooms2);

		MultiMeasureCard oooRooms2 = new MultiMeasureCard();
		oooRooms2.setTitleText("OOO szobák");
		oooRooms2.setIconType(IconType.SETTINGS);
		oooRooms2.setIconColor(Color.GREY_DARKEN_3);
		oooRooms2.setSumValue("11");
		oooRooms2.setPartTitle1("V.vett");
		oooRooms2.setPartValue1("11");
		oooRooms2.setPartTitle2("Vár");
		oooRooms2.setPartValue2("5");
		oooRooms2.setPartTitle3("Marad");
		oooRooms2.setPartValue3("6");
		addCard(oooRooms2);

		MultiMeasureCard publicAreas2 = new MultiMeasureCard();
		publicAreas2.setTitleText("Közösségi terek");
		publicAreas2.setIconType(IconType.ZOOM_OUT_MAP);
		publicAreas2.setIconColor(Color.PURPLE_LIGHTEN_2);
		publicAreas2.setSumValue("45");
		publicAreas2.setPartTitle1("Piszkos");
		publicAreas2.setPartValue1("11");
		publicAreas2.setPartTitle2("Tiszta");
		publicAreas2.setPartValue2("15");
		publicAreas2.setPartTitle3("Ellenörzött");
		publicAreas2.setPartValue3("14");
		addCard(publicAreas2);

		MultiMeasureCard tasks2 = new MultiMeasureCard();
		tasks2.setTitleText("Takarítási készültség");
		tasks2.setIconType(IconType.ASSISTANT);
		tasks2.setIconColor(Color.TEAL_LIGHTEN_2);
		tasks2.setSumValue("45%");
		tasks2.setPartTitle1("Összes");
		tasks2.setPartValue1("900");
		tasks2.setPartTitle2("Elvégzett");
		tasks2.setPartValue2("90");
		tasks2.setPartTitle3("Vár");
		tasks2.setPartValue3("110");
		addCard(tasks2);

		new MaterialAnimation().transition(Transition.SHOW_GRID).animate(rowCards);
	}

	@Override
	public void showChiefEngBoard(List<MaintenanceSumDto> data, List<MaintenanceSumDto> data2) {
		rowCards.clear();

		MaterialColumn column = new MaterialColumn(12, 6, 8);
		column.add(new ListMeasureCard(data, "FELADAT ÖSSZESÍTŐ MŰSZAKI TERÜLETENKÉNT", IconType.ASSIGNMENT, "s12 m12 l6"));
		rowCards.add(column);

		MaterialColumn column2 = new MaterialColumn(12, 6, 4);
		column2.add(new ListMeasureCard(data2, "FELADAT ÖSSZESÍTŐ FELELŐSÖNKÉNT", IconType.ASSIGNMENT_IND, "s12"));
		rowCards.add(column2);

//		new MaterialAnimation().transition(Transition.SHOW_GRID).animate(rowCards);

		// TODO Auto-generated method stub

	}

	@Override
	public void createReceptionistDashboard() {
		rowCards.clear();
		
		MultiMeasureCard arrivals = new MultiMeasureCard();
		arrivals.setTitleText("Érkezős szobák");
		arrivals.setIconType(IconType.FLIGHT_LAND);
		arrivals.setIconColor(Color.RED_LIGHTEN_2);
		arrivals.setSumValue("299");
		arrivals.setPartTitle1("Piszkos");
		arrivals.setPartValue1("89");
		arrivals.setPartTitle2("Tiszta");
		arrivals.setPartValue2("140");
		arrivals.setPartTitle3("Ellenőrzött");
		arrivals.setPartValue3("70");
		addCard(arrivals);

		MultiMeasureCard stayOvers = new MultiMeasureCard();
		stayOvers.setTitleText("Lakó szobák");
		stayOvers.setIconType(IconType.LOCAL_HOTEL);
		stayOvers.setIconColor(Color.BLUE_LIGHTEN_2);
		stayOvers.setSumValue("11");
		stayOvers.setPartTitle1("Piszkos");
		stayOvers.setPartValue1("11");
		stayOvers.setPartTitle2("Tiszta");
		stayOvers.setPartValue2("0");
		stayOvers.setPartTitle3("Ellenőrzött");
		stayOvers.setPartValue3("0");
		addCard(stayOvers);

		MultiMeasureCard vacants = new MultiMeasureCard();
		vacants.setTitleText("Üres szobák");
		vacants.setIconType(IconType.CHECK_BOX_OUTLINE_BLANK);
		vacants.setIconColor(Color.GREEN_LIGHTEN_2);
		vacants.setSumValue("11");
		vacants.setPartTitle1("Piszkos");
		vacants.setPartValue1("11");
		vacants.setPartTitle2("Tiszta");
		vacants.setPartValue2("0");
		vacants.setPartTitle3("Ellenőrzött");
		vacants.setPartValue3("0");
		addCard(vacants);

		MultiMeasureCard oooRooms = new MultiMeasureCard();
		oooRooms.setTitleText("OOO szobák");
		oooRooms.setIconType(IconType.SETTINGS);
		oooRooms.setIconColor(Color.GREY_DARKEN_3);
		oooRooms.setSumValue("11");
		oooRooms.setPartTitle1("V.vett");
		oooRooms.setPartValue1("11");
		oooRooms.setPartTitle2("Vár");
		oooRooms.setPartValue2("5");
		oooRooms.setPartTitle3("Marad");
		oooRooms.setPartValue3("6");
		addCard(oooRooms);

		MultiMeasureCard oosRooms = new MultiMeasureCard();
		oosRooms.setTitleText("OOS szobák");
		oosRooms.setIconType(IconType.WARNING);
		oosRooms.setIconColor(Color.PURPLE_LIGHTEN_2);
		oosRooms.setSumValue("2");
		oosRooms.setPartTitle1("");
		oosRooms.setPartValue1("");
		oosRooms.setPartTitle2("");
		oosRooms.setPartValue2("");
		oosRooms.setPartTitle3("");
		oosRooms.setPartValue3("");
		addCard(oosRooms);

		MultiMeasureCard showRooms = new MultiMeasureCard();
		showRooms.setTitleText("Bemutató szobák");
		showRooms.setIconType(IconType.VISIBILITY);
		showRooms.setIconColor(Color.AMBER_LIGHTEN_2);
		showRooms.setSumValue("1");
		showRooms.setPartTitle1("");
		showRooms.setPartValue1("");
		showRooms.setPartTitle2("");
		showRooms.setPartValue2("");
		showRooms.setPartTitle3("");
		showRooms.setPartValue3("");
		addCard(showRooms);

		new MaterialAnimation().transition(Transition.SHOW_GRID).animate(rowCards);
	}
}
