/**
 * 
 */
package io.crs.hsys.client.fro.reservation.header;

import javax.inject.Inject;

import com.google.gwt.editor.client.Editor;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;

import io.crs.hsys.shared.dto.reservation.ReservationDto;

/**
 * @author robi
 *
 */
public class ReservationEditor extends Composite implements Editor<ReservationDto> {

	interface Binder extends UiBinder<Widget, ReservationEditor> {
	}

	/**
	 */
	@Inject
	ReservationEditor(Binder uiBinder) {
		initWidget(uiBinder.createAndBindUi(this));
	}

}
