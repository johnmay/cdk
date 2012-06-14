package org.openscience.cdk.io.stream.atom;

import org.openscience.cdk.interfaces.IAtom;
import org.openscience.cdk.io.stream.AbstractInputMarshal;

/**
 * @author John May
 * @cdk.module io
 */
public abstract class AtomInputMarshal<A extends IAtom>
        extends AbstractInputMarshal
        implements IAtomInputMarshal<A> {


    /**
     * @inheritDoc
     */
    @Override
    public void setDefault(IAtom atom) {
        // do nothing - leave value as null
    }

}
