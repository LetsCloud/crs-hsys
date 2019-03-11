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
import io.crs.hsys.shared.dto.common.AppUserDtor;

/**
 * @author robi
 *
 */
public class AppUserDataSource2 implements DataSource<AppUserDtor> {
	private static Logger logger = Logger.getLogger(AppUserDataSource.class.getName());

	private Boolean isLoaded = false;
	
	private final ResourceDelegate<AppUserResource> usersDelegate;

	@Inject
	AppUserDataSource2(ResourceDelegate<AppUserResource> usersDelegate) {
		logger.info("AppUserDataSource()");
		this.usersDelegate = usersDelegate;
	}

	
	@Override
	public void load(LoadConfig<AppUserDtor> loadConfig, LoadCallback<AppUserDtor> callback) {
		usersDelegate.withCallback(new AbstractAsyncCallback<List<AppUserDtor>>() {
			@Override
			public void onSuccess(List<AppUserDtor> result) {
				callback.onSuccess(new LoadResult<>(result, loadConfig.getOffset(), result.size()));
				isLoaded = true;
			}
		}).listReduced();
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