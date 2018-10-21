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
import io.crs.hsys.shared.api.AppUserResource;
import io.crs.hsys.shared.dto.common.AppUserDto;

/**
 * @author CR
 *
 */
public class AppUserDataSource implements DataSource<AppUserDto> {
	private static Logger logger = Logger.getLogger(AppUserDataSource.class.getName());

	private Boolean isLoaded = false;
	
	private final ResourceDelegate<AppUserResource> usersDelegate;

	@Inject
	AppUserDataSource(ResourceDelegate<AppUserResource> usersDelegate) {
		logger.info("AppUserDataSource()");
		this.usersDelegate = usersDelegate;
	}

	
	@Override
	public void load(LoadConfig<AppUserDto> loadConfig, LoadCallback<AppUserDto> callback) {
		usersDelegate.withCallback(new AbstractAsyncCallback<List<AppUserDto>>() {
			@Override
			public void onSuccess(List<AppUserDto> result) {
				callback.onSuccess(new LoadResult<>(result, loadConfig.getOffset(), result.size()));
				isLoaded = true;
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