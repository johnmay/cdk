package org.openscience.cdk.io.stream.bond.marshal;

import org.openscience.cdk.interfaces.IAtomContainer;
import org.openscience.cdk.interfaces.IBond;
import org.openscience.cdk.io.stream.bond.BondInputMarshal;

import java.io.DataInput;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author John May
 * @cdk.module io
 */
public class BondOrderInput extends BondInputMarshal {

    private static final Map<Byte, IBond.Order> orders = new HashMap<Byte, IBond.Order>(3) {{
        put((byte) 2, IBond.Order.DOUBLE);
        put((byte) 3, IBond.Order.TRIPLE);
        put((byte) 4, IBond.Order.QUADRUPLE);
    }};

    @Override
    public void read(DataInput in, IAtomContainer container, IBond bond) throws IOException {
        bond.setOrder(orders.get(in.readByte()));
    }

    @Override
    public void setDefault(IBond bond) {
        bond.setOrder(IBond.Order.SINGLE);
    }
}
