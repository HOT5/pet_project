package com.epam.rd.converter;

import com.epam.rd.dto.CarDto;
import com.epam.rd.models.Car;

import java.util.ArrayList;
import java.util.List;

public class CarConverter {

    public static List<CarDto> carsView(List<Car> cars) {
        List<CarDto> carsDto = new ArrayList<>();

        for (Car car : cars) {
            carsDto.add(new CarDto(car));
        }

        return carsDto;
    }

}
