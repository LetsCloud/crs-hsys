package io.crs.hsys.client.core.app;

import java.util.logging.Logger;

import javax.inject.Inject;

import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.Widget;
import com.gwtplatform.mvp.client.ViewImpl;

import gwt.material.design.client.ui.MaterialLabel;
import gwt.material.design.client.ui.MaterialRow;

public class AppView extends ViewImpl implements AbstractAppPresenter.MyView {
	private static Logger logger = Logger.getLogger(AppView.class.getName());

	public interface Binder extends UiBinder<Widget, AppView> {
	}

	@UiField
	MaterialRow main;

	@UiField
	HTMLPanel menu;

	@UiField
	MaterialLabel title, description;

	@UiField
	SimplePanel modalSlot;

	@Inject
	AppView(Binder uiBinder) {
		logger.info("ApplicationView()");
		initWidget(uiBinder.createAndBindUi(this));

		bindSlot(AbstractAppPresenter.SLOT_MENU, menu);
		bindSlot(AbstractAppPresenter.SLOT_MAIN, main);
		bindSlot(AbstractAppPresenter.SLOT_MODAL, modalSlot);
	}

	@Override
	public void displayUserName(String userName) {
		// TODO Auto-generated method stub

	}

	@Override
	public void setPageTitle(String title, String description) {
		this.title.setText(title);
		this.description.setText(description);
	}
}
