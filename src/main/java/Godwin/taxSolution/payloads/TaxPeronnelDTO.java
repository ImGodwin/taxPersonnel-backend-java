package Godwin.taxSolution.payloads;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

import java.util.UUID;

public record TaxPeronnelDTO(@NotEmpty(message = "Please add name") String name,
                             @NotEmpty(message = "Please add surname") String surname,
                             @NotEmpty(message = "Please add an email") String email,
                             @NotEmpty(message = "Please add a telephone number") @Size(message = "Number must be max 10 digits excluding +39", min = 10, max = 10) int telephone,
                             @NotEmpty(message = "Please include an address") String address,
                             @NotEmpty(message = "Please add city name") String cityName,
                             @NotEmpty(message = "Please add your Partita Iva") @Size(message = "number must be 8 digits", min = 8, max = 8) int pIva,
                             @NotEmpty(message = "Please add a short description") @Size(max = 100 ) String description,
                             @NotEmpty(message = "Pòease include an address") UUID city) {
}
