package org.openscience.cdk.io.stream.atom;

import org.openscience.cdk.interfaces.IAtom;
import org.openscience.cdk.interfaces.IPseudoAtom;

import java.io.DataOutput;
import java.io.IOException;
import java.security.InvalidParameterException;
import java.util.*;

/**
 * @author John May
 * @cdk.module io
 */
public class AggregatedAtomOutput
        implements IAtomOutputMarshal {

    private Map<Byte, AtomOutput>    outputs  = new HashMap<Byte, AtomOutput>();
    private List<IAtomOutputMarshal> marshals = new ArrayList<IAtomOutputMarshal>(8);

    // could remove this map but need a conversion utility
    private static final Map<Integer, Byte> indexMap = new HashMap<Integer, Byte>(8) {{
        put(0, (byte) 0x01);
        put(1, (byte) 0x02);
        put(2, (byte) 0x04);
        put(3, (byte) 0x08);
        put(4, (byte) 0x10);
        put(5, (byte) 0x20);
        put(6, (byte) 0x40);
        // 0x80 is reserved to indicate a pseudo atom
    }};

    public AggregatedAtomOutput(IAtomOutputMarshal... marshals) {
        this(Arrays.asList(marshals));
    }

    public AggregatedAtomOutput(List<IAtomOutputMarshal> marshals) {
        this.marshals.addAll(marshals);

        if (marshals.size() > 7)
            throw new InvalidParameterException("Too many marshals to aggregate, use a nested aggregator");

    }


    private byte createFlag(IAtom atom) {

        // indicates whether we're writing a pseudo atom
        byte flag = (byte) (atom instanceof IPseudoAtom ? 0x80 : 0x00);

        for (int i = 0; i < marshals.size(); i++) {
            if (!marshals.get(i).isDefault(atom)) {
                flag |= indexMap.get(i);
            }
        }

        return flag;

    }

    private AtomOutput getAtomOutput(final byte flag) {
        AtomOutput output = outputs.get(flag);
        if (output == null) {
            output = createAtomOutput(flag);
            outputs.put(flag, output);
        }
        return output;
    }

    private AtomOutput createAtomOutput(byte flag) {

        List<IAtomOutputMarshal> marshalList = new ArrayList<IAtomOutputMarshal>(8);

        for (int i = 0; i < marshals.size(); i++) {

            int mask = indexMap.get(i);

            if ((mask & flag) == mask) {
                marshalList.add(marshals.get(i));
            }
        }

        return new AtomOutput(marshalList.toArray(new IAtomOutputMarshal[marshalList.size()]));

    }

    @Override
    public void write(DataOutput out, IAtom atom) throws IOException {
        final byte flag = createFlag(atom);
        out.writeByte(flag);
        getAtomOutput(flag).write(out, atom);
    }

    @Override
    public boolean isDefault(IAtom atom) {
        return false; // should always be false as it's aggregated we could check here whether all the flags are off
    }
}
