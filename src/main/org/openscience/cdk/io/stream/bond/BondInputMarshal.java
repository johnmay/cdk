package org.openscience.cdk.io.stream.bond;

import org.openscience.cdk.interfaces.IBond;
import org.openscience.cdk.io.stream.AbstractInputMarshal;

/**
 * @author John May
 * @cdk.module io
 */
public abstract class BondInputMarshal
        extends AbstractInputMarshal
        implements IBondInputMarshal {


    @Override
    public void setDefault(IBond bond) {
        // do nothing - leave value as null
    }

}
