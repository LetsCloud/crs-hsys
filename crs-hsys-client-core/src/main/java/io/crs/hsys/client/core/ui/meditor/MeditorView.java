/**
 * 
 */
package io.crs.hsys.client.core.ui.meditor;

import com.gwtplatform.mvp.client.View;

import io.crs.hsys.shared.dto.BaseDto;

/**
 * @author robi
 *
 */
public interface MeditorView<T extends BaseDto> extends View {

	void open(T dto);

	void close();

}