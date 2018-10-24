/**
 * 
 */
package io.crs.hsys.server.repository.ofy;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.googlecode.objectify.ObjectifyService;

import io.crs.hsys.server.entity.common.Account;
import io.crs.hsys.server.entity.common.AppUser;
import io.crs.hsys.server.entity.common.UserGroup;
import io.crs.hsys.server.entity.hotel.Hotel;
import io.crs.hsys.server.entity.profile.Contact;
import io.crs.hsys.server.entity.profile.Guest;
import io.crs.hsys.server.entity.profile.Organization;
import io.crs.hsys.server.entity.profile.Person;
import io.crs.hsys.server.entity.profile.Profile;
import io.crs.hsys.server.entity.profile.ProfileGroup;
import io.crs.hsys.server.entity.profile.ProfileLink;
import io.crs.hsys.server.entity.profile.Relationship;


/**
 * @author CR
 *
 */
public class ObjectifyRegistration {
	private static final Logger loger = LoggerFactory.getLogger(ObjectifyRegistration.class);

	static {
		loger.info("ObjectifyService.register");
		ObjectifyService.register(Account.class);
		ObjectifyService.register(AppUser.class);
		ObjectifyService.register(UserGroup.class);
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
	
		ObjectifyService.register(Hotel.class);
/*		ObjectifyService.register(RoomType.class);
		ObjectifyService.register(Room.class);
		ObjectifyService.register(MarketGroup.class);
		ObjectifyService.register(Chat.class);
		ObjectifyService.register(Task.class);
		ObjectifyService.register(Reservation.class);
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
