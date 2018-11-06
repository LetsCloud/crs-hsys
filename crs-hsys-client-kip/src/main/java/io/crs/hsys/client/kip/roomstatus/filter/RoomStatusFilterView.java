/**
 * 
 */
package io.crs.hsys.client.kip.roomstatus.filter;

import java.util.logging.Level;
import java.util.logging.Logger;

import javax.inject.Inject;

import com.google.gwt.editor.client.Editor;
import com.google.gwt.editor.client.SimpleBeanEditorDriver;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.Widget;
import com.gwtplatform.mvp.client.ViewWithUiHandlers;

import io.crs.hsys.shared.dto.filter.RoomStatusFilterDto;

import gwt.material.design.client.ui.MaterialButton;
import gwt.material.design.client.ui.MaterialCheckBox;
import gwt.material.design.client.ui.MaterialTextBox;

/**
 * @author CR
 *
 */
public class RoomStatusFilterView extends ViewWithUiHandlers<RoomStatusFilterUiHandlers>
		implements RoomStatusFilterPresenter.MyView, Editor<RoomStatusFilterDto> {
	private static final Logger LOGGER = Logger.getLogger(RoomStatusFilterView.class.getName());

	interface Binder extends UiBinder<Widget, RoomStatusFilterView> {
	}

	interface Driver extends SimpleBeanEditorDriver<RoomStatusFilterDto, RoomStatusFilterView> {
	}

	private final Driver driver;

	@UiField
	MaterialTextBox fromRoom, toRoom, fromFloor, toFloor;

	@UiField
	MaterialCheckBox dirtyVacant, cleanVacant, okVacant, dirtyOccupied, cleanOccupied, okOccupied, outOfService,
			showRoom;

	@UiField
	MaterialButton filterButton;

	/**
	 */
	@Inject
	RoomStatusFilterView(Binder uiBinder, Driver driver) {
		this.driver = driver;
		LOGGER.log(Level.INFO, "RoomStatusFilterView()");
		initWidget(uiBinder.createAndBindUi(this));
		driver.initialize(this);
	}

	@Override
	public void createFilter() {
		driver.edit(new RoomStatusFilterDto());
	}

	@UiHandler("filterButton")
	void onFilterClick(ClickEvent event) {
		LOGGER.log(Level.INFO, "onFilterClick()");
		getUiHandlers().doFilter(driver.flush());
	}
}
