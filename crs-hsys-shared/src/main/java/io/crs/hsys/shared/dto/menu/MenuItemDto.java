/**
 * 
 */
package io.crs.hsys.shared.dto.menu;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import io.crs.hsys.shared.constans.MenuItemType;
import io.crs.hsys.shared.dto.common.AccountChildDto;
import io.crs.hsys.shared.dto.hotel.RoomDto;

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

	private List<MenuItemDto> items = new ArrayList<MenuItemDto>();

	private List<MenuItemParamDto> params = new ArrayList<MenuItemParamDto>();

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

	@Override
	public String toString() {
		return "MenuItemDto [index=" + this.index + ", type=" + this.type + ", icon=" + this.icon + ", text="
				+ this.text + ", nameToken=" + this.nameToken + super.toString() + "]";
	}

	@JsonIgnore
	public static Comparator<RoomDto> ORDER_BY_CODE = new Comparator<RoomDto>() {
		public int compare(RoomDto one, RoomDto other) {
			return one.getCode().compareTo(other.getCode());
		}
	};

	public static class Builder {

		private Integer index;
		private MenuItemType type;
		private String icon;
		private String text;
		private String nameToken;
		private List<MenuItemDto> items = new ArrayList<MenuItemDto>();
		private List<MenuItemParamDto> params = new ArrayList<MenuItemParamDto>();
		private Integer itemIndex = 0;

		public Builder index(Integer index) {
			this.index = index;
			return this;
		}

		public Builder type(MenuItemType type) {
			this.type = type;
			return this;
		}

		public Builder icon(String icon) {
			this.icon = icon;
			return this;
		}

		public Builder text(String text) {
			this.text = text;
			return this;
		}

		public Builder nameToken(String nameToken) {
			this.nameToken = nameToken;
			return this;
		}

		public Builder addItem(MenuItemDto item) {
			item.setIndex(itemIndex++);
			this.items.add(item);
			return this;
		}

		public Builder addParam(MenuItemParamDto param) {
			this.params.add(param);
			return this;
		}

		public MenuItemDto build() {
			MenuItemDto object = new MenuItemDto();
			object.setIndex(index);
			object.setType(type);
			object.setIcon(icon);
			object.setText(text);
			object.setNameToken(nameToken);
			object.setItems(items);
			object.setParams(params);
			return object;
		}
	}
}
