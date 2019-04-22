/**
 * 
 */
package io.crs.hsys.client.cfg.editor.quotation;

import com.gwtplatform.mvp.client.gin.AbstractPresenterModule;

/**
 * @author robi
 *
 */
public class QuotationEditorModule extends AbstractPresenterModule {

	@Override
	protected void configure() {
		bindPresenter(QuotationEditorPresenter.class, QuotationEditorPresenter.MyView.class, QuotationEditorView.class,
				QuotationEditorPresenter.MyProxy.class);
	}
}
