/**
 * 
 */
package io.crs.hsys.client.kip.task.creator;

import java.util.List;

import com.gwtplatform.mvp.client.UiHandlers;

import io.crs.hsys.shared.dto.common.AppUserDto;

/**
 * @author robi
 *
 */
public interface TaskCreatorUiHandlers extends UiHandlers {
	void saveChat(List<AppUserDto> receivers, String message);
}
