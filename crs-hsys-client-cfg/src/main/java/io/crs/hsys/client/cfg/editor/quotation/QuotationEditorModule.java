/**
 * 
 */
package io.crs.hsys.client.cfg.editor.quotation;

import com.google.gwt.inject.client.assistedinject.GinFactoryModuleBuilder;
import com.gwtplatform.mvp.client.gin.AbstractPresenterModule;

/**
 * @author robi
 *
 */
public class QuotationEditorModule extends AbstractPresenterModule {

	@Override
	protected void configure() {
		bindPresenterWidget(QuotationEditorPresenter.class, QuotationEditorPresenter.MyView.class,
				QuotationEditorView.class);

		install(new GinFactoryModuleBuilder().build(QuotationEditorFactory.class));
	}
}
