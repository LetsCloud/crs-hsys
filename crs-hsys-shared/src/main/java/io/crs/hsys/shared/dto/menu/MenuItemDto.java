/**
 * 
 */
package io.crs.hsys.shared.dto.menu;

import java.util.List;

import io.crs.hsys.shared.constans.MenuItemType;
import io.crs.hsys.shared.dto.common.AccountChildDto;

/**
 * @author CR
 *
 */
@SuppressWarnings("serial")
public class MenuItemDto extends AccountChildDto {

	private Integer index;

	private MenuItemType type;

	private String icon;

	private String text;

	private String nameToken;

	private List<MenuItemParamDto> params;

	private List<MenuItemDto> items;

	public Integer getIndex() {
		return index;
	}

	public void setIndex(Integer index) {
		this.index = index;
	}

	public MenuItemType getType() {
		return type;
	}

	public void setType(MenuItemType type) {
		this.type = type;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getNameToken() {
		return nameToken;
	}

	public void setNameToken(String nameToken) {
		this.nameToken = nameToken;
	}

	public List<MenuItemParamDto> getParams() {
		return params;
	}

	public void setParams(List<MenuItemParamDto> params) {
		this.params = params;
	}

	public List<MenuItemDto> getItems() {
		return items;
	}

	public void setItems(List<MenuItemDto> items) {
		this.items = items;
	}

	public void addItem(MenuItemDto item) {
		this.items.add(item);
	}
}
