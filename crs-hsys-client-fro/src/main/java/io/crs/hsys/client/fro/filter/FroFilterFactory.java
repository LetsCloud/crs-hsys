/**
 * 
 */
package io.crs.hsys.client.fro.filter;

import io.crs.hsys.client.fro.filter.createres.CreateResFilterPresenter;

/**
 * @author robi
 *
 */
public interface FroFilterFactory {

	CreateResFilterPresenter createBookingFilter();

}
