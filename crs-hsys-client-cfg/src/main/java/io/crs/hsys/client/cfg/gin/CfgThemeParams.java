package io.crs.hsys.client.cfg.gin;

import io.crs.hsys.client.core.resources.ThemeColorsBlueGrey;
import io.crs.hsys.client.core.resources.ThemeParams;

public class CfgThemeParams implements ThemeParams {

	@Override
	public String getPrimaryColor() {
		return ThemeColorsBlueGrey.C_PRIMARY;
	}

	@Override
	public String getPrimaryLightColor() {
		return ThemeColorsBlueGrey.C_PRIMARY_LIGHT;
	}

	@Override
	public String getSecondaryColor() {
		return ThemeColorsBlueGrey.C_SECONDARY;
	}

	@Override
	public String getSecondaryLightColor() {
		return ThemeColorsBlueGrey.C_SECONDARY_LIGHT;
	}

	@Override
	public String getSecondaryDarkColor() {
		return ThemeColorsBlueGrey.C_SECONDARY_DARK;
	}

	@Override
	public String getPrimaryDarkColor() {
		return ThemeColorsBlueGrey.C_PRIMARY_DARK;
	}
}
