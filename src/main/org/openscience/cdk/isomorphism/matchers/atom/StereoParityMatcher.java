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
 * An implementation of IAtomMatcher for matching the atom mass numbers
 * and can be used to ensure the atoms are the same isotope (i.e. C12 and C13
 * would not match).
 *
 * @author John May
 * @cdk.module standard
 * @see org.openscience.cdk.isomorphism.UniversalIsomorphismTester
 */
public class StereoParityMatcher implements IAtomMatcher {

    /**
     * Matches atomic mass numbers of the query and subject atoms. If both mass numbers are
     * equal or null the match will return true.
     *
     * @param query   atom from a query molecule (atom container)
     * @param subject atom for a subject/target molecule (atom container)
     *
     * @return the atom mass numbers match or are both null
     */
    @Override
    public boolean matches(IAtom query, IAtom subject) {

        Integer queryParity   = query.getStereoParity();
        Integer subjectParity = subject.getStereoParity();

        return queryParity != null ? queryParity.equals(subjectParity) : subjectParity == null;

    }

    /**
     * @inheritDoc
     */
    @Override
    public String toString() {
        return "query.getStereoParity() == subject.getStereoParity()";
    }

}
