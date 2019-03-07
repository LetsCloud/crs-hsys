/**
 * 
 */
package io.crs.hsys.client.kip.tasks;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;

import gwt.material.design.client.ui.MaterialButton;
import gwt.material.design.client.ui.MaterialCheckBox;
import gwt.material.design.client.ui.MaterialCollapsibleBody;
import gwt.material.design.client.ui.MaterialCollapsibleHeader;
import gwt.material.design.client.ui.MaterialCollapsibleItem;
import gwt.material.design.client.ui.MaterialToast;

/**
 * @author robi
 *
 */
public class TaskWidget extends Composite {

	private static TaskWidgetUiBinder uiBinder = GWT.create(TaskWidgetUiBinder.class);

	interface TaskWidgetUiBinder extends UiBinder<Widget, TaskWidget> {
	}

	@UiField
	MaterialCollapsibleItem item;
	
	@UiField
	MaterialCollapsibleHeader header;
	
	@UiField
	MaterialCollapsibleBody body;

	@UiField
	MaterialCheckBox title;
	
	/**
	 */
	public TaskWidget() {
		initWidget(uiBinder.createAndBindUi(this));
	}
	
	@UiHandler("header")
	public void onHeaderClick(ClickEvent event) {
		header.setVisible(false);
		MaterialButton b = new MaterialButton("Close");
		b.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				header.setVisible(true);
				body.clear();
				item.collapse();
			}
		});
		body.add(b);
	}
	
	@UiHandler("title")
	public void onTitleClick(ClickEvent event) {
		MaterialToast.fireToast("I Love Material Design");
	}

}
