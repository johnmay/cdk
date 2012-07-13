package org.openscience.cdk.isomorphism.matchers.atom;

import org.apache.log4j.Logger;
import org.junit.Assert;
import org.junit.Test;
import org.openscience.cdk.interfaces.IAtom;
import org.openscience.cdk.interfaces.IChemObjectBuilder;
import org.openscience.cdk.silent.SilentChemObjectBuilder;

/**
 * @author John May
 */
public class SymbolMatcherTest {

    private final IChemObjectBuilder builder = SilentChemObjectBuilder.getInstance();
    private final IAtomMatcher matcher = new SymbolMatcher();

    @Test
    public void testMatches_null() throws Exception {

        IAtom query   = builder.newInstance(IAtom.class);
        IAtom subject = builder.newInstance(IAtom.class);

        query.setSymbol(null);
        subject.setSymbol(null);

        Assert.assertTrue(matcher.matches(query, subject));

    }

    @Test
    public void testMatches_oneNull() throws Exception {

        IAtom query   = builder.newInstance(IAtom.class);
        IAtom subject = builder.newInstance(IAtom.class);

        query.setSymbol("?");
        subject.setSymbol(null);

        Assert.assertFalse(matcher.matches(query, subject));

    }

    @Test
    public void testMatches_value() throws Exception {

        IAtom query   = builder.newInstance(IAtom.class);
        IAtom subject = builder.newInstance(IAtom.class);

        query.setSymbol("C");
        subject.setSymbol("C");

        Assert.assertTrue(matcher.matches(query, subject));

    }

    @Test
    public void testMatches_diffValue() throws Exception {

        IAtom query   = builder.newInstance(IAtom.class);
        IAtom subject = builder.newInstance(IAtom.class);

        query.setSymbol("C");
        subject.setSymbol("O");

        Assert.assertFalse(matcher.matches(query, subject));

    }

    @Test
    public void testToString(){
        Assert.assertEquals("query.getSymbol() == subject.getSymbol()", matcher.toString());
    }

}
