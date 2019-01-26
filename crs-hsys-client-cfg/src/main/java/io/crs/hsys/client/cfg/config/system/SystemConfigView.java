/**
 * 
 */
package io.crs.hsys.client.cfg.config.system;

import javax.inject.Inject;

import io.crs.hsys.client.core.config.AbstractConfigView;

/**
 * @author CR
 *
 */
public class SystemConfigView extends AbstractConfigView implements SystemConfigPresenter.MyView {

	@Inject
	SystemConfigView() {
		super();
	}
}
