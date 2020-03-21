/**
 * 
 */
package io.crs.hsys.client.fro.filter;

import io.crs.hsys.client.fro.filter.createres.CreateResFilterPresenter;
import io.crs.hsys.client.fro.filter.ratecode.RateCodeFilterPresenter;
import io.crs.hsys.client.fro.filter.ratemngr.RateMngrFilterPresenter;

/**
 * @author robi
 *
 */
public interface FroFilterFactory {

	RateCodeFilterPresenter createRateCodeFilter();

	RateMngrFilterPresenter createRateMngrFilter();

	CreateResFilterPresenter createBookingFilter();

}
