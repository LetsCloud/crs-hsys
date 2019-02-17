/**
 * 
 */
package io.crs.hsys.client.cfg.editor.profile.contact;

import io.crs.hsys.client.core.editor.AbstractEditorUiHandlers;
import io.crs.hsys.shared.dto.profile.ContactDto;

/**
 * @author robi
 *
 */
public interface ContactEditorUiHandlers extends AbstractEditorUiHandlers<ContactDto> {
	
	void edit(String name);
	
	void cancel(String name);

}
