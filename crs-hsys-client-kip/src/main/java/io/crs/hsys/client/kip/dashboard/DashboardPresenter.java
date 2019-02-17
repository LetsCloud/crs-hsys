/**
 * 
 */
package io.crs.hsys.client.kip.dashboard;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.google.inject.Inject;
import com.google.web.bindery.event.shared.EventBus;
import com.gwtplatform.mvp.client.HasUiHandlers;
import com.gwtplatform.mvp.client.Presenter;
import com.gwtplatform.mvp.client.View;
import com.gwtplatform.mvp.client.annotations.NameToken;
import com.gwtplatform.mvp.client.annotations.ProxyCodeSplit;
import com.gwtplatform.mvp.client.proxy.ProxyPlace;

import io.crs.hsys.client.core.app.AbstractAppPresenter;
import io.crs.hsys.client.core.event.ContentPushEvent;
import io.crs.hsys.client.core.event.SetPageTitleEvent;
import io.crs.hsys.client.core.security.CurrentUser;
import io.crs.hsys.client.kip.KipNameTokens;
import io.crs.hsys.client.kip.i18n.KipMessages;
import io.crs.hsys.shared.constans.MenuItemType;
import io.crs.hsys.shared.constans.UserPerm;
import io.crs.hsys.shared.dto.hk.MaintenanceSubTotalDto;
import io.crs.hsys.shared.dto.hk.MaintenanceSumDto;

/**
 * @author CR
 *
 */
