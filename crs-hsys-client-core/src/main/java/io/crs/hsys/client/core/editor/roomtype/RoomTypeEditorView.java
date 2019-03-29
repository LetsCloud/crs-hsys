/**
 * 
 */
package io.crs.hsys.client.core.editor.roomtype;

import java.util.Arrays;
import java.util.Map;
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
import gwt.material.design.client.ui.MaterialCheckBox;
import gwt.material.design.client.ui.MaterialFloatBox;
import gwt.material.design.client.ui.MaterialIntegerBox;
import gwt.material.design.client.ui.MaterialTextBox;
import io.crs.hsys.client.core.i18n.CoreConstants;
import io.crs.hsys.shared.cnst.InventoryType;
import io.crs.hsys.shared.dto.EntityPropertyCode;
import io.crs.hsys.shared.dto.hotel.RoomTypeDto;

/**
 * @author robi
 *
 */
public class RoomTypeEditorView extends ViewWithUiHandlers<RoomTypeEditorUiHandlers>
		implements RoomTypeEditorPresenter.MyView, Editor<RoomTypeDto> {
	private static Logger logger = Logger.getLogger(RoomTypeEditorView.class.getName());

	interface Binder extends UiBinder<Widget, RoomTypeEditorView> {
	}

	interface Driver extends SimpleBeanEditorDriver<RoomTypeDto, RoomTypeEditorView> {
	}

	private final Driver driver;

	// private final CoreConstants i18nCoreCnst;

	@UiField
	MaterialTextBox code, name, description;

	@UiField
	MaterialIntegerBox beds, xtrBeds;

	@UiField
	MaterialFloatBox cleaningFactor;

	@UiField
	MaterialCheckBox active;

	@UiField
	@Ignore
	MaterialComboBox<InventoryType> inventoryTypeCombo;
	TakesValueEditor<InventoryType> inventoryType;

	@UiField
	MaterialButton saveButton;

	/**
	* 
	*/
	@Inject
	RoomTypeEditorView(Binder uiBinder, Driver driver, CoreConstants i18nCoreCnst) {
		logger.info("RoomTypeEditorView()");

		initWidget(uiBinder.createAndBindUi(this));

		inventoryType = TakesValueEditor.of(new TakesValue<InventoryType>() {

			@Override
			public void setValue(InventoryType value) {
				inventoryTypeCombo.setSingleValue(value);
			}

			@Override
			public InventoryType getValue() {
				return inventoryTypeCombo.getSingleValue();
			}
		});

		// saveButton.setBackgroundColor(Color.GREY);

		initInventoryTypeCombo(i18nCoreCnst.inventoryTypeMap());

		this.driver = driver;
		driver.initialize(this);
	}

	private void initInventoryTypeCombo(Map<String, String> i18nSalesTypes) {
		Arrays.asList(InventoryType.values())
				.forEach(st -> inventoryTypeCombo.addItem(i18nSalesTypes.get(st.toString()), st));
	}

	@Override
	public void show(RoomTypeDto dto) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void edit(RoomTypeDto dto) {
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
		RoomTypeDto dto = driver.flush();
		logger.info("RoomTypeEditorView().onSaveClick()-" + dto);
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
