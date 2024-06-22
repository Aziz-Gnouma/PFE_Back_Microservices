package org.vaadin.marcus.service;

//import org.vaadin.marcus.data.BookingStatus;

import java.time.LocalDate;

public record ReclamationDetails(Long ReclamationId,
                                 String matricule,

                                 String firstName,
                                 String lastName,
                                 String email,
                                 String tel,
                                 String entreprise,

                                 LocalDate date,
                                 String description,
                                 String ReclamationStatut

                                 )
{
}
