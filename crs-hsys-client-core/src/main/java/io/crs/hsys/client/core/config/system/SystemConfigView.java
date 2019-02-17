/**
 * 
 */
package io.crs.hsys.client.core.config.system;

import javax.inject.Inject;

import io.crs.hsys.client.core.ui.config.AbstractConfigView;

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
