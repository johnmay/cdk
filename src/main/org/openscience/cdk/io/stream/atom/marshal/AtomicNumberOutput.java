package org.openscience.cdk.io.stream.atom.marshal;

import org.openscience.cdk.interfaces.IAtom;
import org.openscience.cdk.interfaces.IAtomContainer;
import org.openscience.cdk.io.stream.atom.AtomOutputMarshal;

import java.io.DataOutput;
import java.io.IOException;

/**
 * Writes the atomic number of an atom to the {@see DataOutput} as an unsigned byte.
 *
 * @author John May
 * @cdk.module io
 * @see org.openscience.cdk.interfaces.IAtom#getAtomicNumber()
 * @see AtomicNumberInput
 */
public class AtomicNumberOutput
        extends AtomOutputMarshal {

    /**
     * @param out  where the data will be written
     * @param atom the atom of which to write the information on
     *
     * @throws IOException low-level io exception
     * @inheitDoc
     */
    @Override
    public void write(DataOutput out, IAtomContainer container, IAtom atom) throws IOException {
        writeAsUnsignedByte(out, atom.getAtomicNumber());
    }

    /**
     * Determines whether the atomic number is default. In this case
     * value will not be written if the atomic number is null or the
     * atomic number is less then 0
     *
     * @param atom instance of the atom that will be stored
     *
     * @return whether the value is default and should be skipped
     */
    @Override
    public boolean isDefault(IAtom atom) {
        return atom.getAtomicNumber() == null || atom.getAtomicNumber() < 0;
    }


}
