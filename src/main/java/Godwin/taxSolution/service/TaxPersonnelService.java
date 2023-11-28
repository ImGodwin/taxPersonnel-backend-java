package Godwin.taxSolution.service;

import Godwin.taxSolution.entities.TaxPersonnel;
import Godwin.taxSolution.repository.TaxPersonneRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class TaxPersonnelService {

    @Autowired
    TaxPersonneRepository taxPersonneRepository;

    public Page<TaxPersonnel> getTaxPersonnels(int page, int size, String orderBy){

        Pageable pageable = PageRequest.of(page, size, Sort.by(orderBy));
        return taxPersonneRepository.findAll(pageable);
    }


}
