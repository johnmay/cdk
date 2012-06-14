package org.openscience.cdk.io.stream.bond.marshal;

import org.openscience.cdk.interfaces.IAtomContainer;
import org.openscience.cdk.interfaces.IBond;
import org.openscience.cdk.io.stream.bond.BondOutputMarshal;

import java.io.DataOutput;
import java.io.IOException;

/**
 * @author John May
 * @cdk.module io
 */
public class SmallBondOutput extends BondOutputMarshal {

    @Override
    public void write(DataOutput out, IAtomContainer container, IBond bond) throws IOException {
        writeAsUnsignedByte(out, container.getAtomNumber(bond.getAtom(0)));
        writeAsUnsignedByte(out, container.getAtomNumber(bond.getAtom(1)));
    }

    @Override
    public boolean isDefault(IAtomContainer container, IBond bond) {
        return bond.getAtomCount() != 2 || container.getAtomCount() >= 255;
    }
}
