package org.course.typestypestypes.buildingpatterns.after;

public class NutritionFactsTelescopingConstructor {
    private final int servingSize; // (mL) required
    private final int servings; // (per container) required
    private final int calories; // (per serving) optional
    private final int fat; // (g/serving) optional
    private final int sodium; // (mg/serving) optional
    private final int carbohydrate; // (g/serving) optional

    public NutritionFactsTelescopingConstructor(final int servingSize,
                                                final int servings) {
        this(servingSize, servings, 0);
    }

    public NutritionFactsTelescopingConstructor(final int servingSize,
                                                final int servings,
                                                final int calories) {
        this(servingSize, servings, calories, 0);
    }

    public NutritionFactsTelescopingConstructor(final int servingSize,
                                                final int servings,
                                                final int calories,
                                                final int fat) {
        this(servingSize, servings, calories, fat, 0);
    }

    public NutritionFactsTelescopingConstructor(final int servingSize,
                                                final int servings,
                                                final int calories,
                                                final int fat,
                                                final int sodium) {
        this(servingSize, servings, calories, fat, sodium, 0);
    }

    public NutritionFactsTelescopingConstructor(final int servingSize,
                                                final int servings,
                                                final int calories,
                                                final int fat,
                                                final int sodium,
                                                final int carbohydrate) {
        this.servingSize = servingSize;
        this.servings = servings;
        this.calories = calories;
        this.fat = fat;
        this.sodium = sodium;
        this.carbohydrate = carbohydrate;
    }

    @Override
    public String toString() {
        return "NutritionFacts{" +
                "servingSize=" + servingSize +
                ", servings=" + servings +
                ", calories=" + calories +
                ", fat=" + fat +
                ", sodium=" + sodium +
                ", carbohydrate=" + carbohydrate +
                '}';
    }
}
