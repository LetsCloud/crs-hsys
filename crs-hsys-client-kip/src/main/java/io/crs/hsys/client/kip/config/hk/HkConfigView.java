/**
 * 
 */
package io.crs.hsys.client.kip.config.hk;

import javax.inject.Inject;

import io.crs.hsys.client.core.ui.config.AbstractConfigView;

/**
 * @author robi
 *
 */
public class HkConfigView extends AbstractConfigView implements HkConfigPresenter.MyView {

	@Inject
	HkConfigView() {
		super();
	}
}
