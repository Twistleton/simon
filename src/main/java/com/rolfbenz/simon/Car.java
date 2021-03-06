package com.rolfbenz.simon;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Car {

    private final int gasLevel;
    private final String color;
    private final List<String> passengers;
    private final List<String> trunkContents;

    private Car(int gasLevel, String color, List<String> passengers, List<String> trunkContents) {
        this.gasLevel = gasLevel;
        this.color = color;
        this.passengers = passengers;
        this.trunkContents = trunkContents;
    }

    public static Car withGasColorPassengers(int gas, String color, String... passengers) {
        List<String> p = Collections.unmodifiableList(Arrays.asList(passengers));
        Car self = new Car(gas, color, p, null);
        return self;
    }

    public static Car withGasColorPassengersAndTrunk(int gas, String color, String... passengers) {
        List<String> p = Collections.unmodifiableList(Arrays.asList(passengers));
        Car self = new Car(gas, color, p, Arrays.asList("bucket", "wrench", "spare wheel"));
        return self;
    }

    public int getGasLevel() {
        return gasLevel;
    }

    public String getColor() {
        return color;
    }

    public List<String> getPassengers() {
        return passengers;
    }

    public List<String> getTrunkContents() {
        return trunkContents;
    }

    @Override
    public String toString() {
        return "Car{" +
                "gasLevel=" + gasLevel +
                ", color='" + color + '\'' +
                ", passengers=" + passengers +
                (trunkContents != null ? "trunkContents=" + trunkContents : " no trunk");
    }

    public static Criterion<Car> getRedCarCriterion() {
//        return new RedCarCriterion();            // return a new object
        return RED_CAR_CRITERION;                // return a singleton
    }

//    private static final CarCriterion RED_CAR_CRITERION = new /* RedCarCriterion();
//
//    private static class RedCarCriterion implements */ CarCriterion() {
//
//        @Override
//        public boolean test(Car c) {
//            return c.color.equalsIgnoreCase("Red");
//        }
//    };

    // anonymous inner class
//    private static final CarCriterion RED_CAR_CRITERION = new CarCriterion() {
//        @Override
//        public boolean test(Car c) {
//            return c.color.equalsIgnoreCase("Red");
//        }
//    };

    // lambda expression
    private static final Criterion<Car> RED_CAR_CRITERION = c -> c.color.equalsIgnoreCase("Red");

    public static Criterion<Car> getGasLevelCarCriterion(int threshold) {
        return new GasLevelCriterion(threshold);
    }

    private static class GasLevelCriterion implements Criterion<Car> {

        private int threshold;

        public GasLevelCriterion(int threshold) {
            this.threshold = threshold;
        }

        @Override
        public boolean test(Car c) {
            return c.gasLevel >= threshold;
        }
    }

    static class NumberOfPassengerCriterion implements Criterion<Car> {

        private int passengerNumber;

        public NumberOfPassengerCriterion(int passengerNumber) {
            this.passengerNumber = passengerNumber;
        }

        @Override
        public boolean test(Car c) {
            return c.passengers.size()==passengerNumber;
        }
    }

    public static Criterion<Car> getPassengerByNameCriterion(String name) {
        return new PassengerByNameCriterion(name);
    }

    private static class PassengerByNameCriterion implements Criterion<Car> {

        private String name;

        public PassengerByNameCriterion(String name) {
            this.name = name;
        }

        @Override
        public boolean test(Car c) {
            return c.getPassengers().contains(name);
        }
    }

    class PassengerCountOrder implements Comparator<Car> {

        @Override
        public int compare(Car o1, Car o2) {
            return o1.getPassengers().size() - o2.getPassengers().size();
        }
    }

    public static Comparator<Car> getGasComparator() {
      return gasComparator;
    }
//
//    private static class CarGasComparator implements Comparator<Car> {
//        @Override
//        public int compare(Car o1, Car o2) {
//            return o1.gasLevel - o2.getGasLevel();
//        }*
//    }

    // anonymous inner class
//    public static Comparator<Car> getCarGasComparator = new Comparator<Car>() {
//        @Override
//        public int compare(Car o1, Car o2) {
//            return o1.gasLevel - o2.getGasLevel();
//        }
//    };

    // lambda expression
    private static final Comparator<Car> gasComparator = (Car o1, Car o2) -> o1.gasLevel - o2.gasLevel;

//    public static Criterion<Car> getFourPassengerCriterion() {
//        return c -> c.getPassengers.size() == 4;
//    }

}


