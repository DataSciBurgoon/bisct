package tests;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.HashMap;

import org.junit.Test;
import org.junit.rules.ExpectedException;

import aopbn_models.NoNodeException;
import aopbn_models.SteatosisNetwork;

public class TestBayesNet {
	
	public final ExpectedException exception = ExpectedException.none();

	@Test
	public void test() {
		HashMap<String, String> hmEvidence = new HashMap<String, String>();
		hmEvidence.put("cytosolic_fatty_acids", "true");
		hmEvidence.put("fatty_acid_beta_oxidation", "false");
		SteatosisNetwork sn = new SteatosisNetwork(hmEvidence);
		try {
			assertEquals(0.99, sn.getPosteriorProbsForNode("steatosis")[0], 0.001);
		} catch (NoNodeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		HashMap<String, String> hmEvidence2 = new HashMap<String, String>();
		hmEvidence2.put("cytosolic_fatty_acids", "false");
		hmEvidence2.put("fatty_acid_beta_oxidation", "true");
		SteatosisNetwork sn2 = new SteatosisNetwork(hmEvidence2);
		try {
			assertEquals(0.01, sn2.getPosteriorProbsForNode("steatosis")[0], 0.001);
		} catch (NoNodeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		HashMap<String, String> hmEvidence3 = new HashMap<String, String>();
		hmEvidence3.put("nrf2", "false");
		SteatosisNetwork sn3 = new SteatosisNetwork(hmEvidence3);
		try {
			assertEquals(0.74, sn3.getPosteriorProbsForNode("steatosis")[0], 0.01);
		} catch (NoNodeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		HashMap<String, String> hmEvidence4 = new HashMap<String, String>();
		hmEvidence4.put("nrf2", "false");
		SteatosisNetwork sn4 = new SteatosisNetwork(hmEvidence4);
		exception.expect(NoNodeException.class);
		try {
			sn4.getPosteriorProbsForNode("blah");
		} catch (NoNodeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
