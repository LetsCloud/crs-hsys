/**
 * 
 */
package io.crs.hsys.client.kip.meditor.taskgroup;

import javax.inject.Inject;

import io.crs.hsys.client.core.i18n.CoreMessages;

/**
 * @author robi
 *
 */
public class MtTaskGroupEditorView extends TaskGroupEditorView implements MtTaskGroupEditorPresenter.MyView {

	@Inject
	MtTaskGroupEditorView(Binder uiBinder, Driver driver, CoreMessages i18n) {
		super(uiBinder, driver, i18n);
	}

}
