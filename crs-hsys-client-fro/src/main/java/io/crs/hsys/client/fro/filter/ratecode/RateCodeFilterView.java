/**
 * 
 */
package io.crs.hsys.client.fro.filter.ratecode;

import java.util.logging.Logger;

import javax.inject.Inject;

import io.crs.hsys.client.core.filter.hotelchild.AbstractHotelChildFilterView;
import io.crs.hsys.client.core.i18n.CoreConstants;
import io.crs.hsys.client.core.i18n.CoreMessages;

/**
 * @author robi
 *
 */
public class RateCodeFilterView extends AbstractHotelChildFilterView implements RateCodeFilterPresenter.MyView {
	private static Logger logger = Logger.getLogger(RateCodeFilterView.class.getName());

	@Inject
	RateCodeFilterView(CoreMessages i18nCore, CoreConstants cnstCore) {
		super(i18nCore, cnstCore);
	}

	@Override
	protected void createLayout() {
		hotelComboBox.setGrid("s12 m6");
		controlPanel.add(hotelComboBox);

		onlyActiveCheckBox.setGrid("s12 m6");
		controlPanel.add(onlyActiveCheckBox);
	}

	@Override
	protected void initView() {
		super.initView();
	}

	@Override
	public void reset() {
		logger.info("RateCodeFilterView().reset()");
	}
}
