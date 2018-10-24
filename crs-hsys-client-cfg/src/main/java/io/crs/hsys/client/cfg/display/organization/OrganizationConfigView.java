/**
 * 
 */
package io.crs.hsys.client.cfg.display.organization;

import javax.inject.Inject;

import io.crs.hsys.client.cfg.config.AbstractConfigView;

/**
 * @author robi
 *
 */
public class OrganizationConfigView extends AbstractConfigView implements OrganizationConfigPresenter.MyView {

	@Inject
	OrganizationConfigView() {
		super();
	}

}
