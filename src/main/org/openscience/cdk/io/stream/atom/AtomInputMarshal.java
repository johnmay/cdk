package org.openscience.cdk.io.stream.atom;

import org.openscience.cdk.interfaces.IAtom;
import org.openscience.cdk.interfaces.IAtomContainer;
import org.openscience.cdk.interfaces.IChemObject;
import org.openscience.cdk.io.stream.AbstractInputMarshal;

import java.io.DataInput;
import java.io.IOException;

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

    @Override
    public void read(DataInput in, IAtomContainer container, IChemObject chemobj) throws IOException {
        read(in, container, (IAtom) chemobj);
    }

    @Override
    public void setDefault(IChemObject atom) {
        setDefault((IAtom) atom);
    }
}
