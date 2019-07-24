package aopbn_models;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.eclipse.recommenders.jayes.BayesNet;
import org.eclipse.recommenders.jayes.BayesNode;
import org.eclipse.recommenders.jayes.inference.IBayesInferer;
import org.eclipse.recommenders.jayes.inference.jtree.JunctionTreeAlgorithm;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class SteatosisNetwork implements AopbnNetwork{
	
	private double[] steatosisPosteriorProbabilities;
	private HashMap<String, double[]> hmPosteriorProbs = new HashMap<String, double[]>();
	private ObservableList<AopbnResult> olPosteriorProbs = FXCollections.observableArrayList();
	
	
	
	public SteatosisNetwork(HashMap<String, String> hmEvidence){
		
		BayesNet net = new BayesNet();
		BayesNode nrf2 = net.createNode("nrf2");
		nrf2.addOutcomes("true", "false");
		nrf2.setProbabilities(0.5, 0.5);
		
		BayesNode ir = net.createNode("ir");
		ir.addOutcomes("true", "false");
		ir.setProbabilities(0.5, 0.5);
		
		BayesNode pi3k = net.createNode("pi3k");
		pi3k.addOutcomes("true", "false");
		pi3k.setProbabilities(0.5, 0.5);
		
		BayesNode fxr = net.createNode("fxr");
		fxr.addOutcomes("true", "false");
		fxr.setParents(Arrays.asList(nrf2));
		fxr.setProbabilities(
				0.95, 0.05,	//nrf2==true
				0.05, 0.95	//nrf2==false
		);
		
		BayesNode shp = net.createNode("shp");
		shp.addOutcomes("true", "false");
		shp.setParents(Arrays.asList(fxr));
		shp.setProbabilities(
				0.95, 0.05, //fxr==true
				0.05, 0.95	//fxr==false
		);
		
		BayesNode lxr = net.createNode("lxr");
		lxr.addOutcomes("true", "false");
		lxr.setParents(Arrays.asList(shp));
		lxr.setProbabilities(
				0.05, 0.95,  //shp==true 
				0.95, 0.05); //shp==false
		
		BayesNode ppara = net.createNode("ppara");
		ppara.addOutcomes("true", "false");
		ppara.setParents(Arrays.asList(fxr, shp, lxr));
		ppara.setProbabilities(
				//fxr == true, shp==true
				0.50, 0.50, //lxr==true
				0.95, 0.05, //lxr==false
				//fxr == true, shp==false
				0.05, 0.95, //lxr==true
				0.95, 0.05, //lxr==false 
				//fxr == false, shp==true
				0.50, 0.50, //lxr==true
				0.95, 0.05, //lxr==false
				//fxr == false, shp==false
				0.05, 0.95, //lxr==true
				0.50, 0.50 //lxr==false
				);
		
		BayesNode hsd17b4 = net.createNode("hsd17b4");
		hsd17b4.addOutcomes("true", "false");
		hsd17b4.setParents(Arrays.asList(ppara));
		hsd17b4.setProbabilities(
				0.95, 0.05, //ppara==true
				0.05, 0.95 //ppara==false
				);
		
		BayesNode fabo = net.createNode("fatty_acid_beta_oxidation");
		fabo.addOutcomes("true", "false");
		fabo.setParents(Arrays.asList(hsd17b4));
		fabo.setProbabilities(
				0.99, 0.01, //hsd17b4==true
				0.01, 0.99 //hsd17b4==false
				);
		
		BayesNode lrh1 = net.createNode("lrh1");
		lrh1.addOutcomes("true", "false");
		lrh1.setParents(Arrays.asList(shp));
		lrh1.setProbabilities(
				0.05, 0.95, //shp==true
				0.95, 0.05 //shp==false
				);
		
		BayesNode mtorc2 = net.createNode("mtorc2");
		mtorc2.addOutcomes("true", "false");
		mtorc2.setParents(Arrays.asList(ir));
		mtorc2.setProbabilities(
				0.99, 0.01, //ir==true
				0.01, 0.99 //ir==false
				);
		
		BayesNode foxo1 = net.createNode("foxo1");
		foxo1.addOutcomes("true", "false");
		foxo1.setParents(Arrays.asList(ir));
		foxo1.setProbabilities(
				0.01, 0.99, //ir == true
				0.99, 0.01 //ir == false
				);
		
		BayesNode mtp = net.createNode("mtp");
		mtp.addOutcomes("true", "false");
		mtp.setParents(Arrays.asList(foxo1));
		mtp.setProbabilities(
				0.99, 0.01, //foxo1 == true
				0.01, 0.99  //foxo1 == false
				);
		
		BayesNode efflux = net.createNode("efflux");
		efflux.addOutcomes("true", "false");
		efflux.setParents(Arrays.asList(mtp));
		efflux.setProbabilities(
				0.99, 0.01, //mtp == true
				0.01, 0.99  //mtp == false
				);
		
		
		BayesNode akt = net.createNode("akt");
		akt.addOutcomes("true", "false");
		akt.setParents(Arrays.asList(pi3k, mtorc2));
		akt.setProbabilities(
				//pi3k==true
				0.95, 0.05, //mtorc2==true
				0.05, 0.95, //mtorc2==false 
				//pi3k==false
				0.95, 0.05, //mtorc2==true
				0.05, 0.95 //mtorc2==false
				);

		BayesNode lfabp = net.createNode("lfabp");
		lfabp.addOutcomes("true", "false");
		lfabp.setParents(Arrays.asList(akt));
		lfabp.setProbabilities(
				0.95, 0.05, //akt==true
				0.05, 0.95 //akt==false
				);
		
		BayesNode influx = net.createNode("influx");
		influx.addOutcomes("true", "false");
		influx.setParents(Arrays.asList(lfabp));
		influx.setProbabilities(
				0.99, 0.01, //lfabp==true
				0.01, 0.99 //lfabp==false
				);
		
		BayesNode pparg = net.createNode("pparg");
		pparg.addOutcomes("true", "false");
		pparg.setParents(Arrays.asList(lfabp));
		pparg.setProbabilities(
				0.95, 0.05, //lfabp==true 
				0.05, 0.95  //lfabp==false
				);
		
		BayesNode fas = net.createNode("fas");
		fas.addOutcomes("true", "false");
		fas.setParents(Arrays.asList(lrh1, lxr, pparg));
		fas.setProbabilities(
				//lrh1==true, lxr==true
				0.95, 0.05, //pparg==true
				0.75, 0.25, //pparg==false
				//lrh1==true, lxr==false
				0.75, 0.25, //pparg==true
				0.50, 0.50, //pparg==false
				//lrh1==false, lxr==true
                0.75, 0.25, //pparg==true 
                0.50, 0.50, //pparg==false
                //lrh1==false, lxr==false
                0.50, 0.50, //pparg==true 
                0.01, 0.99  //pparg==false
                );
		
		BayesNode mtorc1 = net.createNode("mtorc1");
		mtorc1.addOutcomes("true", "false");
		mtorc1.setParents(Arrays.asList(akt));
		mtorc1.setProbabilities(
				0.95, 0.05,  //akt==true 
				0.05, 0.95  //akt==false
				);
		
		BayesNode apkc = net.createNode("apkc");
		apkc.addOutcomes("true", "false");
		apkc.setParents(Arrays.asList(pi3k));
		apkc.setProbabilities(
				0.99, 0.01,  //pi3k==true 
				0.01, 0.99  //pi3k==false
				);
		
		BayesNode srebp1 = net.createNode("srebp1");
		srebp1.addOutcomes("true", "false");
		srebp1.setParents(Arrays.asList(mtorc1, apkc));
		srebp1.setProbabilities(
				//mtorc1==true
				0.99, 0.01, //apkc==true
				0.99, 0.01, //apkc==false
				//mtorc2==false
                0.99, 0.01,  //apkc==true 
                0.01, 0.99  //apkc==false
                );
		
		BayesNode scd1 = net.createNode("scd1");
		scd1.addOutcomes("true", "false");
		scd1.setParents(Arrays.asList(srebp1));
		scd1.setProbabilities(
				0.99, 0.01, //srebp1==true 
				0.01, 0.99 //srebp1==false
				);
		
		BayesNode lipogenesis = net.createNode("lipogenesis");
		lipogenesis.addOutcomes("true", "false");
		lipogenesis.setParents(Arrays.asList(scd1, fas));
		lipogenesis.setProbabilities(
				//scd1==true
				0.95, 0.05, //fas==true 
				0.05, 0.95, //fas==false
				//scd1==false
                0.95, 0.05, //fas==true
                0.05, 0.95 //fas==false
                );
		
		BayesNode steatosis = net.createNode("steatosis");
		steatosis.addOutcomes("true", "false");
		steatosis.setParents(Arrays.asList(fabo, lipogenesis, influx, efflux));
		steatosis.setProbabilities(
				//fabo==true, lipog==true, influx==true
				0.50, 0.50, //efflux==true
				0.99, 0.01, //efflux==false
				
				//fabo==true, lipog==true, influx==false
				0.50, 0.50, //efflux==true
				0.50, 0.50, //efflux==false
				
				//fabo==true, lipog==false, influx==true
				0.50, 0.50, //efflux==true
				0.80, 0.20, //efflux==false
				
				//fabo==true, lipog==false, influx==false
				0.01, 0.99, //efflux==true
				0.20, 0.80, //efflux==false
				
				//fabo==false, lipog==true, influx==true
				0.50, 0.50, //efflux==true
				0.99, 0.01, //efflux==false
				
				//fabo==false, lipog==true, influx==false
				0.50, 0.50, //efflux==true
				0.99, 0.01, //efflux==false
				
				//fabo==false, lipog==false, influx==true
				0.50, 0.50, //efflux==true
				0.99, 0.01, //efflux==false
				
				//fabo==false, lipog==false, influx==false
				0.01, 0.99, //efflux==true
				0.50, 0.50 //efflux==false
				);
		
		@SuppressWarnings("deprecation")
		IBayesInferer inferer = new JunctionTreeAlgorithm();
		inferer.setNetwork(net);
		
		
		Map<BayesNode, String> evidence = new HashMap<BayesNode, String>();
		hmEvidence.forEach((key, value) -> {
			BayesNode node = net.getNode(key);
			evidence.put(node, value);
		});
		
		inferer.setEvidence(evidence);
		
		this.steatosisPosteriorProbabilities = inferer.getBeliefs(steatosis);
		
		for(BayesNode node : net.getNodes()){
			System.out.println("node: " + node);
			hmPosteriorProbs.put(node.getName(), inferer.getBeliefs(node));
			AopbnResult aopbnResult = new AopbnResult(
					node.getName(), 
					inferer.getBeliefs(node)[0],
					inferer.getBeliefs(node)[1]);
			this.olPosteriorProbs.add(aopbnResult);
		}
		
	}
	
	public ObservableList<AopbnResult> getPosteriorProbsForGUI(){
		return this.olPosteriorProbs;
	}
	
	public double[] getPosteriorProbsForNode(String nodeName) throws NoNodeException{
		
		if(hmPosteriorProbs.containsKey(nodeName)){
			return hmPosteriorProbs.get(nodeName);
		}
		else{
			throw new NoNodeException("The node " + nodeName + " does not exist in this AOPN");
		}
		
	}
	
//	public double[] getSteatosisPosteriorProbabilities(){
//		return this.steatosisPosteriorProbabilities;
//	}

}
