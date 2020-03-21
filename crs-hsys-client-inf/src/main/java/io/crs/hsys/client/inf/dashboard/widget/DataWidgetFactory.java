/**
 * 
 */
package io.crs.hsys.client.inf.dashboard.widget;

import io.crs.hsys.shared.dto.cube.dw.DataWidgetConfigDto;

/**
 * @author CR
 *
 */
public interface DataWidgetFactory {
	DataWidgetPresenter create(DataWidgetPresenter.DataWidgetView view, DataWidgetConfigDto configDto);
}
