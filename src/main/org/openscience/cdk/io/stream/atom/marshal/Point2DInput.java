package org.openscience.cdk.io.stream.atom.marshal;

import org.openscience.cdk.interfaces.IAtom;
import org.openscience.cdk.io.stream.atom.AtomInputMarshal;

import javax.vecmath.Point2d;
import java.io.DataInput;
import java.io.IOException;

/**
 * @author John May
 * @cdk.module io
 */
public class Point2DInput
        extends AtomInputMarshal<IAtom> {

    @Override
    public void read(DataInput in, IAtom atom) throws IOException {
        atom.setPoint2d(new Point2d(in.readDouble(), in.readDouble()));
    }

    @Override
    public void setDefault(IAtom atom) {
        // could set to null
        atom.setPoint2d(new Point2d(0, 0));
    }
}
