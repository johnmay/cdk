package org.openscience.cdk.io.stream.bond;

import org.openscience.cdk.interfaces.IAtomContainer;
import org.openscience.cdk.interfaces.IBond;
import org.openscience.cdk.interfaces.IChemObjectBuilder;

import java.io.DataInput;
import java.io.IOException;
import java.security.InvalidParameterException;
import java.util.*;

/**
 * @author John May
 * @cdk.module io
 */
public class AggregatedBondInput implements IBondInputMarshal {

    private final List<IBondInputMarshal> marshals = new ArrayList<IBondInputMarshal>(8);
    private final Map<Byte, BondInput>    inputs   = new HashMap<Byte, BondInput>();

    private final IBond templateBond;

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

    public AggregatedBondInput(IChemObjectBuilder builder,
                               IBondInputMarshal... marshals) {
        this(builder, Arrays.asList(marshals));
    }

    public AggregatedBondInput(IChemObjectBuilder builder,
                               List<IBondInputMarshal> marshals) {
        this.marshals.addAll(marshals);

        if (marshals.size() > 7)
            throw new InvalidParameterException("Too many marshals to aggregate, use a nested aggregator");


        templateBond = builder.newInstance(IBond.class);

        setDefault(templateBond);

    }


    /**
     * Attempts to fetch a bond input for the given flag. If no input was fetched
     * a new input is constructed
     *
     * @param flag
     *
     * @return
     */
    private BondInput getBondInput(final DataInput in, final byte flag) {

        // normally I would use 'containsKey()' but we want to optimise for speed
        BondInput input = inputs.get(flag);

        if (input == null) {
            input = createBondInput(in, flag);
            inputs.put(flag, input);
        }

        return input;

    }

    /**
     * Creates a new {@see BondInput} for a given flag
     *
     * @param flag
     *
     * @return
     */
    private BondInput createBondInput(final DataInput in, final byte flag) {

        List<IBondInputMarshal> marshalList = new ArrayList<IBondInputMarshal>(8);

        for (int i = 0; i < marshals.size(); i++) {

            byte mask = indexMap.get(i);

            if ((mask & flag) == mask) {
                marshalList.add(marshals.get(i));
            }
        }

        return new BondInput(templateBond, marshalList.toArray(new IBondInputMarshal[marshalList.size()]));

    }


    public IBond read(DataInput in, IAtomContainer container) throws IOException {
        return getBondInput(in, in.readByte()).read(in, container);
    }

    @Override
    public void read(DataInput in, IAtomContainer container, IBond bond) throws IOException {
        getBondInput(in, in.readByte()).read(in, container, bond);
    }

    @Override
    public void setDefault(IBond bond) {
        for (IBondInputMarshal marshal : marshals) {
            marshal.setDefault(bond);
        }
    }
}
