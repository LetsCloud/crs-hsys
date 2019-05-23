/**
 * 
 */
package io.crs.hsys.client.kip.gin;

import com.gwtplatform.mvp.client.gin.AbstractPresenterModule;

import io.crs.hsys.client.kip.KipAppModule;
import io.crs.hsys.client.kip.assignments.AssignmentsModule;
import io.crs.hsys.client.kip.atendants.AtendantsModule;
import io.crs.hsys.client.kip.browser.guestroom.GuestRoomBrowserModule;
import io.crs.hsys.client.kip.browser.oooroom.OooRoomBrowserModule;
import io.crs.hsys.client.kip.browser.task.TaskMngrModule;
import io.crs.hsys.client.kip.chat.ChatRoomModule;
import io.crs.hsys.client.kip.chat.creator.ChatCreatorModule;
import io.crs.hsys.client.kip.chat.editor.ChatEditorModule;
import io.crs.hsys.client.kip.chat.list.ChatListModule;
import io.crs.hsys.client.kip.config.hk.HkConfigModule;
import io.crs.hsys.client.kip.config.mt.MtConfigModule;
import io.crs.hsys.client.kip.creator.oooroom.OooRoomCreatorModule;
import io.crs.hsys.client.kip.dashboard.DashboardModule;
import io.crs.hsys.client.kip.editor.oooroom.OooRoomEditorModule;
import io.crs.hsys.client.kip.editor.task.TaskEditorModule;
import io.crs.hsys.client.kip.editor.translate.TranslateEditorModule;
import io.crs.hsys.client.kip.filter.KipFilterModule;
import io.crs.hsys.client.kip.gfilter.display.GfilterDisplayModule;
import io.crs.hsys.client.kip.roomstatus.RoomStatusModule;
import io.crs.hsys.client.kip.roomstatus.filter.RoomStatusFilterModule;
import io.crs.hsys.client.kip.roomstatus.list.RoomStatusListModule;
import io.crs.hsys.client.kip.search.SearchModule;
import io.crs.hsys.client.kip.task.creator.TaskCreatorModule;
import io.crs.hsys.client.core.gin.CoreModule;

/**
 * @author CR
 *
 */
public class ClientModule extends AbstractPresenterModule {

	@Override
	protected void configure() {
		install(new CoreModule());

		bind(ResourceLoader.class).asEagerSingleton();

		install(new KipAppModule());
		install(new DashboardModule());
		install(new GfilterDisplayModule());
		install(new AtendantsModule());
		install(new AssignmentsModule());
		install(new RoomStatusModule());
		install(new RoomStatusListModule());
		install(new RoomStatusFilterModule());
		install(new ChatRoomModule());
		install(new ChatListModule());
		install(new ChatCreatorModule());
		install(new ChatEditorModule());
		install(new TaskMngrModule());
		install(new TaskEditorModule());
		install(new KipFilterModule());
		install(new SearchModule());
		install(new GuestRoomBrowserModule());
		install(new TaskCreatorModule());
		install(new OooRoomBrowserModule());
		install(new OooRoomCreatorModule());
		install(new OooRoomEditorModule());

		install(new HkConfigModule());
		install(new MtConfigModule());
		
		install(new TranslateEditorModule());

		/*
		 * install(new UserListModule()); install(new RoleListModule()); install(new
		 * HotelListModule());
		 */
	}
}
