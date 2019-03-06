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
import io.crs.hsys.shared.constans.TaskStatus;
import io.crs.hsys.shared.constans.UserPerm;
import io.crs.hsys.shared.dto.common.AppUserDtor;
import io.crs.hsys.shared.dto.hotel.RoomDto;
import io.crs.hsys.shared.dto.hotel.RoomTypeDtor;
import io.crs.hsys.shared.dto.task.TaskDto;
import io.crs.hsys.shared.dto.task.TaskGroupDto;
import io.crs.hsys.shared.dto.task.TaskTypeDto;

/**
 * @author robi
 *
 */
public class DataBuilder {

	public static final String AU_HAKA = "HAKA";
	public static final String AU_KIPI = "KIPI";
	public static final String AU_SZAKI = "SZAKI";
	public static final String AU_KARA = "KARA";

	public static final String RT_DBLB = "DBLB";
	public static final String RT_TWIN = "TWIN";

	public static final String R_1001 = "1001";
	public static final String R_1002 = "1002";
	public static final String R_1003 = "1003";
	public static final String R_1007 = "1007";
	public static final String R_1008 = "1008";

	// Task Groups
	public static final String TG_STDCLEAN = "STDTAK";
	public static final String TG_XTRCLEA = "XTRTAK";
	public static final String TG_PUBCLEAN = "KÖZTAK";
	public static final String TG_AMENITIE = "BEKESZ";
	public static final String TG_INSPECT = "ELLENŐR";
	public static final String TG_ELECRTICS = "ELEKTRO";
	public static final String TG_PLUMB = "VÍZGÁZ";

	// Task Types
	public static final String TT_DAILY = "LAKTAK";
	public static final String TT_LINEN = "ÁGYAZ";
	public static final String TT_INSPEKT = "ELLENŐR";
	public static final String TT_TAPREP = "TAPREP";
	public static final String TT_FRUIT = "FRUIT";
	public static final String TT_TURCSI = "TÜRCSI";
	public static final String TT_RECI = "RECI";

	// Tasks
	public static final String T_001 = "T001";
	public static final String T_002 = "T002";
	public static final String T_003 = "T003";
	public static final String T_004 = "T004";
	public static final String T_005 = "T005";
	public static final String T_006 = "T006";
	public static final String T_007 = "T007";
	public static final String T_008 = "T008";
	public static final String T_009 = "T009";
	public static final String T_010 = "T010";

	private List<AppUserDtor> appUserDtors = new ArrayList<AppUserDtor>();
	private List<RoomTypeDtor> roomTypeDtor = new ArrayList<RoomTypeDtor>();
	private List<RoomDto> roomDtos = new ArrayList<RoomDto>();
	private List<TaskGroupDto> taskGroupDtos = new ArrayList<TaskGroupDto>();
	private List<TaskTypeDto> taskTypeDtos = new ArrayList<TaskTypeDto>();
	private List<TaskDto> taskDtos = new ArrayList<TaskDto>();

	@Inject
	DataBuilder() {
		buildAppUserDtors();
		buildRoomTypeDtors();
		buildRoomDtos();
		buildTaskGroupDtos();
		buildTaskTypeDtos();
		buildTaskDtos();
	}

	private void buildAppUserDtors() {
		appUserDtors.clear();
		appUserDtors.add(new AppUserDtor.Builder().code(AU_HAKA).name("Háká Kata")
				.permissions(new ArrayList<UserPerm>(Arrays.asList(UserPerm.UP_HKSUPERVISOR))).build());
		appUserDtors.add(new AppUserDtor.Builder().code(AU_KIPI).name("Kipi Viki")
				.permissions(new ArrayList<UserPerm>(Arrays.asList(UserPerm.UP_HOUSEKEEPER))).build());
		appUserDtors.add(new AppUserDtor.Builder().code(AU_SZAKI).name("Szaki Szabi")
				.permissions(new ArrayList<UserPerm>(Arrays.asList(UserPerm.UP_MAINTMANAGER))).build());
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
		roomDtos.clear();
		roomDtos.add(new RoomDto.Builder().code(R_1001).roomType(getRoomTypeDtor(RT_DBLB)).roomStatus(RoomStatus.DIRTY)
				.build());
		roomDtos.add(new RoomDto.Builder().code(R_1002).roomType(getRoomTypeDtor(RT_TWIN)).roomStatus(RoomStatus.CLEAN)
				.build());
		roomDtos.add(new RoomDto.Builder().code(R_1003).roomType(getRoomTypeDtor(RT_TWIN))
				.roomStatus(RoomStatus.INSPECTED).build());
		roomDtos.add(new RoomDto.Builder().code(R_1007).roomType(getRoomTypeDtor(RT_DBLB)).roomStatus(RoomStatus.DIRTY)
				.build());
		roomDtos.add(new RoomDto.Builder().code(R_1008).roomType(getRoomTypeDtor(RT_DBLB)).roomStatus(RoomStatus.DIRTY)
				.build());
	}

	public RoomDto getRoomDto(String code) {
		return roomDtos.stream().filter(o -> o.getCode().equals(code)).findFirst().get();
	}

