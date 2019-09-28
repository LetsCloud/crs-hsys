/**
 * 
 */
package io.crs.hsys.client.fro.rate.manager;

import java.util.List;
import java.util.logging.Logger;

import javax.inject.Inject;

import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ScrollEvent;
import com.google.gwt.event.dom.client.ScrollHandler;
import com.google.gwt.event.logical.shared.ResizeEvent;
import com.google.gwt.event.logical.shared.ResizeHandler;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ScrollPanel;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.Widget;
import com.gwtplatform.mvp.client.ViewWithUiHandlers;

import gwt.material.design.client.js.Window;
import gwt.material.design.client.ui.MaterialPanel;
import io.crs.hsys.client.core.event.ContentPushEvent.MenuState;
import io.crs.hsys.client.core.util.DateUtils;
import io.crs.hsys.shared.dto.rate.query.RateByDateDto;
import io.crs.hsys.shared.dto.rate.query.RateQueryRespDto;

/**
 * @author robi
 *
 */
public class RateManagerView extends ViewWithUiHandlers<RateManagerUiHandlers> implements RateManagerPresenter.MyView {
	private static Logger logger = Logger.getLogger(RateManagerView.class.getName());

	interface Binder extends UiBinder<Widget, RateManagerView> {
	}

	@UiField
	MaterialPanel contentPanel;

	@UiField
	SimplePanel filterPanel;

	@UiField
	FlexTable fixedCol, headerTable, flexTable, codeHeaderTable;

	@UiField
	ScrollPanel headerScrollPanel, contentScrollPanel;

	private MenuState menuState = MenuState.CLOSE;

	/**
	* 
	*/
	@Inject
	RateManagerView(Binder uiBinder) {
		logger.info("RateBrowserView()");
		initWidget(uiBinder.createAndBindUi(this));

		bindSlot(RateManagerPresenter.SLOT_FILTER, filterPanel);

		headerScrollPanel.addScrollHandler(new ScrollHandler() {
			@Override
			public void onScroll(ScrollEvent event) {
				contentScrollPanel.setHorizontalScrollPosition(headerScrollPanel.getHorizontalScrollPosition());
			}
		});

		contentScrollPanel.addScrollHandler(new ScrollHandler() {
			@Override
			public void onScroll(ScrollEvent event) {
				headerScrollPanel.setHorizontalScrollPosition(contentScrollPanel.getHorizontalScrollPosition());
			}
		});

		Window.addResizeHandler(new ResizeHandler() {

			@Override
			public void onResize(ResizeEvent event) {
				resizeScrollPanels(menuState);
			}
		});
	}

	private void resizeScrollPanels(MenuState menuState) {
		int newWidth = contentPanel.getWidth() - 145;
		if (menuState.equals(MenuState.OPEN))
			newWidth = contentPanel.getWidth() - 155;

		headerScrollPanel.setWidth(Integer.toString(newWidth) + "px");
		contentScrollPanel.setWidth(Integer.toString(newWidth) + "px");
	}

	@Override
	public void setData(List<RateQueryRespDto> data) {
		codeHeaderTable.clear();
		fixedCol.clear();
		flexTable.clear();
		headerTable.clear();
		codeHeaderTable.setWidget(0, 0, new RateCodeWidget("Árkód"));
		codeHeaderTable.setWidget(0, 1, new RateCodeWidget("Szobatípus"));
		codeHeaderTable.getCellFormatter().getElement(0, 0).getStyle().setPadding(1, Unit.PX);
		codeHeaderTable.getCellFormatter().getElement(0, 1).getStyle().setPadding(1, Unit.PX);
		int i = 0;
		for (RateQueryRespDto line : data) {
			fixedCol.setWidget(i, 0, new RateCodeWidget(line.getRateCode().getCode()));
			fixedCol.setWidget(i, 1, new RateCodeWidget(line.getRoomType().getCode()));
			fixedCol.getCellFormatter().getElement(i, 0).getStyle().setPadding(1, Unit.PX);
			fixedCol.getCellFormatter().getElement(i, 1).getStyle().setPadding(1, Unit.PX);
			int j = 0;
			for (RateByDateDto rbd : line.getRatesByDate()) {
				if (i == 1) {
//					headerTable.getFlexCellFormatter().setWidth(0, j, "40px");
//					headerTable.setText(0, j, Integer.toString(j));
					Label headerLabel = new Label(Integer.toString(j));
					headerLabel.setWidth("40px");
					headerTable.setWidget(0, j, new HeaderWidget(DateUtils.getDayOfWeek3(rbd.getDate(), "hu"),
							DateUtils.getMonthAndDay(rbd.getDate())));
					headerTable.getCellFormatter().getElement(0, j).getStyle().setPadding(1, Unit.PX);
				}
//				flexTable.getFlexCellFormatter().setWidth(i, j, "40px");
//				flexTable.setText(i, j, rbd.getRates().get(RatePriceType.DOUBLE).toString());
				RateWidget celllLabel = new RateWidget(rbd);
				flexTable.setWidget(i, j, celllLabel);
				flexTable.getCellFormatter().getElement(i, j).getStyle().setPadding(1, Unit.PX);
				j++;
			}
			i++;
		}
		resizeScrollPanels(menuState);
	}

	@Override
	public void resizePanls(MenuState menuState) {
		this.menuState = menuState;
		resizeScrollPanels(menuState);
	}

	@UiHandler("updateButton")
	public void onClickUpdate(ClickEvent event) {
		getUiHandlers().update();
	}
}
