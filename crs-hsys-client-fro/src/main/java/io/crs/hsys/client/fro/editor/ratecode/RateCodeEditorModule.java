/**
 * 
 */
package io.crs.hsys.client.fro.editor.ratecode;

import com.gwtplatform.mvp.client.gin.AbstractPresenterModule;

/**
 * @author robi
 *
 */
public class RateCodeEditorModule extends AbstractPresenterModule {

	@Override
	protected void configure() {
		bindPresenter(RateCodeEditorPresenter.class, RateCodeEditorPresenter.MyView.class, RateCodeEditorView.class,
				RateCodeEditorPresenter.MyProxy.class);
	}
}
