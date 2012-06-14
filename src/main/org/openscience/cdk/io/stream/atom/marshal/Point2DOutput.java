package org.openscience.cdk.io.stream.atom.marshal;

import org.openscience.cdk.interfaces.IAtom;
import org.openscience.cdk.io.stream.atom.AtomOutputMarshal;

import javax.vecmath.Point2d;
import java.io.DataOutput;
import java.io.IOException;

/**
 * @author John May
 * @cdk.module io
 */
public class Point2DOutput
        extends AtomOutputMarshal {

    @Override
    public void write(DataOutput out, IAtom atom) throws IOException {
        Point2d p = atom.getPoint2d();
        out.writeDouble(p.x);
        out.writeDouble(p.y);
    }

    @Override
    public boolean isDefault(IAtom atom) {
        return atom.getPoint2d() == null;
    }


}
