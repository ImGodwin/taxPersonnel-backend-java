package Godwin.taxSolution.controllers;

import Godwin.taxSolution.entities.City;
import Godwin.taxSolution.exceptions.BadRequestException;
import Godwin.taxSolution.payloads.CityDTO;
import Godwin.taxSolution.service.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/")
public class CityController {

    @Autowired
    private CityService cityService;

    @GetMapping("")
    public Page<City> getCities(@RequestParam(defaultValue = "0") int page,
                                @RequestParam(defaultValue = "15") int size,
                                @RequestParam(defaultValue = "id") String orderBy){
        return cityService.getAllCities(page, size, orderBy);
    }

    @GetMapping(value = "/{id}")
    public City findById(@PathVariable UUID id) {
        return cityService.findById(id);
    }

    @PostMapping("")
    @ResponseStatus(HttpStatus.CREATED)
    public City saveNewCity(@RequestBody @Validated CityDTO newCity, BindingResult validation){
        if (validation.hasErrors()){
            throw new BadRequestException(validation.getAllErrors());
        }
        return cityService.saveCity(newCity);
    }

    @DeleteMapping("/{id}")
    //@PreAuthorize("hasAuthority('ADMIN')")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void findByIdAndDelete(@PathVariable UUID id){
        cityService.findCityByIdAndDelete(id);
    }


}
