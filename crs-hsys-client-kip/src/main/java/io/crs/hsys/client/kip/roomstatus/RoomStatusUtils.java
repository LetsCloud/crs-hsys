/**
 * 
 */
package io.crs.hsys.client.kip.roomstatus;

import gwt.material.design.addext.client.ui.constants.MdiType;
import gwt.material.design.client.constants.Color;
import gwt.material.design.client.constants.IconType;
import io.crs.hsys.shared.constans.OccStatus;
import io.crs.hsys.shared.constans.RoomStatus;
import io.crs.hsys.shared.constans.TaskKind;

/**
 * @author robi
 *
 */
public class RoomStatusUtils {

	public static Color getStatusBgColor(RoomStatus roomStatus) {
		switch (roomStatus) {
		case DIRTY:
			return Color.RED_LIGHTEN_2;
		case CLEAN:
			return Color.BLUE_LIGHTEN_2;
		case INSPECTED:
			return Color.GREEN_LIGHTEN_2;
		case OOO:
			return Color.GREY_DARKEN_3;
		case OOS:
			return Color.PURPLE_LIGHTEN_2;
		case SHOW:
			return Color.AMBER_LIGHTEN_2;
		default:
			break;
		}
		return null;
	}

	public static Color getStatusIconColor(RoomStatus roomStatus) {
		switch (roomStatus) {
		case DIRTY:
			return Color.RED_LIGHTEN_4;
		case CLEAN:
			return Color.BLUE_LIGHTEN_4;
		case INSPECTED:
			return Color.GREEN_LIGHTEN_4;
		case OOO:
			return Color.GREY_LIGHTEN_1;
		case OOS:
			return Color.PURPLE_LIGHTEN_4;
		case SHOW:
			return Color.AMBER_LIGHTEN_4;
		default:
			break;
		}
		return null;
	}

	public static MdiType getStatusIcon(RoomStatus roomStatus) {
		switch (roomStatus) {
		case DIRTY:
			return MdiType.DELETE;
		case CLEAN:
			return MdiType.STAR;
		case INSPECTED:
			return MdiType.CHECK;
		case OOO:
			return MdiType.SETTINGS_OUTLINE;
		case OOS:
			return MdiType.CLOSE;
		case SHOW:
			return MdiType.EYE_OUTLINE;
		default:
			break;
		}
		return null;
	}

	public static IconType getStatusIcon2(RoomStatus roomStatus) {
		switch (roomStatus) {
		case DIRTY:
			return IconType.DELETE;
		case CLEAN:
			return IconType.STAR;
		case INSPECTED:
			return IconType.CHECK;
		case OOO:
			return IconType.SETTINGS;
		case OOS:
			return IconType.CLOSE;
		case SHOW:
			return IconType.VISIBILITY;
		default:
			break;
		}
		return null;
	}

	public static Color getOccStatusColor(OccStatus occStatus) {
		switch (occStatus) {
		case VACANT:
			return Color.GREY;
		case EARLYCI:
			return Color.GREEN_LIGHTEN_2;
		case CHECKIN:
			return Color.GREEN_LIGHTEN_2;
		case INHOUSE:
			return Color.BLUE_LIGHTEN_2;
		case CHECKOUT:
			return Color.RED_LIGHTEN_2;
		case LATECO:
			return Color.RED_LIGHTEN_2;
		case OOO:
			return Color.BLACK;
		case OOS:
			return Color.PURPLE;
		case SHOW:
			return Color.AMBER;
		default:
			break;
		}
		return null;
	}

	public static MdiType getOccStatusIcon(OccStatus occStatus) {
		switch (occStatus) {
		case VACANT:
			return MdiType.CHECKBOX_BLANK;
		case EARLYCI:
			return MdiType.ALPHA_E_BOX;
		case CHECKIN:
			return MdiType.ARROW_DOWN_BOX;
		case INHOUSE:
			return MdiType.ACCOUNT_BOX;
		case CHECKOUT:
			return MdiType.ARROW_UP_BOX;
		case LATECO:
			return MdiType.ALPHA_L_BOX;
		case OOO:
			return MdiType.SETTINGS_BOX;
		case OOS:
			return MdiType.CLOSE_BOX;
		case SHOW:
			return MdiType.EYE;
		default:
			break;
		}
		return null;
	}

	public static IconType getTaskIcon(TaskKind kind) {
		switch (kind) {
		case COMMON:
			return IconType.ASSIGNMENT;
		case CLEANING:
			return IconType.BRUSH;
		case MAINTENANCE:
			return IconType.BUILD;
		case REQUEST:
			return IconType.FAVORITE;
		default:
			break;
		}
		return null;
	}

	public static Color getTaskBgColor(TaskKind kind) {
		switch (kind) {
		case COMMON:
			return Color.AMBER_LIGHTEN_5;
		case CLEANING:
			return Color.CYAN_LIGHTEN_5;
		case MAINTENANCE:
			return Color.PURPLE_LIGHTEN_5;
		case REQUEST:
			return Color.RED_LIGHTEN_5;
		default:
			break;
		}
		return null;
	}

	public static Color getTaskColor(TaskKind kind) {
		switch (kind) {
		case COMMON:
			return Color.AMBER;
		case CLEANING:
			return Color.CYAN_DARKEN_1;
		case MAINTENANCE:
			return Color.PURPLE_LIGHTEN_2;
		case REQUEST:
			return Color.RED_LIGHTEN_2;
		default:
			break;
		}
		return null;
	}

}
