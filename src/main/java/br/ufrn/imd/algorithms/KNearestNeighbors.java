package br.ufrn.imd.algorithms;

import br.ufrn.imd.dataStructure.Stack;
import br.ufrn.imd.model.ImageTagged;
import br.ufrn.imd.service.LoadImageService;
import br.ufrn.imd.service.ParallelSearchAndStackService;
import lombok.*;


import java.io.File;
import java.util.*;

@Builder
public class KNearestNeighbors {

    private final Distance distance;

    private final Stack stack;

    private final String foodsFolder;

    private final String pathImage;

    public String classify() throws InterruptedException {

        LoadImageService imageService = new LoadImageService();
        ImageTagged imageToClassify = imageService.searchImage(pathImage, "");

        File file = new File(foodsFolder);

        Vector<Thread> threads = new Vector<>();

        for (File folder : Objects.requireNonNull(file.listFiles(File::isDirectory))) {
            for (File image : Objects.requireNonNull(folder.listFiles(File::isFile))) {
                ParallelSearchAndStackService service = ParallelSearchAndStackService.builder().stack(stack)
                                .image(imageToClassify)
                                .imagePath(image.getAbsolutePath())
                                .imageTag(folder.getName())
                                .distance(distance)
                                .build();

                Thread thread = new Thread(service);
                thread.start();
                threads.add(thread);
                if (threads.size() == 20){
                    for( Thread innerThread: threads)
                        innerThread.join();
                    threads.clear();
                }
            }
        }

        for (Thread thread: threads)
            thread.join();

        return stack.mostPopular();
    }

}
