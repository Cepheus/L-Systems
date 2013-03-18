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

	/**
	 * Create the panel.
	 * @param app the application 3D
	 * @param settings the settings used to create the 3D application 
	 */
	public Panel3D (Drawer app, AppSettings settings)
	{
		createJMEPanel(app, settings);
	}

	/**
	 * Display the JME application.
	 * It is launched in a new Thread.
	 * @param app the application 3D
	 * @param settings the settings used to create the 3D application 
	 */
	public void createJMEPanel (Drawer app, AppSettings settings)
	{
		// we create the canvas
		drawer = app;

		createCanvas(settings);
		// canvas is initialized
		// we add the canvas
		add(canvas, BorderLayout.CENTER);
	}

	/**
	 * Remove the JMEPanel to display nothing
	 */
	public void removeJMEPanel ()
	{
		drawer.stop();
		remove(canvas);

		// we "delete" à la java the JME application
		drawer = null;
		context = null;
		canvas = null;
	}

	private void createCanvas (AppSettings settings)
	{
		drawer.createCanvas();
		context = (JmeCanvasContext) drawer.getContext();
		canvas = context.getCanvas();
		canvas.setSize(settings.getWidth(), settings.getHeight());
		try
		{
			Thread.sleep(500);
		}
		catch (InterruptedException ex)
		{
		}

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
