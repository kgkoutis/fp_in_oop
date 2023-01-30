package org.course.typestypestypes.buildingpatterns.before;

// JavaBeans Pattern - allows inconsistency, mandates mutability
public class NutritionFacts {
    // Parameters initialized to default values (if any)
    private int servingSize = -1; // Required; no default value
    private int servings = -1; // Required; no default value
    private int calories = 0;
    private int fat = 0;
    private int sodium = 0;
    private int carbohydrate = 0;

    public NutritionFacts() {
    }

    // Setters
    public void setServingSize(final int val) {
        servingSize = val;
    }

    public void setServings(final int val) {
        servings = val;
    }

    public void setCalories(final int val) {
        calories = val;
    }

    public void setFat(final int val) {
        fat = val;
    }

    public void setSodium(final int val) {
        sodium = val;
    }

    public void setCarbohydrate(final int val) {
        carbohydrate = val;
    }

    @Override
    public String toString() {
        return "NutritionFacts2{" +
                "servingSize=" + servingSize +
                ", servings=" + servings +
                ", calories=" + calories +
                ", fat=" + fat +
                ", sodium=" + sodium +
                ", carbohydrate=" + carbohydrate +
                '}';
    }
}
