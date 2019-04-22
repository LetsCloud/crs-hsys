/**
 * 
 */
package io.crs.hsys.server.service.ofy;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.crs.hsys.server.entity.doc.Quotation;
import io.crs.hsys.server.entity.profile.Organization;
import io.crs.hsys.server.repository.OrganizationRepository;
import io.crs.hsys.server.repository.QuotationRepository;
import io.crs.hsys.server.service.AccountService;
import io.crs.hsys.server.service.QuotationService;

/**
 * @author robi
 *
 */
public class QuotationServiceImpl extends AccountChildServiceImpl<Quotation, QuotationRepository>
		implements QuotationService {

	private final OrganizationRepository organizationRepository;

	public QuotationServiceImpl(QuotationRepository repository, AccountService accountService,
			OrganizationRepository organizationRepository) {
		super(repository, accountService);
		this.organizationRepository = organizationRepository;
	}

	@Override
	public List<Quotation> findByOrg(String organizationKey) {
		String currentAccountKey = getCurrentAccount().getWebSafeKey();
		Organization org = organizationRepository.findByWebSafeKey(organizationKey);
		if (org == null)
			return getChildren(currentAccountKey);
		Map<String, Object> filters = new HashMap<String, Object>();
		filters.put("organization", org);
		return getChildrenByFilters(currentAccountKey, filters);
	}
}
