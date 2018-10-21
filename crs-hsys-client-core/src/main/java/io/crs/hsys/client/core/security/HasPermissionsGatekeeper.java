/**
 * 
 */
package io.crs.hsys.client.core.security;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.google.inject.Inject;
import com.gwtplatform.mvp.client.proxy.GatekeeperWithParams;

import io.crs.hsys.shared.dto.common.RoleDto;

/**
 * @author CR
 *
 */
public class HasPermissionsGatekeeper implements GatekeeperWithParams {
	private static final Logger LOGGER = Logger.getLogger(HasPermissionsGatekeeper.class.getName());

	private final CurrentUser currentUser;
	private String[] requiredPermissions;

	@Inject
	public HasPermissionsGatekeeper(CurrentUser currentUser) {
		this.currentUser = currentUser;
	}

	@Override
	public GatekeeperWithParams withParams(String[] params) {
		requiredPermissions = params;
		return this;
	}

	@Override
	public boolean canReveal() {
		LOGGER.log(Level.INFO, "canReveal()->" + currentUser.getAppUserDto());
		if (currentUser.getAppUserDto().getAdmin())
			return true;

		LOGGER.log(Level.INFO, "canReveal()-2");

		List<RoleDto> roleDtos = currentUser.getAppUserDto().getRoles();
		if (roleDtos == null)
			return false;

		LOGGER.log(Level.INFO, "canReveal()-3");

		List<String> permissions = new ArrayList<String>();
		for (RoleDto roleDto : roleDtos) {
			permissions.addAll(roleDto.getPermissions());
		}
		return permissions.containsAll(Arrays.asList(requiredPermissions));
	}
}