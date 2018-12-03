/**
 * 
 */
package io.crs.hsys.client.kip.roomstatus.controll;

import com.google.gwt.inject.client.assistedinject.GinFactoryModuleBuilder;
import com.gwtplatform.mvp.client.gin.AbstractPresenterModule;

/**
 * @author robi
 *
 */
public class RoomStatusControllModule extends AbstractPresenterModule {
	@Override
	protected void configure() {
		bindSingletonPresenterWidget(RoomStatusControllPresenter.class, RoomStatusControllPresenter.MyView.class,
				RoomStatusControllView.class);
		
		install(new GinFactoryModuleBuilder().build(RoomStatusControllPresenterFactory.class));
	}
}
