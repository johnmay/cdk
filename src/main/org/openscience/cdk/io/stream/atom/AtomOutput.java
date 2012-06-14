package org.openscience.cdk.io.stream.atom;

import org.openscience.cdk.interfaces.IAtom;

import java.io.DataOutput;
import java.io.IOException;

/**
 * @author John May
 * @cdk.module io
 */
public class AtomOutput {

    private final IAtomOutputMarshal[] marshals;

    public AtomOutput(IAtomOutputMarshal[] marshals) {
        this.marshals = marshals;
    }

    public void write(final DataOutput out, final IAtom atom) throws IOException {
        for (int i = 0; i < marshals.length; i++) {
            marshals[i].write(out, atom);
        }
    }

}
