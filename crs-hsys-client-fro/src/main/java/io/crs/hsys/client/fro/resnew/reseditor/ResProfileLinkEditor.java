/**
 * 
 */
package io.crs.hsys.client.fro.resnew.reseditor;

import javax.inject.Inject;

import com.google.gwt.editor.client.Editor;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;

import io.crs.hsys.client.fro.event.DeleteEvent;
import io.crs.hsys.client.fro.event.DeleteEvent.DeleteEventHandler;
import io.crs.hsys.shared.dto.hotel.RoomAvailabilityDto;

/**
 * @author robi
 *
 */
public class ResProfileLinkEditor extends Composite implements Editor<RoomAvailabilityDto> {

	interface Binder extends UiBinder<Widget, ResProfileLinkEditor> {
	}

	/**
	 */
	@Inject
	ResProfileLinkEditor(Binder uiBinder) {
		initWidget(uiBinder.createAndBindUi(this));
	}

	public final HandlerRegistration addDeleteHandler(DeleteEventHandler handler) {
		return addHandler(handler, DeleteEvent.TYPE);
	}
}
