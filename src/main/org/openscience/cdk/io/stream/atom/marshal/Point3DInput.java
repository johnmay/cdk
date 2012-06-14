package org.openscience.cdk.io.stream.atom.marshal;

import org.openscience.cdk.interfaces.IAtom;
import org.openscience.cdk.interfaces.IAtomContainer;
import org.openscience.cdk.io.stream.atom.AtomInputMarshal;

import javax.vecmath.Point3d;
import java.io.DataInput;
import java.io.IOException;

/**
 * @author John May
 * @cdk.module io
 */
public class Point3DInput
        extends AtomInputMarshal<IAtom> {

    @Override
    public void read(final DataInput in, final IAtomContainer container, final IAtom atom) throws IOException {
        atom.setPoint3d(new Point3d(in.readDouble(), in.readDouble(), in.readDouble()));
    }

    @Override
    public void setDefault(final IAtom atom) {
        // could set to null
        atom.setPoint3d(new Point3d(0, 0, 0));
    }
}
