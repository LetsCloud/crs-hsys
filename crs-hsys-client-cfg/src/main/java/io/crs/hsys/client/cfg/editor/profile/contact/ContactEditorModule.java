/**
 * 
 */
package io.crs.hsys.client.cfg.editor.profile.contact;

import com.google.gwt.inject.client.assistedinject.GinFactoryModuleBuilder;
import com.gwtplatform.mvp.client.gin.AbstractPresenterModule;

/**
 * @author robi
 *
 */
public class ContactEditorModule extends AbstractPresenterModule {

	@Override
	protected void configure() {
		bindPresenterWidget(ContactEditorPresenter.class, ContactEditorPresenter.MyView.class, ContactEditorView.class);

		install(new GinFactoryModuleBuilder().build(ContactEditorFactory.class));
	}
}
