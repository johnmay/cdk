/*
 * Copyright (C) 2012 John May <jwmay@users.sf.net>
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
package org.openscience.cdk.isomorphism.matchers.atom.combinator;

import org.openscience.cdk.interfaces.IAtom;
import org.openscience.cdk.isomorphism.matchers.atom.IAtomMatcher;


/**
 * A IAtomMatcher implementation that allows disjunction ('||')
 * of other matchers. We don't have functions in java (yet) but this
 * can be thought of as a high-order function.
 *
 * The matcher can be used with the UniversalIsomorphismTester to
 * create a custom isomorphism match.
 *
 * @author John May
 * @cdk.module standard
 *
 * @link http://en.wikipedia.org/wiki/Higher-order_function
 * @see org.openscience.cdk.isomorphism.UniversalIsomorphismTester
 *
 */
public class DisjunctionMatcher implements IAtomMatcher {

    private final IAtomMatcher left;
    private final IAtomMatcher right;

    /**
     * Constructor the matcher with a left or right matcher. The left
     * matcher will be tested first therefore conditions more likely to
     * pass should be put on the left. If the left condition fails the right
     * condition will be tested.
     *
     * @param left  a atom matcher implementation
     * @param right a atom matcher implementation
     */
    public DisjunctionMatcher(IAtomMatcher left, IAtomMatcher right) {
        this.left  = left;
        this.right = right;
    }

    /**
     * Tests the for a match in the left or right matchers. The matchers
     * are passed to the constructor of the combinator.
     *
     * @param query atom from a query molecule (atom container)
     * @param subject atom for a subject/target molecule (atom container)
     *
     * @return true if either left and right matchers are true, false if both are false.
     */
    @Override
    public boolean matches(IAtom query, IAtom subject) {
        return left.matches(query, subject) && right.matches(query, subject);
    }

    /**
     * @inheritDoc
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("(");
        sb.append(left.toString()).append(" || ").append(right.toString());
        sb.append(")");
        return sb.toString();
    }

}
