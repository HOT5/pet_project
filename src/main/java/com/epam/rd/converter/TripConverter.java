package com.epam.rd.converter;

import com.epam.rd.dto.TripDto;
import com.epam.rd.models.Trip;
import com.epam.rd.dto.TripDto;

import java.util.ArrayList;
import java.util.List;

public class TripConverter {

    public static List<TripDto> tipsView(List<Trip> trips) {
        List<TripDto> tripsDto = new ArrayList<>();

        for (Trip trip : trips) {
            tripsDto.add(new TripDto(trip));
        }

        return tripsDto;
    }

}
