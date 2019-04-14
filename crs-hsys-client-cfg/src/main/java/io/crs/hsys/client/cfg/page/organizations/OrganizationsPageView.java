/**
 * 
 */
package io.crs.hsys.client.cfg.page.organizations;

import java.util.logging.Level;
import java.util.logging.Logger;

import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.inject.Inject;
import com.gwtplatform.mvp.client.ViewWithUiHandlers;

/**
 * @author robi
 *
 */
public class OrganizationsPageView extends ViewWithUiHandlers<OrganizationsPageUiHandlers>
		implements OrganizationsPagePresenter.MyView {
	private static Logger logger = Logger.getLogger(OrganizationsPageView.class.getName());

	private SimplePanel contentPanel = new SimplePanel();

	@Inject
	OrganizationsPageView() {
		logger.log(Level.INFO, "OrganizationsPageView()");
		initWidget(contentPanel);

		contentPanel.getElement().getStyle().setPadding(20, Unit.PX);
		
		bindSlot(OrganizationsPagePresenter.SLOT_CONTENT, contentPanel);
	}
}
