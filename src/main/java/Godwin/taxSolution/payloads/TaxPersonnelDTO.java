package Godwin.taxSolution.payloads;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record TaxPersonnelDTO(@NotEmpty(message = "Please add name") String name,
                              @NotEmpty(message = "Please add surname") String surname,
                              @NotEmpty(message = "Please add an email")
                             @Pattern(regexp = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$", message = "Incorrect email pattern")
                             String email,
                              @NotNull(message = "Please add a telephone number") long telephone,
                              @NotEmpty(message = "Please include an address") String address,
                              @NotEmpty(message = "Please add city name") String cityName,
                              @NotNull(message = "Please add your Partita Iva") long pIva,
                              @NotEmpty(message = "Please add a short description") @Size(max = 100 ) String description,
                              @NotEmpty(message = "Please add a password") String password) {
}
