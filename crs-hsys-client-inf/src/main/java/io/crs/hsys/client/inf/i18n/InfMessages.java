/**
 * 
 */
package io.crs.hsys.client.inf.i18n;

import com.google.gwt.i18n.client.Messages;

/**
 * @author CR
 *
 */
public interface InfMessages extends Messages {

	/*
	 * SIDE NAV
	 */
	@DefaultMessage("Dashboard")
	String mainMenuItemDashhboard();

	@DefaultMessage("Performance analysis")
	String mainMenuGroupPerformance();

	@DefaultMessage("Room nts performacce")
	String mainMenuItemPerfRoom();

	@DefaultMessage("Room nts by market")
	String mainMenuItemPerfRoomMarket();

	@DefaultMessage("Room nts by day and market")
	String mainMenuItemPerfRoomDayMarket();

	@DefaultMessage("Occupancy and ADR")
	String mainMenuItemPerfOccAdr();

	@DefaultMessage("Pick-up analysis")
	String mainMenuGroupPickup();

	@DefaultMessage("PickUp report")
	String mainMenuItemPickUp();

	@DefaultMessage("Pace analysis")
	String mainMenuGroupPace();

	@DefaultMessage("PickUp report")
	String mainMenuItemPickUp2();

	@DefaultMessage("Booking curve analysis")
	String mainMenuGroupCurve();

	@DefaultMessage("WEB page analysis")
	String mainMenuGroupWeb();

	@DefaultMessage("Social media analysis")
	String mainMenuGroupSocial();

	@DefaultMessage("Satisfaction analysis")
	String mainMenuGroupSatisfaction();

	@DefaultMessage("Benchmark analysis")
	String mainMenuGroupBenchmark();

	/*
	 * GPS
	 */
	@DefaultMessage("Global period selection")
	String gpsTitle();

	@DefaultMessage("Actual")
	String gpsActualPeriod();

	@DefaultMessage("From")
	String gpsActualFrom();

	@DefaultMessage("To")
	String gpsActualTo();

	@DefaultMessage("Base")
	String gpsBasePeriod();

	@DefaultMessage("From")
	String gpsBaseFrom();

	@DefaultMessage("Time scale")
	String gpsTimeScale();

}
