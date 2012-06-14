package org.openscience.cdk.io.stream.bond;

import org.openscience.cdk.interfaces.IAtomContainer;
import org.openscience.cdk.interfaces.IBond;

import java.io.DataInput;
import java.io.IOException;

/**
 * @author John May
 * @cdk.module io
 */
public class BondInput {

    private final IBond               template;
    private final IBondInputMarshal[] marshals;

    public BondInput(IBond template,
                     IBondInputMarshal[] marshals) {


        this.marshals = marshals;
        this.template = template;

        if (template == null) {
            throw new NullPointerException("Template bond must not be null");
        }
    }

    public IBond read(DataInput in, IAtomContainer container) throws IOException {

        try {

            return read(in, container, (IBond) template.clone());

        } catch (CloneNotSupportedException e) {
            throw new IOException("Unable to clone template atom: " + e.getMessage());
        }
    }

    /**
     * IOC method
     *
     * @param bond
     *
     * @throws java.io.IOException
     */
    public IBond read(final DataInput in, final IAtomContainer container, final IBond bond) throws IOException {

        for (int i = 0; i < marshals.length; i++) {
            marshals[i].read(in, container, bond);
        }

        return bond;

    }

}
