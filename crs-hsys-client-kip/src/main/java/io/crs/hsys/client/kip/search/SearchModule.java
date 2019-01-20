/**
 * 
 */
package io.crs.hsys.client.kip.search;

import com.google.gwt.inject.client.assistedinject.GinFactoryModuleBuilder;
import com.gwtplatform.mvp.client.gin.AbstractPresenterModule;

import io.crs.hsys.client.kip.search.roomstatus.RoomStatusSearchPresenter;
import io.crs.hsys.client.kip.search.roomstatus.RoomStatusSearchView;

/**
 * @author robi
 *
 */
public class SearchModule extends AbstractPresenterModule {

	@Override
	protected void configure() {
		bindPresenterWidget(RoomStatusSearchPresenter.class, RoomStatusSearchPresenter.MyView.class,
				RoomStatusSearchView.class);

		install(new GinFactoryModuleBuilder().build(SearchPresenterFactory.class));
	}
}
