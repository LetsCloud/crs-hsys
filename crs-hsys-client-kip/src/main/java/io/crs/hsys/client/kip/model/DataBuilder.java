/**
 * 
 */
package io.crs.hsys.client.kip.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import com.google.inject.Inject;

import io.crs.hsys.shared.cnst.Language;
import io.crs.hsys.shared.cnst.RoomStatus;
import io.crs.hsys.shared.cnst.TaskKind;
import io.crs.hsys.shared.cnst.TaskNoteType;
import io.crs.hsys.shared.cnst.TaskStatus;
import io.crs.hsys.shared.cnst.UserPerm;
import io.crs.hsys.shared.dto.common.AppUserDtor;
import io.crs.hsys.shared.dto.common.TranslationDto;
import io.crs.hsys.shared.dto.hotel.RoomDto;
import io.crs.hsys.shared.dto.hotel.RoomTypeDtor;
import io.crs.hsys.shared.dto.task.TaskDto;
import io.crs.hsys.shared.dto.task.TaskGroupDto;
import io.crs.hsys.shared.dto.task.TaskNoteDto;
import io.crs.hsys.shared.dto.task.TaskTodoDto;
import io.crs.hsys.shared.dto.task.TaskTypeDto;

/**
 * @author robi
 *
 */
public class DataBuilder {

	public static final String LNG_UK = "uk";

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
		roomDtos.add(new RoomDto.Builder().code(R_1001).roomType(getRoomTypeDtor(RT_DBLB)).roomStatus(RoomStatus.RS_DIRTY)
				.build());
		roomDtos.add(new RoomDto.Builder().code(R_1002).roomType(getRoomTypeDtor(RT_TWIN)).roomStatus(RoomStatus.RS_CLEAN)
				.build());
		roomDtos.add(new RoomDto.Builder().code(R_1003).roomType(getRoomTypeDtor(RT_TWIN))
				.roomStatus(RoomStatus.RS_INSPECTED).build());
		roomDtos.add(new RoomDto.Builder().code(R_1007).roomType(getRoomTypeDtor(RT_DBLB)).roomStatus(RoomStatus.RS_DIRTY)
				.build());
		roomDtos.add(new RoomDto.Builder().code(R_1008).roomType(getRoomTypeDtor(RT_DBLB)).roomStatus(RoomStatus.RS_DIRTY)
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
		taskTypeDtos.add(TaskTypeDto.builder().kind(TaskKind.TK_CLEANING).code(TT_DAILY)
				.description("Lakó szoba takarítás")
				.addTranslation(new TranslationDto(Language.uk, "Прибирання в приміщенні"))
				.addTodo(TaskTodoDto.builder().description("Szellőztetés")
						.addTranslation(new TranslationDto(Language.uk, "Cушильний")).build())
				.addTodo(TaskTodoDto.builder().description("Szemetesek űrítése")
						.addTranslation(new TranslationDto(Language.uk, "Баки для сміття")).build())
				.addTodo(TaskTodoDto.builder().description("Ágyazás")
						.addTranslation(new TranslationDto(Language.uk, "Oтложной")).build())
				.addTodo(TaskTodoDto.builder().description("Portalanítás")
						.addTranslation(new TranslationDto(Language.uk, "пилу")).build())
				.addTodo(TaskTodoDto.builder().description("Porszívózás")
						.addTranslation(new TranslationDto(Language.uk, "Bакуумна чистка")).build())
				.addTodo(TaskTodoDto.builder().description("Műszaki ellenőzések")
						.addTranslation(new TranslationDto(Language.uk, "Технічні перевірки")).build())
				.addTodo(TaskTodoDto.builder().description("Fürdőszoba takarítás")
						.addTranslation(new TranslationDto(Language.uk, "Прибирання ванної кімнати")).build())
				.build());
		taskTypeDtos.add(TaskTypeDto.builder().kind(TaskKind.TK_CLEANING).code(TT_LINEN).description("Ágynemű csere")
				.addTranslation(new TranslationDto(Language.uk, "Зміна білизни")).build());
		taskTypeDtos
				.add(TaskTypeDto.builder().kind(TaskKind.TK_CLEANING).code(TT_INSPEKT).description("Szoba ellenőrzés")
						.addTranslation(new TranslationDto(Language.uk, "Управління кімнатами")).build());
		taskTypeDtos.add(
				TaskTypeDto.builder().kind(TaskKind.TK_MAINTENANCE).code(TT_TAPREP).description("Csaptelep javítás")
						.addTranslation(new TranslationDto(Language.uk, "Ремонт крана")).build());
		taskTypeDtos.add(TaskTypeDto.builder().kind(TaskKind.TK_CLEANING).code(TT_FRUIT).description("Gyümölcskosár")
				.addTranslation(new TranslationDto(Language.uk, "Кошик з фруктами")).build());
		taskTypeDtos.add(TaskTypeDto.builder().kind(TaskKind.TK_CLEANING).code(TT_TURCSI).description("Extra törölköző")
				.addTranslation(new TranslationDto(Language.uk, "Додаткові рушники")).build());
//		taskTypeDtos.add(new TaskTypeDto.Builder().kind(TaskKind.TK_COMMON).code(TT_RECI).description("Recepció").build());
	}

