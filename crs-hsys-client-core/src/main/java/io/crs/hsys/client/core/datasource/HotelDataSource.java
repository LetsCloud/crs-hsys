/**
 * 
 */
package io.crs.hsys.client.core.datasource;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import javax.inject.Inject;

import com.gwtplatform.dispatch.rest.delegates.client.ResourceDelegate;

import gwt.material.design.client.data.DataSource;
import gwt.material.design.client.data.loader.LoadCallback;
import gwt.material.design.client.data.loader.LoadConfig;
import gwt.material.design.client.data.loader.LoadResult;

import io.crs.hsys.client.core.util.AbstractAsyncCallback;
import io.crs.hsys.shared.api.HotelResource;
import io.crs.hsys.shared.dto.hotel.HotelDto;

/**
 * @author robi
 *
 */
public class HotelDataSource implements DataSource<HotelDto> {
	private static Logger logger = Logger.getLogger(HotelDataSource.class.getName());

	private Boolean isLoaded = false;

	private String webSafeKey;

	private final ResourceDelegate<HotelResource> resourceDelegate;

	@Inject
	HotelDataSource(ResourceDelegate<HotelResource> resourceDelegate) {
		logger.info("HotelDataSource()");
		this.resourceDelegate = resourceDelegate;
	}

	@Override
	public void load(LoadConfig<HotelDto> loadConfig, LoadCallback<HotelDto> callback) {
		isLoaded = false;
		resourceDelegate.withCallback(new AbstractAsyncCallback<List<HotelDto>>() {
			@Override
			public void onSuccess(List<HotelDto> result) {
				isLoaded = true;
				result.sort((HotelDto h1, HotelDto h2) -> h1.getName().compareTo(h2.getName()));
				callback.onSuccess(new LoadResult<HotelDto>(result, loadConfig.getOffset(), result.size()));
			}
		}).list();
	}

	public void get(LoadConfig<HotelDto> loadConfig, LoadCallback<HotelDto> callback) {
		isLoaded = false;
		resourceDelegate.withCallback(new AbstractAsyncCallback<HotelDto>() {
			@Override
			public void onSuccess(HotelDto result) {
				isLoaded = true;
				List<HotelDto> list = new ArrayList<HotelDto>();
				list.add(result);
				callback.onSuccess(new LoadResult<HotelDto>(list, loadConfig.getOffset(), list.size()));
			}
		}).read(webSafeKey);
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

	public String getWebSafeKey() {
		return webSafeKey;
	}

	public void setWebSafeKey(String webSafeKey) {
		this.webSafeKey = webSafeKey;
	}

}