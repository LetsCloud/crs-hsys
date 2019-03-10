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
import io.crs.hsys.shared.api.TaskTypeResource;
import io.crs.hsys.shared.dto.task.TaskTypeDto;

/**
 * @author robi
 *
 */
public class TaskTypeDataSource implements DataSource<TaskTypeDto> {
	private static Logger logger = Logger.getLogger(TaskTypeDataSource.class.getName());

	private Boolean isLoaded = false;

	private final ResourceDelegate<TaskTypeResource> resourceDelegate;

	@Inject
	TaskTypeDataSource(ResourceDelegate<TaskTypeResource> resourceDelegate) {
		logger.info("RoomTypeDataSource()");
		this.resourceDelegate = resourceDelegate;
	}

	@Override
	public void load(LoadConfig<TaskTypeDto> loadConfig, LoadCallback<TaskTypeDto> callback) {
		resourceDelegate.withCallback(new AbstractAsyncCallback<List<TaskTypeDto>>() {
			@Override
			public void onSuccess(List<TaskTypeDto> result) {
				isLoaded = true;
				result.sort((TaskTypeDto o1, TaskTypeDto o2) -> o1.getCode().compareTo(o2.getCode()));
				callback.onSuccess(new LoadResult<>(result, loadConfig.getOffset(), result.size()));
			}
		}).getAll();
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