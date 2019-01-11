/**
 * 
 */
package io.crs.hsys.client.fro.gin;

import com.gwtplatform.mvp.client.gin.AbstractPresenterModule;

import io.crs.hsys.client.core.gin.CoreModule;
import io.crs.hsys.client.fro.AppModule;

/**
 * @author CR
 *
 */
public class ClientModule extends AbstractPresenterModule {

	@Override
	protected void configure() {
		install(new CoreModule());

		bind(ResourceLoader.class).asEagerSingleton();

		install(new AppModule());
	}
}
