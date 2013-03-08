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
 * * Project: L-Systems* File: Controller.java** Created on: 8 mars 2013* Author: xinouch*
 * *****************************************************
 */
import gui.MainFrame;
import gui.Panel3D;

import java.util.ArrayList;
import java.util.concurrent.Callable;

import javax.swing.SwingUtilities;

import com.jme3.app.SimpleApplication;
import com.jme3.system.AppSettings;

import ATuin.Drawer;

import parser.Generator;
import parser.Grammar;


/**
 * Contains the controller of the application that make the link between the parser and the gui.
 * 
 * @author xinouch
 */
public class Controller
{
	/** The list of grammar launched in the program */
	private ArrayList<Grammar> grammars = null;
	/** The generator for the current grammar */
	private Generator generator = null;
	/** index of the current grammar that we're working on */
	private int indexOfCurrentGrammar = -1;
	/** The main window */
	private MainFrame mainFrame;
	/** The panel containing the 3D application */
	private Drawer application3d;

	/**
	 * Constructor
	 */
	public Controller ()
	{
	}
	
	/**
	 * Create the window of the application and the 3D panel
	 */
	public void startLSystem ()
	{
		// gestion des objets 3D
		AppSettings settings = new AppSettings(true);
		settings.setWidth(800);
		settings.setHeight(600);
		application3d = new Drawer(settings);
		final Panel3D pan = new Panel3D(application3d, settings);

		SwingUtilities.invokeLater(new Runnable()
		{
			public void run ()
			{
				try
				{
					mainFrame = new MainFrame(pan);
					start3dApp();
					mainFrame.setVisible(true);
				}
				catch (Exception e)
				{
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * start the 3D application in the canvas
	 */
	private void start3dApp ()
	{
		application3d.startCanvas();
		application3d.enqueue(new Callable<Void>()
		{
			public Void call ()
			{
				if (application3d instanceof SimpleApplication)
				{
					SimpleApplication simpleApp = (SimpleApplication) application3d;
					simpleApp.getFlyByCamera().setDragToRotate(true);
				}
				return null;
			}
		});
	}

	/**
	 * @return the grammars
	 */
	public ArrayList<Grammar> getGrammars ()
	{
		return grammars;
	}

	/**
	 * @param grammars the grammars to set
	 */
	public void setGrammars (ArrayList<Grammar> grammars)
	{
		this.grammars = grammars;
	}

	/**
	 * @return the generator
	 */
	public Generator getGenerator ()
	{
		return generator;
	}

	/**
	 * @param generator the generator to set
	 */
	public void setGenerator (Generator generator)
	{
		this.generator = generator;
	}

	/**
	 * @return the indexOfCurrentGrammar
	 */
	public int getIndexOfCurrentGrammar ()
	{
		return indexOfCurrentGrammar;
	}

	/**
	 * @param indexOfCurrentGrammar the indexOfCurrentGrammar to set
	 */
	public void setIndexOfCurrentGrammar (int indexOfCurrentGrammar)
	{
		this.indexOfCurrentGrammar = indexOfCurrentGrammar;
	}

	/**
	 * @return the current grammar
	 */
	public Grammar getCurrentGrammar ()
	{
		return grammars.get(indexOfCurrentGrammar);
	}
}
