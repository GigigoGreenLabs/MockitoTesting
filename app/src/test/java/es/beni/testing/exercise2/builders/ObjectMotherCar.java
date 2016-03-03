package es.beni.testing.exercise2.builders;

import android.graphics.Color;

import es.beni.testing.exercise2.Car;

public class ObjectMotherCar {

    public static Car getSportCar() {
        return CarBuilder.Builder().build();
    }

    public static Car getNotSportCar() {
        return CarBuilder.Builder().setColorCar(Color.BLACK).build();
    }
}
