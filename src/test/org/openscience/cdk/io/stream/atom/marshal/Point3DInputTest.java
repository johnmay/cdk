package org.openscience.cdk.io.stream.atom.marshal;

import org.apache.log4j.Logger;
import org.junit.Assert;
import org.junit.Test;
import org.openscience.cdk.CDKTest;
import org.openscience.cdk.DefaultChemObjectBuilder;
import org.openscience.cdk.interfaces.IAtom;
import org.openscience.cdk.interfaces.IAtomContainer;
import org.openscience.cdk.interfaces.IChemObjectBuilder;
import org.openscience.cdk.io.stream.atom.AtomInputMarshal;

import javax.vecmath.Point2d;
import javax.vecmath.Point3d;
import java.io.ByteArrayInputStream;
import java.io.DataInput;
import java.io.DataInputStream;
import java.io.IOException;
import java.nio.ByteBuffer;

/**
 * Unit tests for Point2DInput
 * @author John May
 */
public class Point3DInputTest extends CDKTest {

    private static final Logger LOGGER = Logger.getLogger(Point3DInputTest.class);

    private AtomInputMarshal   marshal = new Point3DInput();
    private IChemObjectBuilder BUILDER = DefaultChemObjectBuilder.getInstance();

    @Test
    public void testRead() throws IOException {

        ByteBuffer buffer = ByteBuffer.allocate(32);

        Point3d expected = new Point3d(13.4, 0.67, 7.5);

        buffer.putDouble(expected.x);
        buffer.putDouble(expected.y);
        buffer.putDouble(expected.z);

        DataInput in = new DataInputStream(new ByteArrayInputStream(buffer.array()));

        IAtom atom               = BUILDER.newInstance(IAtom.class);
        IAtomContainer container = BUILDER.newInstance(IAtomContainer.class);

        marshal.read(in, container, atom);

        Point3d actual = atom.getPoint3d();

        Assert.assertEquals(expected, actual);

    }

    @Test
    public void testSetDefault() throws Exception {

        IAtom atom = BUILDER.newInstance(IAtom.class);

        marshal.setDefault(atom);

        Assert.assertNotNull(atom.getPoint3d());

        Point3d expected = new Point3d(0,0,0);
        Point3d actual   = atom.getPoint3d();

        Assert.assertEquals(expected, actual);

    }
}
