/**
 * 
 */
package io.crs.hsys.client.kip.gin;

import com.gwtplatform.mvp.client.gin.AbstractPresenterModule;

import io.crs.hsys.client.kip.app.KipAppModule;
import io.crs.hsys.client.kip.assignments.AssignmentsModule;
import io.crs.hsys.client.kip.atendants.AtendantsModule;
import io.crs.hsys.client.kip.chat.ChatRoomModule;
import io.crs.hsys.client.kip.chat.creator.ChatCreatorModule;
import io.crs.hsys.client.kip.chat.editor.ChatEditorModule;
import io.crs.hsys.client.kip.chat.list.ChatListModule;
import io.crs.hsys.client.kip.gfilter.display.GfilterDisplayModule;
import io.crs.hsys.client.kip.roomstatus.DesktopRoomStatusModule;
import io.crs.hsys.client.kip.roomstatus.filter.RoomStatusFilterModule;
import io.crs.hsys.client.kip.roomstatus.list.RoomStatusListModule;
import io.crs.hsys.client.kip.task.TaskMngrModule;
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
		install(new GfilterDisplayModule());
		install(new AtendantsModule());
		install(new AssignmentsModule());
		install(new DesktopRoomStatusModule());
		install(new RoomStatusListModule());
		install(new RoomStatusFilterModule());
		install(new ChatRoomModule());
		install(new ChatListModule());
		install(new ChatCreatorModule());
		install(new ChatEditorModule());
		install(new TaskMngrModule());
		/*
		 * install(new UserListModule()); install(new RoleListModule()); install(new
		 * HotelListModule());
		 */
	}
}
