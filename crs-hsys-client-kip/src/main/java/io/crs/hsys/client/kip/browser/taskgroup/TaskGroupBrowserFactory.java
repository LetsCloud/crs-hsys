/**
 * 
 */
package io.crs.hsys.client.kip.browser.taskgroup;

/**
 * @author robi
 *
 */
public interface TaskGroupBrowserFactory {

	HkTaskGroupBrowserPresenter createHkTaskGroupBrowser();

	MtTaskGroupBrowserPresenter createMtTaskGroupBrowser();

	AdminTaskGroupBrowserPresenter createAdminTaskGroupBrowser();

}
