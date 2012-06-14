package org.openscience.cdk.io.stream.bond.marshal;

import org.openscience.cdk.interfaces.IAtomContainer;
import org.openscience.cdk.interfaces.IBond;
import org.openscience.cdk.io.stream.bond.BondOutputMarshal;

import java.io.DataOutput;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author John May
 * @cdk.module io
 */
public class BondOrderOutput extends BondOutputMarshal {

    static final Map<IBond.Order, Integer> orders = new HashMap<IBond.Order, Integer>(3, 1f) {{
        put(IBond.Order.DOUBLE, 2);
        put(IBond.Order.TRIPLE, 3);
        put(IBond.Order.QUADRUPLE, 4);
    }};

    @Override
    public void write(DataOutput out, IAtomContainer container, IBond bond) throws IOException {
        out.writeByte(orders.get(bond.getOrder()).byteValue());
    }

    @Override
    public boolean isDefault(IAtomContainer container, IBond bond) {
        return bond.getOrder() == null || bond.getOrder() == IBond.Order.SINGLE;
    }
}
