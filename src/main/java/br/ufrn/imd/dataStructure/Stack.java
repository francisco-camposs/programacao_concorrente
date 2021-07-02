package br.ufrn.imd.dataStructure;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class Stack {

    private final StackNode[] array;

    private int full;

    public Stack(int size) {
        if (size <= 0)
            throw new ArrayStoreException();

        array = new StackNode[size];
        full = 0;
    }


    public void tryAdd(StackNode newNode) {
        if (full == array.length){
            if (newNode.compareTo(array[full-1]) < 0){
                array[full-1] = newNode;
                trySwap(full-2, full-1);
            }
        } else if (full > 0 ) {
            array[full] = newNode;
            this.trySwap(full-1, full);
            full++;
        } else {
            array[full] = newNode;
            full++;
        }
    }

    private void trySwap(int prev, int next) {
        if (prev >= 0){
            if (array[prev].compareTo(array[next]) > 0){
                StackNode aux = array[prev];
                array[prev] = array[next];
                array[next] = aux;
                trySwap(prev-1, prev);
            }
        }
    }


    public String mostPopular() {
        Map<String, Integer> occurrences = new HashMap<>();
        for (StackNode node: array){
            if (occurrences.containsKey(node.getTag())) {
                occurrences.put(node.getTag(), occurrences.get(node.getTag())+1);
            } else {
                occurrences.put(node.getTag(), 1);
            }
        }

        Integer max = Collections.max(occurrences.entrySet(), Map.Entry.comparingByValue()).getValue();
        Map<String, Integer> maximus = new HashMap<>();
        for (String key: occurrences.keySet()){
            if (occurrences.get(key).equals(max)){
                maximus.put(key, max);
            }
        }

        if (maximus.size() > 1){
            return array[0].getTag();
        }else{
            return (String) maximus.keySet().toArray()[0];
        }
    }

}
