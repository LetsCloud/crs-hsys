/**
 * 
 */
package io.crs.hsys.client.cfg.page.organizations;

import com.gwtplatform.mvp.client.gin.AbstractPresenterModule;

/**
 * @author robi
 *
 */
public class OrganizationsPageModule extends AbstractPresenterModule {

	@Override
	protected void configure() {
		bindPresenter(OrganizationsPagePresenter.class, OrganizationsPagePresenter.MyView.class,
				OrganizationsPageView.class, OrganizationsPagePresenter.MyProxy.class);
	}
}