public class DashboardPresenter extends Presenter<DashboardPresenter.MyView, DashboardPresenter.MyProxy>
		implements DashboardUiHandlers, ContentPushEvent.ContentPushHandler {
	private static Logger logger = Logger.getLogger(DashboardPresenter.class.getName());

	interface MyView extends View, HasUiHandlers<DashboardUiHandlers> {
		void showCards();

		void showChiefEngBoard(List<MaintenanceSumDto> data, List<MaintenanceSumDto> data2);
		
		void createReceptionistDashboard();
	}

	@ProxyCodeSplit
	@NameToken(KipNameTokens.HOME)
	// @UseGatekeeper(LoggedInGatekeeper.class)
	interface MyProxy extends ProxyPlace<DashboardPresenter> {
	}

	private final CurrentUser currentUser;
	private final KipMessages i18n;

	@Inject
	DashboardPresenter(EventBus eventBus, MyView view, MyProxy proxy, CurrentUser currentUser, KipMessages i18n) {
		super(eventBus, view, proxy, AbstractAppPresenter.SLOT_MAIN);
		logger.log(Level.INFO, "DashboardPresenter()");

		this.currentUser = currentUser;
		this.i18n = i18n;

		addRegisteredHandler(ContentPushEvent.TYPE, this);
	}

	@Override
	protected void onReveal() {
		super.onReveal();
		SetPageTitleEvent.fire(getTitle(currentUser.getAppUserDto().getPermissions().get(0)),
				getSubTitle(currentUser.getAppUserDto().getPermissions().get(0)), MenuItemType.MENU_ITEM, this);

		createContent(currentUser.getAppUserDto().getPermissions().get(0));
	}

	@Override
	public void onContentPush(ContentPushEvent event) {
		// TODO Auto-generated method stub

	}

	private String getTitle(UserPerm permission) {
		switch (permission) {
		case UP_HKSUPERVISOR:
			return i18n.mainMenuItemHousekeepingDashboard();
		case UP_HOUSEKEEPER:
			return i18n.mainMenuItemDashboard();
		case UP_MAINTMANAGER:
			return i18n.mainMenuItemDashboardMaintenance();
		case UP_TECHNICIAN:
			return i18n.mainMenuItemDashboard();
		case UP_RECEPTIONIST:
			return i18n.mainMenuItemReceptionDashboard();
		case UP_ADMIN:
			return i18n.mainMenuItemDashboard();
		default:
			return i18n.mainMenuItemDashboard();
		}
	}

	private String getSubTitle(UserPerm permission) {
		switch (permission) {
		case UP_HKSUPERVISOR:
			return i18n.dashboardSubtitle();
		case UP_HOUSEKEEPER:
			return i18n.dashboardSubtitle();
		case UP_MAINTMANAGER:
			return i18n.dashboardMaintenanceSubtitle();
		case UP_TECHNICIAN:
			return i18n.dashboardSubtitle();
		case UP_RECEPTIONIST:
			return i18n.dashboardReceptionSubtitle();
		case UP_ADMIN:
			return i18n.dashboardSubtitle();
		default:
			return i18n.dashboardSubtitle();
		}
	}

	private void createContent(UserPerm permission) {
		switch (permission) {
		case UP_HKSUPERVISOR:
			getView().showCards();
			break;
		case UP_HOUSEKEEPER:
			getView().showCards();
			break;
		case UP_MAINTMANAGER:
			getView().showChiefEngBoard(loadMaintenanceSum(), loadMaintenanceSum2());
			break;
		case UP_TECHNICIAN:
			getView().showCards();
			break;
		case UP_RECEPTIONIST:
			getView().createReceptionistDashboard();
			break;
		case UP_ADMIN:
			getView().createReceptionistDashboard();
			break;
		default:
			getView().showCards();
			break;
		}
	}

	private List<MaintenanceSumDto> loadMaintenanceSum() {
		List<MaintenanceSumDto> result = new ArrayList<MaintenanceSumDto>();

		result.add(new MaintenanceSumDto.Builder().title("Víz és csatorna")
				.addSubTotal(
						new MaintenanceSubTotalDto.Builder().title("Mosdó csaptelep").col1(2).col2(1).col3(9).build())
				.addSubTotal(new MaintenanceSubTotalDto.Builder().title("WC öblítő").col1(1).col2(0).col3(0).build())
				.addSubTotal(
						new MaintenanceSubTotalDto.Builder().title("Zuhany lefolyó").col1(1).col2(1).col3(9).build())
				.build());

		result.add(new MaintenanceSumDto.Builder().title("Elektromos")
				.addSubTotal(new MaintenanceSubTotalDto.Builder().title("TV").col1(2).col2(1).col3(9).build())
				.addSubTotal(new MaintenanceSubTotalDto.Builder().title("Telefon").col1(1).col2(0).col3(9).build())
				.addSubTotal(new MaintenanceSubTotalDto.Builder().title("Izzó").col1(3).col2(0).col3(9).build())
				.build());

		result.add(new MaintenanceSumDto.Builder().title("Festő")
				.addSubTotal(
						new MaintenanceSubTotalDto.Builder().title("Tapéta javítás").col1(1).col2(1).col3(9).build())
				.addSubTotal(
						new MaintenanceSubTotalDto.Builder().title("Festés javítás").col1(2).col2(2).col3(9).build())
				.build());

		result.add(new MaintenanceSumDto.Builder().title("IT")
				.addSubTotal(
						new MaintenanceSubTotalDto.Builder().title("Nyomtató hiba").col1(1).col2(1).col3(9).build())
				.addSubTotal(new MaintenanceSubTotalDto.Builder().title("Festékpatron csere").col1(2).col2(0).col3(9)
						.build())
				.addSubTotal(
						new MaintenanceSubTotalDto.Builder().title("Hálózati hiba").col1(1).col2(1).col3(9).build())
				.build());

		result.add(new MaintenanceSumDto.Builder().title("Klíma")
				.addSubTotal(
						new MaintenanceSubTotalDto.Builder().title("Klíma tisztítás").col1(20).col2(0).col3(9).build())
				.build());

		return result;
	}

	private List<MaintenanceSumDto> loadMaintenanceSum2() {
		List<MaintenanceSumDto> result = new ArrayList<MaintenanceSumDto>();

		result.add(new MaintenanceSumDto.Builder().title("ÖSSZES")
				.addSubTotal(new MaintenanceSubTotalDto.Builder().title("BEOSZTATLAN").col1(25).col2(0).col3(9).build())
				.addSubTotal(
						new MaintenanceSubTotalDto.Builder().title("Szaki Szabolcs").col1(5).col2(3).col3(0).build())
				.addSubTotal(new MaintenanceSubTotalDto.Builder().title("Kara Karesz").col1(4).col2(2).col3(0).build())
				.addSubTotal(new MaintenanceSubTotalDto.Builder().title("Lakat Lali").col1(3).col2(3).col3(9).build())
				.build());

		return result;
	}

}
