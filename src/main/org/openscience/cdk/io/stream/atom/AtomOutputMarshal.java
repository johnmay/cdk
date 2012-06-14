package org.openscience.cdk.io.stream.atom;

import org.openscience.cdk.interfaces.IAtom;
import org.openscience.cdk.io.stream.AbstractOutputMarshal;

/**
 * @author John May
 * @cdk.module io
 */
public abstract class AtomOutputMarshal<A extends IAtom>
        extends AbstractOutputMarshal
        implements IAtomOutputMarshal<A> {

    @Override
    public boolean isDefault(IAtom atom) {
        return false;
    }
}
