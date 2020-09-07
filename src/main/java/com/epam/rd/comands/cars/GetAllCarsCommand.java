package com.epam.rd.comands.cars;

import com.epam.rd.comands.ActionCommand;
import com.epam.rd.converter.CarConverter;
import com.epam.rd.dto.CarDto;
import com.epam.rd.models.Car;
import com.epam.rd.resource.ConfigManager;
import com.epam.rd.services.impl.SimpleServiceFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Comparator;
import java.util.List;

public class GetAllCarsCommand implements ActionCommand {

    private final Logger logger = LoggerFactory.getLogger(GetAllCarsCommand.class);

    @Override
    public String execute(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session.getAttribute("role").equals("ADMIN")) {
            String type = request.getParameter("carType");
            String brand = request.getParameter("carBrand");

            List<String> types = SimpleServiceFactory.getServiceFactory().getCarService().getCarTypes();
            List<String> brands = SimpleServiceFactory.getServiceFactory().getCarService().getCarBrand();
            List<CarDto> cars = CarConverter.carsView(getCars(brand, type));

            String sortReq = request.getParameter("sort");

            if (sortReq != null && sortReq.equals("name")) {
                cars = sortByName(cars);
            }
            session.setAttribute("cars", cars);
            session.setAttribute("carTypes", types);
            session.setAttribute("carBrands", brands);
            logger.info("Get all cars command finished.");
            return ConfigManager.getProperty("path.page.cars");
        } else return ConfigManager.getProperty("path.page.index");
    }


    private List<CarDto> sortByName(List<CarDto> cars) {
        logger.info("Cars sorting by name.");
        Comparator<CarDto> nameComparator = Comparator.comparing(CarDto::getBrand);
        cars.sort(nameComparator);
        return cars;
    }

    private List<Car> getCars(String brand, String type) {
        List<Car> cars;
        if (type != null) {
            logger.info(String.format("Getting all cars with type %s.", type));
            cars = SimpleServiceFactory.getServiceFactory().getCarService().getAllWithType(type);
        } else if (brand != null) {
            logger.info(String.format("Getting all cars with make %s.", brand));
            cars = SimpleServiceFactory.getServiceFactory().getCarService().getAllWithBrand(brand);
        } else {
            logger.info("Getting all cars by default.");
            cars = SimpleServiceFactory.getServiceFactory().getCarService()
                    .getAll();
        }
        return cars;
    }

}
