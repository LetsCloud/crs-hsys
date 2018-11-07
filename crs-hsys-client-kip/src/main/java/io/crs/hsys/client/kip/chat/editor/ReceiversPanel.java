/**
 * 
 */
package io.crs.hsys.client.kip.chat.editor;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;

import gwt.material.design.client.ui.MaterialRow;

/**
 * @author robi
 *
 */
public class ReceiversPanel extends Composite {

	private static ReceiversPanelUiBinder uiBinder = GWT.create(ReceiversPanelUiBinder.class);

	interface ReceiversPanelUiBinder extends UiBinder<Widget, ReceiversPanel> {
	}

	@UiField
	MaterialRow receiversRow, selReceiverRow;
	
	/**
	 */
	public ReceiversPanel() {
		initWidget(uiBinder.createAndBindUi(this));
		
		receiversRow.setVisible(false);
		selReceiverRow.setVisible(false);
	}

}
