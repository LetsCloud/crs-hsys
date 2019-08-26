/**
 * 
 */
package io.crs.hsys.client.fro.filter;

import io.crs.hsys.client.fro.filter.createres.CreateResFilterPresenter;
import io.crs.hsys.client.fro.filter.ratecode.RateCodeFilterPresenter;

/**
 * @author robi
 *
 */
public interface FroFilterFactory {

	RateCodeFilterPresenter createRateCodeFilter();

	CreateResFilterPresenter createBookingFilter();

}
