/**
 * 
 */
package io.crs.hsys.client.inf.gps.config;

import com.google.gwt.inject.client.assistedinject.GinFactoryModuleBuilder;
import com.gwtplatform.mvp.client.gin.AbstractPresenterModule;

/**
 * @author CR
 *
 */
public class GpsConfigModule extends AbstractPresenterModule {
    @Override
    protected void configure() {
        bindSingletonPresenterWidget(GpsConfigPresenter.class, GpsConfigPresenter.MyView.class, GpsConfigView.class);

        install(new GinFactoryModuleBuilder().build(GpsConfigWidgetsFactory.class));
    }

}
