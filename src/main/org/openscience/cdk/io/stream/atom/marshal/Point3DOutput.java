package org.openscience.cdk.io.stream.atom.marshal;

import org.openscience.cdk.interfaces.IAtom;
import org.openscience.cdk.interfaces.IAtomContainer;
import org.openscience.cdk.io.stream.atom.AtomOutputMarshal;

import javax.vecmath.Point3d;
import java.io.DataOutput;
import java.io.IOException;

/**
 * @author John May
 * @cdk.module io
 */
public class Point3DOutput
        extends AtomOutputMarshal {

    @Override
    public void write(DataOutput out, IAtomContainer container, IAtom atom) throws IOException {
        Point3d p = atom.getPoint3d();
        out.writeDouble(p.x);
        out.writeDouble(p.y);
        out.writeDouble(p.z);
    }

    @Override
    public boolean isDefault(IAtom atom) {
        return atom.getPoint3d() == null;
    }


}
