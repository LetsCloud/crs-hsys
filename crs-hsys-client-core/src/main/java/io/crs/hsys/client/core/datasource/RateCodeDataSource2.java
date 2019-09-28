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
import io.crs.hsys.shared.api.RateCodeResource;
import io.crs.hsys.shared.dto.rate.RateCodeDtor;

/**
 * @author robi
 *
 */
public class RateCodeDataSource2 implements DataSource<RateCodeDtor> {
	private static Logger logger = Logger.getLogger(RateCodeDataSource2.class.getName());

	private Boolean isLoaded = false;

	private String hotelKey;

	private final ResourceDelegate<RateCodeResource> resourceDelegate;

	@Inject
	RateCodeDataSource2(ResourceDelegate<RateCodeResource> resourceDelegate) {
		logger.info("RateCodeDataSource2()");
		this.resourceDelegate = resourceDelegate;
	}

	@Override
	public void load(LoadConfig<RateCodeDtor> loadConfig, LoadCallback<RateCodeDtor> callback) {
		resourceDelegate.withCallback(new AbstractAsyncCallback<List<RateCodeDtor>>() {
			@Override
			public void onSuccess(List<RateCodeDtor> result) {
				isLoaded = true;
				result.sort((RateCodeDtor o1, RateCodeDtor o2) -> o1.getCode().compareTo(o2.getCode()));
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