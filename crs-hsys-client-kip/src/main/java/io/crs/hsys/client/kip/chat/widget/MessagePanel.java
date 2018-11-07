/**
 * 
 */
package io.crs.hsys.client.kip.chat.widget;

import com.google.gwt.core.client.Scheduler;
import com.google.gwt.user.client.ui.TextBox;

import gwt.material.design.client.constants.Color;
import gwt.material.design.client.constants.IconType;
import gwt.material.design.client.constants.InputType;
import gwt.material.design.client.ui.MaterialIcon;
import gwt.material.design.client.ui.MaterialValueBox;
import gwt.material.design.client.ui.html.Label;

import static gwt.material.design.jquery.client.api.JQuery.$;

/**
 * @author robi
 *
 */
public class MessagePanel extends MaterialValueBox<String> {

    private Label label = new Label();
    private MaterialIcon iconSearch = new MaterialIcon(IconType.MESSAGE);
    private MaterialIcon iconClose = new MaterialIcon(IconType.SEND);
    private boolean active;

	public MessagePanel() {
		super(new TextBox());
	}

	public MessagePanel(String placeholder) {
		this();
		setPlaceholder(placeholder);
	}

	public MessagePanel(String placeholder, Color backgroundColor, Color iconColor, boolean active, int shadow) {
		this(placeholder);
		setBackgroundColor(backgroundColor);
		setIconColor(iconColor);
		setActive(active);
		setShadow(shadow);
	}

    @Override
    protected void onLoad() {
        super.onLoad();

        setType(InputType.SEARCH);
        label.add(iconSearch);
        label.getElement().setAttribute("for", "search");
        add(label);
        add(iconClose);
        
        open();

 //       registerHandler(iconClose.addMouseDownHandler(mouseDownEvent -> CloseEvent.fire(MaterialSearch.this, getText())));
    }

    @Override
    protected void onUnload() {
        super.onUnload();

        clear();
//        setCurSel(-1);
    }
    public void open() {
        setActive(true);
        Scheduler.get().scheduleDeferred(() -> $(valueBoxBase.getElement()).focus());
 //       OpenEvent.fire(MaterialSearch.this, getText());
    }
    @Override
    public void setActive(boolean active) {
        this.active = active;
        if (active) {
            setTextColor(Color.BLACK);
            iconClose.setIconColor(Color.BLACK);
            iconSearch.setIconColor(Color.BLACK);
        } else {
            iconClose.setIconColor(Color.WHITE);
            iconSearch.setIconColor(Color.WHITE);
        }
    }

    @Override
    public boolean isActive() {
        return active;
    }

}
