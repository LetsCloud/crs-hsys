/**
 * 
 */
package io.crs.hsys.client.cfg.config.hotel;

import javax.inject.Inject;

import io.crs.hsys.client.cfg.config.AbstractConfigView;

/**
 * @author robi
 *
 */
public class HotelConfigView extends AbstractConfigView implements HotelConfigPresenter.MyView {

	@Inject
	HotelConfigView() {
		super();
	}
}