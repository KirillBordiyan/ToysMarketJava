package org.example.model.market;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.example.model.toy.Toy;

import java.util.*;

@Getter
@Setter
public class Market {
    List<Toy> possibleToys = new ArrayList<>();
    Queue<Toy> toys = new PriorityQueue<>();
    public static  final int QUEUE_SIZE = 10;

    public void addToy(String name, int dropChance){
        possibleToys.add(new Toy(possibleToys.size(), name, dropChance));
    }

    public void removeAll() {
        if (possibleToys.isEmpty()) {
            throw new NullPointerException("Список пуст");
        }else {
            possibleToys.clear();
        }
    }

    public void printAlL(){
        if(!possibleToys.isEmpty()){
            for (Toy toy: possibleToys){
                System.out.println(toy.toString());
            }
        }else {
            System.out.println("Вы ничего не добавили\n");
        }
    }

    public void fullFillQueue() throws NullPointerException{
        double fullDropChance = possibleToys.stream()
                .mapToInt(Toy::getDropChance)
                .sum();

        List<WeightedPair> list = new ArrayList<>();

        for (int i = 0; i < possibleToys.size(); i++){
            Toy toy = possibleToys.get(i);
            double currentChance = toy.getDropChance()/fullDropChance;
            double prevChance = i == 0 ? 0: list.get(i-1).weight;
            list.add(new WeightedPair(toy, currentChance+prevChance));
        }

        for(int i = 0; i < QUEUE_SIZE; i++){
            WeightedPair chosenToy = null;
            double currentChance = Math.random();
            for(int j = 0; j < list.size(); j++){

                var pair = list.get(j);
                double prevGap = j == 0 ? 0: list.get(j-1).weight;
                double nextGap = pair.weight;
                if(currentChance > prevGap && currentChance <= nextGap ){
                    chosenToy = pair;
                }

            }
            toys.add(chosenToy.toy);
        }
    }

    public Toy getToy(){
        return toys.poll();
    }

    @AllArgsConstructor
    public static class WeightedPair {
        Toy toy;
        double weight;

    }
}
