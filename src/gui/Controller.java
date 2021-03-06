package gui;

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

import java.awt.Color;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.concurrent.Callable;

import javax.swing.SwingUtilities;

import parser.BadSymbolException;
import parser.Generator;
import parser.GeneratorPseudoListener;
import parser.Grammar;
import parser.ListSymbols;
import parser.IOmanager.Analyzer;
import parser.IOmanager.BadFileException;
import parser.IOmanager.ParseException;

import ATuin.*;

import com.jme3.math.ColorRGBA;
import com.jme3.system.AppSettings;


/**
 * Contains the controller of the application that make the link between the parser and the gui.
 * 
 * @author xinouch
 */
public class Controller implements GeneratorPseudoListener
{
	/** Size of the panel where the 3D appears (width) */
	public static int canvasJMEWidth = 1024;
	/** Size of the panel where the 3D appears (height) */
	public static int canvasJMEHeight = 768;

	/** The list of grammar launched in the program */
	private ArrayList<Grammar> grammars = new ArrayList<Grammar>();
	/** The list of the interpretations launched in the program */
	private ArrayList<Turtle> turtles = new ArrayList<Turtle>();
	/** the TubeTurtle if there is one */
	private TubeTurtle tubeTurtle = null;
	/** The TreeTurtle if there is one */
	private TreeTurtle treeTurtle = null;

	/** total nb iterations, used in play mode */
	private int totalIterations = 0;
	/** the actual iteration */
	private int currentIteration = 1; // we begin at 1 because we want to display totalIterations - 1
	/** the list of symbols for the current iteration, used in play mode */
	private ListSymbols listGenerated = null;
	/** the initial axiom of the grammar, used in play mode */
	private ListSymbols initialAxiom = null;

	/** The generator for the current grammar */
	private Generator generator = null;
	/** if true we are currently displaying a turtle */
	private boolean isDisplaying = false;
	/** if true, we display sequecently all the iterations from 0, otherwise we just display the given iteration */
	private boolean isInPlayingMode = false;
	/** index of the current grammar that we're working on */
	private int indexOfCurrentGrammar = 0;
	/** index of the current interpretation that we're working on */
	private int indexOfCurrentTurtle = 0;
	/** The main window */
	private MainFrame mainFrame;
	/** The panel 3D containing the JME application */
	private Panel3D panel3D;
	/** The panel containing the 3D application */
	private Drawer application3d;
	/** pointer on this */
	private final Controller me = this;

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
		start3dApp();

