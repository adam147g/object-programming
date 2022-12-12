package agh.ics.proj1;


import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;


public class World {
    public static void main(String[] args) {
        System.out.println("Start");
        WorldMap map = new WorldMap(100,100,true);
        System.out.println(map);
        WorldMap map2 = new WorldMap(100,100,false);
        System.out.println(map2);
        System.out.println("Stop");
    }
}
