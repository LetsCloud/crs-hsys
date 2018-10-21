/**
 * 
 */
package io.crs.hsys.client.cfg.browser.relationship;

import com.google.gwt.inject.client.assistedinject.GinFactoryModuleBuilder;
import com.gwtplatform.mvp.client.gin.AbstractPresenterModule;

import io.crs.hsys.client.cfg.meditor.profilegroup.ProfileGroupEditorModule;
import io.crs.hsys.client.cfg.meditor.relationship.RelationshipEditorModule;

/**
 * @author robi
 *
 */
public class RelationshipBrowserModule extends AbstractPresenterModule {
	@Override
	protected void configure() {

		install(new RelationshipEditorModule());

		bindPresenterWidget(RelationshipBrowserPresenter.class, RelationshipBrowserPresenter.MyView.class,
				RelationshipBrowserView.class);

		install(new GinFactoryModuleBuilder().build(RelationshipBrowserFactory.class));
	}
}
