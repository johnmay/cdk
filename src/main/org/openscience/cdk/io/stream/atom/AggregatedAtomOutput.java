package org.openscience.cdk.io.stream.atom;

import org.openscience.cdk.interfaces.IAtom;
import org.openscience.cdk.interfaces.IAtomContainer;
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
                flag |= (1 << i);
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

    /**
     * Given a fingerprint of the format to write and AtomOutput is created
     * @param fingerprint
     * @return
     */
    private AtomOutput createAtomOutput(byte fingerprint) {

        List<IAtomOutputMarshal> marshalList = new ArrayList<IAtomOutputMarshal>(8);

        for (int i = 0; i < marshals.size(); i++) {
            if ((fingerprint & 1 << i) != 0) {
                marshalList.add(marshals.get(i));
            }
        }

        return new AtomOutput(marshalList.toArray(new IAtomOutputMarshal[marshalList.size()]));

    }

    /**
     * Delegates writing of the output to a selected AtomOutput
     * @param out
     * @param container
     * @param atom
     * @throws IOException
     */
    @Override
    public void write(DataOutput out, IAtomContainer container, IAtom atom) throws IOException {
        final byte flag = createFlag(atom);
        out.writeByte(flag);
        getAtomOutput(flag).write(out, container, atom);
    }


    @Override
    public boolean isDefault(IAtom atom) {
        // should always be false as it's aggregated we could check here whether all the flags are off
        // but would have to move the atom mask 0X80 to not indicate pseudo atoms
        return false;
    }
}
