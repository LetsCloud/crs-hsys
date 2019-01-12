/**
 * 
 */
package io.crs.hsys.client.fro.resnew.reseditor;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;

/**
 * @author robi
 *
 */
public class ResEditor extends Composite {

	private static ResEditorUiBinder uiBinder = GWT.create(ResEditorUiBinder.class);

	interface ResEditorUiBinder extends UiBinder<Widget, ResEditor> {
	}

	/**
	 */
	public ResEditor() {
		initWidget(uiBinder.createAndBindUi(this));
	}

}
