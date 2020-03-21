/**
 * 
 */
package io.crs.hsys.client.inf.dashboard.widget;

import java.util.List;

import io.crs.hsys.shared.dto.cube.DataWidgetValueM1Dto;
import io.crs.hsys.shared.dto.cube.dw.DataWidgetConfigDto;

/**
 * @author CR
 *
 */
public interface DataWidget {

	DataWidgetConfigDto getConfig();

	DataWidgetPresenter.DataWidgetView getView();

	void setValues(List<DataWidgetValueM1Dto> values);
}
