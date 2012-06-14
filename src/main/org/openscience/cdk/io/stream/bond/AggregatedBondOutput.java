package org.openscience.cdk.io.stream.bond;

import org.openscience.cdk.interfaces.IAtomContainer;
import org.openscience.cdk.interfaces.IBond;

import java.io.DataOutput;
import java.io.IOException;
import java.security.InvalidParameterException;
import java.util.*;

/**
 * @author John May
 * @cdk.module io
 */
public class AggregatedBondOutput
        implements IBondOutputMarshal {

    private Map<Byte, BondOutput>    outputs  = new HashMap<Byte, BondOutput>();
    private List<IBondOutputMarshal> marshals = new ArrayList<IBondOutputMarshal>(8);

    // could remove this map but need a conversion utility
    private static final Map<Integer, Byte> indexMap = new HashMap<Integer, Byte>(8) {{
        put(0, (byte) 0x01);
        put(1, (byte) 0x02);
        put(2, (byte) 0x04);
        put(3, (byte) 0x08);
        put(4, (byte) 0x10);
        put(5, (byte) 0x20);
        put(6, (byte) 0x40);
        // 0x80 is reserved atm
    }};

    public AggregatedBondOutput(IBondOutputMarshal... marshals) {
        this(Arrays.asList(marshals));
    }

    public AggregatedBondOutput(List<IBondOutputMarshal> marshals) {
        this.marshals.addAll(marshals);

        if (marshals.size() > 7)
            throw new InvalidParameterException("Too many marshals to aggregate, use a nested aggregator");


    }

    private byte createFlag(IAtomContainer container, IBond bond) {

        // indicates whether we're writing a pseudo atom
        byte flag = (byte) 0x00;

        for (int i = 0; i < marshals.size(); i++) {
            if (!marshals.get(i).isDefault(container, bond)) {
                flag |= indexMap.get(i);
            }
        }

        return flag;

    }

    private BondOutput getBondOutput(final byte flag) {
        BondOutput output = outputs.get(flag);
        if (output == null) {
            output = createBondOutput(flag);
            outputs.put(flag, output);
        }
        return output;
    }

    private BondOutput createBondOutput(byte flag) {

        List<IBondOutputMarshal> marshalList = new ArrayList<IBondOutputMarshal>(8);

        for (int i = 0; i < marshals.size(); i++) {
            byte mask = indexMap.get(i);
            if ((mask & flag) == mask) {
                marshalList.add(marshals.get(i));
            }
        }

        return new BondOutput(marshalList.toArray(new IBondOutputMarshal[marshalList.size()]));

    }

    @Override
    public void write(DataOutput out, IAtomContainer container, IBond bond) throws IOException {
        final byte flag = createFlag(container, bond);
        out.writeByte(flag);
        getBondOutput(flag).write(out, container, bond);
    }

    @Override
    public boolean isDefault(IAtomContainer container, IBond bond) {
        return false; // should always be false as it's aggregated we could check here whether all the flags are off
    }
}
