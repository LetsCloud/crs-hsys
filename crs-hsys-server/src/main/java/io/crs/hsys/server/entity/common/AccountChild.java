/**
 * 
 */
package io.crs.hsys.server.entity.common;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.googlecode.objectify.Ref;
import com.googlecode.objectify.annotation.Parent;

import io.crs.hsys.server.entity.BaseEntity;

/**
 * Az Account entitás gyermekeinek őse.
 * <p>
 * Az ős Account-ra való hivatkozással egészül ki.
 * 
 * @author robi
 *
 */
public class AccountChild extends BaseEntity {
	private static final Logger logger = LoggerFactory.getLogger(AccountChild.class.getName());

	@Parent
	private Ref<Account> account;

	/**
	 * Objectify miatt van rá szükség.
	 */
	public AccountChild() {
		super();
//		logger.info("AccountChild()");
	}

	public AccountChild(AccountChild source) {
		super(source);
		account = source.account;
	}

	public Account getAccount() {
		if (account == null)
			return null;
		return account.get();
	}

	public void setAccount(Account account) {
//		logger.info("setAccount()->" + account);
		if (account.getId() != null)
			this.account = Ref.create(account);
	}

	@Override
	public String toString() {
		return "AccountChild:[account=" + getAccount() + ", " + super.toString() + "]";
	}

	/**
	 * 
	 * @author CR
	 *
	 * @param <T>
	 */
	public static class Builder<T extends Builder<?>> {

		private Account account;

		public Builder() {
		}

		@SuppressWarnings("unchecked")
		public T account(Account account) {
			this.account = account;
			return (T) this;
		}

		public AccountChild build() {
			return new AccountChild(this);
		}
	}

	protected AccountChild(Builder<?> builder) {
		this.setAccount(builder.account);
	}
}
