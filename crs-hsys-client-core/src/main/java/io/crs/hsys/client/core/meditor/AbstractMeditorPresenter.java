/**
 * 
 */
package io.crs.hsys.client.core.meditor;

import com.google.web.bindery.event.shared.EventBus;
import com.gwtplatform.mvp.client.PresenterWidget;

import io.crs.hsys.shared.dto.BaseDto;

/**
 * @author robi
 *
 */
public abstract class AbstractMeditorPresenter<T extends BaseDto, V extends MeditorView<T>> extends PresenterWidget<V>
		implements MeditorUiHandlers<T> {

	public AbstractMeditorPresenter(EventBus eventBus, V view) {
		super(eventBus, view);
		// TODO Auto-generated constructor stub
	}

	protected abstract T createDto();

	@Override
	public void create() {
		getView().open(createDto());
	}

	@Override
	public void edit(T dto) {
		getView().open(dto);
	}

	protected abstract void saveDto(T dto);

	@Override
	public void save(T dto) {
		saveDto(dto);
	}

}
