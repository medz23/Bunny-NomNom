package com.example.bunnynomnom;

public class MealItem {
    private String mealRatio;
    private String mealType;


    public MealItem(String mealRatio, String mealType) {
        this.mealRatio = mealRatio;
        this.mealType = mealType;
    }

    public String getMealRatio() {
        return mealRatio;
    }

    public void setMealRatio(String mealRatio) {
        this.mealRatio = mealRatio;
    }

    public String getMealType() {
        return mealType;
    }

    public void setMealType(String mealType) {
        this.mealType = mealType;
    }

    @Override
    public String toString() {
        return "MealItem{" +
                "mealRatio='" + mealRatio + '\'' +
                ", mealType='" + mealType + '\'' +
                '}';
    }
}
