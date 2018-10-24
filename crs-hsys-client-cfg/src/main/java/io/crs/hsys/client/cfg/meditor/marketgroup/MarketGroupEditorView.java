/**
 * 
 */
package io.crs.hsys.client.cfg.meditor.marketgroup;

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
import com.gwtplatform.mvp.client.ViewWithUiHandlers;

import gwt.material.design.client.ui.MaterialButton;
import gwt.material.design.client.ui.MaterialCheckBox;
import gwt.material.design.client.ui.MaterialDialog;
import gwt.material.design.client.ui.MaterialTextBox;
import io.crs.hsys.client.core.i18n.CoreConstants;
import io.crs.hsys.shared.dto.EntityPropertyCode;
import io.crs.hsys.shared.dto.hotel.MarketGroupDto;

/**
 * @author robi
 *
 */
public class MarketGroupEditorView extends ViewWithUiHandlers<MarketGroupEditorUiHandlers>
		implements MarketGroupEditorPresenter.MyView, Editor<MarketGroupDto> {
	private static Logger logger = Logger.getLogger(MarketGroupEditorView.class.getName());

	interface Binder extends UiBinder<Widget, MarketGroupEditorView> {
	}

	interface Driver extends SimpleBeanEditorDriver<MarketGroupDto, MarketGroupEditorView> {
	}

	private final Driver driver;

// private final CoreConstants i18nCoreCnst;

	@UiField
	MaterialDialog modal;

	@UiField
	MaterialTextBox code, description;

	@UiField
	MaterialCheckBox active;

	@UiField
	MaterialButton saveButton;

	/**
	* 
	*/
	@Inject
	MarketGroupEditorView(Binder uiBinder, Driver driver, CoreConstants i18nCoreCnst) {
		logger.info("RoomTypeEditorView()");

		initWidget(uiBinder.createAndBindUi(this));

// saveButton.setBackgroundColor(Color.GREY);

		this.driver = driver;
		driver.initialize(this);
	}

	@Override
	public void open(MarketGroupDto dto) {
		driver.edit(dto);

		modal.open();

		Timer t = new Timer() {
			@Override
			public void run() {
				code.setFocus(true);
			}
		};
		t.schedule(100);
	}

	@Override
	public void close() {
		modal.close();
	}

	@Override
	public void displayError(EntityPropertyCode code, String message) {
// TODO Auto-generated method stub

	}

	@UiHandler("saveButton")
	void onSaveClick(ClickEvent event) {
		getUiHandlers().save(driver.flush());
	}

	@UiHandler("cancelButton")
	void onCancelClick(ClickEvent event) {
		getUiHandlers().cancel();
	}
}
