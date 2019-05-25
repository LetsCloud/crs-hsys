/**
 * 
 */
package io.crs.hsys.server.repository.ofy;

import io.crs.hsys.server.entity.doc.Contract;
import io.crs.hsys.server.entity.doc.Document;
import io.crs.hsys.server.repository.ContractRepository;
import io.crs.hsys.shared.exception.cnst.ErrorMessageCode;

/**
 * @author robi
 *
 */
public class ContractRepositoryImpl extends AccountChildRepositoryImpl<Contract> implements ContractRepository {

	public ContractRepositoryImpl() {
		super(Contract.class);
	}

	@Override
	protected void loadUniqueIndexMap(Contract entiy) {
		if ((entiy.getCode() != null) && (!entiy.getCode().isEmpty()))
			entiy.addUniqueIndex(Document.PROPERTY_CODE, entiy.getCode(),
					ErrorMessageCode.CONTRACT_CODE_ALREADY_EXISTS);
	}
}
