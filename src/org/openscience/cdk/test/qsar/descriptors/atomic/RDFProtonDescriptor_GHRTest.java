package org.openscience.cdk.test.qsar.descriptors.atomic;

import java.io.InputStream;

import junit.framework.Test;
import junit.framework.TestSuite;

import org.openscience.cdk.ChemFile;
import org.openscience.cdk.ChemObject;
import org.openscience.cdk.interfaces.IChemModel;
import org.openscience.cdk.interfaces.IChemSequence;
import org.openscience.cdk.interfaces.IMolecule;
import org.openscience.cdk.interfaces.IMoleculeSet;
import org.openscience.cdk.io.MDLReader;
import org.openscience.cdk.qsar.DescriptorValue;
import org.openscience.cdk.qsar.descriptors.atomic.RDFProtonDescriptor_GHR;
import org.openscience.cdk.qsar.result.DoubleArrayResult;
import org.openscience.cdk.qsar.result.IDescriptorResult;
import org.openscience.cdk.test.CDKTestCase;

/**
 * @cdk.module test-qsar
 */
public class RDFProtonDescriptor_GHRTest extends CDKTestCase {

	public RDFProtonDescriptor_GHRTest() {
    }

	public static Test suite() {
		return new TestSuite(RDFProtonDescriptor_GHRTest.class);
	}
    
	/**
	 *  @cdk.bug 1632419
	 */
	public void testExample1() throws Exception {
		//firstly read file to molecule		
		String filename = "data/mdl/hydroxyamino.mol" +
				"";
		InputStream ins = this.getClass().getClassLoader().getResourceAsStream(filename);
		MDLReader reader = new MDLReader(ins);
		ChemFile chemFile = (ChemFile)reader.read((ChemObject)new ChemFile());
		IChemSequence seq = chemFile.getChemSequence(0);
		IChemModel model = seq.getChemModel(0);
		IMoleculeSet som = model.getMoleculeSet();
		IMolecule mol = som.getMolecule(0);

		RDFProtonDescriptor_GHR descriptor = new RDFProtonDescriptor_GHR();
		int hNumber =0;
		for (int i=0; i < mol.getAtomCount(); i++) {
			System.out.println("Atom: " + mol.getAtom(i).getSymbol());
			System.out.println("  charge: " + mol.getAtom(i).getCharge());
			if(mol.getAtom(i).getSymbol().equals("H")){
				hNumber++;
				//secondly perform calculation on it.
				DescriptorValue dv = descriptor.calculate(mol.getAtom(i),mol );
				IDescriptorResult result = dv.getValue();
				System.out.println("array: " + result.toString());
				System.out.println("i=" +hNumber);
				assertTrue(result instanceof DoubleArrayResult);
				DoubleArrayResult daResult = (DoubleArrayResult) result;
				double daSum = 0.0;
				for (int j=0; j<daResult.size(); j++) {
					double val = daResult.get(j);
					if (hNumber > 7) {
						assertTrue("Expected NaN for non-rigid substructure hydrogen: " + mol.getAtom(i), Double.isNaN(val));
					} else {
						assertFalse("Unexpected NaN for rigid substructure hydrogen: " + mol.getAtom(i), Double.isNaN(val));
					}
					daSum += Math.abs(daResult.get(j));
				}
				if (hNumber < 7) assertTrue(daSum > 0.1);
			}		

		}
	}
	
	public void testChloroBenzene() throws Exception {
		//firstly read file to molecule		
		String filename = "data/mdl/clorobenzene.mol";
		InputStream ins = this.getClass().getClassLoader().getResourceAsStream(filename);
		MDLReader reader = new MDLReader(ins);
		ChemFile chemFile = (ChemFile)reader.read((ChemObject)new ChemFile());
		IChemSequence seq = chemFile.getChemSequence(0);
		IChemModel model = seq.getChemModel(0);
		IMoleculeSet som = model.getMoleculeSet();
		IMolecule mol = som.getMolecule(0);

		RDFProtonDescriptor_GHR descriptor = new RDFProtonDescriptor_GHR();
		for (int i=0; i < mol.getAtomCount(); i++) {
			System.out.println("Atom: " + mol.getAtom(i).getSymbol());
			System.out.println("  charge: " + mol.getAtom(i).getCharge());
			if(mol.getAtom(i).getSymbol().equals("H")){
				//secondly perform calculation on it.
				DescriptorValue dv = descriptor.calculate(mol.getAtom(i),mol );
				IDescriptorResult result = dv.getValue();
				System.out.println("array: " + result.toString());
				assertTrue(result instanceof DoubleArrayResult);
				DoubleArrayResult daResult = (DoubleArrayResult) result;
				double daSum = 0.0;
				for (int j=0; j<daResult.size(); j++) {
					double val = daResult.get(j);
					System.out.println(val);
					assertFalse("Unexpected NaN for rigid substructure hydrogen: " + mol.getAtom(i), Double.isNaN(val));
					daSum += Math.abs(daResult.get(j));
				}
				assertTrue(daSum > 0.1);
			}		

		}
	}

}
