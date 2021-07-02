package br.ufrn.imd.dataStructure;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class StackNode{

    private String tag;

    private Double distance;


    public int compareTo(StackNode node) {
        double difference = this.distance - node.getDistance();
        if (difference == 0)
            return 0;
        else
            return difference > 0 ? 1 : -1;
    }

}
