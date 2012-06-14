package org.openscience.cdk.io.stream.atom.marshal;

import org.openscience.cdk.interfaces.IAtom;
import org.openscience.cdk.interfaces.IPseudoAtom;
import org.openscience.cdk.io.stream.atom.AtomOutputMarshal;

import java.io.DataOutput;
import java.io.IOException;

/**
 * Writes the label of a pseudo atom to a data output.
 * <p/>
 * This output is used in conjunction with {@see AtomContainerOutput} and is analogous to the
 * output {@see PseudoLabelInput} in {@see AtomContainerInput}
 *
 * @author John May
 * @cdk.module io
 * @see org.openscience.cdk.interfaces.IAtom#getAtomicNumber()
 * @see PseudoLabelInput
 * @see org.openscience.cdk.io.stream.container.AtomContainerInput
 * @see org.openscience.cdk.io.stream.container.AtomContainerOutput
 */
public class PseudoLabelOutput
        extends AtomOutputMarshal<IPseudoAtom> {

    /**
     * Stores the pseudo atom label as a UTF string in the provided
     * data output.
     *
     * @param out  where the data will be written
     * @param atom the atom of which to write the information on
     *
     * @throws java.io.IOException low-level io exception
     * @inheitDoc
     * @see DataOutput#writeUTF(String)
     * @see org.openscience.cdk.interfaces.IPseudoAtom#getLabel()
     */
    @Override
    public void write(DataOutput out, IPseudoAtom atom) throws IOException {
        out.writeUTF(atom.getLabel());
    }

    /**
     * Determines whether the atom is a pseudo atom and whether the
     * pseudo atom label is null or empty. If any of the above conditions
     * are met the atom is considered 'default' and not written to the stream.
     *
     * @param atom instance of the atom that will be stored
     *
     * @return whether the value is default and should be skipped
     */
    @Override
    public boolean isDefault(IAtom atom) {
        return atom instanceof IPseudoAtom ? isDefault((IPseudoAtom) atom) : true;
    }


    private boolean isDefault(IPseudoAtom atom) {
        return atom.getLabel() == null || atom.getLabel().isEmpty();
    }

}
