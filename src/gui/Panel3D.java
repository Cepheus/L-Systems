/* ******************************************************
 **	Copyright (C) 2013  xinouch
 **
 **	This file is part of L-Systems
 **
 **	L-Systems is free software: you can redistribute it and/or modify
 **	it under the terms of the GNU General Public License as published by
 **	the Free Software Foundation, either version 3 of the License, or
 **	(at your option) any later version.
 **
 **	This program is distributed in the hope that it will be useful,
 **	but WITHOUT ANY WARRANTY; without even the implied warranty of
 **	MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 **	GNU General Public License for more details.
 **
 **	You should have received a copy of the GNU General Public License
 **	along with this program.  If not, see <http://www.gnu.org/licenses/>.
 ****************************************************** */
/* ******************************************************
 **
 **          Project: L-Systems
 **             File: Panel3D.java
 **
 **       Created on: 8 mars 2013
 **           Author: xinouch
 **
 ****************************************************** */

package gui;

import java.awt.BorderLayout;
import java.awt.Canvas;

import javax.swing.JPanel;

import com.jme3.system.AppSettings;
import com.jme3.system.JmeCanvasContext;

import ATuin.Drawer;

/**
 * Contains the 3D application.
 * @author xinouch
 *
 */
public class Panel3D extends JPanel
{
	/** */
	private static final long serialVersionUID = 5295930914508336846L;
	/** The 3D application : inheritate SimpleApplication */
	private Drawer drawer;
	/** used to integrate the canvas in the panel */
	private static JmeCanvasContext context;
	/** used to integrate JMonkey into the frame */
	private static Canvas canvas;
	

	/**
	 * Create the panel.
	 * @param app the application 3D
	 * @param settings the settings used to create the 3D application
	 */
	public Panel3D (Drawer app, AppSettings settings)
	{
		drawer = app;
		
		createCanvas(settings);
		// canvas is initialized, we put it in the panel
		add(canvas, BorderLayout.CENTER);
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
	}

	/**
	 * Stop the 3D application
	 */
	public void dispose ()
	{
		drawer.stop();
	}

	/**
	 * @return the turtle
	 */
	public Drawer getDrawer ()
	{
		return drawer;
	}
}
