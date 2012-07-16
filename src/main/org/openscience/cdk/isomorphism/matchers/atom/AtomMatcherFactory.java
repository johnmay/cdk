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

import org.openscience.cdk.isomorphism.matchers.atom.combinator.ConjunctionMatcher;
import org.openscience.cdk.isomorphism.matchers.atom.combinator.DisjunctionMatcher;
import org.openscience.cdk.isomorphism.matchers.atom.combinator.NegationMatcher;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

/**
 * A factory for creation and combination of AtomMatchers. The methods are static so they can
 * be imported and neatly used.
 * <p/>
 * <pre>{@code
 * import static org.openscience.cdk.isomorphism.matchers.atom.AtomMatcherFactory.and;
 * <p/>
 * // use the and combinator from the factor
 * and(SymbolMatcher.class, FormalChargeMatcher.class);
 * <p/>
 * }</pre>
 *
 * @author John May
 * @cdk.module standard
 */
public class AtomMatcherFactory {

    private static final Map<Class<? extends IAtomMatcher>, IAtomMatcher> classMap = new HashMap<Class<? extends IAtomMatcher>, IAtomMatcher>();
    private static final Map<String, IAtomMatcher>                        nameMap  = new HashMap<String, IAtomMatcher>();

    // static constructor initialises maps
    static {

        classMap.put(SymbolMatcher.class, new SymbolMatcher());
        classMap.put(FormalChargeMatcher.class, new FormalChargeMatcher());
        classMap.put(MassNumberMatcher.class, new MassNumberMatcher());
        classMap.put(ChargeMatcher.class, new ChargeMatcher());
        classMap.put(StereoParityMatcher.class, new StereoParityMatcher());

        // make sure these are lower case
        nameMap.put("symbol", new SymbolMatcher());
        nameMap.put("formalcharge", new FormalChargeMatcher());
        nameMap.put("charge", new FormalChargeMatcher());
        nameMap.put("mass", new MassNumberMatcher());
        nameMap.put("parity", new StereoParityMatcher());

    }

    /**
     * Access an appropriate matcher for a given class name. If no
     * matcher is found for the class a runtime exception is thrown.
     * <p/>
     * <pre>{@code
     *     AtomMatcherFactory.ofClass(SymbolMatcher.class);
     * }</pre>
     *
     * @param c class of the matcher
     *
     * @return instance of the matcher for the provided class
     */
    public static IAtomMatcher ofClass(Class<? extends IAtomMatcher> c) {

        IAtomMatcher matcher = classMap.get(c);

        if (matcher == null)
            throw new IllegalArgumentException(c.getName() + " is not managed by the MatchFactor");

        return matcher;

    }

    /**
     * Access an appropriate matcher for a given matcher name. If no
     * matcher is found for the class a runtime exception is thrown.
     * <p/>
     * Current matcher names are:
     * <ul>
     * <li>symbol</li>
     * <li>formalcharge</li>
     * </ul>
     * <p/>
     * <p/>
     * <pre>{@code
     *     AtomMatcherFactory.ofName("symbol");
     * }</pre>
     *
     * @param name name of the matcher
     *
     * @return instance of the matcher for the provided name
     */
    public static IAtomMatcher ofName(String name) {

        name = name.toLowerCase(Locale.ENGLISH);

        IAtomMatcher matcher = nameMap.get(name);

        if (matcher == null)
            throw new IllegalArgumentException("A matcher named '" + name
                                                       + "' was not found, valid names are; " + nameMap.keySet());

        return matcher;

    }

    public static IAtomMatcher ofObject(Object obj) {
        if (obj instanceof IAtomMatcher)
            return (IAtomMatcher) obj;
        if (obj instanceof Class && IAtomMatcher.class.isAssignableFrom((Class) obj))
            return ofClass((Class<? extends IAtomMatcher>) obj);
        if (obj instanceof String)
            return ofName((String) obj);
        throw new IllegalArgumentException("Object could not be converted into an AtomMatcher");
    }

    /**
     * Access an array of atom matchers given a variable array of atom matcher
     * classes.
     * <p/>
     * If any class is not found a runtime exception is thrown
     *
     * @param classes classes of atom matchers
     *
     * @return fixed array of atom matchers
     */
    public static IAtomMatcher[] ofClasses(Class<? extends IAtomMatcher>... classes) {

        IAtomMatcher[] matchers = new IAtomMatcher[classes.length];

        for (int i = 0; i < classes.length; i++) {
            matchers[i] = ofClass(classes[i]);
        }

        return matchers;

    }

    /**
     * Access an array of atom matchers given a variable array of atom matcher names
     *
     * @param names names of the atom matchers
     *
     * @return fixed array of atom matchers
     */
    public static IAtomMatcher[] ofNames(String... names) {

        IAtomMatcher[] matchers = new IAtomMatcher[names.length];

        for (int i = 0; i < names.length; i++) {
            matchers[i] = ofName(names[i]);
        }

        return matchers;

    }

    public static IAtomMatcher[] ofObjects(Object... objs) {

        IAtomMatcher[] matchers = new IAtomMatcher[objs.length];

        for (int i = 0; i < objs.length; i++) {
            matchers[i] = ofObject(objs[i]);
        }

        return matchers;

    }

    /**
     * Convenience method for creating an 'and' matcher. The and matcher can be used
     * with two or more atom matchers. When used with more then two matchers the operation
     * could be thought of as 'all'.
     *
     * @param matchers variable list of atom matchers
     *
     * @return combined matchers
     */
    public static IAtomMatcher and(IAtomMatcher... matchers) {

        if (matchers.length < 2)
            throw new IllegalArgumentException("Please provide at least two matchers");

        if (matchers.length == 2)
            return new ConjunctionMatcher(matchers[0], matchers[1]);

        // recursively call the and function with a shifted array, we
        // could get a small speed increase by iterating instead of copying
        // but I wouldn't expect the matcher array to be that long
        return new ConjunctionMatcher(matchers[0], and(Arrays.copyOfRange(matchers, 1, matchers.length)));

    }

