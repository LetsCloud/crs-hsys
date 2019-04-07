/**
 * 
 */
package io.crs.hsys.client.cfg.config.doc;

import javax.inject.Inject;

import io.crs.hsys.client.core.ui.config.AbstractConfigView;

/**
 * @author robi
 *
 */
public class DocConfigView extends AbstractConfigView implements DocConfigPresenter.MyView {

	@Inject
	DocConfigView() {
		super();
	}
}
