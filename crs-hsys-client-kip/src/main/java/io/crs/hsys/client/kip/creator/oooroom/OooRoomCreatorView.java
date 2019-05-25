/**
 * 
 */
package io.crs.hsys.client.kip.creator.oooroom;

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
import io.crs.hsys.client.core.message.MessageData;
import io.crs.hsys.client.core.message.dialog.MessageDialogWidget;
import io.crs.hsys.client.core.security.CurrentUser;
import io.crs.hsys.shared.cnst.OooReturnWhen;
import io.crs.hsys.shared.cnst.RoomStatus;
import io.crs.hsys.shared.dto.EntityPropertyCode;
import io.crs.hsys.shared.dto.hotel.OooCreateDto;
import io.crs.hsys.shared.dto.hotel.RoomDto;
import io.crs.hsys.shared.dto.hotel.RoomTypeDtor;

/**
 * @author CR
 *
 */
public class OooRoomCreatorView extends ViewWithUiHandlers<OooRoomCreatorUiHandlers>
		implements OooRoomCreatorPresenter.MyView, Editor<OooCreateDto> {
	private static Logger logger = Logger.getLogger(OooRoomCreatorView.class.getName());

	interface Binder extends UiBinder<Widget, OooRoomCreatorView> {
	}

	interface Driver extends SimpleBeanEditorDriver<OooCreateDto, OooRoomCreatorView> {
	}

	private final Driver driver;

	private final CoreConstants i18nCoreCnst;

	@Ignore
	@UiField
	MaterialComboBox<RoomDto> roomFilterComboBox;
	TakesValueEditor<List<RoomDto>> rooms;

	@Ignore
	@UiField
	MaterialComboBox<RoomStatus> roomStatusFilterComboBox;
	TakesValueEditor<List<RoomStatus>> roomStatuses;

	@Ignore
	@UiField
	MaterialComboBox<RoomTypeDtor> roomTypeFilterComboBox;
	TakesValueEditor<List<RoomTypeDtor>> roomTypes;

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

	@UiField
	@Ignore
	MessageDialogWidget messageDialog;

	/**
	* 
	*/
	@Inject
	OooRoomCreatorView(Binder uiBinder, Driver driver, CoreConstants i18nCoreCnst, CurrentUser currentUser) {
		logger.info("OooRoomEditorView()");

		initWidget(uiBinder.createAndBindUi(this));

		initRoomFilterComboBox();
		initRoomStatusFilterComboBox();
		initOooReturnTimeComboBox();
		initRoomStatusComboBox();
		initRoomTypeFilterComboBox();
		
		this.driver = driver;
		this.i18nCoreCnst = i18nCoreCnst;

		if (currentUser.getLocale().startsWith("hu")) {
			fromDate.setLanguage(DatePickerLanguage.HU);
			toDate.setLanguage(DatePickerLanguage.HU);
		}

		driver.initialize(this);
	}

	private void initRoomFilterComboBox() {
		rooms = TakesValueEditor.of(new TakesValue<List<RoomDto>>() {
			@Override
			public void setValue(List<RoomDto> value) {
				roomFilterComboBox.setValue(value);
			}

			@Override
			public List<RoomDto> getValue() {
				return roomFilterComboBox.getSelectedValue();
			}
		});
	}

	@Override
	public void setRoomData(List<RoomDto> data) {
		roomFilterComboBox.clear();
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
				roomFilterComboBox.addGroup(optGroup);
				typeCode = room.getRoomType().getCode();
				optGroup = new OptGroup(typeCode);
			}
			roomFilterComboBox.addItem(room.getCode() + " - " + room.getDescription(), room, optGroup);
		}
		roomFilterComboBox.addGroup(optGroup);
		roomFilterComboBox.unselect();
	}

	private void initRoomTypeFilterComboBox() {
		roomTypes = TakesValueEditor.of(new TakesValue<List<RoomTypeDtor>>() {
			@Override
			public void setValue(List<RoomTypeDtor> value) {
				roomTypeFilterComboBox.setValue(value);
			}

			@Override
			public List<RoomTypeDtor> getValue() {
				return roomTypeFilterComboBox.getSelectedValue();
			}
		});
	}

	private void initRoomStatusFilterComboBox() {
		roomStatuses = TakesValueEditor.of(new TakesValue<List<RoomStatus>>() {
			@Override
			public void setValue(List<RoomStatus> value) {
				roomStatusFilterComboBox.setValue(value);
			}

			@Override
			public List<RoomStatus> getValue() {
				return roomStatusFilterComboBox.getSelectedValue();
			}
		});
	}

	@Override
	public void setRoomStatusFilterData(RoomStatus[] data) {
		roomStatusFilterComboBox.clear();
		for (RoomStatus item : data) {
			roomStatusFilterComboBox.addItem(i18nCoreCnst.roomStatusMap().get(item.toString()), item);
		}
		roomStatusFilterComboBox.unselect();
	}

	private void initOooReturnTimeComboBox() {
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
	public void show(OooCreateDto dto) {
	}

	@Override
	public void edit(OooCreateDto dto) {
		driver.edit(dto);

		Timer t = new Timer() {
			@Override
			public void run() {
				roomFilterComboBox.setFocus(true);
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
		OooCreateDto dto = driver.flush();
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

	@Override
	public void setRoomTypeData(List<RoomTypeDtor> data) {
		roomTypeFilterComboBox.clear();
		for (RoomTypeDtor item : data) {
			roomTypeFilterComboBox.addItem(item.getCode() + " -" + item.getName(), item);
		}
		roomTypeFilterComboBox.unselect();
	}

	@Override
	public void showMessage(MessageData message) {
		messageDialog.showMessage(message);
	}
}
