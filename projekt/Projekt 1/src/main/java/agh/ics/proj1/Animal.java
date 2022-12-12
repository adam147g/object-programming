package agh.ics.proj1;

import java.util.ArrayList;
import java.util.List;

public class Animal implements IMapElement{
    private MapDirection direction = MapDirection.NORTH;
    private int age = 0;
    private int deadDate = -1;
    public int children = 0;
    private boolean firstMutationVariantGene = true;
    private boolean firstBehaviorVariantGene = true;
    private boolean firstMapOption = true;
    private Vector2d position = new Vector2d(1,1);
    public IWorldMap map;
    public int energy = 10;
    private int startEnergy = 10;
    private double lostEnergy = 0.25;
    public Genes genes = new Genes(10, true, true);
    public Animal(IWorldMap map) {
        this.map = map;
    }
    public Animal(Vector2d position, IWorldMap map, boolean firstMapOption) {
        this.position = position;
        this.map = map;
        this.firstMapOption = firstMapOption;
    }
    public Animal(Vector2d position, int energy, IWorldMap map, boolean firstMapOption) {
        this.position = position;
        this.startEnergy = energy;
        this.energy = energy;
        this.map = map;
        this.firstMapOption = firstMapOption;
    }

    public Animal(Vector2d position, int energy, int sizeGenes, IWorldMap map, boolean firstMapOption) {
        this.position = position;
        this.startEnergy = energy;
        this.energy = energy;
        genes = new Genes(sizeGenes, firstMutationVariantGene, firstBehaviorVariantGene);
        this.map = map;
        this.firstMapOption = firstMapOption;
    }

    public Animal(Vector2d position, int energy, Genes genes, IWorldMap map, boolean firstMapOption) {
        this.position = position;
        this.startEnergy = energy;
        this.energy = energy;
        this.genes = genes;
        this.map = map;
        this.firstMapOption = firstMapOption;
    }

    public int getAge(){return age;}
    public int getDeadDate(){return deadDate;}
    public void setDeadDate(int date){deadDate = date;}
    public void addAge(){age += 1;}
    public void addEnergy (int energyToAdd){
        this.energy += energyToAdd;
    }

    @Override
    public Vector2d getPosition() {
        return this.position;
    }

    public MapDirection getDirection() {
        return this.direction;
    }

    @Override
    public String toString() {
        return this.position.toString() + direction.toString() + " energia: " + this.energy ;
    }
    public void rotate(int where){
        for (int i = 0; i < where; i++)
            this.direction = this.direction.next();
    }
    public void move(){
        this.map.removeAnimal(this);
        rotate(genes.getGene());
//      do naprawienia - dodać granice mapy
        Vector2d topRight = this.map.getTopRight();
        Vector2d bottomLeft = this.map.getBottomLeft();

        if (firstMapOption){
            if (this.position.y + this.direction.toUnitVector().y > topRight.y ||
                    this.position.y + this.direction.toUnitVector().y < bottomLeft.y){
                rotate(4);
            }
            else {
                this.position = new Vector2d((this.position.x + this.direction.toUnitVector().x + topRight.x) % topRight.x,
                        (this.position.y + this.direction.toUnitVector().y + topRight.y) % topRight.y);
            }
        }
        else {
            if (this.position.y + this.direction.toUnitVector().y > topRight.y ||
                    this.position.y + this.direction.toUnitVector().y < bottomLeft.y ||
                    this.position.x + this.direction.toUnitVector().x > topRight.x ||
                    this.position.x + this.direction.toUnitVector().x < bottomLeft.x){
            this.position = new Vector2d((int)(Math.random() * (topRight.x + 1)), (int)(Math.random() * (topRight.y + 1)));
            this.energy = (int) ((1 - this.lostEnergy) * this.energy);
            }
        }
        this.map.place(this);
    }
}
