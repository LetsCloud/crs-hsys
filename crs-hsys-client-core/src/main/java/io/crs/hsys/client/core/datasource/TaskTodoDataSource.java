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
import io.crs.hsys.shared.api.TaskTodoResource;
import io.crs.hsys.shared.dto.task.TaskTodoDto;

/**
 * @author robi
 *
 */
public class TaskTodoDataSource implements DataSource<TaskTodoDto> {
	private static Logger logger = Logger.getLogger(TaskTodoDataSource.class.getName());

	private Boolean isLoaded = false;

	private final ResourceDelegate<TaskTodoResource> resourceDelegate;

	@Inject
	TaskTodoDataSource(ResourceDelegate<TaskTodoResource> resourceDelegate) {
		logger.info("RoomTypeDataSource()");
		this.resourceDelegate = resourceDelegate;
	}

	@Override
	public void load(LoadConfig<TaskTodoDto> loadConfig, LoadCallback<TaskTodoDto> callback) {
		resourceDelegate.withCallback(new AbstractAsyncCallback<List<TaskTodoDto>>() {
			@Override
			public void onSuccess(List<TaskTodoDto> result) {
				isLoaded = true;
				result.sort((TaskTodoDto o1, TaskTodoDto o2) -> o1.getDescription().compareTo(o2.getDescription()));
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