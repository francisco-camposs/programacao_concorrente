package br.ufrn.imd.algorithms;

import br.ufrn.imd.model.ImageTagged;

public interface Distance {

    Double imageDistance(ImageTagged img1, ImageTagged img2);

}
