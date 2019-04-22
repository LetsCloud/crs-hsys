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
import io.crs.hsys.shared.api.QuotationStatusResource;
import io.crs.hsys.shared.dto.doc.QuotationStatusDto;

/**
 * @author robi
 *
 */
public class QuotationStatusDataSource implements DataSource<QuotationStatusDto> {
	private static Logger logger = Logger.getLogger(QuotationStatusDataSource.class.getName());

	private Boolean isLoaded = false;
	private Boolean onlyActive = false;

	private final ResourceDelegate<QuotationStatusResource> resourceDelegate;

	@Inject
	QuotationStatusDataSource(ResourceDelegate<QuotationStatusResource> resourceDelegate) {
		logger.info("QuotationStatusDataSource()");
		this.resourceDelegate = resourceDelegate;
	}

	@Override
	public void load(LoadConfig<QuotationStatusDto> loadConfig, LoadCallback<QuotationStatusDto> callback) {
		resourceDelegate.withCallback(new AbstractAsyncCallback<List<QuotationStatusDto>>() {
			@Override
			public void onSuccess(List<QuotationStatusDto> result) {
				isLoaded = true;
				result.sort((QuotationStatusDto o1, QuotationStatusDto o2) -> o1.getCode().compareTo(o2.getCode()));
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