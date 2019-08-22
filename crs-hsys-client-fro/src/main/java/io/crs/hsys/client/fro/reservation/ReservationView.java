/**
 * 
 */
package io.crs.hsys.client.fro.reservation;

import javax.inject.Inject;

import io.crs.hsys.client.core.config.AbstractConfigView;

/**
 * @author robi
 *
 */
public class ReservationView extends AbstractConfigView implements ReservationPresenter.MyView {

	@Inject
	ReservationView() {
		super();
	}
}
