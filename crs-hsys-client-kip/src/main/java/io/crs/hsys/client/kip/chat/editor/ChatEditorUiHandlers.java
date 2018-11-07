/**
 * 
 */
package io.crs.hsys.client.kip.chat.editor;

import com.gwtplatform.mvp.client.UiHandlers;

/**
 * @author robi
 *
 */
public interface ChatEditorUiHandlers extends UiHandlers {
	
	void loadData(String chatWebSafeKey);
	
	void addPost(String message);
}
