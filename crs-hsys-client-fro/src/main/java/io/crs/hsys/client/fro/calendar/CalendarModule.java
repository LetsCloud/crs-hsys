/**
 * 
 */
package io.crs.hsys.client.fro.calendar;

import com.gwtplatform.mvp.client.gin.AbstractPresenterModule;

/**
 * @author robi
 *
 */
public class CalendarModule extends AbstractPresenterModule {
	@Override
	protected void configure() {
		bindPresenter(CalendarPresenter.class, CalendarPresenter.MyView.class, CalendarView.class,
				CalendarPresenter.MyProxy.class);
	}
}
