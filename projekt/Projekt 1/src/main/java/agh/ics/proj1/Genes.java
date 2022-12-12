package agh.ics.proj1;
import java.util.ArrayList;
import java.util.Arrays;

public class Genes {
    public int[] genes;
    public int currGeneidx;
    private int size;
    private int biggestGene = 7;
    private boolean firstMutationVariant;
    private boolean firstBehaviorVariant;

    public Genes(int size, boolean firstMutationVariant, boolean firstBehaviorVariant){
        this.firstMutationVariant = firstMutationVariant;
        this.firstBehaviorVariant = firstBehaviorVariant;
        this.size = size;
        genes = new int[size];
        currGeneidx = (int)(Math.random() * (size + 1));
        for (int i = 0 ; i < size; i++)
            genes[i] = (int)(Math.random() * (biggestGene + 1));
//        Arrays.sort(genes);
    }
    public Genes (Genes g1, int g1Amount, Genes g2, int g2Amount, int size, boolean firstMutationVariant, boolean firstBehaviorVariantGene){
        this.size = size;
        genes = new int[size];
        this.firstMutationVariant = firstMutationVariant;
        this.firstBehaviorVariant = firstBehaviorVariant;
        int side = (int)(Math.random() * 2);
//        System.out.println(side);
        if (side == 0){
            for (int i = 0; i < g1Amount; i++)
                if (g1.getIndexGene(i) != -1)
                    genes[i] = g1.getIndexGene(i);

            for (int i = g1Amount; i < g1Amount + g2Amount; i++)
                if (g2.getIndexGene(i) != -1)
                    genes[i] = g2.getIndexGene(i);
        }
        else {
            for (int i = 0; i < g2Amount; i++)
                if (g2.getIndexGene(i) != -1)
                    genes[i] = g2.getIndexGene(i);

            for (int i = g2Amount; i < g1Amount + g2Amount; i++)
                if (g1.getIndexGene(i) != -1)
                    genes[i] = g1.getIndexGene(i);
        }
        int randomGenes = (int)(Math.random() * (size + 1));
//        System.out.println(randomGenes);
        if (firstMutationVariant){
            for (int i = 0; i < randomGenes; i++)
                genes[(int)(Math.random() * size)] = (int)(Math.random() * (biggestGene + 1));
//        Arrays.sort(genes);
        }
        else {
            for (int i = 0; i < randomGenes; i++) {
                int plusOrMinus = (int) (Math.random() * 2);
                if (plusOrMinus == 1)
                    genes[(int) (Math.random() * size)] = (genes[(int) (Math.random() * size)] + 1) % (biggestGene + 1);
                else  // odejmij 1
                    genes[(int) (Math.random() * size)] = (genes[(int) (Math.random() * size)] + biggestGene) % (biggestGene + 1);
            }
        }
        currGeneidx = (int)(Math.random() * (size + 1));
    }

    public int getGene(){
        if (firstBehaviorVariant) {
            currGeneidx = (currGeneidx + 1) % size;
            return genes[(currGeneidx + (size - 1) % size)];
        }
        else {
            int chooseOption = (int) (Math.random() * 10);
            if (chooseOption < 8){
                currGeneidx = (currGeneidx + 1) % size;
                return genes[(currGeneidx + (size - 1)) % size];
            }
            else {
                currGeneidx = ((int) (Math.random() * size) + 1) % size;
                return genes[(currGeneidx + (size - 1)) % size];
            }
        }
    }
    public int getRandom(){
        return genes[(int)(Math.random() * size)];
    }
    public int getIndexGene(int index){
        if (index >= this.size){
            return -1;
        }
        return genes[index];
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < size; i++)
            result.append(" ").append(genes[i]);
        return result.toString();
    }
}
