/**
 * 
 */
package io.crs.hsys.client.cfg.display.contact;

import javax.inject.Inject;

import io.crs.hsys.client.core.config.AbstractConfigView;

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
