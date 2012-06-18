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

import java.io.ByteArrayInputStream;
import java.io.DataInput;
import java.io.DataInputStream;
import java.io.IOException;
import java.nio.ByteBuffer;

/**
 * Unit tests for FormalChargeInput
 * @author John May
 */
public class FormalChargeInputTest extends CDKTest {

    private static final Logger LOGGER = Logger.getLogger(FormalChargeInputTest.class);

    private FormalChargeInput  marshal = new FormalChargeInput();
    private IChemObjectBuilder BUILDER = DefaultChemObjectBuilder.getInstance();

    @Test
    public void testRead() throws IOException {

        ByteBuffer buffer = ByteBuffer.allocate(1);

        Integer expected = -2;

        buffer.put(expected.byteValue());

        DataInput in = new DataInputStream(new ByteArrayInputStream(buffer.array()));

        IAtom atom               = BUILDER.newInstance(IAtom.class);
        IAtomContainer container = BUILDER.newInstance(IAtomContainer.class);

        marshal.read(in, container, atom);

        Integer actual = atom.getFormalCharge();

        Assert.assertEquals(expected, actual);

    }

    @Test
    public void testSetDefault() throws Exception {

        IAtom atom = BUILDER.newInstance(IAtom.class);

        marshal.setDefault(atom);

        Assert.assertNotNull(atom.getFormalCharge());

        Integer expected = 0;
        Integer actual   = atom.getFormalCharge();

        Assert.assertEquals(expected, actual);

    }
}
