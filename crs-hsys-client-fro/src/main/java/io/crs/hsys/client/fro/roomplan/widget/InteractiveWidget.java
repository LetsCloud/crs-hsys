/**
 * 
 */
package io.crs.hsys.client.fro.roomplan.widget;
import com.google.gwt.dom.client.Element;
import com.google.gwt.dom.client.Style.Position;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.event.dom.client.KeyDownEvent;
import com.google.gwt.event.dom.client.KeyDownHandler;
import com.google.gwt.event.dom.client.KeyPressEvent;
import com.google.gwt.event.dom.client.KeyPressHandler;
import com.google.gwt.event.dom.client.KeyUpEvent;
import com.google.gwt.event.dom.client.KeyUpHandler;
import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.Event;
import com.google.gwt.user.client.ui.ComplexPanel;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.FocusPanel;
import com.google.gwt.user.client.ui.RootPanel;

/**
 * Abstract class for widgets that react to keystrokes and mouse gestures
 * providing a centralized place for the association between user inputs and the
 * logic to perform. Subclasses will implement the <code>onXXXKeyPressed</code>,
 * <code>onMouseDown</code> and <code>onDoubleClick</code> methods to provide
 * the custom event processing logic.
 *
 * @author Brad Rydzewski
 */
public abstract class InteractiveWidget extends Composite {

    /**
     * Focus widget used to add keyboard and mouse focus to a calendar.
     */
    private FocusPanel focusPanel = new FocusPanel();

    /**
     * Main panel hold all other components.
     */
    protected FlowPanel rootPanel = new FlowPanel();

    /**
     * Used by focus widget to make sure a key stroke is only handled once by
     * the calendar.
     */
    private boolean lastWasKeyDown = false;

    public InteractiveWidget() {

        initWidget(rootPanel);

        //Sink events, mouse and keyboard for now
        sinkEvents(Event.ONMOUSEDOWN | Event.ONDBLCLICK | Event.KEYEVENTS | Event.ONMOUSEOVER);

        hideFocusPanel();

        //Add key handler events to the focus panel
        focusPanel.addKeyPressHandler(new KeyPressHandler() {
            public void onKeyPress(KeyPressEvent event) {
                if (!lastWasKeyDown) {
                    keyboardNavigation(event.getNativeEvent().getKeyCode());
                }
                lastWasKeyDown = false;
            }
        });

        focusPanel.addKeyUpHandler(new KeyUpHandler() {
            public void onKeyUp(KeyUpEvent event) {
                lastWasKeyDown = false;
            }
        });
        focusPanel.addKeyDownHandler(new KeyDownHandler() {
            public void onKeyDown(KeyDownEvent event) {
                keyboardNavigation(event.getNativeEvent().getKeyCode());
                lastWasKeyDown = true;
            }
        });
    }

    /**
     * Makes the widget's focus panel invisible.
     */
    private void hideFocusPanel() {
    	/*
    	 * Careful: Seems that making the focusPanel too big is blocking the keyevents
    	 */
        RootPanel.get().add(focusPanel);
        focusPanel.getElement().getStyle().setPosition(Position.ABSOLUTE);
        focusPanel.getElement().getStyle().setTop(0, Unit.PX);
        focusPanel.getElement().getStyle().setLeft(-10, Unit.PX);
        focusPanel.getElement().getStyle().setHeight(100, Unit.PCT);
        focusPanel.getElement().getStyle().setWidth(0, Unit.PX);
    }

    public ComplexPanel getRootPanel() {
        return rootPanel;
    }

    /**
     * Processes mouse double-click events. Concrete interactive widgets should
     * provide the component's specific logic.
     *
     * @param element The HTML DOM element that originated the event
     */
    public abstract void onDoubleClick(Element element, Event event);

    /**
     * Processes mouse over events. Concrete interactive widgets
     * should provide the component's specific logic.
     * 
     * @param element The HTML DOM element that originated the event
     * @param event The HTML DOM event that was triggered
     */
    public abstract void onMouseOver(Element element, Event event);
    
