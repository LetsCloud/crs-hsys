/**
 * 
 */
package io.crs.hsys.client.fro.reservation;

import io.crs.hsys.client.fro.reservation.extra.ExtraResPresenter;
import io.crs.hsys.client.fro.reservation.guest.GuestResPresenter;
import io.crs.hsys.client.fro.reservation.header.MainResPresenter;
import io.crs.hsys.client.fro.reservation.room.RoomResPresenter;

/**
 * @author robi
 *
 */
public interface ResWidgetPresenterFactory {
	MainResPresenter createMainResPresenter();

	RoomResPresenter createRoomResPresenter();

	ExtraResPresenter createExtraResPresenter();

	GuestResPresenter createGuestResPresenter();
}
