/**
 * Copyright (c) 2010-2011, Jean-Francois Brazeau. All rights reserved.
 * 
 * Redistribution and use in source and binary forms, with or without 
 * modification, are permitted provided that the following conditions are met:
 * 
 *  1. Redistributions of source code must retain the above copyright notice,
 *     this list of conditions and the following disclaimer.
 * 
 *  2. Redistributions in binary form must reproduce the above copyright
 *     notice, this list of conditions and the following disclaimer in the
 *     documentation and/or other materials provided with the distribution.
 * 
 *  3. The name of the author may not be used to endorse or promote products
 *     derived from this software without specific prior written permission.
 * 
 * THIS SOFTWARE IS PROVIDED BY THE AUTHOR ``AS IS'' AND ANY EXPRESS OR
 * IMPLIEDWARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES
 * OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE DISCLAIMED.
 * IN NO EVENT SHALL THE AUTHOR BE LIABLE FOR ANY DIRECT, INDIRECT,
 * INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING,
 * BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS
 * OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND
 * ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR
 * TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE
 * USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
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
