package org.course.typestypestypes.buildingpatterns.before;

/**
 * Below we are trying to construct an object that by design should have some parameters required and some optional
 *
 * Optional parameters in Java are a problem. They are simply not supported.
 *
 * That may lead to an implementation like below where the constructor takes 0 to some required parameters and then
 * the object is constructed with setters.
 *
 * Setters are a no-go for FP design, because they class with immutability of the object which is a highly desired property.
 *
 * What can we do?
 * */
public class Main {
    public static void main(String[] args) {
        NutritionFacts cocaCola2 = new NutritionFacts();

        cocaCola2.setServingSize(240); // oh oh, setters...
        cocaCola2.setServings(8);
        cocaCola2.setCalories(100);
        cocaCola2.setSodium(35);
        cocaCola2.setCarbohydrate(27);
        System.out.println(cocaCola2);
    }
}

