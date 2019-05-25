/**
 * 
 */
package io.crs.hsys.client.kip.browser.oooroom;

import com.google.gwt.user.client.ui.Panel;

import gwt.material.design.client.constants.IconType;
import gwt.material.design.client.ui.MaterialDropDown;
import gwt.material.design.client.ui.MaterialIcon;
import gwt.material.design.client.ui.MaterialLink;
import io.crs.hsys.client.core.browser.BaseTableView;
import io.crs.hsys.shared.dto.hotel.OooRoomDto;

/**
 * @author robi
 *
 */
public class OooRoomTableView extends BaseTableView<OooRoomDto> {
	
	public OooRoomTableView() {
		super();		
	}
	
	@Override
	protected void setToolPanel(Panel toolPanel) {
		super.setToolPanel(toolPanel);

		MaterialIcon setOooIcon = new MaterialIcon(IconType.SETTINGS);
		setOooIcon.setMarginRight(15);
		MaterialIcon clearOooIcon = new MaterialIcon(IconType.PLAY_CIRCLE_FILLED);
		clearOooIcon.setMarginRight(15);

		MaterialIcon menuIcon = new MaterialIcon(IconType.MORE_VERT);
		menuIcon.setActivates("dd-menu");

		MaterialDropDown<String> menuDropDown = new MaterialDropDown<String>();
		menuDropDown.setActivator("dd-menu");
		menuDropDown.setConstrainWidth(false);
		menuDropDown.setWidth("180px");

		MaterialLink pdfLink = new MaterialLink();
		pdfLink.setIconType(IconType.PICTURE_AS_PDF);
		pdfLink.setText("PDF export");
		menuDropDown.add(pdfLink);

		MaterialLink xlsLink = new MaterialLink();
		xlsLink.setIconType(IconType.DOCK);
		pdfLink.setText("XLS export");
		menuDropDown.add(xlsLink);

		toolPanel.add(setOooIcon);
		toolPanel.add(clearOooIcon);
		toolPanel.add(getDeleteIcon());
		toolPanel.add(menuIcon);
		toolPanel.add(menuDropDown);
	}

}
