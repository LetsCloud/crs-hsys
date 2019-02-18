/**
 * 
 */
package io.crs.hsys.client.core.meditor;

import com.gwtplatform.mvp.client.UiHandlers;

import io.crs.hsys.shared.dto.BaseDto;

/**
 * @author robi
 *
 */
public interface MeditorUiHandlers<T extends BaseDto> extends UiHandlers {

	void create();

	void edit(T dto);

	void save(T dto);
	
	void cancel();
}
