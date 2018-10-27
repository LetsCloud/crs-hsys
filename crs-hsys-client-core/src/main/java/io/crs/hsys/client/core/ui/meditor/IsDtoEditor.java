/**
 * 
 */
package io.crs.hsys.client.core.ui.meditor;

import com.gwtplatform.mvp.client.UiHandlers;

import io.crs.hsys.shared.dto.BaseDto;

/**
 * @author robi
 *
 */
public interface IsDtoEditor<T extends BaseDto> extends UiHandlers {

	void create();

	void edit(T dto);

	void save(T dto);
}
