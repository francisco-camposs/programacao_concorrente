package br.ufrn.imd.algorithms;

import br.ufrn.imd.dataStructure.Stack;
import br.ufrn.imd.dataStructure.StackNode;
import br.ufrn.imd.model.ImageTagged;
import br.ufrn.imd.service.LoadImage;
import lombok.*;


import java.io.File;
import java.util.Objects;

@Builder
@NoArgsConstructor
public class KNearestNeighbors {

    private Distance distance;

    private Stack stack;

    private String foodsFolder;

    private String pathImage;

    public String classify(){

        LoadImage loadImage = new LoadImage(pathImage, "");
        ImageTagged imageToClassify = loadImage.searchImage();

        File file = new File(foodsFolder);
        for (File folder : Objects.requireNonNull(file.listFiles(File::isDirectory))){
            for (File image : Objects.requireNonNull(folder.listFiles(File::isFile))){
                LoadImage loadImageData = new LoadImage(image.getAbsolutePath(), folder.getName());
                ImageTagged imageData = loadImageData.searchImage();
                StackNode stackNode = new StackNode(folder.getName(), distance.imageDistance(imageToClassify, imageData));
                stack.tryAdd(stackNode);
            }
        }

        return stack.mostPopular();
    }

}
