/**
 * 
 */
package io.crs.hsys.client.kip.meditor.taskgroup;

/**
 * @author robi
 *
 */
public interface TaskGroupEditorFactory {

	HkTaskGroupEditorPresenter createHkTaskGroupEditor();

	MtTaskGroupEditorPresenter createMtTaskGroupEditor();

}