    /**
     * Convenience method for creating an 'and' matcher from matcher objects. This method
     * takes a variable array of matchers (IAtomMatcher, Class objects and String (names).
     *
     * @param objects variable array of matcher objects, classes or names
     *
     * @return combined matcher
     *
     * @see #ofClass(Class)
     * @see #ofClasses(Class[])
     * @see #or(IAtomMatcher...)
     */
    public static IAtomMatcher and(Object... objects) {
        return and(ofObjects(objects));
    }

    /**
     * Convenience method for creating an 'and' matcher from matcher names. This method
     * uses the ofNames method to convert the names to instances and then calls
     * {@see #and(IAtomMatcher ...)} to create the combinator.
     *
     * @param matcherNames variable array of matcher names
     *
     * @return combinded matcher
     *
     * @see #ofName(String)
     * @see #ofNames(String...)
     */
    public static IAtomMatcher and(String... matcherNames) {
        return and(ofNames(matcherNames));
    }


    /**
     * Convenience method for creating an 'and' matcher from matcher classes. This method
     * uses the ofClasses method to convert the names to instances and then calls
     * {@see #and(IAtomMatcher ...)} to create the combinator.
     *
     * @param matcherClasses variable array of matcher classes
     *
     * @return combinded matcher
     *
     * @see #ofClass(Class)
     * @see #ofClasses(Class[])
     */
    public static IAtomMatcher and(Class<? extends IAtomMatcher>... matcherClasses) {
        return and(ofClasses(matcherClasses));
    }

    /**
     * Convenience method for creating an 'or' matcher. The and matcher can be used
     * with two or more atom matchers. When used with more then two matchers the operation
     * could be thought of as 'any'.
     *
     * @param matchers variable list of atom matchers
     *
     * @return combined matchers
     */
    public static IAtomMatcher or(IAtomMatcher... matchers) {

        if (matchers.length < 2)
            throw new IllegalArgumentException("Please provide at least two matchers");

        if (matchers.length == 2)
            return new DisjunctionMatcher(matchers[0], matchers[1]);

        // recursively call the or function with a shifted array, we
        // could get a small speed increase by iterating instead of copying
        // but I wouldn't expect the matcher array to be that long
        return new DisjunctionMatcher(matchers[0], or(Arrays.copyOfRange(matchers, 1, matchers.length)));

    }

    /**
     * Convenience method for creating an 'and' matcher from matcher names. This method
     * uses the ofNames method to convert the names to instances and then calls
     * {@see #or(IAtomMatcher ...)} to create the combinator.
     *
     * @param matcherNames variable array of matcher names
     *
     * @return combinded matcher
     *
     * @see #ofName(String)
     * @see #ofNames(String...)
     * @see #or(IAtomMatcher...)
     */
    public static IAtomMatcher or(String... matcherNames) {
        return or(ofNames(matcherNames));
    }

    /**
     * Convenience method for creating an 'or' matcher from matcher classes. This method
     * uses the ofClasses method to convert the names to instances and then calls
     * {@see #or(IAtomMatcher ...)} to create the combinator.
     *
     * @param matcherClasses variable array of matcher classes
     *
     * @return combinded matcher
     *
     * @see #ofClass(Class)
     * @see #ofClasses(Class[])
     * @see #or(IAtomMatcher...)
     */
    public static IAtomMatcher or(Class<? extends IAtomMatcher>... matcherClasses) {
        return or(ofClasses(matcherClasses));
    }

    /**
     * Convenience method for creating an 'or' matcher from matcher objects. This method
     * takes a variable array of matchers (IAtomMatcher, Class objects and String (names).
     *
     * @param objects variable array of matcher objects, classes or names
     *
     * @return combined matcher
     *
     * @see #ofClass(Class)
     * @see #ofClasses(Class[])
     * @see #or(IAtomMatcher...)
     */
    public static IAtomMatcher or(Object... objects) {
        return or(ofObjects(objects));
    }


    /**
     * Create an inverter for the provided matcher in the form of a
     * 'not' matcher.
     *
     * @param matcher atom matcher implementation
     *
     * @return return an atom matcher that will inverse the return value of the provided matcher
     */
    public static IAtomMatcher not(IAtomMatcher matcher) {
        return new NegationMatcher(matcher);
    }

    /**
     * Create an inverter for the provided matcher in the form of a
     * 'not' matcher using the class.
     *
     * @param matcherClass class of an atom matcher implementation
     *
     * @return return an atom matcher that will inverse the return value of the provided matcher
     */
    public static IAtomMatcher not(Class<? extends IAtomMatcher> matcherClass) {
        return new NegationMatcher(ofClass(matcherClass));
    }

    /**
     * Create an condition inverter for the provided matcher in the form of a
     * 'not' matcher using the name.
     *
     * @param matcherName name of an atom matcher implementation
     *
     * @return return an atom matcher that will inverse the return value of the provided matcher
     */
    public static IAtomMatcher not(String matcherName) {
        return new NegationMatcher(ofName(matcherName));
    }

    /**
     * Create an condition inverter for the provided matcher in the form of a
     * 'not' matcher using the class.
     *
     * @param object an instance of atom matcher, class or string
     *
     * @return return an atom matcher that will inverse the return value of the provided matcher
     */
    public static IAtomMatcher not(Object object) {
        return not(ofObject(object));
    }


}
