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

    private static final Map<Integer, IBond.Order> orders = new HashMap<Integer, IBond.Order>(3) {{
        put(2, IBond.Order.DOUBLE);
        put(3, IBond.Order.TRIPLE);
        put(4, IBond.Order.QUADRUPLE);
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
