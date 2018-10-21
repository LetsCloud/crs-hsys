/**
 * 
 */
package io.crs.hsys.client.cfg.editor.hotel;

import java.util.logging.Logger;

import javax.inject.Inject;

import com.google.gwt.editor.client.Editor;
import com.google.gwt.editor.client.SimpleBeanEditorDriver;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.ui.Widget;
import com.google.web.bindery.event.shared.EventBus;
import com.gwtplatform.mvp.client.ViewWithUiHandlers;

import gwt.material.design.client.ui.MaterialTextBox;
import io.crs.hsys.shared.dto.EntityPropertyCode;
import io.crs.hsys.shared.dto.hotel.HotelDto;

/**
 * @author robi
 *
 */
public class HotelEditorView extends ViewWithUiHandlers<HotelEditorUiHandlers>
		implements HotelEditorPresenter.MyView, Editor<HotelDto> {
	private static Logger logger = Logger.getLogger(HotelEditorView.class.getName());

	interface Binder extends UiBinder<Widget, HotelEditorView> {
	}

	interface Driver extends SimpleBeanEditorDriver<HotelDto, HotelEditorView> {
	}

	private final Driver driver;

	@UiField
	MaterialTextBox code, name;

	/**
	 * 
	 */
	@Inject
	HotelEditorView(Binder uiBinder, Driver driver, EventBus eventBus) {
		logger.info("AppUserEditorView()");

		initWidget(uiBinder.createAndBindUi(this));

		this.driver = driver;
		driver.initialize(this);
	}

	@Override
	public void show(HotelDto dto) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void edit(HotelDto dto) {
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
		HotelDto dto = driver.flush();
		getUiHandlers().save(dto);
	}

	@UiHandler("cancelButton")
	void onCancelClick(ClickEvent ignored) {
		getUiHandlers().cancel();
	}

	@Override
	public void close() {
		// TODO Auto-generated method stub
		
	}
}
