/**
 * 
 */
package io.crs.hsys.client.core.ui.message.ui;

import io.crs.hsys.client.core.ui.message.Message;

/**
 * @author CR
 *
 */
public interface MessageWidgetFactory {
	MessageWidget createMessage(Message message);
}