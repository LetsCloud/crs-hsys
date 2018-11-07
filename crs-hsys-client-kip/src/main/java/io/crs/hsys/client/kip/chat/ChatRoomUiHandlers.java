/**
 * 
 */
package io.crs.hsys.client.kip.chat;

import com.gwtplatform.mvp.client.UiHandlers;

import gwt.material.design.client.base.MaterialWidget;
import io.crs.hsys.client.core.app.AppServiceWorkerManager;
import io.crs.hsys.client.core.firebase.messaging.MessagingManager;

/**
 * @author robi
 *
 */
public interface ChatRoomUiHandlers extends UiHandlers {

	AppServiceWorkerManager getServiceWorkerManager();

	MessagingManager getMessagingManager();
	
	void createChat(MaterialWidget source);
	
	void subToServer(String iidToken);
}
