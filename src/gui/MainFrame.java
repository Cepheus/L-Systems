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
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JMenuItem;


// important line : JPopupMenu.setDefaultLightWeightPopupEnabled(false);

/**
 * Contains the MainWindow of the application (the first that is launched at the start).
 * 
 * @author xinouch
 */
public class MainFrame extends JFrame
{

	/** */
	private static final long serialVersionUID = -1306687705594790857L;
	/** principle panel */
	private JPanel contentPane;
	/** Panel containing the 3D application */
	private Panel3D panel3d;

	/**
	 * Create the frame.
	 * 
	 * @param panel the 3D application
	 */
	public MainFrame (Panel3D panel)
	{
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 385, 330);

		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		JMenu mnFile = new JMenu("File");
		menuBar.add(mnFile);

		JMenuItem mntmQuit = new JMenuItem("Quit");
		mntmQuit.addActionListener(new ActionListener()
		{
			public void actionPerformed (ActionEvent e)
			{
				dispose();
			}
		});
		mnFile.add(mntmQuit);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);

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

		pack();
	}

}
