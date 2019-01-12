/**
 * 
 */
package io.crs.hsys.client.fro.reservation;

import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.inject.Inject;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Widget;
import com.gwtplatform.mvp.client.ViewWithUiHandlers;

import gwt.material.design.client.constants.WavesType;
import gwt.material.design.client.ui.MaterialCollection;
import gwt.material.design.client.ui.MaterialCollectionItem;
import gwt.material.design.client.ui.MaterialColumn;
import gwt.material.design.client.ui.MaterialLabel;
import gwt.material.design.client.ui.MaterialLink;
import gwt.material.design.client.ui.MaterialPanel;

/**
 * @author robi
 *
 */
public class ReservationView extends ViewWithUiHandlers<ReservationUiHandlers> implements ReservationPresenter.MyView {
	private static Logger logger = Logger.getLogger(ReservationView.class.getName());

	private static Binder uiBinder = GWT.create(Binder.class);

	interface Binder extends UiBinder<Widget, ReservationView> {
	}

	@UiField
	MaterialPanel mobilePanel, desktopPanel;

	@UiField
	MaterialColumn tablePanel;

	@UiField
	MaterialCollection desktopMenu, mobileMenu;

	@Inject
	ReservationView() {
		logger.log(Level.INFO, "ReservationView()");
		initWidget(uiBinder.createAndBindUi(this));

		bindSlot(ReservationPresenter.SLOT_CONTENT, tablePanel);
	}

	@Override
	public void buildMenu() {
		logger.log(Level.INFO, "buildMenu()");
		mobileMenu.clear();
		desktopMenu.clear();

		for (Map.Entry<Integer, AbstractResWidget<?>> entry : getUiHandlers().getWidgetsMap().entrySet()) {
			addMobileMenuItem(entry.getKey(), entry.getValue().getTitle());
			addDesktopMenuItem(entry.getKey(), entry.getValue().getTitle());
		}
	}

	private void addMobileMenuItem(Integer index, String caption) {
		MaterialLink link = new MaterialLink(caption);
		link.addClickHandler(handler -> {
			getUiHandlers().showTable(index);
		});
		mobileMenu.add(link);
	}

	private void addDesktopMenuItem(Integer index, String caption) {

		MaterialCollectionItem item = desktopMenuItem(caption);
		item.addClickHandler(handler -> {
			getUiHandlers().showTable(index);
		});
		desktopMenu.add(item);
	}

	private MaterialCollectionItem desktopMenuItem(String caption) {
		MaterialLabel label = new MaterialLabel(caption);
//		label.setPaddingTop(5);

//		MaterialIcon icon = new MaterialIcon(IconType.KEYBOARD_ARROW_RIGHT);
//		icon.setIconPosition(IconPosition.RIGHT);

//		MaterialCollectionSecondary sec = new MaterialCollectionSecondary();
//		sec.setPaddingBottom(15);
//		sec.add(icon);

		MaterialCollectionItem item = new MaterialCollectionItem();
		item.setWaves(WavesType.DEFAULT);
		item.setPaddingLeft(10);
		item.setPaddingRight(10);
		item.setPaddingBottom(10);
		item.add(label);
//		item.add(sec);

		return item;
	}

	@Override
	public void setDesktopMenu(Integer index) {
		desktopMenu.setActive(index, true);
	}

	@Override
	public void setMobileView(Boolean show) {
		mobilePanel.setVisible(show);
		desktopPanel.setVisible(!show);
	}

}
