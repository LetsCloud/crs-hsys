/**
 * 
 */
package io.crs.hsys.client.cfg.creator.organization;

import java.util.logging.Logger;

import javax.inject.Inject;

import com.google.gwt.editor.client.Editor;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.Widget;
import com.gwtplatform.mvp.client.ViewWithUiHandlers;

import io.crs.hsys.shared.dto.profile.OrganizationDto;

/**
 * @author robi
 *
 */
public class OrganizationCreateView extends ViewWithUiHandlers<OrganizationCreateUiHandlers>
		implements OrganizationCreatePresenter.MyView, Editor<OrganizationDto> {
	private static Logger logger = Logger.getLogger(OrganizationCreateView.class.getName());

	interface Binder extends UiBinder<Widget, OrganizationCreateView> {
	}

	@UiField
	HTMLPanel contentPanel;
	
	/**
	* 
	*/
	@Inject
	OrganizationCreateView(Binder uiBinder) {
		logger.info("OrganizationCreateView()");

		initWidget(uiBinder.createAndBindUi(this));
		
		bindSlot(OrganizationCreatePresenter.SLOT_CONTENT, contentPanel);
		
	}
}
