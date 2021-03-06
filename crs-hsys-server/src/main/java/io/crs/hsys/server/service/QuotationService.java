/**
 * 
 */
package io.crs.hsys.server.service;

import java.util.List;

import io.crs.hsys.server.entity.doc.Quotation;

/**
 * @author robi
 *
 */
public interface QuotationService extends CrudService<Quotation> {
	
	List<Quotation> findByOrg(String organizationKey);

}
