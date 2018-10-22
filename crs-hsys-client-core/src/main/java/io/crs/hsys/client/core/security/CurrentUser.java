package io.crs.hsys.client.core.security;

import io.crs.hsys.shared.dto.common.AccountUserDto;
import io.crs.hsys.shared.dto.hotel.HotelDtor;

public class CurrentUser {
	
	private AccountUserDto appUserDto;

	private HotelDtor currentHotel;

	private boolean loggedIn;

	public AccountUserDto getAppUserDto() {
		return appUserDto;
	}

	public void setAppUserDto(AccountUserDto userDto) {
		this.appUserDto = userDto;
		this.currentHotel = userDto.getDefaultHotel();
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
