package com.rolfbenz.simon;

import com.rolfbenz.simon.model.Car;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

class PassengerCountOrder implements Comparator<Car> {

	@Override
	public int compare(Car o1, Car o2) {
		return o1.getPassengers().size() - o2.getPassengers().size();
	}
}

interface CarCriterion{
	boolean test(Car c);
}

class ColorCarCriterion implements CarCriterion {

	private String selectedColor;

	public ColorCarCriterion(String selectedColor) {
		this.selectedColor = selectedColor;
	}

	@Override
	public boolean test(Car c) {
		return c.getColor().equalsIgnoreCase(selectedColor);
	}
}

class GasLevelCarCriterion implements CarCriterion {

	private int gasLevel;

	public GasLevelCarCriterion(int gasLevel) {
		this.gasLevel = gasLevel;
	}

	@Override
	public boolean test(Car c) {
		if (c.getGasLevel()<=gasLevel) {
			return true;
		} else                         {
			return false;
		}

	}

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
				Car.withGasColorPassengers(6, "Red", "Fred", "Jim", "Sheila"),
				Car.withGasColorPassengers(3, "Octarine", "Rincewind", "Ridcully"),
				Car.withGasColorPassengers(9, "Black", "Weatherwax", "Magrat"),
				Car.withGasColorPassengers(7, "Green", "Valentine", "Gillian", "Anne", "Dr. Mahmoud"),
				Car.withGasColorPassengers(6, "Red", "Ender", "Hyrum", "Locke", "Bonza")
		);

		showAll(cars);
//		showAll(getSelectCars(cars, new ColorCarCriterion("Green")));
//		showAll(getColoredCars(cars, "red"));
//		showAll(getCarsByGasLevel(cars, 7));
//		cars.sort(new PassengerCountOrder());
		showAll(getSelectCars(cars, new GasLevelCarCriterion(7)));
		showAll(cars);

		//		SpringApplication.run(SimonApplication.class, args);
	}

}
