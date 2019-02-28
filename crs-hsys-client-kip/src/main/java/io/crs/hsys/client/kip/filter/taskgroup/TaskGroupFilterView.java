/**
 * 
 */
package io.crs.hsys.client.kip.filter.taskgroup;

import java.util.logging.Logger;

import javax.inject.Inject;

import io.crs.hsys.client.core.filter.hotelchild.HotelChildFilterView;
import io.crs.hsys.client.core.i18n.CoreConstants;
import io.crs.hsys.client.core.i18n.CoreMessages;

/**
 * @author robi
 *
 */
public class TaskGroupFilterView extends AbstractTaskGroupFilterView implements TaskGroupFilterPresenter.MyView {
	private static Logger logger = Logger.getLogger(HotelChildFilterView.class.getName());

	@Inject
	TaskGroupFilterView(CoreMessages i18nCore, CoreConstants cnstCore) {
		super(i18nCore, cnstCore);
		logger.info("TaskGroupFilterView()");
	}
}
