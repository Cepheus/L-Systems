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

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JMenuItem;
import javax.swing.KeyStroke;
import java.awt.event.KeyEvent;
import java.awt.event.InputEvent;

import javax.swing.JFileChooser;
import javax.swing.JToolBar;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;


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

	/**
	 * Create the frame.
	 * 
	 * @param c le controller de la fen^etre à appeler lors de manipulation spéciales
	 * @param panel the 3D application
	 */
	public MainFrame (Controller c, Panel3D panel)
	{
		controller = c;

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
		// we create the window
		initFrame();
	}
	
	/**
	 * Open a file chooser to open a grammar file.
	 * It calls the controller if a file has been chosen to be opened.
	 */
	void openFileChooser ()
	{
		JFileChooser chooser = new JFileChooser();
		FileFilter filter = new FileNameExtensionFilter("L-SYSTEM files", "lsys");
		
		chooser.setFileFilter(filter);
		chooser.setFileSelectionMode(JFileChooser.FILES_ONLY );
		chooser.setMultiSelectionEnabled(false);
		chooser.setDialogTitle("Open a grammar file");
		// on ouvre la fenêtre
		int returnVal = chooser.showOpenDialog(me);
		// on teste la valeur de retour
		if(returnVal == JFileChooser.APPROVE_OPTION)
			System.out.println(chooser.getSelectedFile().getAbsolutePath());
	}

	/**
	 * Create the window with the buttons...
	 */
	void initFrame ()
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
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		contentPane.add(panel3d, BorderLayout.CENTER);

		JToolBar toolBar = new JToolBar();
		contentPane.add(toolBar, BorderLayout.NORTH);

		JComboBox<String> comboBoxInterpretation = new JComboBox<String>();
		comboBoxInterpretation.setToolTipText("You have to import a file defining grammars first");
		comboBoxInterpretation.setEnabled(false);
		comboBoxInterpretation.setModel(new DefaultComboBoxModel<String>(new String[] { "Select a grammar" }));
		toolBar.add(comboBoxInterpretation);

		JComboBox<String> comboBox = new JComboBox<String>();
		comboBox.setEnabled(false);
		comboBox.setModel(new DefaultComboBoxModel<String>(new String[] { "Select an interpretation" }));
		comboBox.setToolTipText("You have to import a file defining grammars first");
		toolBar.add(comboBox);

		pack();
	}

}
