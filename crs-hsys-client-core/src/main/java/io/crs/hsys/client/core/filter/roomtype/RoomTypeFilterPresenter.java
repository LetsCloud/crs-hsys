/**
 * 
 */
package io.crs.hsys.client.core.filter.roomtype;

import java.util.logging.Logger;

import javax.inject.Inject;

import com.google.web.bindery.event.shared.EventBus;
import com.gwtplatform.mvp.client.HasUiHandlers;

import io.crs.hsys.client.core.ui.filter.AbstractFilterUiHandlers;
import io.crs.hsys.client.core.datasource.HotelDataSource2;
import io.crs.hsys.client.core.filter.hotelchild.AbstractHotelChildFilterPresenter;
import io.crs.hsys.client.core.security.CurrentUser;
import io.crs.hsys.shared.constans.InventoryType;

/**
 * @author robi
 *
 */
public class RoomTypeFilterPresenter extends AbstractHotelChildFilterPresenter<RoomTypeFilterPresenter.MyView> {
	private static Logger logger = Logger.getLogger(RoomTypeFilterPresenter.class.getName());

	public interface MyView extends AbstractHotelChildFilterPresenter.MyView, HasUiHandlers<AbstractFilterUiHandlers> {
		InventoryType getSelectedInventoryType();
	}

	@Inject
	RoomTypeFilterPresenter(EventBus eventBus, MyView view, CurrentUser currentUser, HotelDataSource2 hotelDataSource) {
		super(eventBus, view, currentUser, hotelDataSource);
		logger.info("RoomTypeFilterPresenter()");
		getView().setUiHandlers(this);
	}

	@Override
	public void onReveal() {
		super.onReveal();
		logger.info("RoomTypeFilterPresenter().onReveal()");
	}

	public Boolean isOnlyActive() {
		return getView().isOnlyActive();
	}

	public InventoryType getSelectedInventoryType() {
		return getView().getSelectedInventoryType();
	}
}
