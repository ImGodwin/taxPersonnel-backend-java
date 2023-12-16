package Godwin.taxSolution.service;


import Godwin.taxSolution.entities.TaxPersonnel;
import Godwin.taxSolution.exceptions.NotFoundException;
import Godwin.taxSolution.payloads.TaxPersonnelDTO;
import Godwin.taxSolution.repository.TaxPersonneRepository;
import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.UUID;

@Service
public class TaxPersonnelService {

    //need to add image with cloudinary
    @Autowired
    TaxPersonneRepository taxPersonneRepository;

    @Autowired
    private CityService cityService;

    @Autowired
    private Cloudinary cloudinary;


    public Page<TaxPersonnel> getTaxPersonnels(int page, int size, String orderBy){

        Pageable pageable = PageRequest.of(page, size, Sort.by(orderBy));
        return taxPersonneRepository.findAll(pageable);
    }

    public TaxPersonnel findById(UUID id) throws NotFoundException {

        return taxPersonneRepository.findById(id).orElseThrow(() -> new NotFoundException(id));
    }

    public TaxPersonnel findByIdAndUpdate(UUID id, TaxPersonnelDTO body)
    {
        TaxPersonnel taxPersonnel = this.findById(id);

        taxPersonnel.setName(body.name());
        taxPersonnel.setSurname(body.surname());
        taxPersonnel.setTelephone(body.telephone());
        taxPersonnel.setAddress(body.address());
        taxPersonnel.setCityName(body.cityName());
        taxPersonnel.setPIva(body.pIva());
        taxPersonnel.setDescription(body.description());
        taxPersonnel.setImage("http://ui-avatars.com/api/?name="+body.name() + "+" + body.surname());
        return taxPersonneRepository.save(taxPersonnel);
    }

    public void findDeviceByIdAndDelete(UUID id) throws NotFoundException {
        TaxPersonnel foundDevice = this.findById(id);
        taxPersonneRepository.delete(foundDevice);
    }

    public TaxPersonnel findByEmail(String email){
        return taxPersonneRepository.findByEmail(email)
                .orElseThrow(() ->
                new NotFoundException("The Email " + email + " was not found"));
    }

    public String uploadPicture(MultipartFile file) throws IOException {
        return (String) cloudinary.uploader().upload(file.getBytes(),
                ObjectUtils.emptyMap()).get("url");
    }

    public TaxPersonnel updateImg(UUID id, String imgUrl){
        TaxPersonnel exitstingTaxPersonnel = this.findById(id);

        exitstingTaxPersonnel.setImage(imgUrl);
        return taxPersonneRepository.save(exitstingTaxPersonnel);
    }

}
