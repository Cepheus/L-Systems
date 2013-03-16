/* ******************************************************
 * * Copyright (C) 2013 xinouch** This file is part of L-Systems** L-Systems is free software: you can redistribute it and/or modify* it
 * under the terms of the GNU General Public License as published by* the Free Software Foundation, either version 3 of the License, or* (at
 * your option) any later version.** This program is distributed in the hope that it will be useful,* but WITHOUT ANY WARRANTY; without even
 * the implied warranty of* MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the* GNU General Public License for more details.** You
 * should have received a copy of the GNU General Public License* along with this program. If not, see <http://www.gnu.org/licenses/>.
 * *****************************************************
 */
/* ******************************************************
 * *
 * * Project: L-Systems* File: Panel3D.java** Created on: 8 mars 2013* Author: xinouch******************************************************
 */

package gui;

import java.awt.BorderLayout;
import java.awt.Canvas;

import javax.swing.JPanel;

import ATuin.Drawer;

import com.jme3.system.AppSettings;
import com.jme3.system.JmeCanvasContext;


/**
 * Contains the 3D application.
 * 
 * @author xinouch
 */
public class Panel3D extends JPanel
{
	/** */
	private static final long serialVersionUID = 5295930914508336846L;
	/** The 3D application : inheritate SimpleApplication */
	private Drawer drawer = null;
	/** used to integrate the canvas in the panel */
	private JmeCanvasContext context = null;
	/** used to integrate JMonkey into the frame */
	private Canvas canvas = null;
	/** Default panel used when nothing else to display */
	private JPanel defaultPanel = new JPanel();
	/** Thread pour lancer JME */
	private Thread thread = new Thread()
	{
		public void run ()
		{
			drawer.startCanvas();
			try
			{
				sleep(500);
			}
			catch (InterruptedException ex)
			{
			}
		}
	};

	/**
	 * Create the panel.
	 */
	public Panel3D ()
	{
		defaultPanel.setSize(Controller.canvasJMEWidth, Controller.canvasJMEHeight);
		add(defaultPanel, BorderLayout.CENTER);
	}

	/**
	 * Display the JME application. It is launched in a new Thread.
	 * 
	 * @param app The JME application that we want to display
	 */
	public void createJMEPanel (Drawer app)
	{
		// we create the canvas
		AppSettings settings = new AppSettings(true);
		settings.setWidth(Controller.canvasJMEWidth);
		settings.setHeight(Controller.canvasJMEHeight);
		drawer = app;

		createCanvas(settings);
		// canvas is initialized
		// we remove the panel
		remove(defaultPanel);
		// we add the canvas
		add(canvas, BorderLayout.CENTER);
	}

	/**
	 * Remove the JMEPanel to display nothing
	 */
	public void removeJMEPanel ()
	{
		if (drawer != null)
		{
			drawer.stop();
			try
			{
				Thread.sleep(500);
			}
			catch (InterruptedException ex)
			{
			}
		}
		if (canvas != null)
			remove(canvas);

		// we "delete" à la java the JME application
		drawer = null;
		context = null;
		canvas = null;

		// we put the default panel
		add(defaultPanel, BorderLayout.CENTER);
	}

	private void createCanvas (AppSettings settings)
	{
		drawer.createCanvas();
		context = (JmeCanvasContext) drawer.getContext();
		canvas = context.getCanvas();
		canvas.setSize(settings.getWidth(), settings.getHeight());
		//thread.start();
		drawer.startCanvas();
	}

	/**
	 * Stop the 3D application
	 */
	public void dispose ()
	{
		removeJMEPanel();
	}

	/**
	 * @return the turtle
	 */
	public Drawer getDrawer ()
	{
		return drawer;
	}
}
