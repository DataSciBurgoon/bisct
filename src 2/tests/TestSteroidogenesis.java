package tests;

import static org.junit.Assert.*;

import java.util.HashMap;

import org.junit.Test;
import org.junit.rules.ExpectedException;

import aopbn_models.NoNodeException;
import aopbn_models.SteatosisNetwork;
import aopbn_models.SteroidogenesisNetwork;

public class TestSteroidogenesis {
	
	public final ExpectedException exception = ExpectedException.none();

	@Test
	public void test() {
		HashMap<String, String> hmEvidence = new HashMap<String, String>();
		hmEvidence.put("dhea", "false");
		hmEvidence.put("androstenedione", "false");
		hmEvidence.put("testosterone", "false");
		SteroidogenesisNetwork sn = new SteroidogenesisNetwork(hmEvidence);
		try {
			assertEquals(0.99, sn.getPosteriorProbsForNode("estradiol")[1], 0.001);
			assertEquals(0.50, sn.getPosteriorProbsForNode("cyp19a1")[0], 0.001);
			assertEquals(0.50, sn.getPosteriorProbsForNode("hsd17b3")[0], 0.001);
			assertEquals(0.96, sn.getPosteriorProbsForNode("cortisol")[1], 0.01);
		} catch (NoNodeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		exception.expect(NoNodeException.class);
		try {
			sn.getPosteriorProbsForNode("blah");
		} catch (NoNodeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
