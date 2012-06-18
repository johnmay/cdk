package org.openscience.cdk.io.stream.atom.marshal;

import org.openscience.cdk.interfaces.IAtom;
import org.openscience.cdk.interfaces.IAtomContainer;
import org.openscience.cdk.io.stream.atom.AtomOutputMarshal;

import javax.vecmath.Point2d;
import java.io.DataOutput;
import java.io.IOException;

/**
 * @author John May
 * @cdk.module io
 */
public class CompressedPoint2DOutput
        extends AtomOutputMarshal {

    private double factor;

    public CompressedPoint2DOutput(int decimalPlaces) {
        factor = Math.pow(10, decimalPlaces);
    }

    @Override
    public void write(DataOutput out, IAtomContainer container, IAtom atom) throws IOException {

        Point2d p = atom.getPoint2d();

        int x = (int) (p.x * factor);
        int y = (int) (p.y * factor);

        if (x > Short.MAX_VALUE) {
            throw new IOException("CompressedPoint2DOutput: The x coordinate " + x + " is too" +
                                          " large to write, try less decimal places");
        }
        if (y > Short.MAX_VALUE) {
            throw new IOException("CompressedPoint2DOutput: The y coordinate " + y + " is too" +
                                          " large to write, try less decimal places");
        }

        out.writeShort(x);
        out.writeShort(y);

    }

    @Override
    public boolean isDefault(IAtom atom) {
        return atom.getPoint2d() == null;
    }


}
