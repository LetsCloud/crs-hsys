/**
 * 
 */
package io.crs.hsys.client.kip.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.google.inject.Inject;

import io.crs.hsys.shared.constans.RoomStatus;
import io.crs.hsys.shared.constans.TaskKind;
import io.crs.hsys.shared.constans.UserPerm;
import io.crs.hsys.shared.dto.common.AppUserDtor;
import io.crs.hsys.shared.dto.hotel.RoomDto;
import io.crs.hsys.shared.dto.hotel.RoomTypeDtor;
import io.crs.hsys.shared.dto.task.TaskDto;
import io.crs.hsys.shared.dto.task.TaskTypeDto;

/**
 * @author robi
 *
 */
public class DataBuilder {

	public static final String AU_KIPI = "KIPI";
	public static final String AU_KARA = "KARA";

	public static final String RT_DBLB = "DBLB";
	public static final String RT_TWIN = "TWIN";

	public static final String R_1001 = "1001";
	public static final String R_1002 = "1002";
	public static final String R_1003 = "1003";
	public static final String R_1004 = "1004";

	public static final String TT_DAILY = "DAILY";
	public static final String TT_LINEN = "LINEN";
	public static final String TT_TAPREP = "TAPREP";
	public static final String TT_FRUIT = "FRUIT";
	public static final String TT_TURCSI = "TÜRCSI";
	public static final String TT_RECI = "RECI";

	public static final String T_001 = "T001";

	private List<AppUserDtor> appUserDtors = new ArrayList<AppUserDtor>();
	private List<RoomTypeDtor> roomTypeDtor = new ArrayList<RoomTypeDtor>();
	private List<RoomDto> roomDtos = new ArrayList<RoomDto>();
	private List<TaskTypeDto> taskTypeDtos = new ArrayList<TaskTypeDto>();
	private List<TaskDto> taskDtos = new ArrayList<TaskDto>();

	@Inject
	DataBuilder() {
		buildAppUserDtors();
		buildRoomTypeDtors();
		buildRoomDtos();
		buildTaskTypeDtos();
		buildTaskDtos();
	}

	private void buildAppUserDtors() {
		appUserDtors.clear();
		appUserDtors.add(new AppUserDtor.Builder().code(AU_KIPI).name("Kipi Viki")
				.permissions(new ArrayList<UserPerm>(Arrays.asList(UserPerm.UP_HOUSEKEEPER))).build());
		appUserDtors.add(new AppUserDtor.Builder().code(AU_KARA).name("Kara Karesz")
				.permissions(new ArrayList<UserPerm>(Arrays.asList(UserPerm.UP_TECHNICIAN))).build());
	}

	public AppUserDtor getAppUserDtor(String code) {
		return appUserDtors.stream().filter(o -> o.getCode().equals(code)).findFirst().get();
	}

	private void buildRoomTypeDtors() {
		roomTypeDtor.clear();
		roomTypeDtor.add(RoomTypeDtor.builder().code(RT_DBLB).name("Double bed room").build());
		roomTypeDtor.add(RoomTypeDtor.builder().code(RT_TWIN).name("Twin room").build());
	}

	public RoomTypeDtor getRoomTypeDtor(String code) {
		return roomTypeDtor.stream().filter(o -> o.getCode().equals(code)).findFirst().get();
	}

	private void buildRoomDtos() {
		roomDtos.add(new RoomDto.Builder().code(R_1001).roomType(getRoomTypeDtor(RT_DBLB)).roomStatus(RoomStatus.DIRTY)
				.build());
		roomDtos.add(new RoomDto.Builder().code(R_1002).roomType(getRoomTypeDtor(RT_TWIN)).roomStatus(RoomStatus.CLEAN)
				.build());
		roomDtos.add(new RoomDto.Builder().code(R_1003).roomType(getRoomTypeDtor(RT_TWIN))
				.roomStatus(RoomStatus.INSPECTED).build());
		roomDtos.add(new RoomDto.Builder().code(R_1004).roomType(getRoomTypeDtor(RT_DBLB)).roomStatus(RoomStatus.OOO)
				.build());
	}

	public RoomDto getRoomDto(String code) {
		return roomDtos.stream().filter(o -> o.getCode().equals(code)).findFirst().get();
	}

	private void buildTaskTypeDtos() {
		taskTypeDtos.add(
				new TaskTypeDto.Builder().kind(TaskKind.TK_CLEANING).code(TT_DAILY).description("Napi takarítás").build());
		taskTypeDtos.add(
				new TaskTypeDto.Builder().kind(TaskKind.TK_CLEANING).code(TT_LINEN).description("Ágynemű csere").build());
		taskTypeDtos.add(new TaskTypeDto.Builder().kind(TaskKind.TK_MAINTENANCE).code(TT_TAPREP)
				.description("Csaptelep javítás").build());
		taskTypeDtos.add(
				new TaskTypeDto.Builder().kind(TaskKind.TK_REQUEST).code(TT_FRUIT).description("Gyümölcskosár").build());
		taskTypeDtos.add(new TaskTypeDto.Builder().kind(TaskKind.TK_REQUEST).code(TT_TURCSI).description("Extra törölköző")
				.build());
		taskTypeDtos.add(new TaskTypeDto.Builder().kind(TaskKind.TK_COMMON).code(TT_RECI).description("Recepció").build());
	}

	public TaskTypeDto getTaskTypeDto(String code) {
		return taskTypeDtos.stream().filter(o -> o.getCode().equals(code)).findFirst().get();
	}

	private void buildTaskDtos() {
		taskDtos.add(TaskDto.builder().webSafeKey(T_001).kind(TaskKind.TK_CLEANING).type(getTaskTypeDto(TT_DAILY))
				.assignee(getAppUserDtor(AU_KIPI)).build());
	}
}
