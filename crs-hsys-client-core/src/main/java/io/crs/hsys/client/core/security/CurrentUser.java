package io.crs.hsys.client.core.security;

import hu.hw.cloud.shared.dto.common.AccountDto;
import io.crs.hsys.shared.dto.common.AppUserDto;
import io.crs.hsys.shared.dto.hotel.HotelDtor;

public class CurrentUser {

	private AccountDto account;
	
	private AppUserDto appUserDto;

	private HotelDtor currentHotel;

	private boolean loggedIn;

	public AppUserDto getAppUserDto() {
		return appUserDto;
	}

	public void setAppUserDto(AppUserDto userDto) {
		this.appUserDto = userDto;
		this.currentHotel = userDto.getDefaultHotel();
	}

	public AccountDto getAccount() {
		return account;
	}

	public void setAccount(AccountDto account) {
		this.account = account;
	}

	public HotelDtor getCurrentHotel() {
		return currentHotel;
	}

	public void setCurrentHotel(HotelDtor currentHotel) {
		this.currentHotel = currentHotel;
	}

	public boolean isLoggedIn() {
		return loggedIn;
	}

	public void setLoggedIn(boolean loggedIn) {
		this.loggedIn = loggedIn;
	}

	@Override
	public String toString() {
		String ret = "CurrentUser:{appUserDto=" + appUserDto + ", currentHotelDto=" + currentHotel + ", loggedIn=" + loggedIn +", " + super.toString() + "}";
		return ret;
	}

}
