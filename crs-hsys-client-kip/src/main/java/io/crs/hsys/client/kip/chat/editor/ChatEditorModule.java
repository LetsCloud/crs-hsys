/**
 * 
 */
package io.crs.hsys.client.kip.chat.editor;

import com.google.gwt.inject.client.assistedinject.GinFactoryModuleBuilder;
import com.gwtplatform.mvp.client.gin.AbstractPresenterModule;

/**
 * @author robi
 *
 */
public class ChatEditorModule extends AbstractPresenterModule {
	@Override
	protected void configure() {
		bindPresenterWidget(ChatEditorPresenter.class, ChatEditorPresenter.MyView.class, ChatEditorView.class);

		install(new GinFactoryModuleBuilder().build(ChatEditorFactory.class));
	}
}
