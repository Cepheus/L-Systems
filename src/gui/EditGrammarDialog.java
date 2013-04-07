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
 * * Project: L-Systems* File: EditGrammarDialog.java** Created on: 7 avr. 2013* Author: xinouch*
 * *****************************************************
 */

package gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import parser.Grammar;
import parser.ListSymbols;
import parser.Symbol;
import ATuin.Turtle;


/**
 * Adaptative panel to change the interpretations of each symbols of a grammar
 * 
 * @author xinouch
 */
public class EditGrammarDialog extends JDialog
{
	/** */
	private static final long serialVersionUID = -2671895994690627557L;
	/** Main panel */
	private final JPanel contentPanel = new JPanel();
	/** the combobox to choose the interpretations */
	private ArrayList<JComboBox<String>> boxes = new ArrayList<JComboBox<String>>();
	/** the list of standard symbols */
	private ListSymbols standardSymbols;
	/** the list of symbols understood by the turtle */
	private ListSymbols turtleSymbols;
	/** the list of symbols defined in the grammar */
	private ListSymbols grammarSymbols;
	/** true if the user clicked on OK, false otherwise */
	private boolean validated = false;
	/** the panel containing the symbols */
	private final JPanel panelAdapt = new JPanel();
	/** tells if we want to show standards symbols only */
	private final JRadioButton rdbtnStandard = new JRadioButton("Show standard Interpretations");
	/** tells if we want to show turtle's symbols only */
	private final JRadioButton rdbtnTurtle = new JRadioButton("Show interpretations from current turtle");

	/**
	 * Create the dialog.
	 * 
	 * @param owner the owner of this dialog
	 * @param title the title of the window
	 * @param currentGrammar
	 * @param currentTurtle
	 */
	public EditGrammarDialog (Frame owner, String title, Grammar currentGrammar, Turtle currentTurtle)
	{
		super(owner);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setModalityType(ModalityType.APPLICATION_MODAL);
		setModal(true);
		setTitle(title);
		turtleSymbols = currentTurtle.getAuthorizedInterpretation();
		grammarSymbols = currentGrammar.getUsableSymbolsWithoutNull();
		standardSymbols = Symbol.standardsSymbols();
		standardSymbols.remove(standardSymbols.size() - 1); // we remove the NULLCHARACTER

		initDialog();
		initInputs();
		displayInterpretations(standardSymbols);
		pack();
	}

	/**
	 * @return the validated : true if the user clicked on OK, false otherwise
	 */
	public boolean isValidated ()
	{
		return validated;
	}

	/**
	 * initialize he dialog with the parameter list
	 */
	private void initDialog ()
	{
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new BorderLayout(0, 0));
		{
			JPanel panelRadio = new JPanel();
			contentPanel.add(panelRadio, BorderLayout.NORTH);
			panelRadio.setLayout(new GridLayout(0, 1, 0, 0));
			{
				rdbtnStandard.addChangeListener(new ChangeListener()
				{
					public void stateChanged (ChangeEvent arg0)
					{
						if (rdbtnTurtle != null && rdbtnStandard != null)
						{
							rdbtnTurtle.setSelected(!rdbtnStandard.isSelected());
							if (rdbtnStandard.isSelected())
								displayInterpretations(standardSymbols);
						}
					}
				});
				rdbtnStandard.setSelected(true);
				panelRadio.add(rdbtnStandard);
			}
			{
				rdbtnTurtle.addActionListener(new ActionListener()
				{
					public void actionPerformed (ActionEvent arg0)
					{
						if (rdbtnTurtle != null && rdbtnStandard != null)
						{
							rdbtnStandard.setSelected(!rdbtnTurtle.isSelected());
							if (rdbtnTurtle.isSelected())
								displayInterpretations(turtleSymbols);
						}
					}
				});
				panelRadio.add(rdbtnTurtle);
			}
		}
		{
			contentPanel.add(panelAdapt, BorderLayout.CENTER);
		}
		panelAdapt.setLayout(new GridLayout(grammarSymbols.size(), 2, 0, 0));

		JPanel buttonPane = new JPanel();
		buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
		getContentPane().add(buttonPane, BorderLayout.SOUTH);
		{
			JButton okButton = new JButton("OK");
			okButton.addActionListener(new ActionListener()
			{
				public void actionPerformed (ActionEvent e)
				{
					validated = true;
					validateCombo();
					dispose();
				}
			});
			buttonPane.add(okButton);
			getRootPane().setDefaultButton(okButton);
		}
		{
			JButton cancelButton = new JButton("Cancel");
			cancelButton.addActionListener(new ActionListener()
			{
				public void actionPerformed (ActionEvent e)
				{
					validated = false;
					dispose();
				}
			});
			buttonPane.add(cancelButton);
		}
	}

	/**
	 * initialize the list of combobox and put the labels in panelAdapt
	 */
	private void initInputs ()
	{
		final int size = grammarSymbols.size();
		JComboBox<String> combo;

		for (int i = 0; i < size; i++)
		{
			combo = new JComboBox<String>();
			combo.setModel(new DefaultComboBoxModel<String>(new String[] { "No Interpretations found" }));
			boxes.add(combo);

			panelAdapt.add(new JLabel(String.valueOf(grammarSymbols.get(i).getCharacter())));
			panelAdapt.add(combo);
		}
	}

	/**
	 * display in the combobox only the given interpretations
	 * 
	 * @param interpretations the interpretations to display
	 */
	private void displayInterpretations (ListSymbols interpretations)
	{
		final int sizej = interpretations.size(), sizei = boxes.size();
		int selectedItem;

		for (int i = 0; i < sizei; i++)
		{
			selectedItem = -1;
			boxes.get(i).removeAllItems();
			for (int j = 0; j < sizej; j++)
			{
				if (interpretations.get(j).getInterpretation() == Symbol.S_UNDETERMINATE)
				{
					boxes.get(i).addItem("UNDETERMINATE");
					if (selectedItem == -1)
						selectedItem = j;
				}
				else
					boxes.get(i).addItem(interpretations.get(j).getName());
				if (interpretations.get(j).getInterpretation() == grammarSymbols.get(i).getInterpretation())
					selectedItem = j;
			}
			boxes.get(i).setSelectedIndex(selectedItem);
		}
	}

	/**
	 * when push button OK -> we update the grammarSymbols with the values in the combobox
	 */
	private void validateCombo ()
	{
		ListSymbols validatedSymbols = rdbtnStandard.isSelected() ? standardSymbols : turtleSymbols;
		final int size = grammarSymbols.size();

		for (int i = 0; i < size; i++)
		{
			// we set the grammar symbol with the standard or turtle interpretation selected in the combobox
			grammarSymbols.get(i).setInterpretation(validatedSymbols.get(boxes.get(i).getSelectedIndex()).getInterpretation());
		}
	}
}
