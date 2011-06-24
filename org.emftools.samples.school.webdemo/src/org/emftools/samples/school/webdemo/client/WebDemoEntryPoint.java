/**
 * This file is part of emf2gv : an eclipse plugin that allows to
 * generate a graphical representation of an EMF model.
 *
 * Copyright 2010-2011 Jean-Francois Brazeau
 * 
 * emf2gv is free software: you can redistribute it and/or modify
 * it under the terms of either:
 * 
 *      a) the GNU Lesser General Public License as published by
 *       the Free Software Foundation, either version 3 of the License, or
 *       (at your option) any later version.
 *  or
 *      b) the Eclipse Public License version 1.0 as published by
 *       the Eclipse Foundation.
 * 
 * emf2gv is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 * 
 * You should have received a copy of the GNU Lesser General Public License
 * along with emf2gv.  If not, see <http://www.gnu.org/licenses/>.
 * 
 * You should have received a copy of the  Eclipse Public License
 * along with emf2gv.  If not, see <http://www.eclipse.org/legal/epl-v10.html>.
 */
package org.emftools.samples.school.webdemo.client;

import org.emftools.samples.school.webdemo.server.Emf2gvServlet;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.event.dom.client.ChangeHandler;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.KeyUpEvent;
import com.google.gwt.event.dom.client.KeyUpHandler;
import com.google.gwt.event.dom.client.LoadEvent;
import com.google.gwt.event.dom.client.LoadHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.CheckBox;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.ScrollPanel;
import com.google.gwt.user.client.ui.TextArea;

/**
 * Web Demo Entry point.
 * 
 * @author jbrazeau
 */
public class WebDemoEntryPoint implements EntryPoint {

	/** Zoom factors list */
	private static final int[] zoomFactors = new int[] { 25, 50, 75, 100, 150,
			200 };

	/** Diagram orientation list box */
	private ListBox orientationListBox;

	/** Diagram alignment checkbox */
	private CheckBox alignSameEClassesCheckBox;

	/** Hidden image used to download from the server in background */
	private Image hiddenDiagramImage;

	/** Scroll pane containing the diagram image */
	private ScrollPanel diagramScrollPanel;

	/** The diagram image */
	private Image diagramImage;

	/** Zoom list box */
	private ListBox zoomListBox;

	/** Classroom OCL text area */
	private TextArea classroomOclExpressionTextArea;

	/** Classroom OCL expression parsing status label */
	private Label classroomStatusLabel;

	/** Students OCL text area */
	private TextArea studentOclExpressionTextArea;

	/** Student OCL expression parsing status label */
	private Label studentStatusLabel;

	/** Default textara style name */
	private String defaultTexteAreaStyleName;

