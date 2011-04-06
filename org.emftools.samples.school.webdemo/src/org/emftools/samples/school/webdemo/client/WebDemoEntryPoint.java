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
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.ScrollPanel;
import com.google.gwt.user.client.ui.TextArea;

public class WebDemoEntryPoint implements EntryPoint {

	private ListBox orientationListBox;
	private CheckBox alignSameEClassesCheckBox;
	private Image hiddenDiagramImage;
	private ScrollPanel diagramScrollPanel;
	private Image diagramImage;
	private ListBox zoomListBox;
	private TextArea classroomOclExpressionTextArea;
	private TextArea studentOclExpressionTextArea;
	private static final int[] zoomFactors = new int[] { 25, 50, 75, 100, 150,
			200 };
	private String defaultTexteAreaStyleName;

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
		defaultTexteAreaStyleName = classroomOclExpressionTextArea.getStyleName();
		classroomOclExpressionTextArea.setValue("true");
		RootPanel.get("classroomOclExpressionContainer").add(
				classroomOclExpressionTextArea);

		// Student condition
		studentOclExpressionTextArea = new TextArea();
		studentOclExpressionTextArea.setValue("true");
		RootPanel.get("studentOclExpressionContainer").add(
				studentOclExpressionTextArea);

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
						classroomOclExpressionTextArea);
			}
		});
		studentOclExpressionTextArea.addKeyUpHandler(new KeyUpHandler() {
			public void onKeyUp(KeyUpEvent event) {
				oclExpressionHasChanged("Student", studentOclExpressionTextArea);
			}
		});

		// Updates the diagram
		updateDiagram();
	}

	private void oclExpressionHasChanged(final String eClassName,
			final TextArea textArea) {
		service.validate(eClassName, textArea.getText(),
				new AsyncCallback<String>() {
					public void onFailure(Throwable caught) {
						Window.alert("Unexpected error : "
								+ caught.getMessage());
					}

					public void onSuccess(String result) {
						if (result == null) {
							textArea.setStyleName(defaultTexteAreaStyleName);
							updateDiagram();
						} else {
							textArea.setStyleName("badvalue");
						}
					}
				});
	}

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
