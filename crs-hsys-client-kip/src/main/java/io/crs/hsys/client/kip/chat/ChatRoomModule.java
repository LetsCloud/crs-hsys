/**
 * 
 */
package io.crs.hsys.client.kip.chat;

import com.gwtplatform.mvp.client.gin.AbstractPresenterModule;

/**
 * @author robi
 *
 */
public class ChatRoomModule extends AbstractPresenterModule {
	@Override
	protected void configure() {
		bindPresenter(ChatRoomPresenter.class, ChatRoomPresenter.MyView.class, ChatRoomView.class,
				ChatRoomPresenter.MyProxy.class);
	}
}
