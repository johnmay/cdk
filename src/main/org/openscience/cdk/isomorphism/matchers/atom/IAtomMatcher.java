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

package org.openscience.cdk.isomorphism.matchers.atom;

import org.openscience.cdk.interfaces.IAtom;

/**
 * Describes a class that performs a match operation on two atoms. This
 * interfaces allows decoupling of the matching operation from query atoms
 * where creating of the query is not favourable. The matches are primarily
 * used in UniversalIsomorphismTester to provided a specificity when determining
 * molecule isomorphism. A combinator can be used to combined multiple matchers
 * and construct and custom match condition.
 *
 * @author John May
 * @cdk.module interfaces
 *
 * @see IAtom
 * @see org.openscience.cdk.isomorphism.UniversalIsomorphismTester
 * @see org.openscience.cdk.isomorphism.matchers.IQueryAtom
 */
public interface IAtomMatcher {

    /**
     * Indicates a match between the two atoms. Normally this would
     * test a match for a single field/property such as symbol or charge.
     *
     * @param query atom from a query molecule (atom container)
     * @param subject atom for a subject/target molecule (atom container)
     *
     * @return whether the atoms match by the conditions of this matcher
     */
    public boolean matches(IAtom query, IAtom subject);

}
