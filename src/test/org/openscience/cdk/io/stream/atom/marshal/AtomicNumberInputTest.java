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
public class AtomicNumberInputTest extends CDKTest {

    private static final Logger LOGGER = Logger.getLogger(AtomicNumberInputTest.class);

    private AtomInputMarshal   marshal = new AtomicNumberInput();
    private IChemObjectBuilder BUILDER = DefaultChemObjectBuilder.getInstance();

    @Test
    public void testRead() throws IOException {

        ByteBuffer buffer = ByteBuffer.allocate(16);

        Integer expected = 0;

        buffer.put((byte) ( expected.byteValue() - Byte.MAX_VALUE));

        DataInput in = new DataInputStream(new ByteArrayInputStream(buffer.array()));

        IAtom atom               = BUILDER.newInstance(IAtom.class);
        IAtomContainer container = BUILDER.newInstance(IAtomContainer.class);

        marshal.read(in, container, atom);

        Integer actual = atom.getAtomicNumber();

        Assert.assertEquals(expected, actual);

    }

    @Test
    public void testRead_Carbon() throws IOException {

        ByteBuffer buffer = ByteBuffer.allocate(16);

        Integer expected = 6;

        buffer.put((byte) ( expected.byteValue() - Byte.MAX_VALUE));

        DataInput in = new DataInputStream(new ByteArrayInputStream(buffer.array()));

        IAtom atom               = BUILDER.newInstance(IAtom.class);
        IAtomContainer container = BUILDER.newInstance(IAtomContainer.class);

        marshal.read(in, container, atom);

        Integer actual = atom.getAtomicNumber();

        Assert.assertEquals(expected, actual);

        String expectedSymbol = "C";
        String actualSymbol   = atom.getSymbol();

        Assert.assertNotNull(atom.getSymbol());
        Assert.assertEquals(expected, expectedSymbol);

    }

    @Test
    public void testSetDefault() throws Exception {

        IAtom atom = BUILDER.newInstance(IAtom.class);

        marshal.setDefault(atom);

        Assert.assertNotNull(atom.getAtomicNumber());

        Integer expected = 0;
        Integer actual   = atom.getAtomicNumber();

        Assert.assertEquals(expected, actual);

    }
}
