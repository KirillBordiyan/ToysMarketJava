package org.example.model.toy;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Toy implements Comparable<Toy>{

    int id;
    String name;
    int dropChance;

    public Toy(int id, String name, int dropChance){
        this.id = id;
        this.name = name;
        this.dropChance = dropChance;
    }

    @Override
    public int compareTo(Toy o) {
        if(this.getDropChance() == o.dropChance){
            return 0;
        }
        return this.dropChance < o.dropChance ? 1: -1;
    }

    @Override
    public String toString() {
        return "Toy:" +
                " name = " + name +
                ", dropChance =" + dropChance;
    }
}
