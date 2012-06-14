package org.openscience.cdk.io.stream.bond.marshal;

import org.openscience.cdk.interfaces.IAtomContainer;
import org.openscience.cdk.interfaces.IBond;
import org.openscience.cdk.io.stream.bond.BondInputMarshal;

import java.io.DataInput;
import java.io.IOException;

/**
 * @author John May
 * @cdk.module io
 */
public class SmallBondInput extends BondInputMarshal {

    @Override
    public void read(DataInput in, IAtomContainer container, IBond bond) throws IOException {
        bond.setAtom(container.getAtom(readUnsignedByte(in)), 0);
        bond.setAtom(container.getAtom(readUnsignedByte(in)), 1);
    }
}
