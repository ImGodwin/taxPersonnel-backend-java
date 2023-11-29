package Godwin.taxSolution.controllers;

import Godwin.taxSolution.service.TaxPersonnelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/taxpersonnel")
public class TaxPersonnelController {

    @Autowired
    private TaxPersonnelService taxPersonnelService;


}
