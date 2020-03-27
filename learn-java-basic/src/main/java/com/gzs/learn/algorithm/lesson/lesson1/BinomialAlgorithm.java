package com.gzs.learn.algorithm.lesson.lesson1;

import java.util.Collections;
import java.util.List;

import com.google.common.collect.Lists;

public class BinomialAlgorithm {
    public static Double calculateExpression(List<BinomialStructure> list) {
        Collections.sort(list);
        Double result = 0.0d;
        for (BinomialStructure s : list) {
            result += s.getCoefficient() * Math.pow(s.getBase(), s.getIndex());
        }
        return result;
    }

    public static void main(String[] args) {
        List<BinomialStructure> list = Lists.newArrayList();
        for (int i = 0; i < 6; i++) {
            BinomialStructure structure = new BinomialStructure();
            structure.setBase(2);
            structure.setCoefficient(1.0);
            structure.setIndex(i);

            list.add(structure);
        }

        System.out.println(calculateExpression(list));
    }
}
