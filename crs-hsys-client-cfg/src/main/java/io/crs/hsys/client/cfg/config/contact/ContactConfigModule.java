/**
 * 
 */
package io.crs.hsys.client.cfg.config.contact;

import com.gwtplatform.mvp.client.gin.AbstractPresenterModule;

/**
 * @author robi
 *
 */
public class ContactConfigModule extends AbstractPresenterModule {
	@Override
	protected void configure() {

		bindPresenter(ContactConfigPresenter.class, ContactConfigPresenter.MyView.class,
				ContactConfigView.class, ContactConfigPresenter.MyProxy.class);
	}
}
