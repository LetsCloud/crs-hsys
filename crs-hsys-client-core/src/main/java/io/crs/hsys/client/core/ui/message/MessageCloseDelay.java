/**
 * 
 */
package io.crs.hsys.client.core.ui.message;

/**
 * @author CR
 *
 */
public enum MessageCloseDelay {
    NEVER(0),
    DEFAULT(5000);

    private int delay;

    private MessageCloseDelay(int delay) {
        this.delay = delay;
    }

    public int getDelay() {
        return delay;
    }
}