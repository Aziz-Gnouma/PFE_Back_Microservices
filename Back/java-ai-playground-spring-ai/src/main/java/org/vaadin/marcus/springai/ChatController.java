package org.vaadin.marcus.springai;

import org.atmosphere.config.service.Put;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.vaadin.marcus.data.Reclamation;
import org.vaadin.marcus.service.ReclamationRepository;
import org.vaadin.marcus.service.ReclamationService;
import reactor.core.publisher.Flux;
import com.google.cloud.speech.v1.*;
import com.google.protobuf.ByteString;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
public class ChatController {

    private final SpringAiAssistant springAiAssistant;
    @Autowired
    private ReclamationRepository  ReclamationRepository;
    @Autowired
    private ReclamationService ReclamationService;

    @Autowired
    public ChatController(SpringAiAssistant springAiAssistant) {
        this.springAiAssistant = springAiAssistant;
    }

    @GetMapping("/chat/{chatId}/{userMessage}")
    public Flux<String> chat(@PathVariable String chatId, @PathVariable String userMessage) {
        return springAiAssistant.chat(chatId, userMessage);
    }
//    @PostMapping("/aziz/{chatId}")
//    public String chat1(@PathVariable String chatId, @RequestBody byte[] voiceData) throws IOException {
//        // Convert voice data to text
//        String userMessage = convertVoiceToText(voiceData);
//
//        // Here, you can call your chat method with the text message
//        return "Received voice message: " + userMessage;
//    }
//
//    private String convertVoiceToText(byte[] voiceData) throws IOException {
//        // Initialize the SpeechClient
//        try (SpeechClient speechClient = SpeechClient.create()) {
//            // Build the audio content from the byte array
//            ByteString audioBytes = ByteString.copyFrom(voiceData);
//            RecognitionAudio audio = RecognitionAudio.newBuilder().setContent(audioBytes).build();
//
//            // Configure the recognition request
//            RecognitionConfig config =
//                    RecognitionConfig.newBuilder()
//                            .setEncoding(RecognitionConfig.AudioEncoding.LINEAR16) // Assuming the voice data is in LINEAR16 format
//                            .setLanguageCode("en-US") // Set the language code according to your requirements
//                            .build();
//
//            // Perform the speech recognition
//            RecognizeResponse response = speechClient.recognize(config, audio);
//
//            // Extract and return the recognized text
//            StringBuilder resultBuilder = new StringBuilder();
//            for (SpeechRecognitionResult recognitionResult : response.getResultsList()) {
//                // There can be multiple alternative transcripts for a given speech input
//                SpeechRecognitionAlternative alternative = recognitionResult.getAlternativesList().get(0);
//                resultBuilder.append(alternative.getTranscript());
//            }
//            return resultBuilder.toString();
//        }
//    }


    @GetMapping("/ALLRECLAMATION")
    public List<Reclamation> AllReclamations() {
        return ReclamationRepository.findAll();
    }
    @PutMapping("/AcceptRECLAMATION/{id}")
    public ResponseEntity<String> acceptReclamations(@PathVariable Long id) {
        ReclamationService.SendConfirmation2(id);
        return ReclamationService.acceptReclamation(id);
    }
    @DeleteMapping("/DeleteRECLAMATION/{id}")
    public ResponseEntity<String> delleteReclamations(@PathVariable Long id) {
        return ReclamationService.deleteReclamation(id);
    }
    @GetMapping("/totalReclamations")
    public long getTotalReclamations() {
        return ReclamationService.getTotalReclamations();
    }

}
