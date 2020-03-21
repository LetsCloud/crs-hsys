/**
 * 
 */
package io.crs.hsys.client.fro.config.rate;

import javax.inject.Inject;

import io.crs.hsys.client.core.config.AbstractConfigView;

/**
 * @author robi
 *
 */
public class RateConfigView extends AbstractConfigView implements RateConfigPresenter.MyView {

	@Inject
	RateConfigView() {
		super();
	}
}
