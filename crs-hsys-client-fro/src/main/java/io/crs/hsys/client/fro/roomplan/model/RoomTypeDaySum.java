/**
 * 
 */
package io.crs.hsys.client.fro.roomplan.model;

/**
 * @author robi
 *
 */
class RoomTypeDaySum {
	
	private int available;
	
	private double price;

	public RoomTypeDaySum(int available, double price) {
		super();
		this.available = available;
		this.price = price;
	}

	public int getAvailable() {
		return available;
	}

	public void setAvailable(int available) {
		this.available = available;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

}
