/**
 * 
 */
package io.crs.hsys.server.service;

import java.util.List;

import io.crs.hsys.server.entity.GlobalConfig;

/**
 * @author robi
 *
 */
public interface GlobalConfigService {

	public void checkParams();

	public List<GlobalConfig> getParams();

}
