/**
 * 
 */
package io.crs.hsys.client.cfg.config.profile;

import javax.inject.Inject;

import io.crs.hsys.client.cfg.config.AbstractConfigView;

/**
 * @author robi
 *
 */
public class ProfileConfigView extends AbstractConfigView implements ProfileConfigPresenter.MyView {

	@Inject
	ProfileConfigView() {
		super();
	}
}
