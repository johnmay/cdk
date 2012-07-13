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
 * An implementation of IAtomMatcher
 *
 * @author John May
 * @cdk.module standard
 * @see org.openscience.cdk.isomorphism.UniversalIsomorphismTester
 */
public class FormalChargeMatcher implements IAtomMatcher {

    /**
     * Matches formal charges of the query and subject atoms. If both charge values are
     * equal or null the match will return true.
     *
     * @param query   atom from a query molecule (atom container)
     * @param subject atom for a subject/target molecule (atom container)
     *
     * @return the atom formal charges match or are both null
     */
    @Override
    public boolean matches(IAtom query, IAtom subject) {

        Integer queryCharge   = query.getFormalCharge();
        Integer subjectCharge = subject.getFormalCharge();

        return queryCharge != null ? queryCharge.equals(subjectCharge) : subjectCharge == null;

    }

    /**
     * @inheritDoc
     */
    @Override
    public String toString() {
        return "query.getFormalCharge() == subject.getFormalCharge()";
    }

}
