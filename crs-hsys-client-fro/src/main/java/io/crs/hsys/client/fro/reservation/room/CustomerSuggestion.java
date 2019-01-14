/**
 * 
 */
package io.crs.hsys.client.fro.reservation.room;

import com.google.gwt.user.client.ui.SuggestOracle;

/**
 * @author robi
 *
 */
public class CustomerSuggestion implements SuggestOracle.Suggestion {

    private Customer customer;

    public CustomerSuggestion(Customer customer) {
        this.customer = customer;
    }

    @Override
    public String getDisplayString() {
        return getReplacementString();
    }

    @Override
    public String getReplacementString() {
        return customer.getName();
    }

    public Customer getCustomer() {
        return customer;
    }
}
