/**
 * 
 */
package io.crs.hsys.client.kip.chat.list;

import com.google.gwt.inject.client.assistedinject.GinFactoryModuleBuilder;
import com.gwtplatform.mvp.client.gin.AbstractPresenterModule;

/**
 * @author robi
 *
 */
public class ChatListModule extends AbstractPresenterModule {
	@Override
	protected void configure() {
		bindPresenterWidget(ChatListPresenter.class, ChatListPresenter.MyView.class, ChatListView.class);

		install(new GinFactoryModuleBuilder().build(ChatListFactory.class));
	}
}
