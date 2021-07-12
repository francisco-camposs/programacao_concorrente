package br.ufrn.imd.service;

import br.ufrn.imd.algorithms.Distance;
import br.ufrn.imd.dataStructure.Stack;
import br.ufrn.imd.dataStructure.StackNode;
import br.ufrn.imd.model.ImageTagged;
import lombok.Builder;

@Builder
public class ParallelSearchAndStackService implements Runnable {

    private final String imagePath;

    private final String imageTag;

    private final Stack stack;

    private final Distance distance;

    private final ImageTagged image;

    @Override
    public void run() {

        LoadImageService imageService = new LoadImageService();
        ImageTagged imageData = imageService.searchImage(imagePath, imageTag);
        StackNode node = new StackNode(imageTag, distance.imageDistance(image, imageData));
        try {
            stack.tryAdd(node);
        } catch (InterruptedException e) {
            System.out.println("An error occurred while adding stack operation");
            e.printStackTrace();
        }
    }

}
