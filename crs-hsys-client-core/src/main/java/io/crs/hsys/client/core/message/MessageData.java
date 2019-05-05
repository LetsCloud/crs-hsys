/**
 * 
 */
package io.crs.hsys.client.core.message;

import java.util.ArrayList;
import java.util.List;

/**
 * @author CR
 *
 */
public class MessageData {
	private final String title;
	private final String description;
	private final MessageStyle style;
	private final MessageCloseDelay closeDelay;

	private List<MessageButtonConfig> buttonConfigs = new ArrayList<MessageButtonConfig>();

	public MessageData(MessageStyle style, String title, String description) {
		this(style, title, description, MessageCloseDelay.DEFAULT);
	}

	public MessageData(MessageStyle style, String title, String description, MessageCloseDelay closeDelay) {
		this.title = title;
		this.description = description;
		this.style = style;
		this.closeDelay = closeDelay;
	}

	public MessageStyle getStyle() {
		return style;
	}

	public String getTitle() {
		return title;
	}

	public String getDescription() {
		return description;
	}

	public MessageCloseDelay getCloseDelay() {
		return closeDelay;
	}

	public List<MessageButtonConfig> getBttonConfigs() {
		return buttonConfigs;
	}

	public void setBttonConfigs(List<MessageButtonConfig> buttonConfigs) {
		this.buttonConfigs = buttonConfigs;
	}

	public void addBttonConfig(MessageButtonConfig buttonConfig) {
		this.buttonConfigs.add(buttonConfig);
	}

}