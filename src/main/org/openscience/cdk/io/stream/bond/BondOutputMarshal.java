package org.openscience.cdk.io.stream.bond;

import org.openscience.cdk.interfaces.IAtomContainer;
import org.openscience.cdk.interfaces.IBond;
import org.openscience.cdk.io.stream.AbstractOutputMarshal;

/**
 * @author John May
 * @cdk.module io
 */
public abstract class BondOutputMarshal
        extends AbstractOutputMarshal
        implements IBondOutputMarshal {

    @Override
    public boolean isDefault(IAtomContainer container, IBond bond) {
        return false;
    }
}
