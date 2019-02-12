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
