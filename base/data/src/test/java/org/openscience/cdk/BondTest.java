/* Copyright (C) 2002-2007  The Chemistry Development Kit (CDK) project
 * 
 * Contact: cdk-devel@lists.sourceforge.net
 * 
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public License
 * as published by the Free Software Foundation; either version 2.1
 * of the License, or (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 * 
 * You should have received a copy of the GNU Lesser General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA 02110-1301 USA. 
 * 
 */
package org.openscience.cdk;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openscience.cdk.interfaces.AbstractBondTest;
import org.openscience.cdk.interfaces.IAtom;
import org.openscience.cdk.interfaces.IBond;
import org.openscience.cdk.interfaces.IChemObject;
import org.openscience.cdk.interfaces.ITestObjectBuilder;

/**
 * Checks the functionality of the Bond class.
 *
 * @cdk.module test-data
 * @see org.openscience.cdk.Bond
 */
public class BondTest extends AbstractBondTest {

    @BeforeClass
    public static void setUp() {
        setTestObjectBuilder(new ITestObjectBuilder() {
            public IChemObject newTestObject() {
                return new Bond();
            }
        });
    }

    @Test
    public void testBond() {
        IBond bond = new Bond();
        Assert.assertEquals(0, bond.getAtomCount());
        Assert.assertNull(bond.getAtom(0));
        Assert.assertNull(bond.getAtom(1));
        Assert.assertNull(bond.getOrder());
        Assert.assertEquals(IBond.Stereo.NONE, bond.getStereo());
    }

    @Test
    public void testBond_arrayIAtom() {
        IChemObject object = newChemObject();
        IAtom atom1 = object.getBuilder().newInstance(IAtom.class,"C");
        IAtom atom2 = object.getBuilder().newInstance(IAtom.class,"O");
        IAtom atom3 = object.getBuilder().newInstance(IAtom.class,"C");
        IAtom atom4 = object.getBuilder().newInstance(IAtom.class,"C");
        IAtom atom5 = object.getBuilder().newInstance(IAtom.class,"C");

        IBond bond1 = new Bond(new IAtom[]{atom1, atom2, atom3, atom4, atom5});
        Assert.assertEquals(5, bond1.getAtomCount());
        Assert.assertEquals(atom1, bond1.getAtom(0));
        Assert.assertEquals(atom2, bond1.getAtom(1));
    }

    @Test
    public void testBond_arrayIAtom_IBond_Order() {
        IChemObject object = newChemObject();
        IAtom atom1 = object.getBuilder().newInstance(IAtom.class,"C");
        IAtom atom2 = object.getBuilder().newInstance(IAtom.class,"O");
        IAtom atom3 = object.getBuilder().newInstance(IAtom.class,"C");
        IAtom atom4 = object.getBuilder().newInstance(IAtom.class,"C");
        IAtom atom5 = object.getBuilder().newInstance(IAtom.class,"C");

        IBond bond1 = new Bond(new IAtom[]{atom1, atom2, atom3, atom4, atom5}, IBond.Order.SINGLE);
        Assert.assertEquals(5, bond1.getAtomCount());
        Assert.assertEquals(atom1, bond1.getAtom(0));
        Assert.assertEquals(atom2, bond1.getAtom(1));
        Assert.assertEquals(IBond.Order.SINGLE, bond1.getOrder());
    }

    @Test
    public void testBond_IAtom_IAtom() {
        IChemObject object = newChemObject();
        IAtom c = object.getBuilder().newInstance(IAtom.class,"C");
        IAtom o = object.getBuilder().newInstance(IAtom.class,"O");
        IBond bond = new Bond(c, o);

        Assert.assertEquals(2, bond.getAtomCount());
        Assert.assertEquals(c, bond.getAtom(0));
        Assert.assertEquals(o, bond.getAtom(1));
        Assert.assertEquals(IBond.Order.SINGLE, bond.getOrder());
        Assert.assertEquals(IBond.Stereo.NONE, bond.getStereo());
    }

    @Test
    public void testBond_IAtom_IAtom_IBond_Order() {
        IChemObject object = newChemObject();
        IAtom c = object.getBuilder().newInstance(IAtom.class,"C");
        IAtom o = object.getBuilder().newInstance(IAtom.class,"O");
        IBond bond = new Bond(c, o, IBond.Order.DOUBLE);

        Assert.assertEquals(2, bond.getAtomCount());
        Assert.assertEquals(c, bond.getAtom(0));
        Assert.assertEquals(o, bond.getAtom(1));
        Assert.assertTrue(bond.getOrder() == IBond.Order.DOUBLE);
        Assert.assertEquals(IBond.Stereo.NONE, bond.getStereo());
    }

    @Test
    public void testBond_IAtom_IAtom_IBond_Order_IBond_Stereo() {
        IChemObject object = newChemObject();
        IAtom c = object.getBuilder().newInstance(IAtom.class,"C");
        IAtom o = object.getBuilder().newInstance(IAtom.class,"O");
        IBond bond = new Bond(c, o, IBond.Order.SINGLE, IBond.Stereo.UP);

        Assert.assertEquals(2, bond.getAtomCount());
        Assert.assertEquals(c, bond.getAtom(0));
        Assert.assertEquals(o, bond.getAtom(1));
        Assert.assertTrue(bond.getOrder() == IBond.Order.SINGLE);
        Assert.assertEquals(IBond.Stereo.UP, bond.getStereo());
    }

    @Test
    public void testCompare_Object() {
        IChemObject object = newChemObject();
        IAtom c = object.getBuilder().newInstance(IAtom.class,"C");
        IAtom o = object.getBuilder().newInstance(IAtom.class,"O");

        IBond b = new Bond(c, o, IBond.Order.DOUBLE); // C=O bond
        IBond b2 = new Bond(c, o, IBond.Order.DOUBLE); // same C=O bond

        Assert.assertTrue(b.compare(b2));
    }

}
