/**
 * 
 */
package io.crs.hsys.client.core.ui.display;

import java.util.logging.Logger;

import com.google.web.bindery.event.shared.EventBus;
import com.gwtplatform.mvp.client.PresenterWidget;
import com.gwtplatform.mvp.client.View;

/**
 * @author robi
 *
 */
public class AbstractDisplayPresenter<V extends View> extends PresenterWidget<V>
		implements AbstractDisplayUiHandlers {
	private static Logger logger = Logger.getLogger(AbstractDisplayPresenter.class.getName());

	public AbstractDisplayPresenter(EventBus eventBus, V view) {
		super(eventBus, view);
		logger.info("AbstractDisplayPresenter()");
	}

	@Override
	protected void onReveal() {
		super.onReveal();
	}

	@Override
	public void showData(String webSafeKey) {
		// TODO Auto-generated method stub
		
	}
}
