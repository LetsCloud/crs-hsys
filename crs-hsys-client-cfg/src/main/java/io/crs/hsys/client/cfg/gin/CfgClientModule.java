/**
 * 
 */
package io.crs.hsys.client.cfg.gin;

import javax.inject.Singleton;

import com.gwtplatform.mvp.client.gin.AbstractPresenterModule;

import io.crs.hsys.client.cfg.CfgAppModule;
import io.crs.hsys.client.cfg.browser.quotation.QuotationBrowserModule;
import io.crs.hsys.client.cfg.config.doc.DocConfigModule;
import io.crs.hsys.client.cfg.config.profile.ProfileConfigModule;
import io.crs.hsys.client.cfg.creator.contact.ContactCreateModule;
import io.crs.hsys.client.cfg.creator.organization.OrganizationCreateModule;
import io.crs.hsys.client.cfg.dashboard.DashboardModule;
import io.crs.hsys.client.cfg.display.contact.ContactConfigModule;
import io.crs.hsys.client.cfg.display.organization.OrganizationConfigModule;
import io.crs.hsys.client.cfg.editor.quotation.QuotationEditorModule;
import io.crs.hsys.client.core.gin.CoreModule;
import io.crs.hsys.client.core.resources.ThemeParams;

/**
 * @author CR
 *
 */
public class CfgClientModule extends AbstractPresenterModule {

	@Override
	protected void configure() {
		install(new CoreModule());

		bind(CfgResourceLoader.class).asEagerSingleton();
		bind(ThemeParams.class).to(CfgThemeParams.class).in(Singleton.class);

		install(new CfgAppModule());

		install(new DashboardModule());

		install(new ProfileConfigModule());
		install(new DocConfigModule());

		install(new OrganizationConfigModule());
		install(new OrganizationCreateModule());
		install(new QuotationBrowserModule());
		install(new QuotationEditorModule());

		install(new ContactConfigModule());
		install(new ContactCreateModule());
	}
}
