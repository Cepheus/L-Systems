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
 * * Project: L-Systems* File: Parameters.java** Created on: 5 avr. 2013* Author: xinouch*
 * *****************************************************
 */

package ATuin;

/**
 * @author xinouch
 * @brief represents a parameter of a turtle. A parameter has a name, a type and a default value. the value can be retrieved by casting the
 *        object returned by getValue() in the correct type. The type must be one of the given ones in this class.
 */
public class Parameter
{
	/**
	 * the available types of parameters. All paramters must inherit from Object.
	 * 
	 * @author xinouch
	 */
	public enum ParameterType
	{
		/** the parameter is a java.lang.Integer */
		TYPE_INTEGER,
		/** the parameter is a java.lang.Float */
		TYPE_DOUBLE,
		/** the parameter is a java.lang.String */
		TYPE_STRING,
		/** the parameter is a com.jme3.math.ColorRGBA */
		TYPE_COLOR,
		/** the parameter is a java.io.File */
		TYPE_FILE
	}

	/** the type of this parameter */
	private ParameterType m_type = ParameterType.TYPE_INTEGER;
	/** the name of this parameter */
	private String m_name = "";
	/** the value of this parameter */
	private Object m_value;
	
	/**
	 * default constructor
	 */
	public Parameter ()
	{
	}
	
	/**
	 * constructor
	 * @param name
	 * @param type
	 * @param value
	 */
	public Parameter (String name, ParameterType type, Object value)
	{
		m_name = name;
		m_type = type;
		m_value = value;
	}

	/**
	 * @return the m_type
	 */
	public ParameterType getType ()
	{
		return m_type;
	}

	/**
	 * @param m_type the m_type to set
	 */
	public void setType (ParameterType m_type)
	{
		this.m_type = m_type;
	}

	/**
	 * @return the name
	 */
	public String getName ()
	{
		return m_name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName (String name)
	{
		this.m_name = name;
	}

	/**
	 * @return the value
	 */
	public Object getValue ()
	{
		return m_value;
	}

	/**
	 * @param value the value to set
	 */
	public void setValue (Object value)
	{
		this.m_value = value;
	}
	
	@Override
	public String toString ()
	{
		String s = m_name;
		
		switch (m_type)
		{
			case TYPE_INTEGER:
				s += " integer ";
				break;
			case TYPE_DOUBLE:
				s += " double ";
				break;
			case TYPE_STRING:
				s += " string ";
				break;
			case TYPE_COLOR:
				s += " color ";
				break;
			case TYPE_FILE:
				s += " file ";
				break;
		}
		s += m_value;
		
		return s;
	}
}