		SwingUtilities.invokeLater(new Runnable()
		{
			public void run ()
			{
				try
				{
					mainFrame = new MainFrame(me, panel3D);
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
	 * Open and load a file containing grammars.
	 * 
	 * @param path the path to the file
	 * @throws ParseException if the file is corrected written
	 * @throws BadFileException if the file is corrected written
	 */
	public void loadFile (String path) throws ParseException, BadFileException
	{
		InputStream istrm = null;
		try
		{
			istrm = new FileInputStream(path);
			Analyzer analyzer = new Analyzer(istrm, "UTF-8");
			try
			{
				analyzer.startValidation();

				grammars.clear();
				grammars = analyzer.getGrammars();
				ArrayList<String> grs = new ArrayList<String>();
				for (Grammar g : grammars)
					grs.add(g.getName());
				for (Turtle t : turtles)
					t.clearScene(); // we release memory (java style)
				tubeTurtle = null;
				treeTurtle = null;
				mainFrame.setListGrammars(grs, 0); // will call indirectly chooseInterpretation() to reload the turtles
			}
			catch (NumberFormatException e)
			{
				System.out.println(e.getMessage());
				e.printStackTrace();
			}
		}
		catch (FileNotFoundException e)
		{
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
	}

	/**
	 * Launch the grammar generator, and then call the turtle to draw.
	 * 
	 * @param nbIterations the number of iterations to make in the generetor
	 */
	public void launchTurtle (int nbIterations)
	{
		generator = null;
		// on nettoie la scène
		turtles.get(indexOfCurrentTurtle).clearScene();
		isInPlayingMode = false;
		isDisplaying = false;
		// on génère les symboles
		Grammar grammar = grammars.get(indexOfCurrentGrammar);
		generator = new Generator(grammar);
		generator.setTotalIteration(nbIterations);
		Thread t = new Thread()
		{
			public void run ()
			{
				try
				{
					generator.generate(me);
				}
				catch (BadSymbolException e)
				{
					mainFrame.setProgressBar(100, null);
					mainFrame.showException(e, "Error while generating symbols");
				}
			}
		};
		t.start(); // from here, we wait for the generator to finish, it will call finished()
	}

	/**
	 * Launch the turtle with the given symols
	 * 
	 * @param salade the list of symbols to interpret
	 * @throws BadSymbolException if there is an unknown symbol
	 */
	public void launchTurtle (String salade) throws BadSymbolException
	{
		Grammar grammar = grammars.get(indexOfCurrentGrammar);
		mainFrame.setSymbolsGenerated(salade);

		// on donne la salade à la tortue
		Turtle turtle = turtles.get(indexOfCurrentTurtle);
		isDisplaying = false;
		turtle.setSymbols(grammar.stringToListSymbols(salade));
		isDisplaying = true;
		turtle.drawSymbols(this);
	}

	/**
	 * Launch the grammar generator, and then call the turtle to draw. This display all the iterations from 0 to nbIteration so we see the
	 * tree grow.
	 * 
	 * @param nbIterations the number of iterations to make in the generetor
	 * @throws BadSymbolException
	 */
	public void playTurtle (int nbIterations) throws BadSymbolException
	{
		generator = null;
		totalIterations = nbIterations;
		// on nettoie la scène
		isInPlayingMode = true;
		isDisplaying = false;
		// on génère les symboles
		Grammar grammar = grammars.get(indexOfCurrentGrammar);
		if (initialAxiom == null)
			initialAxiom = grammar.getAxiom();
		if (listGenerated != null)
			grammar.setAxiom(listGenerated);
		generator = new Generator(grammar);
		generator.setTotalIteration(1);
		Thread t = new Thread()
		{
			public void run ()
			{
				try
				{
					generator.generate(me);
				}
				catch (BadSymbolException e)
				{
					mainFrame.setProgressBar(100, null);
					mainFrame.showException(e, "Error while generating symbols");
				}
			}
		};
		t.start(); // from here, we wait for the generator to finish, it will call finished()
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
	 * change the index and set the available grammars in the window.
	 * 
	 * @param indexOfCurrentGrammar the indexOfCurrentGrammar to set
	 */
	public void setIndexOfCurrentGrammar (int indexOfCurrentGrammar)
	{
		int index = 0;
		String previousTurtle = null;
		ArrayList<String> its = null;

		this.indexOfCurrentGrammar = indexOfCurrentGrammar;
		if (!turtles.isEmpty())
			previousTurtle = turtles.get(indexOfCurrentTurtle).getName();
		its = chooseInterpretations();

		if (previousTurtle != null)
		{
			for (int i = 0; i < its.size(); i++)
			{
				if (previousTurtle.equals(its.get(i)))
					index = i;
				else
					turtles.get(i).clearScene();
			}
		}
		mainFrame.setListInterpretations(its, index);
	}

	/**
	 * @return the current grammar
	 */
	public Grammar getCurrentGrammar ()
	{
		return grammars.get(indexOfCurrentGrammar);
	}

	/**
	 * @return the current turtle
	 */
	public Turtle getCurrentTurtle ()
	{
		return turtles.get(indexOfCurrentTurtle);
	}

	/**
	 * @return the indexOfCurrentTurtle
	 */
	public int getIndexOfCurrentTurtle ()
	{
		return indexOfCurrentTurtle;
	}

	/**
	 * @param index the indexOfCurrentTurtle to set
	 */
	public void setIndexOfCurrentTurtle (int index)
	{
		indexOfCurrentTurtle = index;
		for (int i = 0; i < turtles.size(); i++)
		{
			if (i != indexOfCurrentTurtle)
				turtles.get(i).clearScene(); // we release the memory (java style)
		}
	}

	/**
	 * @return the parameters of the current turtle
	 */
	public ArrayList<Parameter> getCurrentTurtleParameters ()
	{
		return turtles.get(indexOfCurrentTurtle).getParameters();
	}

	/**
	 * set the current turtle parameters
	 * 
	 * @param params
	 * @throws BadSymbolException
	 */
	public void setCurrentTurtleParameters (ArrayList<Parameter> params) throws BadSymbolException
	{
		turtles.get(indexOfCurrentTurtle).setParameters(params);
		for (Parameter p : params)
		{
			if (p.getName().equals("Angle"))
				grammars.get(indexOfCurrentGrammar).setAngle(((Integer) p.getValue()).intValue());
		}
		if (isDisplaying)
			turtles.get(indexOfCurrentTurtle).drawSymbols(this);
	}
	
	/**
	 * change the background color of the JME panel
	 * @param color
	 */
	public void setBackgroundColor (final Color color)
	{
		final ColorRGBA c = new ColorRGBA(color.getRed() / 255.f, color.getGreen() / 255.f, color.getBlue() / 255.f, color.getAlpha() / 255.f);
		
		application3d.enqueue(new Callable<Void>()
		{
			public Void call ()
			{
				application3d.getViewPort().setBackgroundColor(c);
				return null;
			}
		});
	}
	
	/**
	 * @return the color of the background of the JME panel
	 */
	public Color getBackgroundColor ()
	{
		ColorRGBA c = application3d.getViewPort().getBackgroundColor();
		
		return new Color(c.r, c.g, c.b, c.a);
	}

	/**
	 * @return the isInPlayingMode
	 */
	public boolean isInPlayingMode ()
	{
		return isInPlayingMode;
	}

	/**
	 * stop the generation (of the grammar generator or of a playing mode)
	 */
	public void stopGeneration ()
	{
		if (isInPlayingMode)
			currentIteration = totalIterations;
		if (generator != null)
			generator.stopGenerating();
	}

	/**
	 * start the 3D application in the canvas
	 */
	private void start3dApp ()
	{
		AppSettings settings = new AppSettings(true);
		settings.setWidth(canvasJMEWidth);
		settings.setHeight(canvasJMEHeight);
		application3d = new Drawer(settings);
		panel3D = new Panel3D(application3d, settings);
		application3d.enqueue(new Callable<Void>()
		{
			public Void call ()
			{
				application3d.getFlyByCamera().setDragToRotate(true);
				// we turn off the statistics
				// application3d.setDisplayFps(false); // to hide the FPS
				application3d.setDisplayStatView(false); // to hide the statistics
				// on initialise le bouzin
				application3d.initScene();
				return null;
			}
		});
		Thread t = new Thread()
		{
			public void run ()
			{
				try
				{
					sleep(3000);
				}
				catch (InterruptedException ie)
				{
					System.out.println(ie.getMessage());
				}
				application3d.initInputs();
			}
		};
		t.start();
	}

	/**
	 * Choose the interpretations compatible with the current grammar and put it in the window.
	 */
	private ArrayList<String> chooseInterpretations ()
	{
		ArrayList<String> its = new ArrayList<String>();

		if (indexOfCurrentGrammar >= 0 && !grammars.isEmpty())
		{
			turtles.clear();
			if (TubeTurtle.checkSymbols(grammars.get(indexOfCurrentGrammar).getUsableSymbolsWithoutNull()))
			{
				if (tubeTurtle == null)
					tubeTurtle = new TubeTurtle(application3d);

				for (Parameter p : tubeTurtle.getParameters())
				{
					if (p.getName().equals("Angle"))
						p.setValue(new Integer(grammars.get(indexOfCurrentGrammar).getAngle()));
				}
				tubeTurtle.setParameters(tubeTurtle.getParameters());

				turtles.add(tubeTurtle);
				its.add(tubeTurtle.getName());
			}
			if (TreeTurtle.checkSymbols(grammars.get(indexOfCurrentGrammar).getUsableSymbolsWithoutNull()))
			{
				if (treeTurtle == null)
					treeTurtle = new TreeTurtle(application3d);

				for (Parameter p : treeTurtle.getParameters())
				{
					if (p.getName().equals("Angle"))
						p.setValue(new Integer(grammars.get(indexOfCurrentGrammar).getAngle()));
				}
				treeTurtle.setParameters(treeTurtle.getParameters());

				turtles.add(treeTurtle);
				its.add(treeTurtle.getName());
			}
		}
		return its;
	}

	@Override
	public void setStep (int step, int totalStep)
	{
		if (!isDisplaying)
			mainFrame.setProgressBar(step * 100 / totalStep, "Generation - iteration " + (step + 1) + ": ");
		else
			mainFrame.setProgressBar(step * 100 / totalStep, "Scene creation " + (step + 1) + ": ");
	}

	@Override
	public void finished () throws BadSymbolException
	{
		Turtle turtle = turtles.get(indexOfCurrentTurtle);
		if (!isDisplaying && generator != null && generator.isCorrectlyFinished())
		{
			if (!isInPlayingMode)
			{
				// on affiche le generated dans la mainwindow. As it takes long time, we do it in a thread.
				Thread t = new Thread()
				{
					public void run ()
					{
						mainFrame.setSymbolsGenerated(turtles.get(indexOfCurrentTurtle).getSymbols().toString());
					}
				};
				t.start();
			}
			// on donne la salade à la tortue
			turtle.setSymbols(generator.getGenerated());
			if (isInPlayingMode)
			{
				listGenerated = generator.getGenerated();
				try
				{
					Thread.sleep(250);
				}
				catch (InterruptedException e)
				{
					e.printStackTrace();
				}
			}
			generator = null;

			isDisplaying = true;
			mainFrame.setProgressBar(100, null);
			turtle.drawSymbols(this);
		}
		else if (isDisplaying && !isInPlayingMode)
		{
			turtle.resetCameraPosition();
			mainFrame.setProgressBar(100, null);
		}
		else if (isDisplaying && isInPlayingMode)
		{
			if (currentIteration == totalIterations) // playmode finished
			{
				currentIteration = 1;
				totalIterations = 0;
				// on affiche le generated dans la mainwindow. As it takes long time, we do it in a thread.
				Thread t = new Thread()
				{
					public void run ()
					{
						mainFrame.setSymbolsGenerated(turtles.get(indexOfCurrentTurtle).getSymbols().toString());
					}
				};
				t.start();
				listGenerated = null;
				grammars.get(indexOfCurrentGrammar).setAxiom(initialAxiom);
				initialAxiom = null;
				isInPlayingMode = false;
				mainFrame.setProgressBar(100, null);
			}
			else
			{
				currentIteration++;
				playTurtle(totalIterations);
			}
		}
	}

	@Override
	public void begin () throws BadSymbolException
	{
		if (!isDisplaying)
			mainFrame.setProgressBar(-1, "Generation");
		else
			mainFrame.setProgressBar(-1, "Scene construction");
	}
}
