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
 * * Project: L-Systems* File: MainFrame.java** Created on: 8 mars 2013* Author: xinouch*
 * *****************************************************
 */

package gui;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JToolBar;
import javax.swing.KeyStroke;
import javax.swing.SpinnerNumberModel;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;

import parser.BadSymbolException;
import parser.IOmanager.BadFileException;
import parser.IOmanager.ParseException;
import javax.swing.JTextPane;
import javax.swing.JTextArea;


// important line : JPopupMenu.setDefaultLightWeightPopupEnabled(false);

/**
 * Contains the MainWindow of the application (the first that is launched at the start).
 * 
 * @author xinouch
 */
public class MainFrame extends JFrame
{
	/** pointer to this */
	final MainFrame me = this;
	/** */
	private static final long serialVersionUID = -1306687705594790857L;
	/** Controller of the window */
	private Controller controller;
	/** principle panel */
	private JPanel contentPane;
	/** Panel containing the 3D application */
	private Panel3D panel3d;
	/** Combobox of the interpretations */
	private final JComboBox<String> comboBoxGrammars = new JComboBox<String>();
	/** combobox of the grammars */
	private final JComboBox<String> comboBoxInterpretations = new JComboBox<String>();
	/** Label in the toolbar */
	private JLabel lblNumberOfIterations = new JLabel("Number of iterations: ");
	/** JSpinner to choose the number of iteration */
	private JSpinner spinnerNbIt = new JSpinner();
	/** Menu item edit current grammar */
	private JMenuItem mntmEditCurrentGrammar = new JMenuItem("Edit current grammar");
	/** Menu item edit the turtle */
	private JMenuItem mntmEditCurrentTurtle = new JMenuItem("Edit current turtle");
	/** The button to launch the turtle */
	private JButton btnLaunch = new JButton("Launch!");
	/** button to clear the scene */
	private JButton btnClearScene = new JButton("Clear Scene");
	/** cancel the generation of symbols */
	private JButton btnCancel = new JButton("Cancel");
	/** zone where display the generated symbols */
	private JTextArea txtrGeneratedSymbols = new JTextArea();
	/** the button to apply modifications made in the text area */
	private JButton btnApplyModifs;
	/** the progress bar */
	private JProgressBar progressBar;
	/** The panel containing the progress bar */
	private JPanel panelProgressBar = new JPanel();

	/**
	 * Create the frame.
	 * 
	 * @param c le controller de la fenêtre à appeler lors de manipulation spéciales
	 * @param panel the 3D application
	 */
	public MainFrame (Controller c, Panel3D panel)
	{
		setTitle("LSystem");
		controller = c;

		// we create the window
		initFrame();
		// we add the 3D panel
		panel3d = panel;
		addWindowListener(new WindowAdapter()
		{
			@Override
			public void windowClosed (WindowEvent e)
			{
				panel3d.dispose();
			}
		});
		contentPane.add(panel3d, BorderLayout.CENTER);

		setEnable(false);

		pack();
	}

	/**
	 * display the message o the exception in a dialog with the title title
	 * 
	 * @param e the exception
	 * @param title the title of the dialog
	 */
	public void showException (Exception e, String title)
	{
		JTextPane editor = new JTextPane();
		editor.setText(e.getMessage());
		editor.setEditable(false);

		JOptionPane.showMessageDialog(me, editor, title, JOptionPane.ERROR_MESSAGE);
	}

