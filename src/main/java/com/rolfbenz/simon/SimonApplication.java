package com.rolfbenz.simon;

import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

import static com.rolfbenz.simon.Car.*;

class PassengerCountOrder implements Comparator<Car> {

	@Override
	public int compare(Car o1, Car o2) {
		return o1.getPassengers().size() - o2.getPassengers().size();
	}
}


interface CarCriterion{
	boolean test(Car c);
}


@SpringBootApplication
public class SimonApplication {

	public static List<Car> getSelectCars(Iterable<Car> in, CarCriterion carCriterion) {
		List<Car> out = new ArrayList<>();
		for (Car c : in ) {
			if (carCriterion.test(c)) {
				out.add(c);
			}
		}
		return out;
	}

	public static List<Car> getColoredCars(Iterable<Car> in, String color) {
		List<Car> out = new ArrayList<>();
		for (Car c : in ) {
			if (c.getColor().equalsIgnoreCase(color)) {
				out.add(c);
			}
		}
		return out;
	}

	public static List<Car> getCarsByGasLevel(Iterable<Car> in, int gasLevel) {
		List<Car> out = new ArrayList<>();
		for (Car c : in ) {
			if (c.getGasLevel()<=gasLevel) {
				out.add(c);
			}
		}
		return out;
	}

	public static void showAll(List<Car> lc) {
		for (Car c : lc) {
			System.out.println(c);
		}
		System.out.println("----------------------------------------------------------------------------------------");
	}

	public static void main(String[] args) {

		System.out.println("main");

		List<Car> cars = Arrays.asList(
				withGasColorPassengers(6, "Red", "Fred", "Jim", "Sheila"),
				withGasColorPassengers(3, "Octarine", "Rincewind", "Ridcully"),
				withGasColorPassengers(9, "Black", "Weatherwax", "Magrat"),
				withGasColorPassengers(7, "Green", "Valentine", "Gillian", "Anne", "Dr. Mahmoud"),
				withGasColorPassengers(6, "Red", "Ender", "Hyrum", "Locke", "Bonza")
		);

		showAll(cars);
//		showAll(getSelectCars(cars, new Car.NumberOfPassengerCriterion(4)));

		showAll(getSelectCars(cars, Car.getRedCarCriterion()));
		showAll(getSelectCars(cars, Car.getGasLevelCarCriterion(6)));

		//		showAll(getColoredCars(cars, "red"));
//		showAll(getCarsByGasLevel(cars, 7));
//		cars.sort(new PassengerCountOrder());
//		showAll(getSelectCars(cars, new GasLevelCarCriterion(7)));
		showAll(cars);

		//		SpringApplication.run(SimonApplication.class, args);
	}

}
