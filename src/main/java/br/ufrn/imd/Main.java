package br.ufrn.imd;

import br.ufrn.imd.algorithms.Distance;
import br.ufrn.imd.algorithms.EuclideanDistance;
import br.ufrn.imd.algorithms.KNearestNeighbors;
import org.opencv.core.Core;

public class Main {

    public static void main(String[] args) {

        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);

        Distance distance = new EuclideanDistance();
        KNearestNeighbors algorithm = new KNearestNeighbors(100,"/home/francisco/Documents/projects/foods/test/Apple-Pie-1360x2040.jpg" ,"/home/francisco/Documents/projects/foods/images", distance);
        String result = algorithm.classify();

        System.out.println(result);
    }

}
