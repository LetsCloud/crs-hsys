/**
 * 
 */
package io.crs.hsys.client.cfg;

import java.util.logging.Logger;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.gwtplatform.mvp.client.ApplicationController;

/**
 * @author robi
 *
 */
public class CfgAppEntryPoint implements EntryPoint {
	private static Logger logger = Logger.getLogger(CfgAppEntryPoint.class.getName());

	private static final ApplicationController CONTROLLER = GWT.create(ApplicationController.class);

	@Override
	public void onModuleLoad() {
		logger.info("CfgAppEntryPoint().onModuleLoad()");
		CONTROLLER.init();
	}
}