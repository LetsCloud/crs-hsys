/**
 * 
 */
package io.crs.hsys.client.core.browser.appuser;

import java.util.List;

import io.crs.hsys.client.core.ui.browser.AbstractBrowserUiHandlers;
import io.crs.hsys.shared.dto.common.AppUserDto;

/**
 * @author robi
 *
 */
public interface AppUserBrowserUiHandlers extends AbstractBrowserUiHandlers<AppUserDto> {

	void inviteItem(List<AppUserDto> dtos);

	void clearFcmTokens(List<AppUserDto> dtos);
}
