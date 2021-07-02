package br.ufrn.imd.algorithms;

import br.ufrn.imd.model.ImageTagged;

public class EuclideanDistance implements Distance{

    @Override
    public Double imageDistance(ImageTagged img1, ImageTagged img2) {

        if (img1.getFeatures().length != img2.getFeatures().length)
            return null;

        double distance = 0.0;
        long difference;

        for (int i = 0; i < img1.getFeatures().length; i++){
            difference = img1.getFeatures()[i] - img2.getFeatures()[i];
            distance +=   difference * difference;
        }

       return Math.sqrt(distance);
    }
}
