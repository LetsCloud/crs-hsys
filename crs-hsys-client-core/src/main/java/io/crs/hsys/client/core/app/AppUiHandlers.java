package io.crs.hsys.client.core.app;

import com.gwtplatform.mvp.client.UiHandlers;

import io.crs.hsys.client.core.firebase.messaging.MessagingManager;

public interface AppUiHandlers extends UiHandlers {
	void logout();

	MessagingManager getMessagingManager();
}
