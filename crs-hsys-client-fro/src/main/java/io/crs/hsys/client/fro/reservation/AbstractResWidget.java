/**
 * 
 */
package io.crs.hsys.client.fro.reservation;

import com.google.web.bindery.event.shared.EventBus;
import com.gwtplatform.mvp.client.PresenterWidget;
import com.gwtplatform.mvp.client.View;

/**
 * @author robi
 *
 */
public abstract class AbstractResWidget<V extends View> extends PresenterWidget<V> {

	private String title;
	
	public AbstractResWidget(EventBus eventBus, V view) {
		super(eventBus, view);
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
}
