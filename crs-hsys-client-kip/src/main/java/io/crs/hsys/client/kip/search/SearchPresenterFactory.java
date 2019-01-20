/**
 * 
 */
package io.crs.hsys.client.kip.search;

import io.crs.hsys.client.kip.search.roomstatus.RoomStatusSearchPresenter;

/**
 * @author robi
 *
 */
public interface SearchPresenterFactory {

	RoomStatusSearchPresenter createRoomStatusSearch();

}
