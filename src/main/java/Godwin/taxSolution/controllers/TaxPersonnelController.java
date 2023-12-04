package Godwin.taxSolution.controllers;

import Godwin.taxSolution.entities.TaxPersonnel;
import Godwin.taxSolution.exceptions.BadRequestException;
import Godwin.taxSolution.payloads.TaxPersonnelDTO;
import Godwin.taxSolution.service.AuthService;
import Godwin.taxSolution.service.TaxPersonnelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.UUID;

@RestController
@RequestMapping("/taxpersonnel")
public class TaxPersonnelController {

    @Autowired
    private TaxPersonnelService taxPersonnelService;

    @Autowired
    private AuthService authService;

    @GetMapping("")
    public Page<TaxPersonnel> getTaxPersonnels(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "15") int size,
                                               @RequestParam(defaultValue = "id") String orderBy){
        return taxPersonnelService.getTaxPersonnels(page, size, orderBy);
    }

    @GetMapping(value = "/{id}")
    public TaxPersonnel findById(@PathVariable UUID id){
        return taxPersonnelService.findById(id);
    }

    @GetMapping("/me")
    public UserDetails getMyProfile(@AuthenticationPrincipal TaxPersonnel currentPersonnel,
                                    @RequestBody TaxPersonnelDTO body){
        return taxPersonnelService.findByIdAndUpdate(currentPersonnel.getId(), body);
    }

    @PostMapping("")
    @ResponseStatus(HttpStatus.CREATED)
    public TaxPersonnel saveNewTaxPersonnel(@RequestBody @Validated TaxPersonnelDTO newTaxPersonnel, BindingResult validation){
        if (validation.hasErrors()){
            throw new BadRequestException(validation.getAllErrors());
        }
        return authService.saveTaxPersonnel(newTaxPersonnel);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public TaxPersonnel findByIdAndUpdate(@PathVariable UUID id, @RequestBody @Validated TaxPersonnelDTO body, BindingResult validation){
        if (validation.hasErrors()) {
            throw new BadRequestException(validation.getAllErrors());
        }
        return taxPersonnelService.findByIdAndUpdate(id, body);
    }

    @PostMapping("/{id}/uploadPhoto")
    public TaxPersonnel uploadTaxPersonnelImage(@PathVariable UUID id, @RequestParam("file") MultipartFile body) throws IOException {
        System.out.println(body.getSize());
        System.out.println(body.getContentType());
        String uploadImageUrl = taxPersonnelService.uploadPicture(body);
        return taxPersonnelService.updateImg(id, uploadImageUrl);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void findByIdAndDelete(@PathVariable UUID id){
        taxPersonnelService.findDeviceByIdAndDelete(id);
    }
}
