/**
 * 
 */
package io.crs.hsys.client.cfg.creator.contact;

import com.gwtplatform.mvp.client.UiHandlers;

import io.crs.hsys.shared.dto.profile.ContactDto;

/**
 * @author robi
 *
 */
public interface ContactCreateUiHandlers extends UiHandlers {

	void save(ContactDto dto);
	
	void cancel();

}
