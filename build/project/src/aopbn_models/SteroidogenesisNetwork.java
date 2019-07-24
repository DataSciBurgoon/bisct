package aopbn_models;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import org.eclipse.recommenders.jayes.BayesNet;
import org.eclipse.recommenders.jayes.BayesNode;
import org.eclipse.recommenders.jayes.inference.IBayesInferer;
import org.eclipse.recommenders.jayes.inference.jtree.JunctionTreeAlgorithm;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class SteroidogenesisNetwork {
	
	private HashMap<String, double[]> hmPosteriorProbs = new HashMap<String, double[]>();
	private ObservableList<AopbnResult> olPosteriorProbs = FXCollections.observableArrayList();
	
	
	
	public SteroidogenesisNetwork(HashMap<String, String> hmEvidence){
		
		BayesNet net = new BayesNet();
		
		BayesNode cholesterol = net.createNode("cholesterol");
		cholesterol.addOutcomes("true", "false");
		cholesterol.setProbabilities(0.95, 0.05);
		
		BayesNode cyp11a1 = net.createNode("cyp11a1");
		cyp11a1.addOutcomes("true", "false");
		cyp11a1.setProbabilities(0.5, 0.5);
		
		BayesNode pregnenolone = net.createNode("pregnenolone");
		pregnenolone.addOutcomes("true", "false");
		pregnenolone.setParents(Arrays.asList(cyp11a1, cholesterol));
		pregnenolone.setProbabilities(
				//cyp11a1==true
				0.99, 0.01, //cholesterol==true
				0.01, 0.99, //cholesterol==false 
				//cyp11a1==false
				0.01, 0.99, //cholesterol==true
				0.01, 0.99 //cholesterol==false
				);
		
		BayesNode cyp17a1_hydroxylase = net.createNode("cyp17a1_hydroxylase");
		cyp17a1_hydroxylase.addOutcomes("true", "false");
		cyp17a1_hydroxylase.setProbabilities(0.5, 0.5);
		
		BayesNode cyp17a1_lyase = net.createNode("cyp17a1_lyase");
		cyp17a1_lyase.addOutcomes("true", "false");
		cyp17a1_lyase.setProbabilities(0.5, 0.5);
		
		BayesNode hsd3b1 = net.createNode("hsd3b1");
		hsd3b1.addOutcomes("true", "false");
		hsd3b1.setProbabilities(0.5, 0.5);
		
		BayesNode hsd17b3 = net.createNode("hsd17b3");
		hsd17b3.addOutcomes("true", "false");
		hsd17b3.setProbabilities(0.5, 0.5);
		
		BayesNode cyp19a1 = net.createNode("cyp19a1");
		cyp19a1.addOutcomes("true", "false");
		cyp19a1.setProbabilities(0.5, 0.5);
		
		BayesNode cyp21a1 = net.createNode("cyp21a1");
		cyp21a1.addOutcomes("true", "false");
		cyp21a1.setProbabilities(0.5, 0.5);
		
		BayesNode cyp11b1 = net.createNode("cyp11b1");
		cyp11b1.addOutcomes("true", "false");
		cyp11b1.setProbabilities(0.5, 0.5);
		
		BayesNode hydroxypregnenolone = net.createNode("hydroxypregnenolone");
		hydroxypregnenolone.addOutcomes("true", "false");
		hydroxypregnenolone.setParents(Arrays.asList(cyp17a1_hydroxylase, pregnenolone));
		hydroxypregnenolone.setProbabilities(
				//cyp17a1_hydroxylase==true
				0.99, 0.01, //pregnenolone==true
				0.01, 0.99, //pregnenolone==false
				//cyp17a1_hydroxylase==false
				0.01, 0.99, //pregnenolone==true
				0.01, 0.99 //pregnenolone==false
				);
		
		BayesNode dhea = net.createNode("dhea");
		dhea.addOutcomes("true", "false");
		dhea.setParents(Arrays.asList(cyp17a1_lyase, hydroxypregnenolone));
		dhea.setProbabilities(
				//cyp17a1_lyase==true
				0.99, 0.01, //hydroxypregnenolone==true
				0.01, 0.99, //hydroxypregnenolone==false
				//cyp17a1_lyase==false
				0.01, 0.99, //hydroxypregnenolone==true
				0.01, 0.99 //hydroxypregnenolone==false
				);
		
		BayesNode androstenediol = net.createNode("androstenediol");
		androstenediol.addOutcomes("true", "false");
		androstenediol.setParents(Arrays.asList(hsd17b3, dhea));
		androstenediol.setProbabilities(
				//hsd17b3==true
				0.99, 0.01, //dhea==true
				0.01, 0.99, //dhea==false
				//hsd17b3==false
				0.01, 0.99, //dhea==true
				0.01, 0.99 //dhea==false
				);
		
		BayesNode progesterone = net.createNode("progesterone");
		progesterone.addOutcomes("true", "false");
		progesterone.setParents(Arrays.asList(hsd3b1, pregnenolone));
		progesterone.setProbabilities(
				//hsd3b1==true
				0.99, 0.01, //pregnenolone==true
				0.01, 0.99, //pregnenolone==false
				//hsd3b1==false
				0.01, 0.99, //pregnenolone==true
				0.01, 0.99 //pregnenolone==false
				);
		
		BayesNode hydroxyprogesterone1 = net.createNode("hydroxyprogesterone1");
		hydroxyprogesterone1.addOutcomes("true", "false");
		hydroxyprogesterone1.setParents(Arrays.asList(cyp17a1_hydroxylase, progesterone));
		hydroxyprogesterone1.setProbabilities(
				//cyp17a1_hydroxylase==true
				0.99, 0.01, //progesterone==true
				0.01, 0.99, //progesterone==false
				//cyp17a1_hydroxylase==false
				0.01, 0.99, //progesterone==true
				0.01, 0.99 //progesterone==false
				);
		
		BayesNode hydroxyprogesterone2 = net.createNode("hydroxyprogesterone2");
		hydroxyprogesterone2.addOutcomes("true", "false");
		hydroxyprogesterone2.setParents(Arrays.asList(hsd3b1, hydroxypregnenolone));
		hydroxyprogesterone2.setProbabilities(
				//hsd3b1==true
				0.99, 0.01, //hydroxypregnenolone==true
				0.01, 0.99, //hydroxypregnenolone==false
				//hsd3b1==false
				0.01, 0.99, //hydroxypregnenolone==true
				0.01, 0.99 //hydroxypregnenolone==false
				);
		
		BayesNode hydroxyprogesterone = net.createNode("hydroxyprogesterone");
		hydroxyprogesterone.addOutcomes("true", "false");
		hydroxyprogesterone.setParents(Arrays.asList(hydroxyprogesterone1, hydroxyprogesterone2));
		hydroxyprogesterone.setProbabilities(
				//hydroxyprogesterone1==true
				0.99, 0.01, //hydroxyprogesterone2==true
				0.99, 0.01, //hydroxyprogesterone2==false
				//hydroxyprogesterone1==false
				0.99, 0.01, //hydroxyprogesterone2==true
				0.01, 0.99 //hydroxyprogesterone2==false
				);
		
		BayesNode androstenedione1 = net.createNode("androstenedione1");
		androstenedione1.addOutcomes("true", "false");
		androstenedione1.setParents(Arrays.asList(hsd3b1, dhea));
		androstenedione1.setProbabilities(
				//hsd3b1==true
				0.99, 0.01, //dhea==true
				0.01, 0.99, //dhea==false
				//hsd3b1==false
				0.01, 0.99, //dhea==true
				0.01, 0.99 //dhea==false
				);
		
		BayesNode androstenedione2 = net.createNode("androstenedione2");
		androstenedione2.addOutcomes("true", "false");
		androstenedione2.setParents(Arrays.asList(cyp17a1_lyase, hydroxyprogesterone));
		androstenedione2.setProbabilities(
				//cyp17a1_lyase==true
				0.99, 0.01, //hydroxyprogesterone==true
				0.01, 0.99, //hydroxyprogesterone==false
				//cyp17a1_lyase==false
				0.01, 0.99, //hydroxyprogesterone==true
				0.01, 0.99 //hydroxyprogesterone==false
				);
		
		BayesNode androstenedione = net.createNode("androstenedione");
		androstenedione.addOutcomes("true", "false");
		androstenedione.setParents(Arrays.asList(androstenedione1, androstenedione2));
		androstenedione.setProbabilities(
				//androstenedione1==true
				0.99, 0.01, //androstenedione2==true
				0.99, 0.01, //androstenedione2==false
				//androstenedione1==false
				0.99, 0.01, //androstenedione2==true
				0.01, 0.99 //androstenedione2==false
				);
		
		BayesNode estrone = net.createNode("estrone");
		estrone.addOutcomes("true", "false");
		estrone.setParents(Arrays.asList(cyp19a1, androstenedione));
		estrone.setProbabilities(
				//cyp19a1==true
				0.99, 0.01, //androstenedione==true
				0.01, 0.99, //androstenedione==false
				//cyp19a1==false
				0.01, 0.99, //androstenedione==true
				0.01, 0.99 //androstenedione==false
				);
		
		BayesNode testosterone1 = net.createNode("testosterone1");
		testosterone1.addOutcomes("true", "false");
		testosterone1.setParents(Arrays.asList(hsd17b3, androstenedione));
		testosterone1.setProbabilities(
				//hsd17b3==true
				0.99, 0.01, //androstenedione==true
				0.01, 0.99, //androstenedione==false
				//hsd17b3==false
				0.01, 0.99, //androstenedione==true
				0.01, 0.99 //androstenedione==false
				);
		
		BayesNode testosterone2 = net.createNode("testosterone2");
		testosterone2.addOutcomes("true", "false");
		testosterone2.setParents(Arrays.asList(hsd3b1, androstenediol));
		testosterone2.setProbabilities(
				//hsd17b3==true
				0.99, 0.01, //androstenediol==true
				0.01, 0.99, //androstenediol==false
				//hsd17b3==false
				0.01, 0.99, //androstenediol==true
				0.01, 0.99 //androstenediol==false
				);
		
		BayesNode testosterone = net.createNode("testosterone");
		testosterone.addOutcomes("true", "false");
		testosterone.setParents(Arrays.asList(testosterone1, testosterone2));
		testosterone.setProbabilities(
				//testosterone1==true
				0.99, 0.01, //testosterone2==true
				0.99, 0.01, //testosterone2==false
				//testosterone1==false
				0.99, 0.01, //testosterone2==true
				0.01, 0.99 //testosterone2==false
				);
		
		BayesNode estradiol = net.createNode("estradiol");
		estradiol.addOutcomes("true", "false");
		estradiol.setParents(Arrays.asList(cyp19a1, testosterone));
		estradiol.setProbabilities(
				//cyp19a1==true
				0.99, 0.01, //testosterone==true
				0.01, 0.99, //testosterone==false
				//cyp19a1==false
				0.01, 0.99, //testosterone==true
				0.01, 0.99 //testosterone==false
				);
		
		BayesNode deoxycorticosterone = net.createNode("deoxycorticosterone");
		deoxycorticosterone.addOutcomes("true", "false");
		deoxycorticosterone.setParents(Arrays.asList(cyp21a1, progesterone));
		deoxycorticosterone.setProbabilities(
				//cyp21a1==true
				0.99, 0.01, //progesterone==true
				0.01, 0.99, //progesterone==false
				//cyp21a1==false
				0.01, 0.99, //progesterone==true
				0.01, 0.99 //progesterone==false
				);
		
		BayesNode corticosterone = net.createNode("corticosterone");
		corticosterone.addOutcomes("true", "false");
		corticosterone.setParents(Arrays.asList(cyp11b1, deoxycorticosterone));
		corticosterone.setProbabilities(
				//cyp11b1==true
				0.99, 0.01, //deoxycorticosterone==true
				0.01, 0.99, //deoxycorticosterone==false
				//cyp11b1==false
				0.01, 0.99, //deoxycorticosterone==true
				0.01, 0.99 //deoxycorticosterone==false
				);
		
		BayesNode deoxycortisol = net.createNode("deoxycortisol");
		deoxycortisol.addOutcomes("true", "false");
		deoxycortisol.setParents(Arrays.asList(cyp21a1, hydroxyprogesterone));
		deoxycortisol.setProbabilities(
				//cyp21a1==true
				0.99, 0.01, //hydroxyprogesterone==true
				0.01, 0.99, //hydroxyprogesterone==false
				//cyp21a1==false
				0.01, 0.99, //hydroxyprogesterone==true
				0.01, 0.99 //hydroxyprogesterone==false
				);
		
		BayesNode cortisol = net.createNode("cortisol");
		cortisol.addOutcomes("true", "false");
		cortisol.setParents(Arrays.asList(cyp11b1, deoxycortisol));
		cortisol.setProbabilities(
				//cyp11b1==true
				0.99, 0.01, //deoxycortisol==true
				0.01, 0.99, //deoxycortisol==false
				//cyp11b1==false
				0.01, 0.99, //deoxycortisol==true
				0.01, 0.99 //deoxycortisol==false
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
	

}
