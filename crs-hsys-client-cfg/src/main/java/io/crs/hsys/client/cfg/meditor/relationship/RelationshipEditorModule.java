/**
 * 
 */
package io.crs.hsys.client.cfg.meditor.relationship;

import com.google.gwt.inject.client.assistedinject.GinFactoryModuleBuilder;
import com.gwtplatform.mvp.client.gin.AbstractPresenterModule;

/**
 * @author robi
 *
 */
public class RelationshipEditorModule extends AbstractPresenterModule {
	@Override
	protected void configure() {

		bindPresenterWidget(RelationshipEditorPresenter.class, RelationshipEditorPresenter.MyView.class,
				RelationshipEditorView.class);

		install(new GinFactoryModuleBuilder().build(RelationshipEditorFactory.class));
	}
}
