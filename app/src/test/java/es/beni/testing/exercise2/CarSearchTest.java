package es.beni.testing.exercise2;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import es.beni.testing.exercise2.builders.CarBuilder;
import es.beni.testing.exercise2.builders.ObjectMotherCar;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class CarSearchTest {

    private CarSearch carSearch;
    private int numCylender;

    private List<Car> getCarList(Car... cars) {
        List<Car> carList = new ArrayList<>();
        for (Car car : cars) {
            carList.add(car);
        }
        return carList;
    }

    @Before
    public void setUp() throws Exception {
        carSearch = new CarSearch();
    }

    @Test
    public void shouldReturnAnEmptySportCarListWhenCarListIsEmpty() throws Exception {
        List<Car> sportCars = carSearch.findSportCars();

        assertThat(sportCars.size(), equalTo(0));
        assertEquals(0, sportCars.size());
        assertThat(sportCars, is(Collections.<Car>emptyList()));
    }

    @Test
    public void shouldVerifyAddCarIsNotCalledWhenCarListIsEmpty() throws Exception {
        carSearch.findSportCars();

        CarSearch carSearchSpy = spy(carSearch);

        verify(carSearchSpy, never()).addCar(any(Car.class));
    }

    @Test
    public void shouldReturnEmptySportCarListWhenAddNullCar() throws Exception {
        carSearch.addCar(null);

        List<Car> sportCars = carSearch.findSportCars();

        assertThat(sportCars.size(), equalTo(0));
    }

    @Test
    public void shouldReturnOneItemInListWhenAddedCarIsASportCar() throws Exception {
//        Car carMock = mock(Car.class);
//
//        Manufacter manufacterMock = mock(Manufacter.class);
//        Engine engineMock = mock(Engine.class);
//
//        String nameManufacturerSportCar = "Ferrari";
//        int cylenderSportCar = 1118;
//
//        when(manufacterMock.getName()).thenReturn(nameManufacturerSportCar);
//        when(engineMock.getNbOfCylinders()).thenReturn(cylenderSportCar);
//
//        when(carMock.getManufacturer()).thenReturn(manufacterMock);
//        when(carMock.getColor()).thenReturn(Color.RED);
//        when(carMock.getEngine()).thenReturn(engineMock);

        Car carMock = CarBuilder.Builder().build();

        carSearch.addCar(carMock);

        List<Car> sportCarList = carSearch.findSportCars();

        assertEquals(1, sportCarList.size());
        assertEquals(carMock, sportCarList.get(0));
    }

    @Test
    public void shouldReturnEmptyListWhenAddedCarIsANotSportCar() throws Exception {

        for (int i = 0; i < 10; i++) {
            if (i % 2 == 0) {
                carSearch.addCar(ObjectMotherCar.getNotSportCar());
            } else {
                carSearch.addCar(ObjectMotherCar.getSportCar());
            }
        }

        List<Car> sportCars = carSearch.findSportCars();

        assertEquals(5, sportCars.size());

    }


}