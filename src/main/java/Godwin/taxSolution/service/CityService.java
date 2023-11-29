package Godwin.taxSolution.service;

import Godwin.taxSolution.entities.City;
import Godwin.taxSolution.exceptions.NotFoundException;
import Godwin.taxSolution.payloads.CityDTO;
import Godwin.taxSolution.repository.CityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class CityService {

    //Added getAll, FindById, update and delete... missing save
    @Autowired
    CityRepository cityRepository;

    /*public Page<City> getAllCities(int page, int size, String orderBy)
    {
        Pageable cityPageable = PageRequest.of(page, size, Sort.by(orderBy));

        if (!ascending)cityPageable = PageRequest.of(page, size, Sort.by(orderBy).descending());

        return cityRepository.findAll(cityPageable);
    }*/

    public City findById(UUID uuid) throws NotFoundException {

        return cityRepository.findById(uuid).orElseThrow(() -> new NotFoundException(uuid));
    }

    public City findCityByIdAndUpdate(UUID id, CityDTO body)
    {
        City foundCity = this.findById(id);

       foundCity.setName(body.name());
       foundCity.setAvatar("https://picsum.photos/id/1/200/300");

       return cityRepository.save(foundCity);

    }

    public void findCityByIdAndDelete(UUID id) throws NotFoundException {
        City foundCityToDelete = this.findById(id);
        cityRepository.delete(foundCityToDelete);
    }


}
