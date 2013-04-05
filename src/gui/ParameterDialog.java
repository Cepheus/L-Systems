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
 * * Project: L-Systems* File: ParameterDialog.java** Created on: 5 avr. 2013* Author: xinouch*
 * *****************************************************
 */

package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import ATuin.Parameter;

import com.jme3.math.ColorRGBA;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.File;


/**
 * Adaptative panel to change the parameters of a@SuppressWarnings ("serial") turtle
 * 
 * @author xinouch
 */
public class ParameterDialog extends JDialog
{
	/** */
	private static final long serialVersionUID = -2671895994690627557L;
	/** Main panel */
	private final JPanel contentPanel = new JPanel();
	/** the list of parameters of the turlte */
	private ArrayList<Parameter> parameters;
	/** true if the user clicked on OK, false otherwise */
	private boolean validated = false;

	/**
	 * Create the dialog.
	 * 
	 * @param title the title of the window
	 * @param params the params
	 */
	public ParameterDialog (String title, ArrayList<Parameter> params)
	{
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setModalityType(ModalityType.APPLICATION_MODAL);
		setModal(true);
		setTitle(title);
		parameters = params;

		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new GridLayout(parameters.size(), 2, 0, 0));

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

		initDialog();
		pack();
	}

	/**
	 * @return the parameters
	 */
	public ArrayList<Parameter> getParameters ()
	{
		return parameters;
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
		final int size = parameters.size();
		Parameter param;

		for (int i = 0; i < size; i++)
		{
			param = parameters.get(i);

			contentPanel.add(new JLabel(param.getName()));
			switch (param.getType())
			{
				case TYPE_INTEGER:
				{
					final int current = i;
					final JSpinner spinner = new JSpinner();
					final SpinnerNumberModel model = new SpinnerNumberModel(new Integer(0), null, null, new Integer(1));
					spinner.addChangeListener(new ChangeListener()
					{
						public void stateChanged (ChangeEvent arg0)
						{
							parameters.get(current).setValue(model.getNumber().intValue());
						}
					});
					spinner.setModel(model);
					model.setValue(param.getValue());
					spinner.setToolTipText("Integer");
					contentPanel.add(spinner);
					break;
				}
				case TYPE_DOUBLE:
				{
					final int current = i;
					final JSpinner spinner = new JSpinner();
					final SpinnerNumberModel model = new SpinnerNumberModel(new Double(0), null, null, new Double(1));
					spinner.addChangeListener(new ChangeListener()
					{
						public void stateChanged (ChangeEvent arg0)
						{
							parameters.get(current).setValue(model.getNumber().doubleValue());
						}
					});
					spinner.setToolTipText("Double");
					spinner.setModel(model);
					model.setValue(param.getValue());
					contentPanel.add(spinner);
					break;
				}
				case TYPE_STRING:
				{
					final int current = i;
					final JTextField textField = new JTextField();
					textField.addFocusListener(new FocusAdapter()
					{
						@Override
						public void focusLost (FocusEvent e)
						{
							parameters.get(current).setValue(textField.getText());
						}
					});
					textField.setText((String) param.getValue());
					contentPanel.add(textField);
					textField.setColumns(30);
					break;
				}
				case TYPE_COLOR:
				{
					final int current = i;
					final JTextField textField = new JTextField();
					ColorRGBA c = (ColorRGBA) param.getValue();
					textField.addMouseListener(new MouseAdapter()
					{
						@Override
						public void mouseClicked (MouseEvent e)
						{
							final Color couleur = JColorChooser.showDialog(textField, "Choose a new color", textField.getBackground());
							if (couleur != null)
							{
								float red = couleur.getRed() / 255.f;
								float green = couleur.getGreen() / 255.f;
								float blue = couleur.getBlue() / 255.f;
								float alpha = couleur.getAlpha() / 255.f;
								ColorRGBA crgb = new ColorRGBA(red, green, blue, alpha);
								crgb.clamp();
								parameters.get(current).setValue(crgb);
								textField.setBackground(couleur);
							}
						}
					});
					textField.setEditable(false);
					textField.setBackground(new Color(c.r, c.g, c.b, c.a));
					contentPanel.add(textField);
					textField.setColumns(30);
					break;
				}
				case TYPE_FILE:
				{
					final int current = i;
					final JTextField textField = new JTextField();
					textField.addActionListener(new ActionListener()
					{
						public void actionPerformed (ActionEvent arg0)
						{
							JFileChooser fc = new JFileChooser();
							fc.setDialogTitle("Choose a file");
							fc.setMultiSelectionEnabled(false);
							fc.showOpenDialog(textField);

							File file = fc.getSelectedFile();
							if (file != null)
							{
								parameters.get(current).setValue(file);
								textField.setText(file.getAbsolutePath());
							}
						}
					});
					textField.setText(((File) param.getValue()).getAbsolutePath());
					contentPanel.add(textField);
					textField.setColumns(30);
					break;
				}
			}
		}
	}
}