	/**
	 * display a progress bar and put its progression on percent / 100. as the application should be doing something important, the buttuns
	 * Apply and Launch! are disabled
	 * 
	 * @param percent must be in [0, 100[. if percent < 0, progress bar in indeterminate state. Otherwise, the progress bar is hidden
	 * @param toBeDisplayed a string that can be displayed. if null, default string ("percent %"), otherwise "String percent %"
	 */
	public void setProgressBar (int percent, String toBeDisplayed)
	{
		if (progressBar != null && panelProgressBar != null)
		{
			if (percent < 0)
			{
				if (!panelProgressBar.isVisible())
					panelProgressBar.setVisible(true);
				progressBar.setIndeterminate(true);
				progressBar.setString(toBeDisplayed);
				btnCancel.setEnabled(false);
			}
			else if (percent < 100)
			{
				if (!panelProgressBar.isVisible())
					panelProgressBar.setVisible(true);
				progressBar.setIndeterminate(false);
				progressBar.setValue(percent);
				txtrGeneratedSymbols.setText("Generating symbols...");
				// the button and the text field will be enabled when we'll call setSybolsGenerated()
				txtrGeneratedSymbols.setEnabled(false);
				btnApplyModifs.setEnabled(false);
				btnLaunch.setEnabled(false);
				btnClearScene.setEnabled(false);
				spinnerNbIt.setEnabled(false);
				if (toBeDisplayed != null && !toBeDisplayed.isEmpty())
					progressBar.setString(toBeDisplayed + percent + " %");
				btnCancel.setEnabled(true);
			}
			else
			{
				panelProgressBar.setVisible(false);
				progressBar.setIndeterminate(false);
				// btnApplyModifs.setEnabled(true);
				btnCancel.setEnabled(false);
				btnLaunch.setEnabled(true);
				btnClearScene.setEnabled(true);
				spinnerNbIt.setEnabled(true);
				progressBar.setValue(0);
			}
		}
	}

	/**
	 * Import the list of grammars in the combobox
	 * 
	 * @param grammars
	 */
	public void setListGrammars (ArrayList<String> grammars)
	{
		comboBoxGrammars.removeAllItems();
		for (String s : grammars)
			comboBoxGrammars.addItem(s);
		comboBoxGrammars.setSelectedIndex(0);

		// on active le bouzin
		comboBoxGrammars.setEnabled(true);
		mntmEditCurrentGrammar.setEnabled(true);
		mntmEditCurrentTurtle.setEnabled(true);
		comboBoxGrammars.setToolTipText("Select the wanted grammar");
	}

	/**
	 * Import the list of interpretations in the combobox
	 * 
	 * @param interpretations
	 */
	public void setListInterpretations (ArrayList<String> interpretations)
	{
		if (interpretations.isEmpty())
		{
			comboBoxInterpretations.setEnabled(false);
			lblNumberOfIterations.setEnabled(false);
			spinnerNbIt.setEnabled(false);
			btnLaunch.setEnabled(false);
			btnClearScene.setEnabled(false);
			spinnerNbIt.setEnabled(false);

			comboBoxInterpretations.setToolTipText("No Interpretation fits with the current grammar. Change the symbols!");
		}
		else
		{
			comboBoxInterpretations.removeAllItems();
			for (String s : interpretations)
				comboBoxInterpretations.addItem(s);
			comboBoxInterpretations.setSelectedIndex(0);
			// on active le bouzin
			setEnable(true);
		}
	}

	/**
	 * display in the textPane the symbols generated
	 * 
	 * @param symbols
	 */
	public void setSymbolsGenerated (String symbols)
	{
		txtrGeneratedSymbols.setText(symbols);
		txtrGeneratedSymbols.setEnabled(true);
		btnApplyModifs.setEnabled(true);
	}

	/**
	 * called when click on Launch! button. Launch the turtle calling the controller
	 */
	private void launchTurtle ()
	{
		controller.launchTurtle((int) spinnerNbIt.getValue());
	}

	/**
	 * called when click on Apply button. Launch the turtle with the edited string, calling the controller
	 */
	private void launchTurtleApplyingModifications ()
	{
		try
		{
			controller.launchTurtle(txtrGeneratedSymbols.getText());
		}
		catch (BadSymbolException e)
		{
			showException(e, "Bad interpretation");
		}
	}

	/**
	 * enable or not the components of the window
	 * 
	 * @param enabled
	 */
	private void setEnable (boolean enabled)
	{
		comboBoxInterpretations.setEnabled(enabled);
		comboBoxGrammars.setEnabled(enabled);
		lblNumberOfIterations.setEnabled(enabled);
		spinnerNbIt.setEnabled(enabled);
		mntmEditCurrentGrammar.setEnabled(enabled);
		mntmEditCurrentTurtle.setEnabled(enabled);
		btnLaunch.setEnabled(enabled);
		btnClearScene.setEnabled(enabled);

		if (enabled)
		{
			comboBoxInterpretations.setToolTipText("Select the wanted grammar");
			comboBoxGrammars.setToolTipText("Select wanted interpretation");
		}
		else
		{
			txtrGeneratedSymbols.setEnabled(false);
			btnApplyModifs.setEnabled(false);
			txtrGeneratedSymbols.setText("Generated symbols...");
			comboBoxInterpretations.setToolTipText("You have to import a file defining grammars first");
			comboBoxGrammars.setToolTipText("You have to import a file defining grammars first");
		}
	}

