/**
 * 
 */
package io.crs.hsys.client.cfg.filter.hotelchild;

import java.util.List;
import java.util.logging.Logger;

import gwt.material.design.addins.client.combobox.MaterialComboBox;
import gwt.material.design.client.ui.MaterialChip;

import io.crs.hsys.client.cfg.filter.AbstractFilterView;
import io.crs.hsys.client.core.i18n.CoreConstants;
import io.crs.hsys.client.core.i18n.CoreMessages;
import io.crs.hsys.shared.dto.hotel.HotelDtor;

/**
 * @author robi
 *
 */
public abstract class AbstractHotelChildFilterView extends AbstractFilterView implements AbstractHotelChildFilterPresenter.MyView {
	private static Logger logger = Logger.getLogger(AbstractHotelChildFilterView.class.getName());

	private MaterialChip hotelChip;

	protected MaterialComboBox<HotelDtor> hotelComboBox;

	public AbstractHotelChildFilterView(CoreMessages i18nCore, CoreConstants cnstCore) {
		super(i18nCore);
		logger.info("AbstractHotelChildFilterView()");
	}

	@Override
	protected void createLayout() {
		hotelComboBox.setGrid("s12 m6");
		controlPanel.add(hotelComboBox);

		onlyActiveCheckBox.setGrid("s12 m6");
		controlPanel.add(onlyActiveCheckBox);
	}

	@Override
	protected void initView() {
		super.initView();
		initHotelFilter();
	}

	private void initHotelFilter() {
		hotelChip = new MaterialChip();
		collapsibleHeader.insert(hotelChip, 1);
		
		hotelComboBox = new MaterialComboBox<HotelDtor>();
		hotelComboBox.setLabel(i18nCore.roomTypesTableHotelsLabel());
		hotelComboBox.setPlaceholder(i18nCore.roomTypesTableHotelsPlaceholder());

		hotelComboBox.addSelectionHandler(e -> {
			setHotelChip(e.getSelectedValues().get(0));
			getUiHandlers().filterChange();
		});
	}

	private void setHotelChip(HotelDtor hoteDto) {
		hotelChip.setText(hoteDto.getName());
	}
	
	@Override
	public void setHotelData(List<HotelDtor> hotelData) {
		for (HotelDtor hd : hotelData) {
			hotelComboBox.addItem(hd.getName(), hd);
		}
	}

	@Override
	public void setSelectedHotel(HotelDtor hotelDto) {
		Integer index = hotelComboBox.getValueIndex(hotelDto);
		hotelComboBox.setSelectedIndex(index);
		setHotelChip(hotelDto);
	}

	@Override
	public HotelDtor getSelectedHotel() {
		return hotelComboBox.getSelectedValue().get(0);
	}

	@Override
	public void reset() {
		// TODO Auto-generated method stub
		
	}
}
