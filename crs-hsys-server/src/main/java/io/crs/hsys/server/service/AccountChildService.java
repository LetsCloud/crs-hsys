package io.crs.hsys.server.service;

import io.crs.hsys.server.entity.BaseEntity;
import io.crs.hsys.server.entity.common.Account;

public interface AccountChildService<T extends BaseEntity> extends CrudService<T> {
	Account getCurrentAccount();
}
