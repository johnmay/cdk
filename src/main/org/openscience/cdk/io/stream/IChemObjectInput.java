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
package org.openscience.cdk.io.stream;

import org.openscience.cdk.interfaces.IChemObject;

import java.io.DataInput;
import java.io.IOException;

/**
 * Defines a class that can read an IChemObject from an input stream. IChemObjectInput
 * is distinct from IChemObjectReader in that the object is read from a binary
 * data (Input) opposed to a character stream (Reader).
 *
 * @author John May
 * @cdk.module interfaces
 * @see IChemObject
 * @see org.openscience.cdk.io.IChemObjectReader
 */
public interface IChemObjectInput<C extends IChemObject> {

    /**
     * Reads an IChemObject form an input stream.
     *
     * @return instance of a read IChemObject
     *
     * @throws IOException low-level io exception
     */
    public C read() throws IOException;

    /**
     * IOC method
     *
     * @param in
     *
     * @return
     *
     * @throws IOException
     */
    public C read(DataInput in) throws IOException;

}