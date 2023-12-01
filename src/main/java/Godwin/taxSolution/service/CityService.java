package Godwin.taxSolution.service;

import Godwin.taxSolution.entities.City;
import Godwin.taxSolution.entities.TaxPersonnel;
import Godwin.taxSolution.exceptions.BadRequestException;
import Godwin.taxSolution.exceptions.NotFoundException;
import Godwin.taxSolution.payloads.CityDTO;
import Godwin.taxSolution.payloads.TaxPeronnelDTO;
import Godwin.taxSolution.repository.CityRepository;
import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.UUID;

@Service
public class CityService {

    //Added getAll, FindById, update and delete... missing save
    @Autowired
    CityRepository cityRepository;

    @Autowired
    private Cloudinary cloudinary;

    public Page<City> getAllCities(int page, int size, String orderBy, boolean ascending)
    {
        Pageable cityPageable = PageRequest.of(page, size, Sort.by(orderBy));
        return cityRepository.findAll(cityPageable);
    }

    public City saveCity(CityDTO body){
        cityRepository.findByName(body.name()).ifPresent(nameInRepo -> {
            throw new
                    BadRequestException("The email added already exists");
        });

        City newCity = new City();

        newCity.setName(body.name());
        newCity.setAvatar("https://picsum.photos/id/1/200/300");
        return cityRepository.save(newCity);
    }

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

    public String uploadPicture(MultipartFile file) throws IOException {
        return (String) cloudinary.uploader().upload(file.getBytes(),
                ObjectUtils.emptyMap()).get("url");
    }

    public City findCityByName(String name){
        return cityRepository.findByName(name).
                orElseThrow(()-> {throw new BadRequestException("City not found");});
    }

}
