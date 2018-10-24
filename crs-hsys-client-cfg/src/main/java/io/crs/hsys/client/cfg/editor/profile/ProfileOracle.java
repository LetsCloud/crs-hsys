/**
 * 
 */
package io.crs.hsys.client.cfg.editor.profile;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import gwt.material.design.addins.client.autocomplete.base.MaterialSuggestionOracle;
import io.crs.hsys.shared.dto.profile.ProfileDtor;

/**
 * @author robi
 *
 */
public class ProfileOracle extends MaterialSuggestionOracle {
	private static Logger logger = Logger.getLogger(ProfileOracle.class.getName());

	private List<ProfileDtor> profiles = new ArrayList<ProfileDtor>();

	public void addProfiles(List<ProfileDtor> profiles) {
		this.profiles.addAll(profiles);
	}

	@Override
	public void requestSuggestions(Request request, Callback callback) {
		logger.info("requestSuggestions()");
		Response resp = new Response();
		if (profiles.isEmpty()) {
			logger.info("requestSuggestions()-(profiles.isEmpty())");
			callback.onSuggestionsReady(request, resp);
			return;
		}
		String text = request.getQuery();
		text = text.toLowerCase();
		logger.info("requestSuggestions()->text="+text);

		List<ProfileSuggestion> list = new ArrayList<>();

		for (ProfileDtor profile : profiles) {
			if (profile.getName().toLowerCase().contains(text)) {
				list.add(new ProfileSuggestion(profile));
			}
		}

		resp.setSuggestions(list);
		callback.onSuggestionsReady(request, resp);
	}
}