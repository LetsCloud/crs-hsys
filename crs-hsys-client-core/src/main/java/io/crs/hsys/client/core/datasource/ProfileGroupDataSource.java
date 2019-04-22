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
import io.crs.hsys.client.core.message.callback.AbstractAsyncCallback;
import io.crs.hsys.shared.api.ProfileGroupResource;
import io.crs.hsys.shared.dto.profile.ProfileGroupDto;

/**
 * @author robi
 *
 */
public class ProfileGroupDataSource implements DataSource<ProfileGroupDto> {
	private static Logger logger = Logger.getLogger(ProfileGroupDataSource.class.getName());

	private Boolean isLoaded = false;

	private Boolean onlyActive = true;

	private final ResourceDelegate<ProfileGroupResource> resourceDelegate;

	@Inject
	ProfileGroupDataSource(ResourceDelegate<ProfileGroupResource> resourceDelegate) {
		logger.info("ProfileGroupDataSource()");
		this.resourceDelegate = resourceDelegate;
	}

	@Override
	public void load(LoadConfig<ProfileGroupDto> loadConfig, LoadCallback<ProfileGroupDto> callback) {
		resourceDelegate.withCallback(new AbstractAsyncCallback<List<ProfileGroupDto>>() {
			@Override
			public void onSuccess(List<ProfileGroupDto> result) {
				isLoaded = true;
				result.sort((ProfileGroupDto o1, ProfileGroupDto o2) -> o1.getCode().compareTo(o2.getCode()));
				callback.onSuccess(new LoadResult<>(result, loadConfig.getOffset(), result.size()));
			}
		}).getAll(onlyActive);
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

	public Boolean getOnlyActive() {
		return onlyActive;
	}

	public void setOnlyActive(Boolean onlyActive) {
		this.onlyActive = onlyActive;
	}

}