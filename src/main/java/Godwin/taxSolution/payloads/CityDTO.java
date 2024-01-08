package Godwin.taxSolution.payloads;

import jakarta.validation.constraints.NotEmpty;

public record CityDTO(String name, int latitude, int longitude) {
}
