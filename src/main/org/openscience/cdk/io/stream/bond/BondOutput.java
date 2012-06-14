package org.openscience.cdk.io.stream.bond;

import org.openscience.cdk.interfaces.IAtomContainer;
import org.openscience.cdk.interfaces.IBond;

import java.io.DataOutput;
import java.io.IOException;

/**
 * @author John May
 * @cdk.module io
 */
public class BondOutput {

    private final IBondOutputMarshal[] marshals;

    public BondOutput(IBondOutputMarshal[] marshals) {
        this.marshals = marshals;
    }

    public void write(final DataOutput out, final IAtomContainer container, final IBond bond) throws IOException {
        for (int i = 0; i < marshals.length; i++) {
            marshals[i].write(out, container, bond);
        }
    }

}
