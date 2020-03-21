package io.crs.hsys.client.inf.gps.display;

import javax.inject.Inject;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.gwtplatform.mvp.client.ViewWithUiHandlers;

import io.crs.hsys.client.inf.i18n.InfConstants;

public class GpsDisplayView extends ViewWithUiHandlers<GpsDisplayUiHandlers> implements GpsDisplayPresenter.MyView {

	interface Binder extends UiBinder<HTMLPanel, GpsDisplayView> {
	}

	@Inject
	GpsDisplayView(Binder binder, InfConstants cons) {
		initWidget(binder.createAndBindUi(this));
	}

	@UiHandler("gpsLink")
	void onGpsLink(ClickEvent e) {
		getUiHandlers().gpsSetup();
	}

}
