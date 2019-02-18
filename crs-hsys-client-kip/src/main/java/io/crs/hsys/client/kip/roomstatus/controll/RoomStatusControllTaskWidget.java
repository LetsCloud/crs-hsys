/**
 * 
 */
package io.crs.hsys.client.kip.roomstatus.controll;

import java.util.logging.Level;
import java.util.logging.Logger;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.resources.client.CssResource;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;

import gwt.material.design.client.constants.Color;
import gwt.material.design.client.constants.IconSize;
import gwt.material.design.client.constants.IconType;
import gwt.material.design.client.ui.MaterialCollection;
import gwt.material.design.client.ui.MaterialDropDown;
import gwt.material.design.client.ui.MaterialIcon;
import gwt.material.design.client.ui.MaterialLink;
import gwt.material.design.client.ui.html.Div;
import io.crs.hsys.client.kip.roomstatus.RoomStatusUtils;
import io.crs.hsys.shared.constans.TaskKind;
import io.crs.hsys.shared.dto.task.TaskDto;

/**
 * @author robi
 *
 */
public class RoomStatusControllTaskWidget extends Composite {
	private static final Logger logger = Logger.getLogger(RoomStatusControllTaskWidget.class.getName());

	private static RoomStatusControllTaskWidgetUiBinder uiBinder = GWT
			.create(RoomStatusControllTaskWidgetUiBinder.class);

	interface RoomStatusControllTaskWidgetUiBinder extends UiBinder<Widget, RoomStatusControllTaskWidget> {
	}

	public interface MyStyle extends CssResource {
		String column();
		String middle0();
		String middle1();
		String middle2();
		String middle3();
		String right();
		String right1();
		String right2();
		String right3();
	}
	@UiField MyStyle style;
	
	@UiField
	MaterialIcon leftIcon;

	@UiField
	Div leftCol, middleCol, rightCol, row;

	@UiField
	Label description;

	@UiField
	MaterialCollection toDoList;
	
	private MaterialIcon playIcon, pauseIcon, closeIcon, dropIcon, menuIcon;
	
	/**
	 * 
	 */
	public RoomStatusControllTaskWidget() {
		logger.log(Level.INFO, "RoomStatusControllTaskWidget()");
		initWidget(uiBinder.createAndBindUi(this));
		createIcons();
//		initView(true, TaskKind.CLEANING, false);
	}

	public RoomStatusControllTaskWidget(Boolean ownedBy, Boolean assignedTo, TaskDto task, Boolean oddLine) {
		this();
		logger.log(Level.INFO, "RoomStatusControllTaskWidget()->admin=" + ownedBy);
		initView(ownedBy, assignedTo, task.getKind(), oddLine);
		if (task.getDescription() != null)
			description.setText(task.getDescription());
		else
			description.setText(task.getType().getDescription());

		if (task.getAssignee() != null)
			description.setText(description.getText() + " -> " + task.getAssignee().getCode());
		else
			description.setText(description.getText() + " -> ???");	
	}

	private void createIcons() {
		playIcon = createIcons(IconType.PLAY_ARROW);
		playIcon.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				rightCol.clear();
				middleCol.addStyleName(style.middle2());
				rightCol.addStyleName(style.right2());
				rightCol.add(closeIcon);
				rightCol.add(pauseIcon);
			}
		});
		
		pauseIcon = createIcons(IconType.PAUSE);
		closeIcon = createIcons(IconType.STOP);
		dropIcon = createIcons(IconType.ARROW_DROP_DOWN);
		menuIcon = createIcons(IconType.MORE_VERT);
		menuIcon.setActivates("dp-menu");
	}

	private MaterialIcon createIcons(IconType type) {
		MaterialIcon icon = new MaterialIcon(type);
		icon.setIconSize(IconSize.SMALL);
		icon.setMarginLeft(5);
		icon.setMarginRight(5);
		return icon;
	}
	
	private void initView(Boolean ownedBy, Boolean assignedTo, TaskKind kind, Boolean oddLine) {
		logger.log(Level.INFO, "RoomStatusControllTaskWidget().initView()->admin=" + ownedBy);

		leftIcon.setIconType(RoomStatusUtils.getTaskIcon(kind));
//		leftCol.setBackgroundColor(RoomStatusUtils.getTaskBgColor(kind));
		leftCol.setTextColor(RoomStatusUtils.getTaskColor(kind));

		if (oddLine)
			row.setBackgroundColor(Color.GREY_LIGHTEN_4);

		rightCol.clear();
		if (ownedBy && assignedTo) {
			middleCol.addStyleName(style.middle2());
			rightCol.addStyleName(style.right2());
			rightCol.add(playIcon);
			rightCol.add(menuIcon);
			logger.log(Level.INFO, "RoomStatusControllTaskWidget().initView()->END BY (ownedBy && assignedTo)");
			return;
		} 

		if (ownedBy) {
			middleCol.addStyleName(style.middle1());
			rightCol.addStyleName(style.right1());
			rightCol.add(menuIcon);
			logger.log(Level.INFO, "RoomStatusControllTaskWidget().initView()->END BY (ownedBy");
			return;
		} 
		if (assignedTo) {
			middleCol.addStyleName(style.middle1());
			rightCol.addStyleName(style.right1());
			rightCol.add(playIcon);
			logger.log(Level.INFO, "RoomStatusControllTaskWidget().initView()->END BY (assignedTo");
			return;
		} 
		middleCol.addStyleName(style.middle1());
		rightCol.addStyleName(style.right1());
		rightCol.add(dropIcon);
		logger.log(Level.INFO, "RoomStatusControllTaskWidget().initView()->END");
	}
}