	/** GWT Web Demo service */
	private final WebDemoServiceAsync service = GWT
			.create(WebDemoService.class);

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.google.gwt.core.client.EntryPoint#onModuleLoad()
	 */
	public void onModuleLoad() {
		// Orientation selection
		orientationListBox = new ListBox();
		orientationListBox.addItem("Top down");
		orientationListBox.addItem("Left to right");
		orientationListBox.setSelectedIndex(0);
		RootPanel.get("orientationContainer").add(orientationListBox);

		// Align same EClasses
		alignSameEClassesCheckBox = new CheckBox();
		alignSameEClassesCheckBox.setValue(true);
		RootPanel.get("alignSameEClassesContainer").add(
				alignSameEClassesCheckBox);

		// Image container
		diagramScrollPanel = new ScrollPanel();
		diagramScrollPanel.setPixelSize(690, 500);
		diagramImage = new Image();
		diagramImage.setUrl("timer.gif");
		diagramScrollPanel.add(diagramImage);
		RootPanel.get("imageContainer").add(diagramScrollPanel);
		hiddenDiagramImage = new Image();
		hiddenDiagramImage.setVisible(false);
		RootPanel.get().add(hiddenDiagramImage);

		// Orientation selection
		zoomListBox = new ListBox();
		for (int zoomFactor : zoomFactors) {
			zoomListBox.addItem(zoomFactor + "%");
		}
		zoomListBox.setSelectedIndex(2);
		RootPanel.get("zoomContainer").add(zoomListBox);

		// Classroom condition
		classroomOclExpressionTextArea = new TextArea();
		defaultTexteAreaStyleName = classroomOclExpressionTextArea
				.getStyleName();
		classroomOclExpressionTextArea.setValue("true");
		RootPanel.get("classroomOclExpressionContainer").add(
				classroomOclExpressionTextArea);

		// Student condition
		studentOclExpressionTextArea = new TextArea();
		studentOclExpressionTextArea.setValue("true");
		RootPanel.get("studentOclExpressionContainer").add(
				studentOclExpressionTextArea);

		// Classroom Status label
		classroomStatusLabel = new Label();
		classroomStatusLabel.setText(" ");
		classroomStatusLabel.setStyleName("badvalue");
		RootPanel.get("classroomStatusContainer").add(classroomStatusLabel);

		// Student Status label
		studentStatusLabel = new Label();
		studentStatusLabel.setText(" ");
		studentStatusLabel.setStyleName("badvalue");
		RootPanel.get("studentStatusContainer").add(studentStatusLabel);

		// Change handler
		orientationListBox.addChangeHandler(new ChangeHandler() {
			public void onChange(ChangeEvent event) {
				updateDiagram();
			}
		});
		alignSameEClassesCheckBox.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				updateDiagram();
			}
		});
		zoomListBox.addChangeHandler(new ChangeHandler() {
			public void onChange(ChangeEvent event) {
				updateDiagram();
			}
		});
		classroomOclExpressionTextArea.addKeyUpHandler(new KeyUpHandler() {
			public void onKeyUp(KeyUpEvent event) {
				oclExpressionHasChanged("Classroom",
						classroomOclExpressionTextArea, classroomStatusLabel);
			}
		});
		studentOclExpressionTextArea.addKeyUpHandler(new KeyUpHandler() {
			public void onKeyUp(KeyUpEvent event) {
				oclExpressionHasChanged("Student",
						studentOclExpressionTextArea, studentStatusLabel);
			}
		});

		// Updates the diagram
		updateDiagram();
	}

	/**
	 * This method is invoked when to the OCL textareas change.
	 * 
	 * <p>
	 * The method's boy invokes the OCL validation service provided by the
	 * server and reports the errors when required in the specified status
	 * label.
	 * 
	 * @param eClassName
	 *            the OCL expression context.
	 * @param oclTextArea
	 *            The text area containing the OCL expression.
	 * @param statusLabel
	 *            The label that accepts the parsing errors.
	 */
	private void oclExpressionHasChanged(final String eClassName,
			final TextArea oclTextArea, final Label statusLabel) {
		service.validate(eClassName, oclTextArea.getText(),
				new AsyncCallback<String>() {
					public void onFailure(Throwable caught) {
						Window.alert("Unexpected error : "
								+ caught.getMessage());
					}

					public void onSuccess(String result) {
						if (result == null) {
							oclTextArea.setStyleName(defaultTexteAreaStyleName);
							statusLabel.setText(" ");
							updateDiagram();
						} else {
							statusLabel.setText(result);
							oclTextArea.setStyleName("badvalue");
						}
					}
				});
	}

	/**
	 * Updates the diagram by invoking the <code>Emf2gvServlet<code>.
	 * @see Emf2gvServlet
	 */
	private void updateDiagram() {
		diagramImage.setUrl("timer.gif");
		diagramImage.setPixelSize(48, 48);
		hiddenDiagramImage.setUrl("diagram?orientation="
				+ orientationListBox.getSelectedIndex() + "&alignSameEClasses="
				+ alignSameEClassesCheckBox.getValue()
				+ "&classroomOclExpression="
				+ classroomOclExpressionTextArea.getText()
				+ "&studentOclExpression="
				+ studentOclExpressionTextArea.getText());
		hiddenDiagramImage.addLoadHandler(new LoadHandler() {
			public void onLoad(LoadEvent event) {
				diagramImage.setUrl(hiddenDiagramImage.getUrl());
				double zoomFactor = ((double) zoomFactors[zoomListBox
						.getSelectedIndex()]) / 100d;
				int newWidth = (int) (hiddenDiagramImage.getWidth() * zoomFactor);
				int newHeight = (int) (hiddenDiagramImage.getHeight() * zoomFactor);
				diagramImage.setPixelSize(newWidth, newHeight);
			}
		});
	}

}
