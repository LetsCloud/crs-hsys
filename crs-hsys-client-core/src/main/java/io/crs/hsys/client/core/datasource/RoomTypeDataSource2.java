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
import io.crs.hsys.shared.api.RoomTypeResource;
import io.crs.hsys.shared.dto.hotel.RoomTypeDtor;

/**
 * @author robi
 *
 */
public class RoomTypeDataSource2 implements DataSource<RoomTypeDtor> {
	private static Logger logger = Logger.getLogger(RoomTypeDataSource2.class.getName());

	private Boolean isLoaded = false;

	private String hotelKey;

	private final ResourceDelegate<RoomTypeResource> resourceDelegate;

	@Inject
	RoomTypeDataSource2(ResourceDelegate<RoomTypeResource> resourceDelegate) {
		logger.info("RoomTypeDataSource()");
		this.resourceDelegate = resourceDelegate;
	}

	@Override
	public void load(LoadConfig<RoomTypeDtor> loadConfig, LoadCallback<RoomTypeDtor> callback) {
		resourceDelegate.withCallback(new AbstractAsyncCallback<List<RoomTypeDtor>>() {
			@Override
			public void onSuccess(List<RoomTypeDtor> result) {
				isLoaded = true;
				result.sort((RoomTypeDtor o1, RoomTypeDtor o2) -> o1.getCode().compareTo(o2.getCode()));
				callback.onSuccess(new LoadResult<>(result, loadConfig.getOffset(), result.size()));
			}
		}).listReduced(hotelKey);
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
}