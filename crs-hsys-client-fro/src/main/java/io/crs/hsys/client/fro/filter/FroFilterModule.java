/**
 * 
 */
package io.crs.hsys.client.fro.filter;

import com.google.gwt.inject.client.assistedinject.GinFactoryModuleBuilder;
import com.gwtplatform.mvp.client.gin.AbstractPresenterModule;

import io.crs.hsys.client.fro.filter.createres.CreateResFilterPresenter;
import io.crs.hsys.client.fro.filter.createres.CreateResFilterView;
import io.crs.hsys.client.fro.filter.ratecode.RateCodeFilterPresenter;
import io.crs.hsys.client.fro.filter.ratecode.RateCodeFilterView;
import io.crs.hsys.client.fro.filter.ratemngr.RateMngrFilterPresenter;
import io.crs.hsys.client.fro.filter.ratemngr.RateMngrFilterView;

/**
 * @author robi
 *
 */
public class FroFilterModule extends AbstractPresenterModule {

	@Override
	protected void configure() {
		bindPresenterWidget(RateCodeFilterPresenter.class, RateCodeFilterPresenter.MyView.class,
				RateCodeFilterView.class);
		bindPresenterWidget(RateMngrFilterPresenter.class, RateMngrFilterPresenter.MyView.class,
				RateMngrFilterView.class);
		bindPresenterWidget(CreateResFilterPresenter.class, CreateResFilterPresenter.MyView.class,
				CreateResFilterView.class);
		
		install(new GinFactoryModuleBuilder().build(FroFilterFactory.class));
	}
}