	private void buildTaskGroupDtos() {
		taskGroupDtos.clear();
		taskGroupDtos.add(TaskGroupDto.builder().kind(TaskKind.TK_CLEANING).code(TG_STDCLEAN)
				.description("Standard szoba takarítás").build());
		taskGroupDtos.add(TaskGroupDto.builder().kind(TaskKind.TK_CLEANING).code(TG_XTRCLEA)
				.description("Extra szoba takarítás").build());
		taskGroupDtos.add(TaskGroupDto.builder().kind(TaskKind.TK_CLEANING).code(TG_PUBCLEAN)
				.description("Köztér takarítás").build());
		taskGroupDtos.add(
				TaskGroupDto.builder().kind(TaskKind.TK_CLEANING).code(TG_AMENITIE).description("Bekészítés").build());
		taskGroupDtos.add(
				TaskGroupDto.builder().kind(TaskKind.TK_CLEANING).code(TG_INSPECT).description("Ellenőrzés").build());
		taskGroupDtos.add(TaskGroupDto.builder().kind(TaskKind.TK_MAINTENANCE).code(TG_ELECRTICS)
				.description("Eletromos javítás").build());
		taskGroupDtos.add(TaskGroupDto.builder().kind(TaskKind.TK_MAINTENANCE).code(TG_PLUMB)
				.description("Viz-gáz javítás").build());
	}

	private void buildTaskTypeDtos() {
		taskTypeDtos.clear();
		taskTypeDtos.add(
				TaskTypeDto.builder().kind(TaskKind.TK_CLEANING).code(TT_DAILY).description("Napi takarítás").build());
		taskTypeDtos.add(
				TaskTypeDto.builder().kind(TaskKind.TK_CLEANING).code(TT_LINEN).description("Ágynemű csere").build());
		taskTypeDtos.add(TaskTypeDto.builder().kind(TaskKind.TK_CLEANING).code(TT_INSPEKT)
				.description("Standard ellenőrzés").build());
		taskTypeDtos.add(TaskTypeDto.builder().kind(TaskKind.TK_MAINTENANCE).code(TT_TAPREP)
				.description("Csaptelep javítás").build());
		taskTypeDtos.add(
				TaskTypeDto.builder().kind(TaskKind.TK_CLEANING).code(TT_FRUIT).description("Gyümölcskosár").build());
		taskTypeDtos.add(TaskTypeDto.builder().kind(TaskKind.TK_CLEANING).code(TT_TURCSI).description("Extra törölköző")
				.build());
//		taskTypeDtos.add(new TaskTypeDto.Builder().kind(TaskKind.TK_COMMON).code(TT_RECI).description("Recepció").build());
	}

	public TaskTypeDto getTaskTypeDto(String code) {
		return taskTypeDtos.stream().filter(o -> o.getCode().equals(code)).findFirst().get();
	}

	private void buildTaskDtos() {
		taskDtos.clear();
		taskDtos.add(TaskDto.builder().webSafeKey(T_001).kind(TaskKind.TK_CLEANING).type(getTaskTypeDto(TT_DAILY))
				.room(getRoomDto(R_1001)).assignee(getAppUserDtor(AU_KIPI)).status(TaskStatus.IN_PROGRESS).build());
		taskDtos.add(TaskDto.builder().webSafeKey(T_002).kind(TaskKind.TK_CLEANING).type(getTaskTypeDto(TT_LINEN))
				.room(getRoomDto(R_1001)).assignee(getAppUserDtor(AU_KIPI)).status(TaskStatus.IN_PROGRESS).build());
		taskDtos.add(TaskDto.builder().webSafeKey(T_003).kind(TaskKind.TK_CLEANING).type(getTaskTypeDto(TT_INSPEKT))
				.room(getRoomDto(R_1002)).assignee(getAppUserDtor(AU_HAKA)).status(TaskStatus.NOT_STARTED).build());
		taskDtos.add(TaskDto.builder().webSafeKey(T_004).kind(TaskKind.TK_CLEANING).type(getTaskTypeDto(TT_TURCSI))
				.room(getRoomDto(R_1003)).assignee(getAppUserDtor(AU_KIPI)).reporter(getAppUserDtor(AU_HAKA))
				.status(TaskStatus.COMPLETED).build());
		taskDtos.add(TaskDto.builder().webSafeKey(T_007).kind(TaskKind.TK_CLEANING).type(getTaskTypeDto(TT_DAILY))
				.room(getRoomDto(R_1007)).assignee(getAppUserDtor(AU_KIPI)).status(TaskStatus.IN_PROGRESS).build());
		taskDtos.add(TaskDto.builder().webSafeKey(T_008).kind(TaskKind.TK_CLEANING).type(getTaskTypeDto(TT_DAILY))
				.room(getRoomDto(R_1008)).assignee(getAppUserDtor(AU_KIPI)).status(TaskStatus.IN_PROGRESS).build());
	}

	public List<TaskDto> getTaskDtos() {
		return taskDtos;
	}

}
