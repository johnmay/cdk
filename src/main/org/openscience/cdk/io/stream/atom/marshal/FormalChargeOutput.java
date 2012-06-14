package org.openscience.cdk.io.stream.atom.marshal;

import org.openscience.cdk.interfaces.IAtom;
import org.openscience.cdk.interfaces.IAtomContainer;
import org.openscience.cdk.io.stream.atom.AtomOutputMarshal;

import java.io.DataOutput;
import java.io.IOException;

/**
 * @author John May
 * @cdk.module io
 */
public class FormalChargeOutput
        extends AtomOutputMarshal {

    @Override
    public void write(DataOutput out, IAtomContainer container, IAtom atom) throws IOException {
        out.writeByte(atom.getFormalCharge());
    }

    @Override
    public boolean isDefault(IAtom atom) {
        return atom.getFormalCharge() == null || atom.getFormalCharge() == 0;
    }
}
