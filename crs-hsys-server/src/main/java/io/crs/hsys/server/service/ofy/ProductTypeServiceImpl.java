/**
 * 
 */
package io.crs.hsys.server.service.ofy;

import io.crs.hsys.server.entity.doc.ProductType;
import io.crs.hsys.server.repository.AccountRepository;
import io.crs.hsys.server.repository.ProductTypeRepository;
import io.crs.hsys.server.service.ProductTypeService;

/**
 * @author robi
 *
 */
public class ProductTypeServiceImpl extends AccountChildServiceImpl<ProductType, ProductTypeRepository>
		implements ProductTypeService {

	public ProductTypeServiceImpl(ProductTypeRepository repository, AccountRepository accountRepository) {
		super(repository, accountRepository);
	}
}
