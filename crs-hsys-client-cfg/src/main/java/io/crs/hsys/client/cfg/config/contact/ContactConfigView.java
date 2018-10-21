/**
 * 
 */
package io.crs.hsys.client.cfg.config.contact;

import javax.inject.Inject;

import io.crs.hsys.client.cfg.config.AbstractConfigView;

/**
 * @author robi
 *
 */
public class ContactConfigView extends AbstractConfigView implements ContactConfigPresenter.MyView {

	@Inject
	ContactConfigView() {
		super();
	}

}
