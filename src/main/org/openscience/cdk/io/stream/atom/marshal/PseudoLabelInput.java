package org.openscience.cdk.io.stream.atom.marshal;

import org.openscience.cdk.interfaces.IAtom;
import org.openscience.cdk.interfaces.IAtomContainer;
import org.openscience.cdk.interfaces.IPseudoAtom;
import org.openscience.cdk.io.stream.atom.AtomInputMarshal;

import java.io.DataInput;
import java.io.IOException;

/**
 * Reads the label of a pseudo atom from a DataInput.
 * <p/>
 * This input is used in conjunction with {@see AtomContainerInput} and is analogous to the
 * output {@see PseudoLabelOutput} in {@see AtomContainerOutput}
 *
 * @author John May
 * @cdk.module io
 * @s
 * @see IPseudoAtom#setLabel(String)
 * @see PseudoLabelOutput
 * @see org.openscience.cdk.io.stream.container.AtomContainerInput
 * @see org.openscience.cdk.io.stream.container.AtomContainerOutput
 */
public class PseudoLabelInput
        extends AtomInputMarshal<IPseudoAtom> {

    /**
     * Reads a UTF string from the input and sets this as the label for the pseudo atom.
     *
     * @param in   data input stream
     * @param atom instance of atom to read the label into
     *
     * @throws java.io.IOException low-level io exception
     * @inheritDoc
     * @see PseudoLabelOutput#write(java.io.DataOutput, org.openscience.cdk.interfaces.IPseudoAtom)
     * @see java.io.DataInput#readUTF()
     * @see IPseudoAtom#setLabel(String)
     */
    @Override
    public void read(final DataInput in, final IAtomContainer container, final IPseudoAtom atom) throws IOException {
        atom.setLabel(in.readUTF());
    }

    /**
     * Sets the pseudo atom label to an empty string.
     *
     * @param atom the pseudo atom that needs a default value
     *
     * @inheritDoc
     */
    @Override
    public void setDefault(final IAtom atom) {
        if (atom instanceof IPseudoAtom) {
            setDefault((IPseudoAtom) atom);
        }
    }

    private void setDefault(final IPseudoAtom atom) {
        atom.setLabel("");
    }


}
