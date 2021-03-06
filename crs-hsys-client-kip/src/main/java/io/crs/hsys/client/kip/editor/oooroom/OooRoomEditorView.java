/**
 * 
 */
package io.crs.hsys.client.kip.editor.oooroom;

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
import gwt.material.design.client.constants.DatePickerLanguage;
import gwt.material.design.client.ui.MaterialDatePicker;
import gwt.material.design.client.ui.MaterialTextArea;
import gwt.material.design.client.ui.html.OptGroup;

import io.crs.hsys.client.core.i18n.CoreConstants;
import io.crs.hsys.client.core.security.CurrentUser;
import io.crs.hsys.shared.cnst.OooReturnWhen;
import io.crs.hsys.shared.cnst.RoomStatus;
import io.crs.hsys.shared.dto.EntityPropertyCode;
import io.crs.hsys.shared.dto.hotel.OooRoomDto;
import io.crs.hsys.shared.dto.hotel.RoomDto;

/**
 * @author CR
 *
 */
public class OooRoomEditorView extends ViewWithUiHandlers<OooRoomEditorUiHandlers>
		implements OooRoomEditorPresenter.MyView, Editor<OooRoomDto> {
	private static Logger logger = Logger.getLogger(OooRoomEditorView.class.getName());

	interface Binder extends UiBinder<Widget, OooRoomEditorView> {
	}

	interface Driver extends SimpleBeanEditorDriver<OooRoomDto, OooRoomEditorView> {
	}

	private final Driver driver;

	private final CoreConstants i18nCoreCnst;

	@Ignore
	@UiField
	MaterialComboBox<RoomDto> roomComboBox;
	TakesValueEditor<RoomDto> room;

	@UiField
	MaterialDatePicker fromDate;

	@UiField
	MaterialDatePicker toDate;

	@Ignore
	@UiField
	MaterialComboBox<RoomStatus> roomStatusComboBox;
	TakesValueEditor<RoomStatus> returnAs;

	@Ignore
	@UiField
	MaterialComboBox<OooReturnWhen> returnWhenComboBox;
	TakesValueEditor<OooReturnWhen> returnWhen;

	@UiField
	MaterialTextArea remarks;

	/**
	* 
	*/
	@Inject
	OooRoomEditorView(Binder uiBinder, Driver driver, CoreConstants i18nCoreCnst, CurrentUser currentUser) {
		logger.info("OooRoomEditorView()");

		initWidget(uiBinder.createAndBindUi(this));

		initRoomComboBox();
		initOooReturnWhenComboBox();
		initRoomStatusComboBox();

		this.driver = driver;
		this.i18nCoreCnst = i18nCoreCnst;

		if (currentUser.getLocale().startsWith("hu")) {
			fromDate.setLanguage(DatePickerLanguage.HU);
			toDate.setLanguage(DatePickerLanguage.HU);
		}

		driver.initialize(this);
	}

	private void initRoomComboBox() {
		room = TakesValueEditor.of(new TakesValue<RoomDto>() {
			@Override
			public void setValue(RoomDto value) {
				roomComboBox.setSingleValue(value);
			}

			@Override
			public RoomDto getValue() {
				return roomComboBox.getSingleValue();
			}
		});
	}

	@Override
	public void setRoomData(List<RoomDto> data) {
		roomComboBox.clear();
		if ((data == null) || (data.isEmpty()))
			return;

		data.sort((RoomDto o1, RoomDto o2) -> {
			int value = o1.getRoomType().getCode().compareTo(o2.getRoomType().getCode());
			if (value == 0) {
				return o1.getCode().compareTo(o2.getCode());
			}
			return value;
		});

		String typeCode = data.get(0).getRoomType().getCode();
		OptGroup optGroup = new OptGroup(typeCode);
		for (RoomDto room : data) {
			if (!room.getRoomType().getCode().equals(typeCode)) {
				roomComboBox.addGroup(optGroup);
				typeCode = room.getRoomType().getCode();
				optGroup = new OptGroup(typeCode);
			}
			roomComboBox.addItem(room.getCode() + " - " + room.getDescription(), room, optGroup);
		}
		roomComboBox.addGroup(optGroup);
		roomComboBox.unselect();
	}

	private void initOooReturnWhenComboBox() {
		returnWhen = TakesValueEditor.of(new TakesValue<OooReturnWhen>() {
			@Override
			public void setValue(OooReturnWhen value) {
				returnWhenComboBox.setSingleValue(value);
			}

			@Override
			public OooReturnWhen getValue() {
				return returnWhenComboBox.getSingleValue();
			}
		});
	}

	@Override
	public void setOooReturnTimeData(OooReturnWhen[] data) {
		returnWhenComboBox.clear();
		for (OooReturnWhen item : data) {
			returnWhenComboBox.addItem(i18nCoreCnst.oooReturnWhenMap().get(item.toString()), item);
		}
		returnWhenComboBox.unselect();
	}

	private void initRoomStatusComboBox() {
		returnAs = TakesValueEditor.of(new TakesValue<RoomStatus>() {
			@Override
			public void setValue(RoomStatus value) {
				roomStatusComboBox.setSingleValue(value);
			}

			@Override
			public RoomStatus getValue() {
				return roomStatusComboBox.getSingleValue();
			}
		});
	}

	@Override
	public void setRoomStatusData(RoomStatus[] data) {
		roomStatusComboBox.clear();
		for (RoomStatus item : data) {
			roomStatusComboBox.addItem(i18nCoreCnst.roomStatusMap().get(item.toString()), item);
		}
		roomStatusComboBox.unselect();
	}

	@Override
	public void show(OooRoomDto dto) {
	}

	@Override
	public void edit(OooRoomDto dto) {
		driver.edit(dto);

		Timer t = new Timer() {
			@Override
			public void run() {
				roomComboBox.setFocus(true);
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
		OooRoomDto dto = driver.flush();
		getUiHandlers().save(dto);
	}

	@UiHandler("cancelButton")
	void onCancelClick(ClickEvent event) {
		getUiHandlers().cancel();
	}

	@Override
	public void close() {
		// TODO Auto-generated method stub
	}
}
