package es.beni.testing.exercise2.builders;

import android.graphics.Color;

import org.mockito.Mockito;

import es.beni.testing.exercise2.Car;
import es.beni.testing.exercise2.Engine;
import es.beni.testing.exercise2.Manufacter;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class CarBuilder {

    private static final String NAME_MANUFACTURER = "Ferrari";
    private static final int NUM_CYLENDER = 8111;
    private static final int COLOR_RED = Color.RED;

    private int numCylender = NUM_CYLENDER;
    private int colorCar = COLOR_RED;
    private String nameManufacturer = NAME_MANUFACTURER;

    public CarBuilder setNumCylender(int numCylender) {
        this.numCylender = numCylender;
        return this;
    }

    public CarBuilder setColorCar(int colorCar) {
        this.colorCar = colorCar;
        return this;
    }

    public CarBuilder setNameManufacturer(String nameManufacturer) {
        this.nameManufacturer = nameManufacturer;
        return this;
    }

    public static CarBuilder Builder() {
        return new CarBuilder();
    }

    public Car build() {
        Car carMock = Mockito.mock(Car.class);

        Manufacter manufacterMock = mock(Manufacter.class);
        Engine engineMock = mock(Engine.class);

        when(manufacterMock.getName()).thenReturn(nameManufacturer);
        when(engineMock.getNbOfCylinders()).thenReturn(numCylender);

        when(carMock.getManufacturer()).thenReturn(manufacterMock);
        when(carMock.getColor()).thenReturn(colorCar);
        when(carMock.getEngine()).thenReturn(engineMock);

        return carMock;
    }
}
