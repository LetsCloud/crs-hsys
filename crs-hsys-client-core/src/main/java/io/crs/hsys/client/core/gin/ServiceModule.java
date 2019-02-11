/**
 * 
 */
package io.crs.hsys.client.core.gin;

import com.google.inject.Provides;
import com.gwtplatform.dispatch.rest.client.RestApplicationPath;
import com.gwtplatform.dispatch.rest.client.gin.RestDispatchAsyncModule;
import com.gwtplatform.dispatch.shared.SecurityCookie;
import com.gwtplatform.mvp.client.gin.AbstractPresenterModule;

import io.crs.hsys.client.core.util.UrlUtils;

/**
 * @author CR
 *
 */
public class ServiceModule extends AbstractPresenterModule {

	@Override
	protected void configure() {
		RestDispatchAsyncModule.Builder rdapBuilder = new RestDispatchAsyncModule.Builder();
		install(rdapBuilder.xsrfTokenHeaderName("XSRF-TOKEN").core()
				.responseDeserializer(CustomResponseDeserializer.class).build());
		bindConstant().annotatedWith(SecurityCookie.class).to("JSESSIONID");
	}

	@Provides
	@RestApplicationPath
	String getApplicationPath() {
		return UrlUtils.getBaseUrl();
	}
}
