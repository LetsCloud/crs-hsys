/**
 * 
 */
package io.crs.hsys.client.kip.roomstatus;

import gwt.material.design.addext.client.ui.constants.MdiType;
import gwt.material.design.client.constants.Color;
import gwt.material.design.client.constants.IconType;
import io.crs.hsys.shared.cnst.OccStatus;
import io.crs.hsys.shared.cnst.RoomStatus;
import io.crs.hsys.shared.cnst.TaskKind;

/**
 * @author robi
 *
 */
public class RoomStatusUtils {

	public static Color getStatusBgColor(RoomStatus roomStatus) {
		if (roomStatus == null) return Color.BLUE_GREY;
		
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
			return Color.PURPLE_LIGHTEN_3;
		case SHOW:
			return Color.AMBER_LIGHTEN_1;
		default:
			break;
		}
		return null;
	}

	public static Color getStatusIconColor(RoomStatus roomStatus) {
		if (roomStatus == null) return Color.BLACK;

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
			return Color.PURPLE_LIGHTEN_5;
		case SHOW:
			return Color.AMBER_LIGHTEN_5;
		default:
			break;
		}
		return null;
	}

	public static MdiType getStatusIcon(RoomStatus roomStatus) {
		if (roomStatus == null) return MdiType.ALERT;

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
			return MdiType.ALERT;
		case SHOW:
			return MdiType.EYE_OUTLINE;
		default:
			break;
		}
		return null;
	}

	public static IconType getStatusIcon2(RoomStatus roomStatus) {
		if (roomStatus == null) return IconType.WARNING;
		
		switch (roomStatus) {
		case DIRTY:
			return IconType.DELETE;
		case CLEAN:
			return IconType.STAR;
		case INSPECTED:
			return IconType.DONE_ALL;
		case OOO:
			return IconType.SETTINGS;
		case OOS:
			return IconType.WARNING;
		case SHOW:
			return IconType.VISIBILITY;
		default:
			break;
		}
		return null;
	}

	public static Color getOccStatusColor(OccStatus occStatus) {
		if (occStatus == null) return Color.BLUE_GREY;

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
		if (occStatus == null) return MdiType.ALERT;
		
		switch (occStatus) {
		case VACANT:
			return MdiType.CHECKBOX_BLANK_OUTLINE;
		case EARLYCI:
			return MdiType.ALPHA_E_BOX;
		case CHECKIN:
			return MdiType.ARROW_LEFT_BOX;
		case INHOUSE:
			return MdiType.HOTEL;
		case CHECKOUT:
			return MdiType.ARROW_RIGHT_BOX;
		case LATECO:
			return MdiType.ALPHA_L_BOX;
		case OOO:
			return MdiType.SETTINGS_BOX;
		case OOS:
			return MdiType.ALERT;
		case SHOW:
			return MdiType.EYE;
		default:
			break;
		}
		return null;
	}

	public static IconType getTaskIcon(TaskKind kind) {
		switch (kind) {
//		case TK_COMMON:
//			return IconType.ASSIGNMENT;
		case TK_CLEANING:
			return IconType.BRUSH;
		case TK_MAINTENANCE:
			return IconType.BUILD;
//		case TK_REQUEST:
//			return IconType.FAVORITE;
		default:
			break;
		}
		return null;
	}

	public static Color getTaskBgColor(TaskKind kind) {
		switch (kind) {
//		case TK_COMMON:
//			return Color.AMBER_LIGHTEN_5;
		case TK_CLEANING:
			return Color.CYAN_LIGHTEN_5;
		case TK_MAINTENANCE:
			return Color.PURPLE_LIGHTEN_5;
//		case TK_REQUEST:
//			return Color.RED_LIGHTEN_5;
		default:
			break;
		}
		return null;
	}

	public static Color getTaskColor(TaskKind kind) {
		switch (kind) {
//		case TK_COMMON:
//			return Color.AMBER;
		case TK_CLEANING:
			return Color.CYAN_DARKEN_1;
		case TK_MAINTENANCE:
			return Color.PURPLE_LIGHTEN_2;
//		case TK_REQUEST:
//			return Color.RED_LIGHTEN_2;
		default:
			break;
		}
		return null;
	}

}
