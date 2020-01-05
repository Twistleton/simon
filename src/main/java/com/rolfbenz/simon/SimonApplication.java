package com.rolfbenz.simon;

import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.rolfbenz.simon.Car.withGasColorPassengers;

@FunctionalInterface
interface Criterion<E> {
	boolean test(E c);
}

@SpringBootApplication
public class SimonApplication {

	public static <E> List<E> getByCriterion(Iterable<E> in, Criterion<E> criterion) {
		List<E> out = new ArrayList<>();
		for (E c : in ) {
			if (criterion.test(c)) {
				out.add(c);
			}
		}
		return out;
	}

	public static <E> void showAll(List<E> lc) {
		for (E c : lc) {
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

		showAll(getByCriterion(cars, Car.getRedCarCriterion()));
		showAll(getByCriterion(cars, Car.getGasLevelCarCriterion(6)));
		showAll(getByCriterion(cars, Car.getPassengerByNameCriterion("Gillian")));

		//		showAll(getColoredCars(cars, "red"));
//		showAll(getCarsByGasLevel(cars, 7));
//		cars.sort(new Car.PassengerCountOrder());
//		showAll(getSelectCars(cars, new GasLevelCarCriterion(7)));

		showAll(cars);
		cars.sort(Car.getGasComparator());
		showAll(cars);
//		showAll(getByCriterion(cars, Car.getFourPassengerCriterion()));
		showAll(cars);
		showAll(getByCriterion(cars, c -> c.getColor().equalsIgnoreCase("Green")));

//		Criterion blackCriterion = (Car c) -> c.getColor().equalsIgnoreCase("Black");

//		showAll(getByCriterion(cars, blackCriterion));

//		boolean test = ((Criterion) (c -> c.getColor().equalsIgnoreCase("Blue"))).test(withGasColorPassengers(0, "Blue"));

//		System.out.println(test);


		showAll(cars);
		showAll(getByCriterion(cars, (Car c) -> c.getPassengers().contains("Locke")));

		//		SpringApplication.run(SimonApplication.class, args);
	}

}
