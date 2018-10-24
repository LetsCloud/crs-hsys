/**
 * 
 */
package io.crs.hsys.client.cfg.creator.contact;

import java.util.logging.Logger;

import javax.inject.Inject;

import com.google.gwt.editor.client.Editor;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.Widget;
import com.gwtplatform.mvp.client.ViewWithUiHandlers;

import io.crs.hsys.shared.dto.profile.ContactDto;

/**
 * @author robi
 *
 */
public class ContactCreateView extends ViewWithUiHandlers<ContactCreateUiHandlers>
		implements ContactCreatePresenter.MyView, Editor<ContactDto> {
	private static Logger logger = Logger.getLogger(ContactCreateView.class.getName());

	interface Binder extends UiBinder<Widget, ContactCreateView> {
	}

	@UiField
	HTMLPanel contentPanel;

	/**
	* 
	*/
	@Inject
	ContactCreateView(Binder uiBinder) {
		logger.info("ContactCreateView()");

		initWidget(uiBinder.createAndBindUi(this));

		bindSlot(ContactCreatePresenter.SLOT_CONTENT, contentPanel);

	}
}
