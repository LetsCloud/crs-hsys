/**
 * 
 */
package io.crs.hsys.client.inf.dashboard.widget;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.inject.Inject;

import com.google.inject.assistedinject.Assisted;
import com.google.web.bindery.event.shared.EventBus;
import com.gwtplatform.mvp.client.PresenterWidget;
import com.gwtplatform.mvp.client.View;

import io.crs.hsys.shared.dto.cube.DataWidgetValueM1Dto;
import io.crs.hsys.shared.dto.cube.dw.DataWidgetConfigDto;

/**
 * @author CR
 *
 */
public class DataWidgetPresenter extends PresenterWidget<DataWidgetPresenter.DataWidgetView> implements DataWidget {
	private static Logger logger = Logger.getLogger(DataWidgetPresenter.class.getName());

	public interface DataWidgetView extends View {
		void setConfig(DataWidgetConfigDto config);

		void setValues(List<DataWidgetValueM1Dto> values);

		void setHeight(String height1, String height2);

		void setWidth(String height1, String height2);

		void removeFromParent();
	}

	private DataWidgetConfigDto config;

	@Inject
	public DataWidgetPresenter(EventBus eventBus, @Assisted DataWidgetPresenter.DataWidgetView view,
			@Assisted DataWidgetConfigDto config) {
		super(eventBus, view);
		logger.log(Level.INFO, "DataWidgetPresenter()");
		this.config = config;
		initView();
	}

	private void initView() {
		getView().setConfig(config);
	}

	@Override
	public DataWidgetConfigDto getConfig() {
		return config;
	}

	@Override
	public void setValues(List<DataWidgetValueM1Dto> values) {
		getView().setValues(values);
	}
}
