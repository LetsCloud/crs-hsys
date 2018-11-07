/**
 * 
 */
package io.crs.hsys.client.kip.assignments;

import com.gwtplatform.mvp.client.gin.AbstractPresenterModule;

import io.crs.hsys.client.kip.assignments.editor.AssignmentEditorModule;
import io.crs.hsys.client.kip.assignments.widget.AssignmentWidgetModule;

/**
 * @author CR
 *
 */
public class AssignmentsModule extends AbstractPresenterModule {

	@Override
	protected void configure() {
		bindPresenter(AssignmentsPresenter.class, AssignmentsPresenter.MyView.class, AssignmentsView.class,
				AssignmentsPresenter.MyProxy.class);

		install(new AssignmentEditorModule());
		install(new AssignmentWidgetModule());
	}
}
