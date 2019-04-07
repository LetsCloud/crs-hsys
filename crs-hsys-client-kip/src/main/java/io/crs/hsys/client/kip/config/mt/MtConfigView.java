/**
 * 
 */
package io.crs.hsys.client.kip.config.mt;

import javax.inject.Inject;

import io.crs.hsys.client.core.config.AbstractConfigView;

/**
 * @author robi
 *
 */
public class MtConfigView extends AbstractConfigView implements MtConfigPresenter.MyView {

	@Inject
	MtConfigView() {
		super();
	}
}
