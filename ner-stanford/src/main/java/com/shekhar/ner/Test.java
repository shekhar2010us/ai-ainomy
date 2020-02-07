package com.shekhar.ner;

import edu.stanford.nlp.ie.crf.CRFClassifier;

import java.util.List;

public class Test {

    private static String modelFile = "src/main/resources/data/models/ner-model-1";

    public static void main(String[] args) {
        Test test = new Test();
        CRFClassifier model = test.getModel(modelFile);

        String text = "Transforming biopharma R&D through Machine Learning and Artificial Intelligence. And building bad ass data science teams in the process.";
        test.tag(model, text);

    }

    // Read saved model from file
    public CRFClassifier getModel(String modelPath) {
        return CRFClassifier.getClassifierNoExceptions(modelPath);
    }

    // tag text
    public void tag(CRFClassifier model, String input) {
        input = input.trim();
        String classified = model.classifyToString(input);
        String[] parts = classified.split(" ", -1);
        for (String part : parts) {
            if (!part.contains("/O")) {
                System.out.println(part);
            }
        }
        System.out.println(model.labels());
    }

}
