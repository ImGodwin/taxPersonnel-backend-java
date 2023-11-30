package Godwin.taxSolution.controllers;

import Godwin.taxSolution.entities.TaxPersonnel;
import Godwin.taxSolution.payloads.TaxPersonnelLoginDTO;
import Godwin.taxSolution.payloads.TaxPersonnelLoginSuccessDTO;
import Godwin.taxSolution.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/login")
    public TaxPersonnelLoginSuccessDTO login(@RequestBody TaxPersonnelLoginDTO body){
        return new TaxPersonnelLoginSuccessDTO(authService.authTaxPeronnel(body));
    }

   /* @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public TaxPersonnel saveTax*/
}
