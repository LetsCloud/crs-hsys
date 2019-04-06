/**
 * 
 */
package io.crs.hsys.server.repository.ofy;

import io.crs.hsys.server.entity.profile.Relationship;
import io.crs.hsys.server.repository.RelationshipRepository;

/**
 * @author robi
 *
 */
public class RelationshipRepositoryImpl extends AccountChildRepositoryImpl<Relationship>
		implements RelationshipRepository {

	public RelationshipRepositoryImpl() {
		super(Relationship.class);
	}
}
