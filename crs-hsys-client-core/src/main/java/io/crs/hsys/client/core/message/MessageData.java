/**
 * 
 */
package io.crs.hsys.client.core.message;

/**
 * @author CR
 *
 */
public class MessageData {
    private final String title;
    private final String description;
    private final MessageStyle style;
    private final MessageCloseDelay closeDelay;

    public MessageData(
            MessageStyle style,
            String title,
            String description) {
        this(style, title, description, MessageCloseDelay.DEFAULT);
    }

    public MessageData(
            MessageStyle style,
            String title,
            String description,
            MessageCloseDelay closeDelay) {
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
}