	/**
	 * Open a file chooser to open a grammar file. It calls the controller if a file has been chosen to be opened.
	 */
	private void openFileChooser ()
	{
		JFileChooser chooser = new JFileChooser("./doc");
		FileFilter filter = new FileNameExtensionFilter("L-SYSTEM files", "lsys");

		chooser.setFileFilter(filter);
		chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
		chooser.setMultiSelectionEnabled(false);
		chooser.setDialogTitle("Open a grammar file");
		// on ouvre la fenêtre
		int returnVal = chooser.showOpenDialog(me);
		// on teste la valeur de retour
		if (returnVal == JFileChooser.APPROVE_OPTION)
		{
			try
			{
				controller.loadFile(chooser.getSelectedFile().getAbsolutePath());
			}
			catch (ParseException | BadFileException e)
			{
				showException(new Exception("An error occurs while parsing the file \"" + chooser.getSelectedFile().getAbsolutePath()
						+ "\":\n" + e.getMessage()), "Parsing error in the file");
			}
		}
	}

	/**
	 * Open the parameter dialog for the current turtle
	 */
	private void openParameterDialog ()
	{
		ParameterDialog dialog =
				new ParameterDialog(me, "Edit " + controller.getCurrentTurtle().getName(), controller.getCurrentTurtle().getParameters());
		dialog.setVisible(true);

		if (dialog.isValidated())
		{
			try
			{
				controller.setCurrentTurtleParameters(dialog.getParameters());
			}
			catch (BadSymbolException e)
			{
				showException(e, "Bad exception");
			}
		}
	}

	/**
	 * open a dialog to edit the interpretations of the current grammar.
	 */
	private void openEditGrammarDialog ()
	{
		EditGrammarDialog dialog =
				new EditGrammarDialog(me, "Edit " + controller.getCurrentGrammar().getName(), controller.getCurrentGrammar(),
						controller.getCurrentTurtle());
		dialog.setVisible(true);
		
		if (dialog.isValidated())
			controller.setIndexOfCurrentGrammar(comboBoxGrammars.getSelectedIndex());
	}

	/**
	 * Create the window with the buttons...
	 */
	private void initFrame ()
	{
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 385, 330);

		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		JMenu mnFile = new JMenu("File");
		menuBar.add(mnFile);

