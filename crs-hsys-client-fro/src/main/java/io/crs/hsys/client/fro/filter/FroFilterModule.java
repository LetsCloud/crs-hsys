/**
 * 
 */
package io.crs.hsys.client.fro.filter;

import com.google.gwt.inject.client.assistedinject.GinFactoryModuleBuilder;
import com.gwtplatform.mvp.client.gin.AbstractPresenterModule;

import io.crs.hsys.client.fro.filter.createres.CreateResFilterPresenter;
import io.crs.hsys.client.fro.filter.createres.CreateResFilterView;

/**
 * @author robi
 *
 */
public class FroFilterModule extends AbstractPresenterModule {

	@Override
	protected void configure() {
		bindPresenterWidget(CreateResFilterPresenter.class, CreateResFilterPresenter.MyView.class,
				CreateResFilterView.class);

		install(new GinFactoryModuleBuilder().build(FroFilterFactory.class));
	}
}
