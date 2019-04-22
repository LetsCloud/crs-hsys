/**
 * 
 */
package io.crs.hsys.server.repository.ofy;

import io.crs.hsys.server.entity.ForeignKey;
import io.crs.hsys.server.entity.doc.Contract;
import io.crs.hsys.server.entity.doc.ProductType;
import io.crs.hsys.server.repository.ContractRepository;
import io.crs.hsys.server.repository.ProductTypeRepository;
import io.crs.hsys.shared.exception.ExceptionSubType;

/**
 * @author robi
 *
 */
public class ProductTypeRepositoryImpl extends AccountChildRepositoryImpl<ProductType>
		implements ProductTypeRepository {

	protected ProductTypeRepositoryImpl(ContractRepository contractRepository) {
		super(ProductType.class);
		foreignKeys.add(new ForeignKey(Contract.PROPERTY_PRICESTYPE, contractRepository,
				ExceptionSubType.PRODUCTTYPE_IS_USED_BY_CONTRACT));
	}

	@Override
	protected void loadUniqueIndexMap(ProductType entiy) {
		if ((entiy.getCode() != null) && (!entiy.getCode().isEmpty()))
			entiy.addUniqueIndex(ProductType.PROPERTY_CODE, entiy.getCode(),
					ExceptionSubType.PRODUCTTYPE_CODE_ALREADY_EXISTS);
	}
}
