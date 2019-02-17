/**
 * 
 */
package io.crs.hsys.client.core.editor.room;

import java.util.List;
import java.util.logging.Logger;

import javax.inject.Inject;

import com.google.gwt.editor.client.Editor;
import com.google.gwt.editor.client.SimpleBeanEditorDriver;
import com.google.gwt.editor.client.adapters.TakesValueEditor;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.TakesValue;
import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.ui.Widget;
import com.gwtplatform.mvp.client.ViewWithUiHandlers;

import gwt.material.design.addins.client.combobox.MaterialComboBox;
import gwt.material.design.client.ui.MaterialButton;
import gwt.material.design.client.ui.MaterialTextBox;

import io.crs.hsys.client.core.i18n.CoreConstants;
import io.crs.hsys.shared.dto.EntityPropertyCode;
import io.crs.hsys.shared.dto.hotel.RoomDto;
import io.crs.hsys.shared.dto.hotel.RoomTypeDtor;

/**
 * @author robi
 *
 */
public class RoomEditorView extends ViewWithUiHandlers<RoomEditorUiHandlers>
		implements RoomEditorPresenter.MyView, Editor<RoomDto> {
	private static Logger logger = Logger.getLogger(RoomEditorView.class.getName());

	interface Binder extends UiBinder<Widget, RoomEditorView> {
	}

	interface Driver extends SimpleBeanEditorDriver<RoomDto, RoomEditorView> {
	}

	private final Driver driver;

	// private final CoreConstants i18nCoreCnst;

	@UiField
	MaterialTextBox code, floor, description;

	@Ignore
	@UiField
	MaterialComboBox<RoomTypeDtor> roomTypeCombo;

	TakesValueEditor<RoomTypeDtor> roomType;

	@UiField(provided = true)
	AvailabilityListEditor roomAvailabilities;

	@UiField
	MaterialButton saveButton;

	/**
	* 
	*/
	@Inject
	RoomEditorView(Binder uiBinder, Driver driver, CoreConstants i18nCoreCnst,
			AvailabilityListEditor roomAvailabilityDtos) {
		logger.info("RoomTypeEditorView()");

		this.roomAvailabilities = roomAvailabilityDtos;

		initWidget(uiBinder.createAndBindUi(this));

		initRoomTypeCombo();

		this.driver = driver;
		driver.initialize(this);
	}

	private void initRoomTypeCombo() {

		roomType = TakesValueEditor.of(new TakesValue<RoomTypeDtor>() {

			@Override
			public void setValue(RoomTypeDtor value) {
				roomTypeCombo.setSingleValue(value);
			}

			@Override
			public RoomTypeDtor getValue() {
				return roomTypeCombo.getSingleValue();
			}
		});
	}

	@Override
	public void show(RoomDto dto) {
		// TODO Auto-generated method stub

	}

	@Override
	public void edit(RoomDto dto) {
		driver.edit(dto);

		Timer t = new Timer() {
			@Override
			public void run() {
				code.setFocus(true);
			}
		};
		t.schedule(100);
	}

	@Override
	public void displayError(EntityPropertyCode code, String message) {
		// TODO Auto-generated method stub

	}

	@UiHandler("saveButton")
	void onSaveClick(ClickEvent event) {
		RoomDto dto = driver.flush();
		getUiHandlers().save(dto);
	}

	@UiHandler("cancelButton")
	void onCancelClick(ClickEvent event) {
		getUiHandlers().cancel();
	}

	@Override
	public void setRoomTypeData(List<RoomTypeDtor> roomTypeData) {
		roomTypeCombo.clear();
		for (RoomTypeDtor roomTypeDto : roomTypeData) {
			roomTypeCombo.addItem(roomTypeDto.getCode() + " - " + roomTypeDto.getName(), roomTypeDto);
		}
	}

	@Override
	public void close() {
		// TODO Auto-generated method stub

	}
}
