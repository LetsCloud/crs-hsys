/**
 * 
 */
package io.crs.hsys.client.cfg.creator.organization;

import com.gwtplatform.mvp.client.UiHandlers;

import io.crs.hsys.shared.dto.profile.OrganizationDto;

/**
 * @author robi
 *
 */
public interface OrganizationCreateUiHandlers extends UiHandlers {

	void save(OrganizationDto dto);
	
	void cancel();

}
