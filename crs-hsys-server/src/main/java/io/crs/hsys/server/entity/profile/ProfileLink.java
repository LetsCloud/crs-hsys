/**
 * 
 */
package io.crs.hsys.server.entity.profile;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.googlecode.objectify.Ref;
import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Index;

import io.crs.hsys.server.entity.common.AccountChild;

/**
 * @author robi
 *
 */
@Entity
public class ProfileLink extends AccountChild {
	private static final Logger logger = LoggerFactory.getLogger(ProfileLink.class);

	public static final String FLD_PROFILE_FORWARD = "forward";
	public static final String FLD_PROFILE_REVERSE = "reverse";

	@Index
	private Ref<Profile> forward;

	@Index
	private Ref<Profile> reverse;

	private Ref<Relationship> relationship;

	public ProfileLink() {
		logger.info("ProfileLink()");
	}

	public Profile getForward() {
		if (forward == null)
			return null;
		return forward.get();
	}

	public void setForward(Profile forward) {
		if (forward.getId() != null)
			this.forward = Ref.create(forward);
	}

	public Profile getReverse() {
		if (reverse == null)
			return null;
		return reverse.get();
	}

	public void setReverse(Profile reverse) {
		if (reverse.getId() != null)
			this.reverse = Ref.create(reverse);
	}

	public Relationship getRelationship() {
		if (relationship == null)
			return null;
		return relationship.get();
	}

	public void setRelationship(Relationship relationshsip) {
		logger.info("setRelationship()");
		if (relationshsip.getId() == null)
			return;
		logger.info("setRelationship()->relationship.getId()=" + relationshsip.getId());
		this.relationship = Ref.create(relationshsip);
	}

	@Override
	public String toString() {
		return "ProfileLink:[profileForward=" + getForward() + ", profileReverse=" + getReverse() + ", relationship="
				+ getRelationship() + ", " + super.toString() + "]";
	}
}
