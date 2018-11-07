/**
 * 
 */
package io.crs.hsys.client.kip.chat.creator;

import com.google.gwt.inject.client.assistedinject.GinFactoryModuleBuilder;
import com.gwtplatform.mvp.client.gin.AbstractPresenterModule;

/**
 * @author robi
 *
 */
public class ChatCreatorModule extends AbstractPresenterModule {
	@Override
	protected void configure() {
		bindPresenterWidget(ChatCreatorPresenter.class, ChatCreatorPresenter.MyView.class, ChatCreatorView.class);

		install(new GinFactoryModuleBuilder().build(ChatCreatorFactory.class));
	}
}
