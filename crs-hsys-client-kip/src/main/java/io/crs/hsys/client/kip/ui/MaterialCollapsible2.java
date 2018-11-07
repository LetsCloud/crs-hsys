/**
 * 
 */
package io.crs.hsys.client.kip.ui;

import java.util.logging.Logger;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Document;
import com.google.gwt.dom.client.Element;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.user.client.ui.Widget;

import gwt.material.design.client.base.HasActiveParent;
import gwt.material.design.client.base.HasCollapsibleHandlers;
import gwt.material.design.client.base.HasNoSideNavSelection;
import gwt.material.design.client.base.HasType;
import gwt.material.design.client.base.JsLoader;
import gwt.material.design.client.base.MaterialWidget;
import gwt.material.design.client.base.mixin.CssTypeMixin;
import gwt.material.design.client.constants.CollapsibleType;
import gwt.material.design.client.constants.CssName;
import gwt.material.design.client.events.ClearActiveEvent;
import gwt.material.design.client.events.ClearActiveEvent.ClearActiveHandler;
import gwt.material.design.client.events.CollapseEvent;
import gwt.material.design.client.events.ExpandEvent;

import static gwt.material.design.client.js.JsMaterialElement.$;

/**
 * @author robi
 *
 */
public class MaterialCollapsible2 extends MaterialWidget implements JsLoader, HasType<CollapsibleType>, HasActiveParent,
		HasNoSideNavSelection, HasCollapsibleHandlers<MaterialCollapsibleItem2> {
	private static Logger logger = Logger.getLogger(MaterialCollapsible2.class.getName());

	protected interface HasCollapsibleParent {
		void setParent(MaterialCollapsible2 parent);
	}

	private int activeIndex = -1;
	private Widget activeWidget;

	private CssTypeMixin<CollapsibleType, MaterialCollapsible2> typeMixin;

	public MaterialCollapsible2() {
		super(Document.get().createULElement(), CssName.COLLAPSIBLE);

		// Items need to be added after the widget has loaded to avoid
		// premature configuration issues.
		enableFeature(Feature.ONLOAD_ADD_QUEUE, true);
	}

	public MaterialCollapsible2(final MaterialCollapsibleItem2... widgets) {
		this();

		for (final MaterialCollapsibleItem2 item : widgets) {
			add(item);
		}
	}

	@Override
	protected void onLoad() {
		super.onLoad();

		if (activeIndex != -1 && activeWidget == null) {
			setActive(activeIndex);
		}

		load();
	}

	@Override
	public void load() {
		collapsible(getElement());
	}

	@Override
	public void unload() {
	}

	@Override
	public void reload() {
		unload();
		load();
	}

	@Override
	public void add(final Widget child) {
		if (child instanceof MaterialCollapsibleItem2) {
			((MaterialCollapsibleItem2) child).setParent(this);
		}
		super.add(child);
	}

	@Override
	public boolean remove(Widget w) {
		if (w instanceof MaterialCollapsibleItem2) {
			((MaterialCollapsibleItem2) w).setParent(null);
		}
		w.removeStyleName(CssName.ACTIVE);

		return super.remove(w);
	}

	@Override
	public void clearActive() {
		clearActiveClass(this);
		ClearActiveEvent.fire(this);
	}

	/**
	 * Open the given collapsible item.
	 *
	 * @param index
	 *            the one-based collapsible item index.
	 */
	public void open(int index) {
		logger.info("MaterialCollapsible2().open("+index+")");
		setActive(index, true);
	}

	/**
	 * Close the given collapsible item.
	 *
	 * @param index
	 *            the one-based collapsible item index.
	 */
	public void close(int index) {
		logger.info("MaterialCollapsible2().close("+index+")");
		setActive(index, false);
	}

	/**
	 * Close all the collapsible items.
	 */
	public void closeAll() {
		clearActive();
		reload();
	}

	@Override
	public void setEnabled(boolean enabled) {
		getEnabledMixin().setEnabled(this, enabled);
	}

	@Override
	public void setType(CollapsibleType type) {
		getTypeMixin().setType(type);
	}

	@Override
	public CollapsibleType getType() {
		return getTypeMixin().getType();
	}

	protected void collapsible(final Element e) {
		$(e).collapsible(isAccordion());
	}

	/**
	 * Configure if you want this collapsible container to accordion its child
	 * elements or use expandable.
	 */
	public void setAccordion(boolean accordion) {
		getElement().setAttribute("data-collapsible", accordion ? CssName.ACCORDION : CssName.EXPANDABLE);
		reload();
	}

	/**
	 * Is the collapsible an 'accordion' type.
	 */
	public boolean isAccordion() {
		return getElement().getAttribute("data-collapsible").equals(CssName.ACCORDION);
	}

	@Override
	public void setActive(int index) {
		logger.info("MaterialCollapsible2().setActive("+index+")");
		clearActive();
		setActive(index, true);
	}

	@Override
	public void setActive(int index, boolean active) {
		logger.info("MaterialCollapsible2().setActive("+index+", "+active+")");
		activeIndex = index;
		if (isAttached()) {
			if (index <= getWidgetCount()) {
				if (index != 0) {
					activeWidget = getWidget(index - 1);
					if (activeWidget != null && activeWidget instanceof MaterialCollapsibleItem2) {
						((MaterialCollapsibleItem2) activeWidget).setActive(active);
						reload();
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

	@Override
	public HandlerRegistration addClearActiveHandler(final ClearActiveHandler handler) {
		return addHandler(handler, ClearActiveEvent.TYPE);
	}

	@Override
	public HandlerRegistration addCollapseHandler(CollapseEvent.CollapseHandler<MaterialCollapsibleItem2> handler) {
		return addHandler(handler, CollapseEvent.getType());
	}

	@Override
	public HandlerRegistration addExpandHandler(ExpandEvent.ExpandHandler<MaterialCollapsibleItem2> handler) {
		return addHandler(handler, ExpandEvent.getType());
	}

	protected CssTypeMixin<CollapsibleType, MaterialCollapsible2> getTypeMixin() {
		if (typeMixin == null) {
			typeMixin = new CssTypeMixin<>(this);
		}
		return typeMixin;
	}
}