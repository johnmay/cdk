/* Copyright (C) 2008  Egon Willighagen <egonw@users.sf.net>
 * 
 * Contact: cdk-devel@slists.sourceforge.net
 * 
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public License
 * as published by the Free Software Foundation; either version 2.1
 * of the License, or (at your option) any later version.
 * All we ask is that proper credit is given for our work, which includes
 * - but is not limited to - adding the above copyright notice to the beginning
 * of your source code files, and to any copyright notice that you may distribute
 * with programs based on this work.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 * 
 * You should have received a copy of the GNU Lesser General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA 02110-1301 USA.
 */
package org.openscience.cdk.io.formats;

import org.junit.Assert;
import org.junit.Test;

/**
 * @cdk.module test-ioformats
 */
public class PubChemCompoundXMLFormatTest extends ChemFormatMatcherTest {

    public PubChemCompoundXMLFormatTest() {
        super.setChemFormatMatcher((IChemFormatMatcher)PubChemCompoundXMLFormat.getInstance());
    }
    
    /**
     * @cdk.bug 2832835
     */
    @Test @Override
    public void testMatches() throws Exception {
        String header =
            "<?xml version=\"\"?><PC-Compound/>";
        Assert.assertTrue(matches(header));
    }

    @Test
    public void testFalsePositive() throws Exception {
        String header =
            "<?xml version=\"\"?><PC-Compounds><PC-Compound/></PC-Compounds>";
        Assert.assertFalse(matches(header));
    }

    /**
     * @cdk.bug 2832858
     */
    @Test
    public void testFalsePositiveWithNewlines() throws Exception {
        String header =
            "<PC-Compounds>\n<PC-Compound/>\n</PC-Compounds>";
        Assert.assertFalse(matches(header));
    }

}
