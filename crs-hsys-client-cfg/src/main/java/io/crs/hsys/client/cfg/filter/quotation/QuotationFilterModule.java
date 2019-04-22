/**
 * 
 */
package io.crs.hsys.client.cfg.filter.quotation;

import com.google.gwt.inject.client.assistedinject.GinFactoryModuleBuilder;
import com.gwtplatform.mvp.client.gin.AbstractPresenterModule;

/**
 * @author robi
 *
 */
public class QuotationFilterModule extends AbstractPresenterModule {

	@Override
	protected void configure() {
		bindPresenterWidget(QuotationFilterPresenter.class, QuotationFilterPresenter.MyView.class,
				QuotationFilterView.class);

		install(new GinFactoryModuleBuilder().build(QuotationFilterPresenterFactory.class));
	}

}
