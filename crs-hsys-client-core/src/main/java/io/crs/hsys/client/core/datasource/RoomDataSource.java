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
import io.crs.hsys.shared.api.RoomResource;
import io.crs.hsys.shared.dto.hotel.RoomDto;

/**
 * @author robi
 *
 */
public class RoomDataSource implements DataSource<RoomDto> {
	private static Logger logger = Logger.getLogger(RoomDataSource.class.getName());

	private Boolean isLoaded = false;

	private String hotelKey;

	private Boolean onlyActive = true;

	private final ResourceDelegate<RoomResource> resourceDelegate;

	@Inject
	RoomDataSource(ResourceDelegate<RoomResource> resourceDelegate) {
		logger.info("RoomDataSource()");
		this.resourceDelegate = resourceDelegate;
	}

	@Override
	public void load(LoadConfig<RoomDto> loadConfig, LoadCallback<RoomDto> callback) {
		resourceDelegate.withCallback(new AbstractAsyncCallback<List<RoomDto>>() {
			@Override
			public void onSuccess(List<RoomDto> result) {
				isLoaded = true;
				result.sort((RoomDto o1, RoomDto o2) -> o1.getCode().compareTo(o2.getCode()));
				callback.onSuccess(new LoadResult<>(result, loadConfig.getOffset(), result.size()));
			}
		}).getByHotel(hotelKey, onlyActive);
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

	public String getHotelKey() {
		return hotelKey;
	}

	public void setHotelKey(String hotelKey) {
		this.hotelKey = hotelKey;
	}

	public Boolean getOnlyActive() {
		return onlyActive;
	}

	public void setOnlyActive(Boolean onlyActive) {
		this.onlyActive = onlyActive;
	}
}