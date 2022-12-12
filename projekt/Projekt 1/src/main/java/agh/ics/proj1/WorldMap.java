package agh.ics.proj1;

import java.util.*;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import static java.util.stream.Collectors.*;
import static java.util.Map.Entry.*;


public class WorldMap implements IWorldMap{
    private final Vector2d topRight;
    private Vector2d bottomLeft = new Vector2d(0,0);
    private boolean firstVariant = true;
    private final Vector2d topRightJungle;
    private final Vector2d bottomLeftJungle;
    public Map<Vector2d, ArrayList<Animal>> animals = new HashMap<>();
    public Map<Vector2d, Grass> grasses = new HashMap<>();
    private Map<Vector2d, Integer> deadPlaces = new HashMap<>();
    private int animalAmount = 0;
    private int grassAmount = 0;

    public WorldMap (){
        topRight = new Vector2d(100,100);
        bottomLeft = new Vector2d(0,0);
        topRightJungle = new Vector2d(100,60);
        bottomLeftJungle = new Vector2d(0,40);
    }

    public WorldMap (int mapWidth, int mapHeight, boolean firstVariant){
        if (firstVariant) {
            this.topRight = new Vector2d(mapWidth, mapHeight);
            int jungleHeight = (int)(0.2*mapHeight);
            int heightDiff = mapHeight - jungleHeight;
            this.bottomLeftJungle = new Vector2d(0, heightDiff / 2);
            this.topRightJungle = new Vector2d(mapWidth, (heightDiff / 2) + jungleHeight);
        }
        else {
            this.firstVariant = firstVariant;
            this.topRight = new Vector2d(mapWidth, mapHeight);
            this.bottomLeftJungle = new Vector2d(-1,-1);
            this.topRightJungle = new Vector2d(-1,-1);
        }
    }
    public String toString(){
        if (firstVariant)
            return "Zalesione równiki " + bottomLeft + bottomLeftJungle + topRightJungle + topRight;
        else
            return "Toksyczne trupy " + bottomLeft + topRight;
    }


    public int getAnimalAmount(){return animalAmount;}
    public int getGrassAmount(){return grassAmount;}
    public Vector2d getTopRight(){return topRight;}
    public Vector2d getBottomLeft(){return bottomLeft;}

    @Override
    public boolean canMoveTo(Vector2d position) {
//        if (firstVariant){}
//        else {}
        return false;
    }

    @Override
    public boolean place(Animal animal) {
        // działa
        if (!animals.containsKey(animal.getPosition())){
            ArrayList<Animal> newList = new ArrayList<Animal>();
            newList.add(animal);
            animals.put(animal.getPosition(), newList);
        }
        else {
            ArrayList<Animal> newList = animals.get(animal.getPosition());
            newList.add(animal);
            animals.remove(animal.getPosition());
            animals.put(animal.getPosition(), newList);
        }
        animalAmount += 1;
        return true;
    }
    public boolean placeGrassAt(Vector2d position){
        if (grasses.containsKey(position))
            return false;
        else {
            grasses.put(position, new Grass(position));
            grassAmount += 1;
            return true;
        }
    }
    public boolean placeGrass(Vector2d lowerLeft, Vector2d upperRight) {
        Vector2d randomVect = new Vector2d((int) (Math.random() * (upperRight.x - lowerLeft.x + 1) + lowerLeft.x),
                (int) (Math.random() * (upperRight.y - lowerLeft.y + 1) + lowerLeft.y));
//        System.out.println(grassToAdd.getPosition());
        return placeGrassAt(randomVect);
    }
    public boolean removeAnimal(Animal animal){
        // działa
//        animal.setDeadDate(date);
        if (animals.containsKey(animal.getPosition())) {
            ArrayList<Animal> animalsList = animals.get(animal.getPosition());
            if (animalsList.size() == 1)
                animals.remove(animal.getPosition());
            else {
                animalsList.remove(animal);
                animals.remove(animal.getPosition());
                animals.put(animal.getPosition(), animalsList);
            }
        }
        animalAmount -= 1;
        return true;
    }
    public void removeDead(Animal animal, int day){
        removeAnimal(animal);
        animal.setDeadDate(day);
        int value = 0;
        if (deadPlaces.containsKey(animal.getPosition())){
            value = deadPlaces.get(animal.getPosition());
            deadPlaces.remove(animal.getPosition());
        }
        deadPlaces.put(animal.getPosition(), value + 1);
        deadPlaces = deadPlaces
                .entrySet()
                .stream()
                .sorted(Collections.reverseOrder(Map.Entry.comparingByValue()))
                .collect(toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e2, LinkedHashMap::new));
//        System.out.println(deadPlaces);
    }

    public void animalMapToString(){
        // działa
        for (Vector2d i : animals.keySet()){
            System.out.print(i + " - ");
            ArrayList<Animal> iterate = animals.get(i);
            for (Animal animal : iterate) {
                System.out.print(animal.toString() + ", ");
            }
            System.out.println();
        }
    }

    @Override
    public boolean isOccupied(Vector2d position) {
        if (animals.containsKey(position))
            return true;
        else return grasses.containsKey(position);
    }

    @Override
    public Object objectAt(Vector2d position) {
        if (animals.containsKey(position)){
            ArrayList<Animal> iterate = animals.get(position);
            Animal mostPowerfulAnimal = iterate.get(0);
            for (Animal animal : iterate) {
                if (animal.energy > mostPowerfulAnimal.energy)
                    mostPowerfulAnimal = animal;
            }
            return mostPowerfulAnimal;
        } else if (grasses.containsKey(position)) {
            return grasses.get(position);
        }
        return null;
    }
}
