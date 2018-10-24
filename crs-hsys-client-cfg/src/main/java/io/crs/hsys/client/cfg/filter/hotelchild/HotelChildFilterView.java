/**
 * 
 */
package io.crs.hsys.client.cfg.filter.hotelchild;

import java.util.logging.Logger;

import javax.inject.Inject;

import io.crs.hsys.client.core.i18n.CoreConstants;
import io.crs.hsys.client.core.i18n.CoreMessages;

/**
 * @author robi
 *
 */
public class HotelChildFilterView extends AbstractHotelChildFilterView implements HotelChildFilterPresenter.MyView {
	private static Logger logger = Logger.getLogger(HotelChildFilterView.class.getName());

	@Inject
	HotelChildFilterView(CoreMessages i18nCore, CoreConstants cnstCore) {
		super(i18nCore, cnstCore);
		logger.info("HotelChildFilterView()");
	}
}
