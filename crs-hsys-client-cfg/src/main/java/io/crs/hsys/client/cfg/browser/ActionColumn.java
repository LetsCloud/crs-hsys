/**
 * 
 */
package io.crs.hsys.client.cfg.browser;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;

import gwt.material.design.client.constants.Color;
import gwt.material.design.client.constants.IconType;
import gwt.material.design.client.constants.TextAlign;
import gwt.material.design.client.constants.WavesType;
import gwt.material.design.client.ui.MaterialIcon;
import gwt.material.design.client.ui.table.cell.WidgetColumn;
import io.crs.hsys.shared.dto.BaseDto;

/**
 * WidgetColumn utód, amely segítségével az adott adat objektum szerkesztése
 * vagy megtekintése váltható ki.
 * <p>
 * Egy MaterialIcont jelenít meg, amely típusa beállítható.
 * 
 * @author robi
 *
 */
public class ActionColumn<T extends BaseDto> extends WidgetColumn<T, MaterialIcon> {

	/**
	 * Az adat objektumon végrehajtandó műveletet deifiniáló interféce.
	 * 
	 * @author robi
	 *
	 * @param <T> Az adat objektum típusa, <b>BaseDto</b> utód.
	 */
	public interface ActionRow<T extends BaseDto> {

		/**
		 * Az adat objektumon végrehajtandó művelet.
		 * 
		 * @param object Adat objektum.
		 */
		void onAction(T object);
	}

	private ActionRow<T> actionRow;

	private IconType iconType = IconType.EDIT;

	private Color iconColor = Color.AMBER;

	/**
	 * Egyszerű Edit ikon kontruktor.
	 * 
	 * @param actionRow
	 */
	public ActionColumn(ActionRow<T> actionRow) {
		this.actionRow = actionRow;
		this.setTextAlign(TextAlign.RIGHT);
	}

	/**
	 * Beállítható típusú és színű ikon konstruktora.
	 * 
	 * @param actionRow
	 * @param iconType
	 * @param iconColor
	 */
	public ActionColumn(ActionRow<T> actionRow, IconType iconType, Color iconColor) {
		this(actionRow);
		this.iconType = iconType;
		this.iconColor = iconColor;
	}

	@Override
	public MaterialIcon getValue(T object) {
		MaterialIcon icon = new MaterialIcon();
		icon.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				actionRow.onAction(object);
			}
		});

		icon.setWaves(WavesType.DEFAULT);
		icon.setIconType(iconType);
		icon.setBackgroundColor(iconColor);
		icon.setCircle(true);
		icon.setTextColor(Color.WHITE);
		// icon.setWidth("50px");
		// icon.setIconPosition(IconPosition.NONE);
		return icon;
	}
}
