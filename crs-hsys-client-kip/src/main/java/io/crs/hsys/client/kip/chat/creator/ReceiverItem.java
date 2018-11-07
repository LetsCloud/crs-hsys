/**
 * 
 */
package io.crs.hsys.client.kip.chat.creator;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;

import gwt.material.design.client.ui.MaterialCheckBox;
import gwt.material.design.client.ui.MaterialLabel;
import io.crs.hsys.shared.dto.common.UserGroupDto;

/**
 * @author robi
 *
 */
public class ReceiverItem extends Composite {

	private static ReceiverItemUiBinder uiBinder = GWT.create(ReceiverItemUiBinder.class);

	interface ReceiverItemUiBinder extends UiBinder<Widget, ReceiverItem> {
	}
	
	@UiField
	MaterialLabel title;
	
	@UiField
	MaterialCheckBox checkBox;
	
	private UserGroupDto userGroup;

	/**
	 * 
	 */
	public ReceiverItem(UserGroupDto userGroup) {
		initWidget(uiBinder.createAndBindUi(this));
		this.userGroup = userGroup;
		init();
	}

	private void init() {
		title.setText(userGroup.getName());
	}
	
	public Boolean isSelected() {
		return checkBox.getValue();
	}
	
	public UserGroupDto getValue() {
		return userGroup;
	}
}
