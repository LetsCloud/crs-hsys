/**
 * 
 */
package io.crs.hsys.client.kip.ui;

import java.util.logging.Logger;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Document;
import com.google.gwt.user.client.ui.Widget;

import gwt.material.design.client.base.HasActiveParent;
import gwt.material.design.client.base.MaterialWidget;
import gwt.material.design.client.events.ClearActiveEvent;
import io.crs.hsys.client.kip.resources.KipGssResources;

/**
 * @author robi
 *
 */
public class TaskCollapsible extends MaterialWidget implements HasActiveParent {
	private static Logger logger = Logger.getLogger(TaskCollapsible.class.getName());

	protected interface HasCollapsibleParent {
		void setParent(TaskCollapsible parent);
	}

	private int activeIndex = -1;
	private Widget activeWidget;

	public TaskCollapsible(KipGssResources kipGss) {
		super(Document.get().createULElement(), kipGss.taskStyle().task_collapsible());
	}

	@Override
	public void add(final Widget child) {
		if (child instanceof TaskCollapsibleItem) {
			((TaskCollapsibleItem) child).setParent(this);
		}
		super.add(child);
	}

	@Override
	public boolean remove(Widget w) {
		if (w instanceof TaskCollapsibleItem) {
			((TaskCollapsibleItem) w).setParent(null);
		}
		// w.removeStyleName(CssName.ACTIVE);

		return super.remove(w);
	}

	@Override
	public void clearActive() {
		logger.info("TaskCollapsible().clearActive()");
		for (Widget child: this) {
			if (child instanceof TaskCollapsibleItem) {
				TaskCollapsibleItem tci = (TaskCollapsibleItem) child;
				tci.clearActive();
			}
		}
		clearActiveClass(this);
	}

	@Override
	public void setActive(int index) {
		logger.info("TaskCollapsible().setActive(" + index + ")");
		clearActive();
		setActive(index, true);
	}

	@Override
	public void setActive(int index, boolean active) {
		logger.info("TaskCollapsible().setActive(" + index + ", " + active + ")");
		activeIndex = index;
		if (isAttached()) {
			if (index <= getWidgetCount()) {
				if (index != 0) {
					activeWidget = getWidget(index - 1);
					if (activeWidget != null && activeWidget instanceof TaskCollapsibleItem) {
						((TaskCollapsibleItem) activeWidget).setActive(active);
						// reload();
					}
				} else {
					GWT.log("The active index must be a one-base index to mark as active.",
							new IndexOutOfBoundsException());
				}
			}
		}
	}

	@Override
	public Widget getActive() {
		try {
			return activeWidget;
		} catch (IndexOutOfBoundsException ex) {
			return null;
		}
	}
}
