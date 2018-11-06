/**
 * 
 */
package io.crs.hsys.client.kip.gfilter.config;

import java.util.logging.Level;
import java.util.logging.Logger;

import javax.inject.Inject;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.gwtplatform.mvp.client.ViewWithUiHandlers;

import gwt.material.design.client.ui.MaterialDatePicker;
import gwt.material.design.client.ui.MaterialDropDown;
import gwt.material.design.client.ui.MaterialListBox;
import gwt.material.design.client.ui.MaterialDialog;

/**
 * @author CR
 *
 */
public class GfilterConfigView extends ViewWithUiHandlers<GfilterConfigUiHandlers> implements GfilterConfigPresenter.MyView {
	private static Logger logger = Logger.getLogger(GfilterConfigView.class.getName());

	interface Binder extends UiBinder<HTMLPanel, GfilterConfigView> {
	}

	@UiField
	MaterialDialog modal;

	@Inject
	GfilterConfigView(Binder binder) {
		logger.log(Level.INFO, "GfilterConfigView()");
		initWidget(binder.createAndBindUi(this));
	}

	@Override
	public void initView() {
	}

	@UiHandler("okButton")
	void btnClose(ClickEvent e) {
		modal.close();
	}

	@UiHandler("cancelButton")
	void btnSave(ClickEvent e) {
		modal.close();
	}

	@Override
	public void openModal() {
		modal.open();
	}
}