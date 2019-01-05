package io.crs.hsys.shared.dto.chat;

import java.io.Serializable;

@SuppressWarnings("serial")
public class FcmDataDto implements Serializable {

	private String action;
	private String title;
	private String body;
	private String icon;

	public FcmDataDto(String action) {
		this.action = action;
	}

	public FcmDataDto(String action, String title, String body, String icon) {
		this(action);
		this.title = title;
		this.body = body;
		this.icon = icon;
	}

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	@Override
	public String toString() {
		String ret = "FcmDataDto:{title=" + title + ", body=" + body + ", icon=" + icon + ", action=" + action + "}";
		return ret;
	}

}
