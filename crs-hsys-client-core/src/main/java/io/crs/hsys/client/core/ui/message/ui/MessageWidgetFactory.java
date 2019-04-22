/**
 * 
 */
package io.crs.hsys.client.core.ui.message.ui;

import io.crs.hsys.client.core.message.MessageData;

/**
 * @author CR
 *
 */
public interface MessageWidgetFactory {
	MessageWidget createMessage(MessageData message);
}