/**
 * 
 */
package io.crs.hsys.client.cfg.meditor.marketgroup;

import com.google.gwt.inject.client.assistedinject.GinFactoryModuleBuilder;
import com.gwtplatform.mvp.client.gin.AbstractPresenterModule;

/**
 * @author robi
 *
 */
public class MarketGroupEditorModule extends AbstractPresenterModule {
	@Override
	protected void configure() {
		bindPresenterWidget(MarketGroupEditorPresenter.class, MarketGroupEditorPresenter.MyView.class,
				MarketGroupEditorView.class);

		install(new GinFactoryModuleBuilder().build(MarketGroupEditorFactory.class));
	}
}
