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
import io.crs.hsys.shared.api.OrganizationResource;
import io.crs.hsys.shared.dto.profile.OrganizationDtor;

/**
 * @author robi
 *
 */
public class OrganizationDataSource2 implements DataSource<OrganizationDtor> {
	private static Logger logger = Logger.getLogger(OrganizationDataSource2.class.getName());

	private Boolean isLoaded = false;
	private Boolean onlyActive = false;

	private final ResourceDelegate<OrganizationResource> resourceDelegate;

	@Inject
	OrganizationDataSource2(ResourceDelegate<OrganizationResource> usersDelegate) {
		logger.info("OrganizationDataSource2()");
		this.resourceDelegate = usersDelegate;
	}

	@Override
	public void load(LoadConfig<OrganizationDtor> loadConfig, LoadCallback<OrganizationDtor> callback) {
		resourceDelegate.withCallback(new AbstractAsyncCallback<List<OrganizationDtor>>() {
			@Override
			public void onSuccess(List<OrganizationDtor> result) {
				callback.onSuccess(new LoadResult<>(result, loadConfig.getOffset(), result.size()));
				isLoaded = true;
			}
		}).listReduced(onlyActive);
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