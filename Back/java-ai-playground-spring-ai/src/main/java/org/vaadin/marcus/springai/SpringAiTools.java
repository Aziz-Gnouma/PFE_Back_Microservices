package org.vaadin.marcus.springai;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Description;
import org.springframework.core.NestedExceptionUtils;
//import org.vaadin.marcus.service.BookingDetails;
//import org.vaadin.marcus.service.FlightService;
import org.vaadin.marcus.service.ReclamationDetails;
import org.vaadin.marcus.service.ReclamationService;


import java.util.function.Function;

@Configuration
public class SpringAiTools {

    private static final Logger logger = LoggerFactory.getLogger(SpringAiTools.class);





    public record  AddReclamationRequest(String matricule, String firstName, String lastName , String email, String tel,String entreprise, String description){}
    public record ReclamtionDetailsRequest(Long ReclamationId,String matricule, String firstName, String lastName , String email, String tel,String entreprise, String description,String date  ) {
    }

    public record ChangeReclamtionDatesRequest(Long ReclamationId, String firstName, String lastName, String date,
                                            String description, String matricule) {
    }

    public record CancelReclamtionRequest(Long ReclamationId, String matricule , String firstName, String lastName) {
    }


    private final ReclamationService reclamationService;



    public SpringAiTools(ReclamationService reclamationService) {
       this.reclamationService = reclamationService;

    }


    @Bean
    @Description("Add a reclamation")
    public Function<AddReclamationRequest, String> addReclamation() {
        return request -> {
            reclamationService.addReclamation(request.matricule(),request.firstName(), request.lastName(),request.email(), request.tel(),
                    request.entreprise(),request.description());
            return "Reclamation added successfully";
        };
    }

    @Bean
    @Description("Get Reclamtion details")
    public Function<ReclamtionDetailsRequest, ReclamationDetails> getReclamationDetails() {
        return request -> {
            try {
                return reclamationService.getReclamationDetails(request.ReclamationId,request.matricule(), request.firstName(),
                        request.lastName());
            }
            catch (Exception e) {
                logger.warn("Reclamtion details: {}", NestedExceptionUtils.getMostSpecificCause(e).getMessage());
                return new ReclamationDetails(request.ReclamationId(), request.firstName(), request.lastName, request.email(), request.tel,null,
                         null, null, null, null);
            }
        };
    }

//    @Bean
//    @Description("Change Reclamtion dates")
//    public Function<ChangeReclamtionDatesRequest, String> changeReclamtion() {
//        return request -> {
//            reclamationService.changeReclamation(request.ReclamationId(), request.firstName(), request.lastName(),
//                    request.date(), request.description(), request.matricule());
//            return "";
//        };
//    }
//
    @Bean
    @Description("Cancel Reclamtion")
    public Function<CancelReclamtionRequest, String> cancelReclamtion() {
        return request -> {
            reclamationService.cancelReclamation(request.ReclamationId(),request.matricule(), request.firstName(), request.lastName());
            return "";
        };
    }


}


