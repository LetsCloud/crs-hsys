/**
 * 
 */
package io.crs.hsys.client.kip.ui;

import java.util.logging.Logger;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Document;
import com.google.gwt.dom.client.Element;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.Widget;

import gwt.material.design.client.base.AbstractButton;
import gwt.material.design.client.base.HasActive;
import gwt.material.design.client.base.HasProgress;
import gwt.material.design.client.base.mixin.ProgressMixin;
import gwt.material.design.client.constants.CssName;
import gwt.material.design.client.constants.Display;
import gwt.material.design.client.constants.ProgressType;
import gwt.material.design.client.constants.WavesType;
import gwt.material.design.client.events.CollapseEvent;
import gwt.material.design.client.events.ExpandEvent;
import gwt.material.design.client.ui.MaterialCollapsibleBody;
import gwt.material.design.client.ui.MaterialCollapsibleHeader;
import gwt.material.design.client.ui.MaterialProgress;
import io.crs.hsys.client.kip.task.TaskWidget2;
import io.crs.hsys.client.kip.ui.MaterialCollapsible2.HasCollapsibleParent;

/**
 * @author robi
 *
 */
public class MaterialCollapsibleItem2 extends AbstractButton
		implements HasWidgets, HasCollapsibleParent, HasProgress, HasActive {
	private static Logger logger = Logger.getLogger(TaskWidget2.class.getName());

	private boolean active;
	private MaterialCollapsible2 parent;
	private MaterialCollapsibleBody body;
	private MaterialCollapsibleHeader header;

	private ProgressMixin<MaterialCollapsibleItem2> progressMixin;

	/**
	 * Creates an empty collapsible item.
	 */
	public MaterialCollapsibleItem2() {
		super();
	}

	/**
	 * Adds MaterialCollapsible contents.
	 */
	public MaterialCollapsibleItem2(final Widget... widgets) {
		this();
		for (Widget w : widgets) {
			add(w);
		}
	}

	@Override
	protected Element createElement() {
		return Document.get().createLIElement();
	}

	@Override
	public void add(Widget child) {
		if (child instanceof MaterialCollapsibleBody) {
			body = (MaterialCollapsibleBody) child;
		} else if (child instanceof MaterialCollapsibleHeader) {
			header = (MaterialCollapsibleHeader) child;
			logger.info("MaterialCollapsibleItem2().add()->(child instanceof MaterialCollapsibleHeader)");
//			header.addClickHandler(clickEvent -> fireCollapsibleHandler());
		}
		super.add(child);
	}

	@Override
	public boolean remove(Widget w) {
		if (w instanceof HasCollapsibleParent) {
			((HasCollapsibleParent) w).setParent(null);
		}

		if (w.equals(body)) {
			body = null;
		} else if (w.equals(header)) {
			header = null;
		}
		return super.remove(w);
	}

	/**
	 * Expand the body panel.
	 */
	public void expand() {
		logger.info("MaterialCollapsibleItem2().expand()");
		if (body != null) {
			logger.info("MaterialCollapsibleItem2().expand()->(body != null)");
			setActive(true);
		}
	}

	/**
	 * Collapse the cody panel.
	 */
	public void collapse() {
		logger.info("MaterialCollapsibleItem2().expand()");
		if (body != null) {
			logger.info("MaterialCollapsibleItem2().expand()->(body != null)");
			setActive(false);
		}
	}

	@Override
	public void showProgress(ProgressType type) {
		getProgressMixin().showProgress(type);
	}

	@Override
	public void setPercent(double percent) {
		getProgressMixin().setPercent(percent);
	}

	@Override
	public void hideProgress() {
		getProgressMixin().hideProgress();
	}

	@Override
	public MaterialProgress getProgress() {
		return getProgressMixin().getProgress();
	}

	/**
	 * Make this item active.
	 */
	@Override
	public void setActive(boolean active) {
		logger.info("MaterialCollapsibleItem2().setActive(" + active + ")");
		this.active = active;

		if (parent != null) {
			fireCollapsibleHandler();
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

	protected void fireCollapsibleHandler() {
		logger.info("MaterialCollapsibleItem2().fireCollapsibleHandler()");
		if (getElement().hasClassName(CssName.ACTIVE)) {
			parent.fireEvent(new CollapseEvent<>(this));
		} else {
			parent.fireEvent(new ExpandEvent<>(this));
		}
	}

	@Override
	public boolean isActive() {
		return active;
	}

	public void setParent(MaterialCollapsible2 parent) {
		this.parent = parent;

		for (Widget child : this) {
			if (child instanceof HasCollapsibleParent) {
				((HasCollapsibleParent) child).setParent(parent);
			}
		}
	}

	@Override
	public void setWaves(WavesType waves) {
		super.setWaves(waves);

		setDisplay(Display.BLOCK);
	}

	public MaterialCollapsibleBody getBody() {
		return body;
	}

	public MaterialCollapsibleHeader getHeader() {
		return header;
	}

	public void setBody(MaterialCollapsibleBody body) {
		this.body = body;
	}

	public void setHeader(MaterialCollapsibleHeader header) {
		this.header = header;
	}

	protected ProgressMixin<MaterialCollapsibleItem2> getProgressMixin() {
		if (progressMixin == null) {
			progressMixin = new ProgressMixin<>(this);
		}
		return progressMixin;
	}
}
