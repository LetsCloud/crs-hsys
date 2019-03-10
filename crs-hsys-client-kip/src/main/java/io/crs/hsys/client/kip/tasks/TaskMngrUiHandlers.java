/**
 * 
 */
package io.crs.hsys.client.kip.tasks;

import com.gwtplatform.mvp.client.UiHandlers;

/**
 * @author robi
 *
 */
public interface TaskMngrUiHandlers extends UiHandlers {
	
	void create();
	
	void modify(String webSafeKey);

}
