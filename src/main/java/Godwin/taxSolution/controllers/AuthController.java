package Godwin.taxSolution.controllers;

import Godwin.taxSolution.entities.TaxPersonnel;
import Godwin.taxSolution.exceptions.BadRequestException;
import Godwin.taxSolution.payloads.TaxPeronnelDTO;
import Godwin.taxSolution.payloads.TaxPersonnelLoginDTO;
import Godwin.taxSolution.payloads.TaxPersonnelLoginSuccessDTO;
import Godwin.taxSolution.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/public/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/login")
    public TaxPersonnelLoginSuccessDTO login(@RequestBody TaxPersonnelLoginDTO body){
        return new TaxPersonnelLoginSuccessDTO(authService.authTaxPeronnel(body));
    }

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public TaxPersonnelLoginSuccessDTO saveNewEmployee(@RequestBody @Validated TaxPeronnelDTO body, BindingResult validation){
        if(validation.hasErrors()){
            throw new BadRequestException(validation.getAllErrors());
        }else {
            try {
                TaxPersonnel registerNewTaxPersonnel = authService.saveTaxPersonnel(body);
                return new TaxPersonnelLoginSuccessDTO(authService.authTaxPeronnel(new TaxPersonnelLoginDTO(registerNewTaxPersonnel
                        .getEmail(), body.password())));
            }catch (Exception ex){
                throw new RuntimeException(ex);
            }
        }
    }
}
