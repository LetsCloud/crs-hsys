/**
 * 
 */
package io.crs.hsys.client.kip.editor.tasktype.todo;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;

import gwt.material.design.client.ui.MaterialCheckBox;
import gwt.material.design.client.ui.MaterialCollectionSecondary;
import gwt.material.design.client.ui.MaterialLabel;

/**
 * @author robi
 *
 */
public class TaskTodoWidget extends Composite {

	private static TaskTodoWidgetUiBinder uiBinder = GWT.create(TaskTodoWidgetUiBinder.class);

	interface TaskTodoWidgetUiBinder extends UiBinder<Widget, TaskTodoWidget> {
	}

	@UiField
	MaterialLabel label;

	@UiField
	MaterialCheckBox checkBox;

	@UiField
	MaterialCollectionSecondary mcs;
	
	/**
	 */
	public TaskTodoWidget() {
		initWidget(uiBinder.createAndBindUi(this));
	}

	public TaskTodoWidget(String title) {
		this();
		label.setText(title);
	}

	public MaterialCheckBox getCheckBox() {
		return checkBox;
	}

	public MaterialCollectionSecondary getMcs() {
		return mcs;
	}
}
