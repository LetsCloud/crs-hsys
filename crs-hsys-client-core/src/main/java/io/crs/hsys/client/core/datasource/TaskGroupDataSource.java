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
import io.crs.hsys.shared.api.TaskGroupResource;
import io.crs.hsys.shared.dto.task.TaskGroupDto;

/**
 * @author robi
 *
 */
public class TaskGroupDataSource implements DataSource<TaskGroupDto> {
	private static Logger logger = Logger.getLogger(TaskGroupDataSource.class.getName());

	private Boolean isLoaded = false;

	private final ResourceDelegate<TaskGroupResource> resourceDelegate;

	@Inject
	TaskGroupDataSource(ResourceDelegate<TaskGroupResource> resourceDelegate) {
		logger.info("RoomTypeDataSource()");
		this.resourceDelegate = resourceDelegate;
	}

	@Override
	public void load(LoadConfig<TaskGroupDto> loadConfig, LoadCallback<TaskGroupDto> callback) {
		resourceDelegate.withCallback(new AbstractAsyncCallback<List<TaskGroupDto>>() {
			@Override
			public void onSuccess(List<TaskGroupDto> result) {
				isLoaded = true;
				result.sort((TaskGroupDto o1, TaskGroupDto o2) -> o1.getCode().compareTo(o2.getCode()));
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