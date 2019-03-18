package io.crs.hsys.client.kip.roomstatus;

import com.gwtplatform.mvp.client.UiHandlers;

import io.crs.hsys.shared.dto.hk.RoomStatusDto;

public interface RoomStatusUiHandlers extends UiHandlers {
	void onEdit(String webSafeKey);
}
