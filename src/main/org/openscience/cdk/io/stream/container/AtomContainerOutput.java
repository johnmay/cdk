package org.openscience.cdk.io.stream.container;

import org.openscience.cdk.interfaces.IAtom;
import org.openscience.cdk.interfaces.IAtomContainer;
import org.openscience.cdk.interfaces.IBond;
import org.openscience.cdk.io.stream.AbstractOutputMarshal;
import org.openscience.cdk.io.stream.IChemObjectOutput;
import org.openscience.cdk.io.stream.atom.IAtomOutputMarshal;
import org.openscience.cdk.io.stream.bond.IBondOutputMarshal;

import java.io.DataOutput;
import java.io.IOException;

/**
 * @author John May
 * @cdk.module io
 */
public class AtomContainerOutput
        extends AbstractOutputMarshal
        implements IChemObjectOutput<IAtomContainer> {

    private final DataOutput         out;
    private       IAtomOutputMarshal atomMarshal;
    private       IBondOutputMarshal bondMarshal;

    public AtomContainerOutput(DataOutput out,
                               IAtomOutputMarshal atomMarshal,
                               IBondOutputMarshal bondMarshal) {
        this.out = out;
        this.atomMarshal = atomMarshal;
        this.bondMarshal = bondMarshal;
    }

    public void write(IAtomContainer container) throws IOException {

        // output atom data
        writeAsUnsignedShort(out, container.getAtomCount());

        for (IAtom atom : container.atoms()) {
            atomMarshal.write(out, container, atom);
        }

        // output bond data
        writeAsUnsignedShort(out, container.getBondCount());

        for (IBond bond : container.bonds()) {
            bondMarshal.write(out, container, bond);
        }

    }

}
