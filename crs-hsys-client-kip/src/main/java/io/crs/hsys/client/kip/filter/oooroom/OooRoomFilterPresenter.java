/**
 * 
 */
package io.crs.hsys.client.kip.filter.oooroom;

import java.util.logging.Logger;

import javax.inject.Inject;

import com.google.web.bindery.event.shared.EventBus;
import com.gwtplatform.mvp.client.HasUiHandlers;

import io.crs.hsys.client.core.datasource.AppUserDataSource2;
import io.crs.hsys.client.core.datasource.RoomTypeDataSource2;
import io.crs.hsys.client.core.security.CurrentUser;
import io.crs.hsys.client.core.ui.filter.AbstractFilterPresenter;
import io.crs.hsys.client.core.ui.filter.AbstractFilterUiHandlers;

/**
 * @author robi
 *
 */
public class OooRoomFilterPresenter extends AbstractFilterPresenter<OooRoomFilterPresenter.MyView> {
	private static Logger logger = Logger.getLogger(OooRoomFilterPresenter.class.getName());

	public interface MyView extends AbstractFilterPresenter.MyView, HasUiHandlers<AbstractFilterUiHandlers> {
	}

	protected final AppUserDataSource2 appUserDataSource;
	protected final RoomTypeDataSource2 roomTypeDataSource;

	@Inject
	OooRoomFilterPresenter(EventBus eventBus, OooRoomFilterPresenter.MyView view, CurrentUser currentUser,
			AppUserDataSource2 appUserDataSource, RoomTypeDataSource2 roomTypeDataSource) {
		super(eventBus, view, currentUser);
		logger.info("OooRoomFilterPresenter()");
		this.appUserDataSource = appUserDataSource;
		this.roomTypeDataSource = roomTypeDataSource;
		getView().setUiHandlers(this);
	}

	@Override
	public void onBind() {
		super.onBind();
	}

	@Override
	public void onReveal() {
		super.onReveal();
	}
}
