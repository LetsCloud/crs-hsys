package io.crs.hsys.client.cfg.config.profile;

import com.gwtplatform.mvp.client.gin.AbstractPresenterModule;

import io.crs.hsys.client.cfg.browser.contact.ContactBrowserModule;
import io.crs.hsys.client.cfg.browser.organization.OrganizationBrowserModule;
import io.crs.hsys.client.cfg.browser.profilegroup.ProfileGroupBrowserModule;
import io.crs.hsys.client.cfg.browser.relationship.RelationshipBrowserModule;

public class ProfileConfigModule extends AbstractPresenterModule {
	@Override
	protected void configure() {

		install(new ProfileGroupBrowserModule());
		install(new OrganizationBrowserModule());
		install(new ContactBrowserModule());
		install(new RelationshipBrowserModule());

		bindPresenter(ProfileConfigPresenter.class, ProfileConfigPresenter.MyView.class, ProfileConfigView.class,
				ProfileConfigPresenter.MyProxy.class);
	}
}
