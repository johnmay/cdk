/*
 * Copyright (C) 2012  John May <johnmay@users.sf.net>
 *
 * Contact: cdk-devel@lists.sourceforge.net
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public License
 * as published by the Free Software Foundation; either version 2.1
 * of the License, or (at your option) any later version.
 * All we ask is that proper credit is given for our work, which includes
 * - but is not limited to - adding the above copyright notice to the beginning
 * of your source code files, and to any copyright notice that you may distribute
 * with programs based on this work.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA 02110-1301 USA.
 */
package org.openscience.cdk.io.stream.bond;

import org.openscience.cdk.interfaces.IAtomContainer;
import org.openscience.cdk.interfaces.IBond;

import java.io.DataInput;
import java.io.IOException;

/**
 * Defines a marshal that will marshal the contents of an IBond into an output
 *
 * @author John May
 * @cdk.module interfaces
 */
public interface IBondInputMarshal {

    /**
     * Reads specific information from the input stream
     *
     * @param in   data input stream
     * @param bond instance of bond to read data into
     */
    public void read(DataInput in, IAtomContainer container, IBond bond) throws IOException;

    /**
     * Set the default value for the field you're reading. Currently
     * the default is set on a single object (at the start of the stream)
     * and that object is clone. Therefore the default should be independent
     * of other values
     *
     * @param bond
     */
    public void setDefault(IBond bond);

}
