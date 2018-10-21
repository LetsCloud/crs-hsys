/**
 * 
 */
package io.crs.hsys.client.core.datasource;

import java.util.List;
import java.util.logging.Logger;

import javax.inject.Inject;

import com.gwtplatform.dispatch.rest.delegates.client.ResourceDelegate;

import gwt.material.design.client.data.DataSource;
import gwt.material.design.client.data.loader.LoadCallback;
import gwt.material.design.client.data.loader.LoadConfig;
import gwt.material.design.client.data.loader.LoadResult;

import io.crs.hsys.client.core.util.AbstractAsyncCallback;
import io.crs.hsys.shared.api.UserGroupResource;
import io.crs.hsys.shared.dto.common.UserGroupDto;

/**
 * @author robi
 *
 */
public class UserGroupDataSource implements DataSource<UserGroupDto> {
	private static Logger logger = Logger.getLogger(AppUserDataSource.class.getName());

	private Boolean isLoaded = false;
	
	private final ResourceDelegate<UserGroupResource> resourceDelegate;

	@Inject
	UserGroupDataSource(ResourceDelegate<UserGroupResource> resourceDelegate) {
		logger.info("UserGroupDataSource()");
		this.resourceDelegate = resourceDelegate;
	}
	
	@Override
	public void load(LoadConfig<UserGroupDto> loadConfig, LoadCallback<UserGroupDto> callback) {
		resourceDelegate.withCallback(new AbstractAsyncCallback<List<UserGroupDto>>() {
			@Override
			public void onSuccess(List<UserGroupDto> result) {
				isLoaded = true;
				callback.onSuccess(new LoadResult<>(result, loadConfig.getOffset(), result.size()));
			}
		}).list();
	}

	@Override
	public boolean useRemoteSort() {
		return false;
	}

	public Boolean getIsLoaded() {
		return isLoaded;
	}

	public void setIsLoaded(Boolean isLoaded) {
		this.isLoaded = isLoaded;
	}
	
}