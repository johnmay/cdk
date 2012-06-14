package org.openscience.cdk.io.stream.atom;

import org.openscience.cdk.interfaces.IAtom;

import java.io.DataInput;
import java.io.IOException;

/**
 * @author John May
 * @cdk.module io
 */
public class AtomInput {

    private final IAtom               template;
    private final IAtomInputMarshal[] marshals;

    public AtomInput(IAtom template,
                     IAtomInputMarshal[] marshals) {

        this.marshals = marshals;
        this.template = template;

        if (template == null) {
            throw new NullPointerException("Template atom must not be null");
        }

    }

    public IAtom read(DataInput in) throws IOException {

        try {

            IAtom atom = (IAtom) template.clone();

            for (int i = 0; i < marshals.length; i++) {
                marshals[i].read(in, atom);
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
    public void read(final DataInput in, final IAtom atom) throws IOException {

        for (int i = 0; i < marshals.length; i++) {
            marshals[i].read(in, atom);
        }

    }

}
