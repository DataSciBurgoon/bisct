/**
 * Copyright (c) 2011 Michael Kutschke.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Michael Kutschke - initial API and implementation.
 */
package org.eclipse.recommenders.jayes.inference;

import java.util.Map;
import java.util.Map.Entry;

import org.eclipse.recommenders.jayes.BayesNet;
import org.eclipse.recommenders.jayes.BayesNode;
import org.eclipse.recommenders.jayes.sampling.BasicSampler;
import org.eclipse.recommenders.jayes.util.BayesNodeUtil;
import org.eclipse.recommenders.jayes.util.MathUtils;

@SuppressWarnings("deprecation")
public class LikelihoodWeightedSampling extends AbstractInferer {

    private static final int DEFAULT_SAMPLE_COUNT = 200;

    private int sampleCount = DEFAULT_SAMPLE_COUNT;
    private final BasicSampler sampler = new BasicSampler();

    @Override
    public void setNetwork(BayesNet bn) {
        super.setNetwork(bn);
        sampler.setNetwork(bn);
    }

    @Override
    protected void updateBeliefs() {
        sampler.setEvidence(evidence);
        for (int i = 0; i < sampleCount; i++) {
            Map<BayesNode, String> sample = sampler.sample();
            double weight = computeEvidenceProbability(sample);

            for (Entry<BayesNode, String> e : sample.entrySet()) {
                BayesNode n = e.getKey();
                beliefs[n.getId()][n.getOutcomeIndex(e.getValue())] += weight;
            }
        }

        normalizeBeliefs();
    }

    private void normalizeBeliefs() {
        for (int i = 0; i < beliefs.length; i++) {
            beliefs[i] = MathUtils.normalize(beliefs[i]);
        }
    }

    private double computeEvidenceProbability(Map<BayesNode, String> sample) {
        double factor = 1.0;
        for (Entry<BayesNode, String> entry : evidence.entrySet()) {
            BayesNode n = entry.getKey();
            factor *= BayesNodeUtil.getSubCpt(n, sample)[n.getOutcomeIndex(entry.getValue())];
        }
        return factor;
    }

    public void setSampleCount(int sampleCount) {
        this.sampleCount = sampleCount;
    }

    public void seed(long seed) {
        sampler.seed(seed);
    }
}
