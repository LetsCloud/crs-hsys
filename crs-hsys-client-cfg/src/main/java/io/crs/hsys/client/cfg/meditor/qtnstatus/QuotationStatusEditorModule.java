/**
 * 
 */
package io.crs.hsys.client.cfg.meditor.qtnstatus;

import com.google.gwt.inject.client.assistedinject.GinFactoryModuleBuilder;
import com.gwtplatform.mvp.client.gin.AbstractPresenterModule;

/**
 * @author robi
 *
 */
public class QuotationStatusEditorModule extends AbstractPresenterModule {
	@Override
	protected void configure() {
		bindPresenterWidget(QuotationStatusEditorPresenter.class, QuotationStatusEditorPresenter.MyView.class,
				QuotationStatusEditorView.class);

		install(new GinFactoryModuleBuilder().build(QuotationStatusEditorFactory.class));
	}
}
