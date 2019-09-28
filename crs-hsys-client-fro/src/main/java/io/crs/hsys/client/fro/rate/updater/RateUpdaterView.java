/**
 * 
 */
package io.crs.hsys.client.fro.rate.updater;

import java.util.List;
import java.util.logging.Logger;

import javax.inject.Inject;

import com.google.gwt.editor.client.Editor;
import com.google.gwt.editor.client.SimpleBeanEditorDriver;
import com.google.gwt.editor.client.adapters.TakesValueEditor;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.TakesValue;
import com.google.gwt.user.client.ui.Widget;
import com.gwtplatform.mvp.client.ViewWithUiHandlers;

import gwt.material.design.addins.client.combobox.MaterialComboBox;
import gwt.material.design.client.ui.MaterialRow;
import io.crs.hsys.shared.dto.hotel.RoomTypeDtor;
import io.crs.hsys.shared.dto.rate.RateCodeDtor;
import io.crs.hsys.shared.dto.rate.update.RoomRateUpdateDto;

/**
 * @author robi
 *
 */
public class RateUpdaterView extends ViewWithUiHandlers<RateUpdaterUiHandlers>
		implements RateUpdaterPresenter.MyView, Editor<RoomRateUpdateDto> {
	private static Logger logger = Logger.getLogger(RateUpdaterView.class.getName());

	interface Binder extends UiBinder<Widget, RateUpdaterView> {
	}

	interface Driver extends SimpleBeanEditorDriver<RoomRateUpdateDto, RateUpdaterView> {
	}

	private final Driver driver;

	@UiField
	MaterialRow contentPanel;

	@Ignore
	@UiField
	MaterialComboBox<RateCodeDtor> rateCodeCombo;
	TakesValueEditor<RateCodeDtor> rateCode;

	@Ignore
	@UiField
	MaterialComboBox<RoomTypeDtor> roomTypeCombo;
	TakesValueEditor<List<RoomTypeDtor>> roomTypes;

	/**
	* 
	*/
	@Inject
	RateUpdaterView(Binder uiBinder, Driver driver) {
		logger.info("RateUpdaterView()");
		initWidget(uiBinder.createAndBindUi(this));

		initRateCodeCombo();
		initRoomTypeCombo();

		this.driver = driver;
		driver.initialize(this);
	}

	private void initRateCodeCombo() {

		rateCode = TakesValueEditor.of(new TakesValue<RateCodeDtor>() {

			@Override
			public void setValue(RateCodeDtor value) {
				rateCodeCombo.setSingleValue(value);
			}

			@Override
			public RateCodeDtor getValue() {
				return rateCodeCombo.getSingleValue();
			}
		});
	}

	private void initRoomTypeCombo() {

		roomTypes = TakesValueEditor.of(new TakesValue<List<RoomTypeDtor>>() {

			@Override
			public void setValue(List<RoomTypeDtor> value) {
				roomTypeCombo.setValue(value);
			}

			@Override
			public List<RoomTypeDtor> getValue() {
				return roomTypeCombo.getValue();
			}
		});
	}

	@Override
	public void setRateCodeData(List<RateCodeDtor> data) {
		rateCodeCombo.clear();
		for (RateCodeDtor dto : data) {
			rateCodeCombo.addItem(dto.getCode() + " - " + dto.getDescription(), dto);
		}
	}

	@Override
	public void setRoomTypeData(List<RoomTypeDtor> data) {
		roomTypeCombo.clear();
		for (RoomTypeDtor roomTypeDto : data) {
			roomTypeCombo.addItem(roomTypeDto.getCode() + " - " + roomTypeDto.getName(), roomTypeDto);
		}
	}
}
