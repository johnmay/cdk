package org.openscience.cdk.io.stream.atom.marshal;

import org.openscience.cdk.interfaces.IAtom;
import org.openscience.cdk.io.stream.atom.AtomInputMarshal;

import java.io.DataInput;
import java.io.IOException;

/**
 * @author John May
 * @cdk.module io
 */
public class ValencyInput
        extends AtomInputMarshal<IAtom> {

    @Override
    public void read(DataInput in, IAtom atom) throws IOException {
        atom.setValency(readUnsignedByte(in));
    }

    @Override
    public void setDefault(IAtom atom) {
        atom.setValency(0);
    }
}
