package org.openscience.cdk.io.stream.atom.marshal;

import org.openscience.cdk.interfaces.IAtom;
import org.openscience.cdk.io.stream.atom.AtomInputMarshal;
import org.openscience.cdk.tools.periodictable.PeriodicTable;

import java.io.DataInput;
import java.io.IOException;

/**
 * Reads an unsigned byte from the data input and sets this as the atomic number. The
 * atomic number is then used to lookup the symbol in the {@see PeriodicTable}. The symbol
 * is then set for the atom.
 * <p/>
 * This input is used in conjunction with {@see AtomContainerInput} and is analogous to the
 * output {@see AtomicNumberOutput}
 *
 * @author John May
 * @cdk.module io
 * @see IAtom#setAtomicNumber(Integer)
 * @see PeriodicTable#getSymbol(int)
 * @see org.openscience.cdk.io.stream.container.AtomContainerInput
 * @see AtomicNumberOutput
 */
public class AtomicNumberInput
        extends AtomInputMarshal<IAtom> {

    /**
     * Reads an unsigned byte from the data input and sets the atomic number. The symbol
     * is also set via a lookup to the periodic table.
     *
     * @param in   data input stream
     * @param atom instance of atom to read into
     *
     * @throws IOException low-level io exception
     * @inheritDoc
     */
    @Override
    public void read(DataInput in, IAtom atom) throws IOException {
        atom.setAtomicNumber(readUnsignedByte(in));
        atom.setSymbol(PeriodicTable.getSymbol(atom.getAtomicNumber()));
    }


}
