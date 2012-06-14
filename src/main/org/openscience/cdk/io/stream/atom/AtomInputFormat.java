package org.openscience.cdk.io.stream.atom;

import org.openscience.cdk.interfaces.IAtom;
import org.openscience.cdk.interfaces.IAtomContainer;

import java.io.DataInput;
import java.io.IOException;

/**
 * @author John May
 * @cdk.module io
 */
public class AtomInputFormat {

    private final IAtom               template;
    private final IAtomInputMarshal[] marshals;

    public AtomInputFormat(IAtom template,
                           IAtomInputMarshal[] marshals) {

        this.marshals = marshals;
        this.template = template;

        if (template == null)
            throw new NullPointerException("Provided template atom was null");

    }

    public IAtom read(final DataInput in, final IAtomContainer container) throws IOException {

        try {

            IAtom atom = (IAtom) template.clone();

            for (int i = 0; i < marshals.length; i++) {
                marshals[i].read(in, container, atom);
            }

            return atom;

        } catch (CloneNotSupportedException e) {
            throw new IOException("Unable to clone template atom: " + e.getMessage());
        }

    }

    /**
     * IOC method
     *
     * @param atom
     *
     * @throws IOException
     */
    public void read(final DataInput in, final IAtomContainer container, final IAtom atom) throws IOException {

        for (int i = 0; i < marshals.length; i++) {
            marshals[i].read(in, container, atom);
        }

    }

}
