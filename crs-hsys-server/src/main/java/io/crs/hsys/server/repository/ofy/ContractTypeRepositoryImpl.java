/**
 * 
 */
package io.crs.hsys.server.repository.ofy;

import io.crs.hsys.server.entity.ForeignKey;
import io.crs.hsys.server.entity.doc.Contract;
import io.crs.hsys.server.entity.doc.ContractType;
import io.crs.hsys.server.repository.ContractRepository;
import io.crs.hsys.server.repository.ContractTypeRepository;
import io.crs.hsys.shared.exception.cnst.ErrorMessageCode;

/**
 * @author robi
 *
 */
public class ContractTypeRepositoryImpl extends AccountChildRepositoryImpl<ContractType>
		implements ContractTypeRepository {

	protected ContractTypeRepositoryImpl(ContractRepository contractRepository) {
		super(ContractType.class);
		foreignKeys.add(new ForeignKey(Contract.PROPERTY_TYPE, contractRepository,
				ErrorMessageCode.CONTRACTTYPE_IS_USED_BY_CONTRACT));
	}

	@Override
	protected void loadUniqueIndexMap(ContractType entiy) {
		if ((entiy.getCode() != null) && (!entiy.getCode().isEmpty()))
			entiy.addUniqueIndex(ContractType.PROPERTY_CODE, entiy.getCode(),
					ErrorMessageCode.CONTRACTTYPE_CODE_ALREADY_EXISTS);
	}
}
