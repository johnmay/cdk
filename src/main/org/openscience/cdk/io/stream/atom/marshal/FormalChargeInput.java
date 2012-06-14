package org.openscience.cdk.io.stream.atom.marshal;

import org.openscience.cdk.interfaces.IAtom;
import org.openscience.cdk.interfaces.IAtomContainer;
import org.openscience.cdk.io.stream.atom.AtomInputMarshal;

import java.io.DataInput;
import java.io.IOException;

/**
 * @author John May
 * @cdk.module io
 */
public class FormalChargeInput
        extends AtomInputMarshal<IAtom> {

    @Override
    public void read(final DataInput in, final IAtomContainer container, final IAtom atom) throws IOException {
        atom.setFormalCharge(readByte(in));
    }

    @Override
    public void setDefault(IAtom atom) {
        atom.setFormalCharge(0);
    }
}
