package agh.ics.proj1;

import java.util.List;

public class SimultationEngine implements Runnable{
    public List<Animal> animals;
    private final int delay;

    public SimultationEngine(int delay) {
        this.delay = delay;
    }

    @Override
    public void run() {

    }
}
