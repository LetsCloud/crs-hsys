/**
 * 
 */
package io.crs.hsys.client.cfg.editor.profile;

import com.gwtplatform.mvp.client.gin.AbstractPresenterModule;

import io.crs.hsys.client.cfg.editor.profile.contact.ContactEditorModule;
import io.crs.hsys.client.cfg.editor.profile.organization.OrganizationEditorModule;

/**
 * @author robi
 *
 */
public class ProfileEditorModule extends AbstractPresenterModule {

	@Override
	protected void configure() {
		bind(CommunicationEditor.class);		
		bind(CommunicationListEditor.class);

		bind(AddressEditor.class);		
		bind(AddressListEditor.class);

		bind(WebPresenceEditor.class);		
		bind(WebPresenceListEditor.class);

		bind(ProfileLinkEditor.class);		
		bind(ProfileLinkListEditor.class);

		install(new OrganizationEditorModule());
		install(new ContactEditorModule());
	}
}
