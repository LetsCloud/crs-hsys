/**
 * 
 */
package io.crs.hsys.client.fro.reservation.room;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import gwt.material.design.addins.client.autocomplete.base.MaterialSuggestionOracle;

/**
 * @author robi
 *
 */
public class CustomerOracle extends MaterialSuggestionOracle {

	private List<Customer> customers = new LinkedList<>();

	public void addContacts(List<Customer> customers) {
		customers.addAll(customers);
	}

	@Override
	public void requestSuggestions(Request request, Callback callback) {
		Response resp = new Response();
		if (customers.isEmpty()) {
			callback.onSuggestionsReady(request, resp);
			return;
		}
		String text = request.getQuery();
		text = text.toLowerCase();

		List<CustomerSuggestion> list = new ArrayList<>();

		for (Customer customer : customers) {
			if (customer.getName().toLowerCase().contains(text)) {
				list.add(new CustomerSuggestion(customer));
			}
		}

		resp.setSuggestions(list);
		callback.onSuggestionsReady(request, resp);
	}
}