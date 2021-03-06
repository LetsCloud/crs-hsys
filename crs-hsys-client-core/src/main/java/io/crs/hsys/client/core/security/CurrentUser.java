package io.crs.hsys.client.core.security;

import io.crs.hsys.shared.cnst.Language;
import io.crs.hsys.shared.dto.common.AppUserDto;
import io.crs.hsys.shared.dto.hotel.HotelDtor;

public class CurrentUser {
	
	private AppUserDto appUserDto;

	private HotelDtor currentHotel;

	private String locale;

	private Language language;

	private boolean loggedIn;

	public AppUserDto getAppUserDto() {
		return appUserDto;
	}

	public void setAppUserDto(AppUserDto userDto) {
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

	public String getLocale() {
		return locale;
	}

	public void setLocale(String locale) {
		this.locale = locale;
	}

	public Language getLanguage() {
		return language;
	}

	public void setLanguage(Language language) {
		this.language = language;
	}

	@Override
	public String toString() {
		String ret = "CurrentUser:{appUserDto=" + appUserDto + ", currentHotelDto=" + currentHotel + ", loggedIn=" + loggedIn +", " + super.toString() + "}";
		return ret;
	}

}
