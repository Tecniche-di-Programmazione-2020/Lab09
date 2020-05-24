package it.polito.tdp.borders.model;

import java.util.List;

public class TestModel {

	public static void main(String[] args) {

		Model model = new Model();

		System.out.println("TestModel -- TODO");
		
		System.out.println("Creo il grafo relativo al 2000");
		model.calcolaConfini(2000);
		
		List<Country> countries = model.getCountries();
		System.out.format("Trovate %d nazioni\n", countries.size());

		System.out.format("Numero componenti connesse: %d\n", model.getNumberOfConnectedComponents());
		
		System.out.format("Pesi raggiunti: ");
		for(Country c: model.getCountriesReachable(new Country(0,"0","0")))
			System.out.println(c.toString());
		
		//for (Country country : model.getCountriesDegree())
		//	System.out.format(country.toString());		
		
	}

}