    /**
     * Processes mouse button pressing events. Concrete interactive widgets
     * should provide the component's specific logic.
     *
     * @param element The HTML DOM element that originated the event
     */
    public abstract void onMouseDown(Element element, Event event);

    /**
     * Processes {@link com.google.gwt.event.dom.client.KeyCodes.KEY_DELETE}
     * and {@link com.google.gwt.event.dom.client.KeyCodes.KEY_BACKSPACE}
     * keystrokes. Concrete interactive widgets should provide the component's
     * specific logic.
     */
    public abstract void onDeleteKeyPressed();

    /**
     * Processes {@link com.google.gwt.event.dom.client.KeyCodes.KEY_UP}
     * keystrokes. Concrete interactive widgets should provide the component's
     * specific logic.
     */
    public abstract void onUpArrowKeyPressed();

    /**
     * Processes {@link com.google.gwt.event.dom.client.KeyCodes.KEY_DOWN}
     * keystrokes. Concrete interactive widgets should provide the component's
     * specific logic.
     */
    public abstract void onDownArrowKeyPressed();

    /**
     * Processes {@link com.google.gwt.event.dom.client.KeyCodes.KEY_LEFT}
     * keystrokes. Concrete interactive widgets should provide the component's
     * specific logic.
     */
    public abstract void onLeftArrowKeyPressed();

    /**
     * Processes {@link com.google.gwt.event.dom.client.KeyCodes.KEY_RIGHT}
     * keystrokes. Concrete interactive widgets should provide the component's
     * specific logic.
     */
    public abstract void onRightArrowKeyPressed();

    @Override
    public void onBrowserEvent(Event event) {
//        int eventType = DOM.eventGetType(event);
        int eventType = event.getTypeInt();
        Element element = DOM.eventGetTarget(event);

        switch (eventType) {
            case Event.ONDBLCLICK: {
                onDoubleClick(element, event);
                focusPanel.setFocus(true);
                break;
            }
            case Event.ONMOUSEDOWN: {
                if (DOM.eventGetCurrentTarget(event) == getElement()) {

                    onMouseDown(element, event);
                    focusPanel.setFocus(true);
                    //Cancel events so Firefox / Chrome don't
                    //give child widgets with scrollbars focus.
                    //TODO: Should not cancel onMouseDown events in the event an appointment would have a child widget with a scrollbar (if this would ever even happen).
 //                   DOM.eventCancelBubble(event, true);
//            	    event.preventDefault();
            	    event.stopPropagation();
                    return;
                }
            }
            case Event.ONMOUSEOVER:{
            	if (DOM.eventGetCurrentTarget(event) == getElement()){
            		onMouseOver(element, event);
//            	    DOM.eventCancelBubble(event, true);
//            	    event.preventDefault();
            	    event.stopPropagation();
            	    return;
            	}
            }
        }

        super.onBrowserEvent(event);
    }

    /**
     * Dispatches the processing of a key being pressed to the this widget
     * <code>onXXXXKeyPressed</code> methods.
     *
     * @param key Pressed key code
     */
    protected void keyboardNavigation(int key) {
        switch (key) {
        	case KeyCodes.KEY_BACKSPACE: {
                onDeleteKeyPressed();
                break;        		
        	}
            case KeyCodes.KEY_DELETE: {
                onDeleteKeyPressed();
                break;
            }
            case KeyCodes.KEY_LEFT: {
                onLeftArrowKeyPressed();
                break;
            }
            case KeyCodes.KEY_UP: {
                onUpArrowKeyPressed();
                break;
            }
            case KeyCodes.KEY_RIGHT: {
                onRightArrowKeyPressed();
                break;
            }
            case KeyCodes.KEY_DOWN: {
                onDownArrowKeyPressed();
                break;
            }
        }
    }
}