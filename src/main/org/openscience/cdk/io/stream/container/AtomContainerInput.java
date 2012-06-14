package org.openscience.cdk.io.stream.container;

import org.openscience.cdk.interfaces.IAtomContainer;
import org.openscience.cdk.interfaces.IChemObjectBuilder;
import org.openscience.cdk.io.stream.AbstractInputMarshal;
import org.openscience.cdk.io.stream.IChemObjectInput;
import org.openscience.cdk.io.stream.atom.AggregatedAtomInput;
import org.openscience.cdk.io.stream.bond.AggregatedBondInput;

import java.io.DataInput;
import java.io.IOException;

/**
 * @author John May
 * @cdk.module io
 */
public class AtomContainerInput
        extends AbstractInputMarshal
        implements IChemObjectInput<IAtomContainer> {

    private final DataInput           in;
    private final IChemObjectBuilder  builder;
    private final AggregatedAtomInput atomInput;
    private final AggregatedBondInput bondInput;

    public AtomContainerInput(DataInput in,
                              IChemObjectBuilder builder,
                              AggregatedAtomInput atomInput,
                              AggregatedBondInput bondInput) {
        this.in = in;
        this.builder = builder;
        this.atomInput = atomInput;
        this.bondInput = bondInput;
    }

    @Override
    public IAtomContainer read() throws IOException {
        return read(in);
    }

    @Override
    public IAtomContainer read(final DataInput in) throws IOException {

        IAtomContainer container = builder.newInstance(IAtomContainer.class);

        // read atom data
        int atomCount = readUnsignedShort(in);

        for (int i = 0; i < atomCount; i++) {
            container.addAtom(atomInput.read(in));
        }

        // read bond data
        int bondCount = readUnsignedShort(in);

        for (int i = 0; i < bondCount; i++)
            container.addBond(bondInput.read(in, container));


        return container;

    }
}
