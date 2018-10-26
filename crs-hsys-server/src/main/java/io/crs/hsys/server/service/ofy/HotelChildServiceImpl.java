/**
 * 
 */
package io.crs.hsys.server.service.ofy;

import java.util.ArrayList;
import java.util.List;

import io.crs.hsys.server.entity.BaseEntity;
import io.crs.hsys.server.entity.common.Account;
import io.crs.hsys.server.entity.hotel.Hotel;
import io.crs.hsys.server.repository.AccountRepository;
import io.crs.hsys.server.repository.HotelChildRepository;
import io.crs.hsys.server.repository.HotelRepository;
import io.crs.hsys.server.service.HotelChildService;

/**
 * @author robi
 *
 */
public abstract class HotelChildServiceImpl<T extends BaseEntity, R extends HotelChildRepository<T>>
		extends CrudServiceImpl<T, R> implements HotelChildService<T> {

	private final AccountRepository accountRepository;
	private final HotelRepository hotelRepository;

	public HotelChildServiceImpl(R repository, AccountRepository accountRepository, HotelRepository hotelRepository) {
		super(repository);
		this.accountRepository = accountRepository;
		this.hotelRepository = hotelRepository;
	}

	@Override
	protected List<Object> getParents(Long accountId) {
		Account account = accountRepository.findById(accountId);
		return getHotels(account);
	}

	@Override
	protected List<Object> getParents(String accountWebSafeKey) {
		Account account = accountRepository.findByWebSafeKey(accountWebSafeKey);
		return getHotels(account);
	}

	private List<Object> getHotels(Account account) {
		List<Object> parents = new ArrayList<Object>();
		List<Hotel> hotels = hotelRepository.getAll(account);
		for (Hotel hotel : hotels)
			parents.add(hotel);
		return parents;
	}

}
