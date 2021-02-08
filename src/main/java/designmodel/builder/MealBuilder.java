package designmodel.builder;

import designmodel.builder.itementity.ChickenBurger;
import designmodel.builder.itementity.Coke;
import designmodel.builder.itementity.Pepsi;
import designmodel.builder.itementity.VegBurger;

public class MealBuilder {

   public Meal prepareVegMeal (){
      Meal meal = new Meal();
      meal.addItem(new VegBurger());
      meal.addItem(new Coke());
      return meal;
   }   
 
   public Meal prepareNonVegMeal (){
      Meal meal = new Meal();
      meal.addItem(new ChickenBurger());
      meal.addItem(new Pepsi());
      return meal;
   }
}