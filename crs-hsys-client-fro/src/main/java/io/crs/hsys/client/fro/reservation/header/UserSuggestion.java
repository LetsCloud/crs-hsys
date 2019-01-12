/**
 * 
 */
package io.crs.hsys.client.fro.reservation.header;

import com.google.gwt.user.client.ui.SuggestOracle;

/**
 * @author robi
 *
 */
public class UserSuggestion implements SuggestOracle.Suggestion {

    private User user;

    public UserSuggestion(User user) {
        this.user = user;
    }

    @Override
    public String getDisplayString() {
        return getReplacementString();
    }

    @Override
    public String getReplacementString() {
        return user.getName();
    }

    public User getUser() {
        return user;
    }
}