	public TaskTypeDto getTaskTypeDto(String code) {
		return taskTypeDtos.stream().filter(o -> o.getCode().equals(code)).findFirst().get();
	}

	private void buildTaskDtos() {
		taskDtos.clear();
		// 1001 szoba takarítása folyamatban van
		taskDtos.add(TaskDto.builder().webSafeKey(T_001).kind(TaskKind.TK_CLEANING).type(getTaskTypeDto(TT_DAILY))
				.room(getRoomDto(R_1001)).assignee(getAppUserDtor(AU_KIPI)).status(TaskStatus.TS_IN_PROGRESS).build());
		taskDtos.add(TaskDto.builder().webSafeKey(T_002).kind(TaskKind.TK_CLEANING).type(getTaskTypeDto(TT_LINEN))
				.room(getRoomDto(R_1001)).assignee(getAppUserDtor(AU_KIPI)).status(TaskStatus.TS_IN_PROGRESS).build());
		// 1002 szobba ellenőrzése még nem vette kezdetét
		taskDtos.add(TaskDto.builder().webSafeKey(T_003).kind(TaskKind.TK_CLEANING).type(getTaskTypeDto(TT_INSPEKT))
				.room(getRoomDto(R_1002)).assignee(getAppUserDtor(AU_HAKA)).status(TaskStatus.TS_NOT_STARTED).build());
		// 1003 szobába HAKA kér KIPI-től egy extra törölközőt
		taskDtos.add(TaskDto.builder().webSafeKey(T_004).kind(TaskKind.TK_CLEANING).type(getTaskTypeDto(TT_TURCSI))
				.room(getRoomDto(R_1003)).assignee(getAppUserDtor(AU_KIPI)).reporter(getAppUserDtor(AU_HAKA))
				.status(TaskStatus.TS_NOT_STARTED).description("2-t szeretnének")
				.addNote(new TaskNoteDto(new Date(), getAppUserDtor(AU_HAKA), TaskNoteType.TNT_MOD_DESCRIPTION,
						"Létrehozás"))
				.addNote(new TaskNoteDto(new Date(), getAppUserDtor(AU_HAKA), TaskNoteType.TNT_MOD_DESCRIPTION,
						"Módosítás: Leírás()"))
				.build());
		// 1007 takarítása befejeződött
		taskDtos.add(TaskDto.builder().webSafeKey(T_007).kind(TaskKind.TK_CLEANING).type(getTaskTypeDto(TT_DAILY))
				.room(getRoomDto(R_1007)).assignee(getAppUserDtor(AU_KIPI)).status(TaskStatus.TS_COMPLETED).build());
		// 1008 takarítása szünetel
		taskDtos.add(TaskDto.builder().webSafeKey(T_008).kind(TaskKind.TK_CLEANING).type(getTaskTypeDto(TT_DAILY))
				.room(getRoomDto(R_1008)).assignee(getAppUserDtor(AU_KIPI)).status(TaskStatus.TS_DEFFERED)
				.addNote(new TaskNoteDto(new Date(), getAppUserDtor(AU_KIPI), TaskNoteType.TNT_CREATED))
				.addNote(new TaskNoteDto(new Date(), getAppUserDtor(AU_KIPI), TaskNoteType.TNT_MOD_DESCRIPTION,
						"Szüneteltetés"))
				.build());
		// 1008 szobába HAKA kér KIPI-től egy extra törölközőt, de törölte
		taskDtos.add(TaskDto.builder().webSafeKey(T_009).kind(TaskKind.TK_CLEANING).type(getTaskTypeDto(TT_TURCSI))
				.room(getRoomDto(R_1008)).assignee(getAppUserDtor(AU_KIPI)).reporter(getAppUserDtor(AU_HAKA))
				.status(TaskStatus.TS_DELETED).addNote(new TaskNoteDto(new Date(), getAppUserDtor(AU_HAKA),
						TaskNoteType.TNT_MOD_DESCRIPTION, "Törlés"))
				.build());
	}

	public List<TaskDto> getTaskDtos() {
		return taskDtos;
	}

}
