package br.ufrn.imd;

import br.ufrn.imd.algorithms.Distance;
import br.ufrn.imd.algorithms.EuclideanDistance;
import br.ufrn.imd.algorithms.KNearestNeighbors;
import br.ufrn.imd.dataStructure.Stack;
import org.opencv.core.Core;

public class Main {

    public static void main(String[] args) {

        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);

        KNearestNeighbors algorithm = KNearestNeighbors.builder()
                                                        .foodsFolder("/home/francisco/Documents/projects/Programacao_concorrente/foods/images")
                                                        .distance(new EuclideanDistance())
                                                        .stack(new Stack(100))
                                                        .pathImage("/home/francisco/Documents/projects/Programacao_concorrente/foods/test/torta-de-maca-740x463-1.png")
                                                        .build();

        String result = algorithm.classify();

        System.out.println(result);
    }

}
