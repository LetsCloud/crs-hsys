/**
 * 
 */
package io.crs.hsys.client.cfg.browser.appuser;

import java.util.List;

import io.crs.hsys.client.cfg.browser.AbstractBrowserUiHandlers;
import io.crs.hsys.shared.dto.common.AppUserDto;

/**
 * @author robi
 *
 */
public interface AppUserBrowserUiHandlers extends AbstractBrowserUiHandlers<AppUserDto> {

	void inviteItem(List<AppUserDto> dtos);

	void clearFcmTokens(List<AppUserDto> dtos);
}