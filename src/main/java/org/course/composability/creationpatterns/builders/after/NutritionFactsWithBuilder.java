package org.course.composability.creationpatterns.builders.after;

// Using the builder pattern
public final class NutritionFactsWithBuilder {
    private final int servingSize;
    private final int servings;
    private final int calories;
    private final int fat;
    private final int sodium;
    private final int carbohydrate;

    public static class Builder {
        // Required parameters
        private final int servingSize;
        private final int servings;
        // Optional parameters - initialized to default values
        private int calories = 0;
        private int fat = 0;
        private int sodium = 0;
        private int carbohydrate = 0;

        public Builder(final int servingSize,
                       final int servings) {
            this.servingSize = servingSize;
            this.servings = servings;
        }

        public Builder calories(final int val) {
            calories = val;
            return this;
        }

        public Builder fat(final int val) {
            fat = val;
            return this;
        }

        public Builder sodium(final int val) {
            sodium = val;
            return this;
        }

        public Builder carbohydrate(final int val) {
            carbohydrate = val;
            return this;
        }

        public NutritionFactsWithBuilder build() {
            return new NutritionFactsWithBuilder(this);
        }
    }

    private NutritionFactsWithBuilder(final Builder builder) {
        servingSize = builder.servingSize;
        servings = builder.servings;
        calories = builder.calories;
        fat = builder.fat;
        sodium = builder.sodium;
        carbohydrate = builder.carbohydrate;
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
