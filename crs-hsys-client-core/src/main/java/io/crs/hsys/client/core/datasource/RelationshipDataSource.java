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
import io.crs.hsys.shared.api.RelationshipResource;
import io.crs.hsys.shared.dto.profile.RelationshipDtor;

/**
 * @author robi
 *
 */
public class RelationshipDataSource implements DataSource<RelationshipDtor> {
	private static Logger logger = Logger.getLogger(RelationshipDataSource.class.getName());

	private Boolean isLoaded = false;

	private final ResourceDelegate<RelationshipResource> resourceDelegate;

	@Inject
	RelationshipDataSource(ResourceDelegate<RelationshipResource> resourceDelegate) {
		logger.info("RelationshipDataSource()");
		this.resourceDelegate = resourceDelegate;
	}

	@Override
	public void load(LoadConfig<RelationshipDtor> loadConfig, LoadCallback<RelationshipDtor> callback) {
		resourceDelegate.withCallback(new AbstractAsyncCallback<List<RelationshipDtor>>() {
			@Override
			public void onSuccess(List<RelationshipDtor> result) {
				isLoaded = true;
				result.sort((RelationshipDtor o1, RelationshipDtor o2) -> o1.getForward().compareTo(o2.getForward()));
				callback.onSuccess(new LoadResult<>(result, loadConfig.getOffset(), result.size()));
			}
		}).getAllReduced(true);
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