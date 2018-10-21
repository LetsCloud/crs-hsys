/**
 * 
 */
package io.crs.hsys.client.cfg.editor;

import com.gwtplatform.mvp.client.UiHandlers;

import io.crs.hsys.shared.dto.BaseDto;

/**
 * @author robi
 *
 */
public interface AbstractEditorUiHandlers<T extends BaseDto> extends UiHandlers {

	void save(T dto);
	
	void cancel();
}
