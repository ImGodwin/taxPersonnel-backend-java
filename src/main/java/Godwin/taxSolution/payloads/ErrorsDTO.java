package Godwin.taxSolution.payloads;

import java.util.Date;

public record ErrorsDTO(String message, Date errorTimeStamp) {
}