		JMenuItem mntmQuit = new JMenuItem("Quit");
		mntmQuit.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Q, InputEvent.CTRL_MASK));
		mntmQuit.addActionListener(new ActionListener()
		{
			public void actionPerformed (ActionEvent e)
			{
				dispose();
			}
		});

		JMenuItem mntmOpen = new JMenuItem("Open");
		mntmOpen.addActionListener(new ActionListener()
		{
			public void actionPerformed (ActionEvent arg0)
			{
				openFileChooser();
			}
		});
		mntmOpen.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, InputEvent.CTRL_MASK));
		mnFile.add(mntmOpen);
		mnFile.add(mntmQuit);

		JMenu mnTools = new JMenu("Tools");
		menuBar.add(mnTools);

		mntmEditCurrentGrammar.setEnabled(false);
		mntmEditCurrentTurtle.setEnabled(false);
		mntmEditCurrentGrammar.addActionListener(new ActionListener()
		{
			public void actionPerformed (ActionEvent arg0)
			{
				openEditGrammarDialog();
			}
		});
		mntmEditCurrentTurtle.addActionListener(new ActionListener()
		{
			public void actionPerformed (ActionEvent arg0)
			{
				openParameterDialog();
			}
		});
		mnTools.add(mntmEditCurrentGrammar);
		mnTools.add(mntmEditCurrentTurtle);

		JMenu mnHelp = new JMenu("Help");
		menuBar.add(mnHelp);

		JMenuItem mntmAboutLsystem = new JMenuItem("About LSystem");
		mnHelp.add(mntmAboutLsystem);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);

		JToolBar toolBar = new JToolBar();
		contentPane.add(toolBar, BorderLayout.NORTH);

		comboBoxGrammars.addActionListener(new ActionListener()
		{
			public void actionPerformed (ActionEvent arg0)
			{
				controller.setIndexOfCurrentGrammar(comboBoxGrammars.getSelectedIndex());
			}
		});
		comboBoxGrammars.setEnabled(false);
		comboBoxGrammars.setToolTipText("You have to import a file defining grammars first");
		comboBoxGrammars.setModel(new DefaultComboBoxModel<String>(new String[] { "Select a grammar" }));
		toolBar.add(comboBoxGrammars);
		comboBoxInterpretations.addActionListener(new ActionListener()
		{
			public void actionPerformed (ActionEvent e)
			{
				controller.setIndexOfCurrentTurtle(comboBoxInterpretations.getSelectedIndex());
				// mntmEditCurrentTurtle.setText("Edit the turtle " + controller.getCurrentTurtle().getName());
			}
		});

		comboBoxInterpretations.setEnabled(false);
		comboBoxInterpretations.setModel(new DefaultComboBoxModel<String>(new String[] { "Select an interpretation" }));
		comboBoxInterpretations.setToolTipText("You have to import a file defining grammars first");
		toolBar.add(comboBoxInterpretations);

		lblNumberOfIterations.setEnabled(false);
		toolBar.add(lblNumberOfIterations);

		spinnerNbIt.setEnabled(false);
		spinnerNbIt.setToolTipText("Number of iterations. Maximum is 999");
		spinnerNbIt.setModel(new SpinnerNumberModel(2, 1, 999, 1));
		toolBar.add(spinnerNbIt);
		btnLaunch.setEnabled(false);
		btnLaunch.addActionListener(new ActionListener()
		{
			public void actionPerformed (ActionEvent e)
			{
				launchTurtle();
			}
		});

		toolBar.add(btnLaunch);

		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.SOUTH);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setViewportView(txtrGeneratedSymbols);
		panel.setLayout(new BorderLayout(0, 0));
		panel.add(scrollPane, BorderLayout.CENTER);
		txtrGeneratedSymbols.setText("Generated symbols...");
		txtrGeneratedSymbols.setToolTipText("Generated symbols");
		txtrGeneratedSymbols.setEditable(true);
		txtrGeneratedSymbols.setTabSize(4);
		txtrGeneratedSymbols.setRows(4);
		txtrGeneratedSymbols.setLineWrap(true);

		txtrGeneratedSymbols.setEnabled(false);

		JPanel panel_1 = new JPanel();
		panel.add(panel_1, BorderLayout.SOUTH);
		panel_1.setLayout(new BorderLayout(0, 0));

		btnApplyModifs = new JButton("Apply");
		panel_1.add(btnApplyModifs);
		btnApplyModifs.addActionListener(new ActionListener()
		{
			public void actionPerformed (ActionEvent e)
			{
				launchTurtleApplyingModifications();
			}
		});
		btnApplyModifs.setEnabled(false);

		btnClearScene.setEnabled(false);
		btnClearScene.addActionListener(new ActionListener()
		{
			public void actionPerformed (ActionEvent arg0)
			{
				controller.getCurrentTurtle().clearScene();
			}
		});
		panel_1.add(btnClearScene, BorderLayout.EAST);

		panelProgressBar.setVisible(false);
		panel.add(panelProgressBar, BorderLayout.NORTH);
		panelProgressBar.setLayout(new BorderLayout(0, 0));

		progressBar = new JProgressBar();
		panelProgressBar.add(progressBar);
		progressBar.setStringPainted(true);

		btnCancel.setEnabled(false);
		btnCancel.addActionListener(new ActionListener()
		{
			public void actionPerformed (ActionEvent arg0)
			{
				controller.stopGeneration();
			}
		});
		panelProgressBar.add(btnCancel, BorderLayout.EAST);
	}

}
