package org.openscience.cdk.io.stream.atom;

import org.openscience.cdk.interfaces.IAtom;
import org.openscience.cdk.interfaces.IChemObjectBuilder;
import org.openscience.cdk.interfaces.IPseudoAtom;

import java.io.DataInput;
import java.io.IOException;
import java.security.InvalidParameterException;
import java.util.*;

/**
 * @author John May
 * @cdk.module io
 */
public class AggregatedAtomInput implements IAtomInputMarshal {

    private final List<IAtomInputMarshal> marshals = new ArrayList<IAtomInputMarshal>(8);
    private final Map<Byte, AtomInput>    inputs   = new HashMap<Byte, AtomInput>();

    private final IAtom       templateAtom;
    private final IPseudoAtom templatePseudoAtom;

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

    public AggregatedAtomInput(IChemObjectBuilder builder,
                               IAtomInputMarshal... marshals) {
        this(builder, Arrays.asList(marshals));
    }

    public AggregatedAtomInput(IChemObjectBuilder builder,
                               List<IAtomInputMarshal> marshals) {
        this.marshals.addAll(marshals);

        if (builder == null)
            throw new NullPointerException("No chem object builder was provided, use SilentChemObjectBuilder" +
                                                   " or DefaultChemObjectBuilder");

        if (marshals.size() > 7)
            throw new InvalidParameterException("Too many marshals to aggregate, use a nested aggregator");

        templateAtom = builder.newInstance(IAtom.class);
        templatePseudoAtom = builder.newInstance(IPseudoAtom.class);

        setDefault(templateAtom);
        setDefault(templatePseudoAtom);

    }

    public IAtom read(DataInput in) throws IOException {
        return getAtomInput(in, in.readByte()).read(in);
    }

    /**
     * Attempts to fetch an atom input for the given flag. If no input was fetched
     * a new input is constructed
     *
     * @param flag
     *
     * @return
     */
    private AtomInput getAtomInput(final DataInput in, final byte flag) {

        // normally I would use 'containsKey()' but we want to optimise for speed
        AtomInput input = inputs.get(flag);

        if (input == null) {
            input = createAtomInput(in, flag);
            inputs.put(flag, input);
        }

        return input;

    }

    /**
     * Creates a new {@see AtomInput} for a given flag
     *
     * @param flag
     *
     * @return
     */
    private AtomInput createAtomInput(final DataInput in, final byte flag) {

        IAtom template = getTemplateAtom(flag);
        List<IAtomInputMarshal> marshalList = new ArrayList<IAtomInputMarshal>(8);

        for (int i = 0; i < marshals.size(); i++) {

            byte mask = indexMap.get(i);

            if ((mask & flag) == mask) {
                marshalList.add(marshals.get(i));
            }
        }

        return new AtomInput(template, marshalList.toArray(new IAtomInputMarshal[marshalList.size()]));

    }

    private IAtom getTemplateAtom(final byte flag) {
        return (flag & 0x80) == 0x80 ? templatePseudoAtom : templateAtom;
    }

    @Override
    public void read(DataInput in, IAtom atom) throws IOException {
        getAtomInput(in, in.readByte()).read(in, atom);
    }

    @Override
    public void setDefault(IAtom atom) {
        for (IAtomInputMarshal marshal : marshals) {
            marshal.setDefault(atom);
        }
    }


}
