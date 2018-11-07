/**
 * 
 */
package io.crs.hsys.client.kip.gfilter.display;

import javax.inject.Inject;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.gwtplatform.mvp.client.ViewWithUiHandlers;

/**
 * @author CR
 *
 */
public class GfilterDisplayView extends ViewWithUiHandlers<GfilterDisplayUiHandlers>
		implements GfilterDisplayPresenter.MyView {

	interface Binder extends UiBinder<HTMLPanel, GfilterDisplayView> {
	}

	@Inject
	GfilterDisplayView(Binder binder) {
		initWidget(binder.createAndBindUi(this));
	}

	@UiHandler("gpsLink")
	void onGpsLink(ClickEvent e) {
		getUiHandlers().onConfig();
	}

}
