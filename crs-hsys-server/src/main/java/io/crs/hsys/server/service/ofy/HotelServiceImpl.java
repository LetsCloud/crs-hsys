/**
 * 
 */
package io.crs.hsys.server.service.ofy;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.crs.hsys.server.entity.hotel.Hotel;
import io.crs.hsys.server.repository.AccountRepository;
import io.crs.hsys.server.repository.HotelRepository;
import io.crs.hsys.server.service.HotelService;

/**
 * @author robi
 *
 */
public class HotelServiceImpl extends CrudServiceImpl<Hotel, HotelRepository> implements HotelService {
	private static final Logger logger = LoggerFactory.getLogger(HotelServiceImpl.class.getName());

	private final AccountRepository accountRepository;

	public HotelServiceImpl(HotelRepository repository, AccountRepository accountRepository) {
		super(repository);
		logger.info("HotelServiceImpl");
		this.accountRepository = accountRepository;
	}

	@Override
	protected List<Object> getParents(Long accountId) {
		List<Object> parents = new ArrayList<Object>();
		parents.add(accountRepository.findById(accountId));
		return parents;
	}

	@Override
	protected List<Object> getParents(String accountWebSafeKey) {
		List<Object> parents = new ArrayList<Object>();
		parents.add(accountRepository.findByWebSafeKey(accountWebSafeKey));
		return parents;
	}
}
