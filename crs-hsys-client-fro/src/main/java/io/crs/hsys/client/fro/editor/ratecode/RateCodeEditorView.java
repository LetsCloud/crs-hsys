/**
 * 
 */
package io.crs.hsys.client.fro.editor.ratecode;

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

import gwt.material.design.client.ui.MaterialTextBox;
import io.crs.hsys.client.core.editor.room.AvailabilityListEditor;
import io.crs.hsys.client.core.i18n.CoreConstants;
import io.crs.hsys.shared.dto.hotel.RateCodeDto;

/**
 * @author robi
 *
 */
public class RateCodeEditorView extends ViewWithUiHandlers<RateCodeEditorUiHandlers>
		implements RateCodeEditorPresenter.MyView, Editor<RateCodeDto> {
	private static Logger logger = Logger.getLogger(RateCodeEditorView.class.getName());

	interface Binder extends UiBinder<Widget, RateCodeEditorView> {
	}

	interface Driver extends SimpleBeanEditorDriver<RateCodeDto, RateCodeEditorView> {
	}

	private final Driver driver;

// private final CoreConstants i18nCoreCnst;

	@UiField
	MaterialTextBox code, description;


	/**
	* 
	*/
	@Inject
	RateCodeEditorView(Binder uiBinder, Driver driver, CoreConstants i18nCoreCnst,
			AvailabilityListEditor roomAvailabilityDtos) {
		logger.info("RoomTypeEditorView()");

		initWidget(uiBinder.createAndBindUi(this));

		this.driver = driver;
		driver.initialize(this);
	}

	@Override
	public void show(RateCodeDto dto) {
// TODO Auto-generated method stub

	}

	@Override
	public void edit(RateCodeDto dto) {
		driver.edit(dto);

		Timer t = new Timer() {
			@Override
			public void run() {
				code.setFocus(true);
			}
		};
		t.schedule(100);
	}

	@UiHandler("saveButton")
	void onSaveClick(ClickEvent event) {
		RateCodeDto dto = driver.flush();
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
