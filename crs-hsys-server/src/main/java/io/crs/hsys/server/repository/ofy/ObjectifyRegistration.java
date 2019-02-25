/**
 * 
 */
package io.crs.hsys.server.repository.ofy;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.googlecode.objectify.ObjectifyService;

import io.crs.hsys.server.entity.GlobalConfig;
import io.crs.hsys.server.entity.chat.Chat;
import io.crs.hsys.server.entity.common.Account;
import io.crs.hsys.server.entity.common.AppUser;
import io.crs.hsys.server.entity.common.UserGroup;
import io.crs.hsys.server.entity.hotel.Hotel;
import io.crs.hsys.server.entity.hotel.Room;
import io.crs.hsys.server.entity.hotel.RoomType;
import io.crs.hsys.server.entity.profile.Contact;
import io.crs.hsys.server.entity.profile.Guest;
import io.crs.hsys.server.entity.profile.Organization;
import io.crs.hsys.server.entity.profile.Person;
import io.crs.hsys.server.entity.profile.Profile;
import io.crs.hsys.server.entity.profile.ProfileGroup;
import io.crs.hsys.server.entity.profile.ProfileLink;
import io.crs.hsys.server.entity.profile.Relationship;
import io.crs.hsys.server.entity.reservation.MarketGroup;
import io.crs.hsys.server.entity.reservation.Reservation;
import io.crs.hsys.server.entity.task.TaskGroup;
import io.crs.hsys.server.entity.task.TaskTodo;
import io.crs.hsys.server.entity.task.TaskType;


/**
 * @author CR
 *
 */
public class ObjectifyRegistration {
	private static final Logger logger = LoggerFactory.getLogger(ObjectifyRegistration.class);

	static {
		logger.info("ObjectifyService.register");
		ObjectifyService.register(GlobalConfig.class);
		ObjectifyService.register(Account.class);
		ObjectifyService.register(AppUser.class);
		ObjectifyService.register(UserGroup.class);
		ObjectifyService.register(TaskGroup.class);
		ObjectifyService.register(TaskTodo.class);
		ObjectifyService.register(TaskType.class);
		/*		ObjectifyService.register(Role.class);
		ObjectifyService.register(Currency.class);
		ObjectifyService.register(Service.class);
*/		
		ObjectifyService.register(ProfileGroup.class);
		ObjectifyService.register(Relationship.class);
		ObjectifyService.register(Profile.class);
		ObjectifyService.register(Organization.class);
		ObjectifyService.register(Person.class);
		ObjectifyService.register(Contact.class);
		ObjectifyService.register(Guest.class);
		ObjectifyService.register(ProfileLink.class);

		ObjectifyService.register(Chat.class);

		ObjectifyService.register(Hotel.class);
		ObjectifyService.register(RoomType.class);
		ObjectifyService.register(Room.class);
		ObjectifyService.register(MarketGroup.class);
		/*		ObjectifyService.register(Chat.class);
		ObjectifyService.register(Task.class);*/
		ObjectifyService.register(Reservation.class);
		/*
		ObjectifyService.register(CubeBdgtCap.class);
		ObjectifyService.register(CubeBdgtPfm.class);
		ObjectifyService.register(CubeActlCap.class);
		ObjectifyService.register(CubeActlPfm.class);
		ObjectifyService.register(CubeFcstCap.class);
		ObjectifyService.register(CubeFcstPfm.class); */
		// ObjectifyService.register(VatRate.class);
		// ObjectifyService.register(Service.class);
	}

}
