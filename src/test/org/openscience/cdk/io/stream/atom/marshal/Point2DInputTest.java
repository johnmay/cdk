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
import java.io.ByteArrayInputStream;
import java.io.DataInput;
import java.io.DataInputStream;
import java.io.IOException;
import java.nio.ByteBuffer;

/**
 * Unit tests for Point2DInput
 * @author John May
 */
public class Point2DInputTest extends CDKTest {

    private static final Logger LOGGER = Logger.getLogger(Point2DInputTest.class);

    private AtomInputMarshal   marshal = new Point2DInput();
    private IChemObjectBuilder BUILDER = DefaultChemObjectBuilder.getInstance();

    @Test
    public void testRead() throws IOException {

        ByteBuffer buffer = ByteBuffer.allocate(16);

        Point2d expected = new Point2d(-23.6, 0.23);

        buffer.putDouble(expected.x);
        buffer.putDouble(expected.y);

        DataInput in = new DataInputStream(new ByteArrayInputStream(buffer.array()));

        IAtom atom               = BUILDER.newInstance(IAtom.class);
        IAtomContainer container = BUILDER.newInstance(IAtomContainer.class);

        marshal.read(in, container, atom);

        Point2d actual = atom.getPoint2d();

        Assert.assertEquals(expected, actual);

    }

    @Test
    public void testSetDefault() throws Exception {

        IAtom atom = BUILDER.newInstance(IAtom.class);

        marshal.setDefault(atom);

        Assert.assertNotNull(atom.getPoint2d());

        Point2d expected = new Point2d(0,0);
        Point2d actual   = atom.getPoint2d();

        Assert.assertEquals(expected, actual);

    }
}
