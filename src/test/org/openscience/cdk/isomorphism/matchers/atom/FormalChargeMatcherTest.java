package org.openscience.cdk.isomorphism.matchers.atom;

import org.apache.log4j.Logger;
import org.junit.Assert;
import org.junit.Test;
import org.openscience.cdk.interfaces.IAtom;
import org.openscience.cdk.interfaces.IChemObject;
import org.openscience.cdk.interfaces.IChemObjectBuilder;
import org.openscience.cdk.silent.SilentChemObjectBuilder;

/**
 * @author John May
 */
public class FormalChargeMatcherTest {

    private final IChemObjectBuilder builder = SilentChemObjectBuilder.getInstance();
    private final FormalChargeMatcher matcher = new FormalChargeMatcher();

    @Test
    public void testMatches_null() throws Exception {

        IAtom query   = builder.newInstance(IAtom.class);
        IAtom subject = builder.newInstance(IAtom.class);

        query.setFormalCharge(null);
        subject.setFormalCharge(null);

        Assert.assertTrue(matcher.matches(query, subject));

    }

    @Test
    public void testMatches_value() throws Exception {

        IAtom query   = builder.newInstance(IAtom.class);
        IAtom subject = builder.newInstance(IAtom.class);

        query.setFormalCharge(0);
        query.setFormalCharge(0);

        Assert.assertTrue(matcher.matches(query, subject));

    }

    @Test
    public void testMatches_diffValue() throws Exception {

        IAtom query   = builder.newInstance(IAtom.class);
        IAtom subject = builder.newInstance(IAtom.class);

        query.setFormalCharge(0);
        query.setFormalCharge(1);

        Assert.assertFalse(matcher.matches(query, subject));

    }


    @Test
    public void testMatches_oneNull() throws Exception {

        IAtom query   = builder.newInstance(IAtom.class);
        IAtom subject = builder.newInstance(IAtom.class);

        query.setFormalCharge(0);
        query.setFormalCharge(null);

        Assert.assertFalse(matcher.matches(query, subject));

    }

}
