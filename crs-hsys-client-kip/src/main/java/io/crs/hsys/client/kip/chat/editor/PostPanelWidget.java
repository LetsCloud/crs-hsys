/**
 * 
 */
package io.crs.hsys.client.kip.chat.editor;

import java.util.List;
import java.util.logging.Logger;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.Widget;

import io.crs.hsys.shared.dto.chat.ChatPostDto;

/**
 * @author robi
 *
 */
public class PostPanelWidget extends Composite {
	private static Logger logger = Logger.getLogger(PostPanelWidget.class.getName());

	private static ChatPanelUiBinder uiBinder = GWT.create(ChatPanelUiBinder.class);

	interface ChatPanelUiBinder extends UiBinder<Widget, PostPanelWidget> {
	}

	@UiField
	HTMLPanel panel;

	/**
	 * 
	 */
	public PostPanelWidget() {
		logger.info("PostPanelWidget()");
		initWidget(uiBinder.createAndBindUi(this));
	}

	public void setPosts(List<ChatPostDto> posts, String currenUserKey) {
		panel.clear();
		for (ChatPostDto p : posts) {
			logger.info("PostPanelWidget().setPosts()->p=" + p);
			panel.add(new PostWidget(p.getSender().getPicture(), p.getMessage(), p.getCreated().toString(),
					currenUserKey.equals(p.getSender().getWebSafeKey())));
		}
	}
}
