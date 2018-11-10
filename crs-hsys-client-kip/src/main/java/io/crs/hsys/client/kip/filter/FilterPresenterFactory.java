/**
 * 
 */
package io.crs.hsys.client.kip.filter;

import io.crs.hsys.client.kip.filter.assignment.AssignmentFilterPresenter;

/**
 * @author robi
 *
 */
public interface FilterPresenterFactory {

	AssignmentFilterPresenter createAssignmentFilterPresenter();

}
