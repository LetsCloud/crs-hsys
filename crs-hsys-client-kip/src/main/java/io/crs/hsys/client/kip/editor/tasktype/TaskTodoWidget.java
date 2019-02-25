/**
 * 
 */
package io.crs.hsys.client.kip.editor.tasktype;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;

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

	/**
	 */
	public TaskTodoWidget() {
		initWidget(uiBinder.createAndBindUi(this));
	}

	public TaskTodoWidget(String title) {
		this();
		label.setText(title);
	}

}
