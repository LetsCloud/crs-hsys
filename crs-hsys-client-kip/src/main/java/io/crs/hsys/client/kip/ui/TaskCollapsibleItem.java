/**
 * 
 */
package io.crs.hsys.client.kip.ui;

import java.util.logging.Logger;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Document;
import com.google.gwt.user.client.ui.Widget;

import gwt.material.design.client.base.HasActive;
import gwt.material.design.client.base.MaterialWidget;
import gwt.material.design.client.constants.CssName;
import gwt.material.design.client.constants.Display;
import io.crs.hsys.client.kip.ui.TaskCollapsible.HasCollapsibleParent;

/**
 * @author robi
 *
 */
public class TaskCollapsibleItem extends MaterialWidget implements HasCollapsibleParent, HasActive {
	private static Logger logger = Logger.getLogger(TaskCollapsibleItem.class.getName());

	private boolean active;
	private TaskCollapsible parent;
	protected TaskCollapsibleHeader header;
	protected TaskCollapsibleBody body;

	public TaskCollapsibleItem() {
		super(Document.get().createLIElement());
	}

	@Override
	public void add(Widget child) {
		if (child instanceof TaskCollapsibleBody) {
			body = (TaskCollapsibleBody) child;
		} else if (child instanceof TaskCollapsibleHeader) {
			header = (TaskCollapsibleHeader) child;
			// logger.info("TaskCollapsibleItem().add()->(child instanceof
			// MaterialCollapsibleHeader)");
			// header.addClickHandler(clickEvent -> fireCollapsibleHandler());
		}
		super.add(child);
	}

	@Override
	public void setParent(TaskCollapsible parent) {
		this.parent = parent;

		for (Widget child : this) {
			if (child instanceof HasCollapsibleParent) {
				((HasCollapsibleParent) child).setParent(parent);
			}
		}
	}

	@Override
	public void setActive(boolean active) {
		logger.info("TaskCollapsibleItem().setActive(" + active + ")");
		this.active = active;

		if (parent != null) {
			removeStyleName(CssName.ACTIVE);
			if (header != null) {
				header.removeStyleName(CssName.ACTIVE);
			}
			if (active) {
				if (parent != null) {
					parent.clearActive();
				}
				addStyleName(CssName.ACTIVE);

				if (header != null) {
					header.addStyleName(CssName.ACTIVE);
				}
			}

			if (body != null) {
				body.setDisplay(active ? Display.BLOCK : Display.NONE);
			}
		} else {
			GWT.log("Please make sure that the Collapsible parent is attached or existed.",
					new IllegalStateException());
		}
	}

	@Override
	public boolean isActive() {
		return getElement().hasClassName(CssName.ACTIVE);
	}

	/**
	 * Expand the body panel.
	 */
	public void expand() {
		logger.info("TaskCollapsibleItem().expand()");
		if (body != null) {
			logger.info("TaskCollapsibleItem().expand()->(body != null)");
			setActive(true);
		}
	}

	/**
	 * Collapse the cody panel.
	 */
	public void collapse() {
		logger.info("TaskCollapsibleItem().collapse()");
		if (body != null) {
			logger.info("TaskCollapsibleItem().collapse()->(body != null)");
			setActive(false);
		}
	}

	protected void clearActive() {
	}
}
