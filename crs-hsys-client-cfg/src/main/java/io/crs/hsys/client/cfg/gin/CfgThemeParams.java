package io.crs.hsys.client.cfg.gin;

import io.crs.hsys.client.core.resources.BlueGreyThemeColors;
import io.crs.hsys.client.core.resources.ThemeParams;

public class CfgThemeParams implements ThemeParams {

	@Override
	public String getPrimaryColor() {
		return BlueGreyThemeColors.C_PRIMARY;
	}

	@Override
	public String getPrimaryLightColor() {
		return BlueGreyThemeColors.C_PRIMARY_LIGHT;
	}

	@Override
	public String getSecondaryColor() {
		return BlueGreyThemeColors.C_SECONDARY;
	}

	@Override
	public String getSecondaryLightColor() {
		return BlueGreyThemeColors.C_SECONDARY_LIGHT;
	}

	@Override
	public String getSecondaryDarkColor() {
		return BlueGreyThemeColors.C_SECONDARY_DARK;
	}

	@Override
	public String getPrimaryDarkColor() {
		return BlueGreyThemeColors.C_PRIMARY_DARK;
	}
}
