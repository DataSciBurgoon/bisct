# BISCT

Bayesian Inference for Substance and Chemical Toxicity

## What Does BISCT Do?

BISCT is a predictive toxicology tool that allows users to predict if an adverse event is likely to occur given the evidence that they have.

For instance, if you have gene expression data, or maybe high throughput in vitro data, you can place your evidence from your data into an input file, and make predictions. Here's a more concrete example:

Go to the src directory, and look at the file: steroidogenesis_prochloraz_example.txt. It looks like this:

progesterone	true
deoxycorticosterone	true
estrone	false
estradiol	false
androstenedione	false
testosterone	false
cortisol	false
deoxycortisol	false


What this input file is saying is that we have detected progesterone and deoxycorticosterone, while we are seeing significant decreases in estrone, estradiol, androstenedione, testosterone, cortisol, and deoxycortisol.

When we run this through the steroidogenesis model in BISCT, we can see what the model predicts is going on for the other hormones and intermediates. But more importantly in this case, we can also establish a mode of action for prochloraz -- we can see that the model says that the hydroxylase activity of CYP17A1 is definitely inhibited given this evidence. Note, this data is from Karmaus, et al. (2016) ToxSci 150(2): 323-332.

## Combining BISCT Results with AOPXplorer

AOPXplorer makes it easy to visualize what's going on -- to see the probabilities laid over the adverse outcome pathway network (AOPN). In the prochloraz example, we visualized the results onto the steroidogenesis AOPN. You can download the AOPXplorer from the Cytoscape App Store (http://apps.cytoscape.org/apps/aopxplorer).

## Content Packs

BISCT consists of two parts -- the software + graphical user interface (GUI) and the Content Pack. The Content Pack contains the Bayesian Network models. Over time, the Content Packs will be updated to include new Bayesian Network models, or to make updates to existing ones. We will always show the version number for the Content Pack on the user interface. Content Packs do not automatically update at this time -- so you'll need to download a new version.

## Let Us Notify You About updates

If you want to know about updates, you can email the developer at lyle.d.burgoon@usace.army.mil to be added to the update list.
