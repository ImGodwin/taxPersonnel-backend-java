package Godwin.taxSolution.payloads;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record UpdatePersonnelDTO(@NotEmpty(message = "Please add name") String name,
                                 @NotEmpty(message = "Please add surname") String surname,
                                 @NotNull(message = "Please add a telephone number") long telephone,
                                 @NotEmpty(message = "Please include an address") String address,
                                 @NotEmpty(message = "Please add city name") String cityName,
                                 @NotNull(message = "Please add your Partita Iva") long pIva,
                                 @NotEmpty(message = "Please add a short description") @Size(max = 100 ) String description
                                ) {
}
