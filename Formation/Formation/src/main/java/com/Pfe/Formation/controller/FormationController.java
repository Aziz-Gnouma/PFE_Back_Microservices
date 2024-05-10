package com.Pfe.Formation.download;

import com.Pfe.Formation.DAO.FormationDTO;
import com.Pfe.Formation.DAO.UpdateFormationRequest;
import com.Pfe.Formation.Repo.CategoryRepo;
import com.Pfe.Formation.Repo.FormationRepository;
import com.Pfe.Formation.entity.Category;
import com.Pfe.Formation.entity.Formation;
import com.Pfe.Formation.exception.ResourceNotFoundException;
import com.Pfe.Formation.upload.FileUploadResponse;
import com.Pfe.Formation.upload.FormationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import jakarta.transaction.Transactional;
import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@RestController
public class FormationController {

	@Autowired
	private FormationService formationService;
	@Autowired
	private FormationRepository formationRepository;
	@Autowired
	private CategoryRepo CategoryRepo ;

	@PostMapping("/ajouterFormation")
	public ResponseEntity<?> uploadFile(@RequestParam("file") MultipartFile multipartFile,
										@RequestParam("formateur") String formateur,
										@RequestParam("description") String description,
										@RequestParam("titre") String titre,
										@RequestParam("duree") String duree,
										@RequestParam("langue") String langue,
										@RequestParam("niveau") String niveau,
										@RequestParam("RHMatricule") String RHMatricule,
										@RequestParam("entreprise") String entreprise,
										@RequestParam("categories") Set<Long> categoryIds) {
		try {
			String fileName = multipartFile.getOriginalFilename();
			byte[] fileContent = multipartFile.getBytes();

			Set<Category> categories = new HashSet<>();
			for (Long categoryId : categoryIds) {
				Category category = CategoryRepo.findById(categoryId).orElse(null);
				if (category != null) {
					categories.add(category);
				}
			}

			Long fileId = formationService.saveFormation(fileName, fileContent,
					titre, duree, langue, niveau, formateur, description, RHMatricule,
					entreprise, categories );

			FileUploadResponse response = new FileUploadResponse();
			response.setFileName(fileName);
			response.setSize(multipartFile.getSize());
			response.setDownloadUri("/downloadFile/" + fileId);

			return new ResponseEntity<>("Formation Saved", HttpStatus.OK);
		} catch (IOException e) {
			e.printStackTrace();
			return new ResponseEntity<>("Failed to upload Formation.", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	@PutMapping("/modifierFormation/{id}")
	public ResponseEntity<?> updateFormation(
			@PathVariable Long id,
			@RequestBody UpdateFormationRequest requestBody) {

		try {
			Formation formation = formationRepository.findById(id)
					.orElseThrow(() -> new ResourceNotFoundException("Formation", "id", id));
			System.out.println("Received values:");
			System.out.println("Formateur: " + requestBody.getFormateur());
			System.out.println("Description: " + requestBody.getDescription() );
			System.out.println("Titre: " + requestBody.getTitre() );
			System.out.println("Duree: " + requestBody.getDuree());
			System.out.println("Langue: " + requestBody.getLangue());
			System.out.println("Niveau: " + requestBody.getNiveau());
			System.out.println("RHMatricule: " + requestBody.getRHMatricule());
			System.out.println("Entreprise: " + requestBody.getEntreprise());
			System.out.println("CategoryIds: " + requestBody.getCategoryIds());

			if (requestBody.getFormateur() != null && !requestBody.getFormateur().isEmpty()) {
				formation.setFormateur(requestBody.getFormateur());
			}
			if (requestBody.getDescription() != null && !requestBody.getDescription().isEmpty()) {
				formation.setDescription(requestBody.getDescription());
			}
			if (requestBody.getTitre() != null && !requestBody.getTitre().isEmpty()) {
				formation.setTitre(requestBody.getTitre());
			}
			if (requestBody.getDuree() != null && !requestBody.getDuree().isEmpty()) {
				formation.setDuree(requestBody.getDuree());
			}
			if (requestBody.getLangue() != null && !requestBody.getLangue().isEmpty()) {
				formation.setLangue(requestBody.getLangue());
			}
			if (requestBody.getNiveau() != null && !requestBody.getNiveau().isEmpty()) {
				formation.setNiveau(requestBody.getNiveau());
			}
			if (requestBody.getRHMatricule() != null && !requestBody.getRHMatricule().isEmpty()) {
				formation.setRHMatricule(requestBody.getRHMatricule());
			}
			if (requestBody.getStatut() != null && !requestBody.getStatut().isEmpty()) {
				formation.setStatut(requestBody.getStatut());
			}
			if (requestBody.getEntreprise() != null && !requestBody.getEntreprise().isEmpty()) {
				formation.setEntreprise(requestBody.getEntreprise());
			}
			if (requestBody.getCategoryIds() != null && !requestBody.getCategoryIds().isEmpty()) {
				Set<Category> categories = new HashSet<>();
				for (Long categoryId : requestBody.getCategoryIds()) {
					Category category = CategoryRepo.findById(categoryId)
							.orElseThrow(() -> new ResourceNotFoundException("Category", "id", categoryId));
					categories.add(category);
				}
				formation.setCategories(categories);
			}
			if (requestBody.getFile() != null && !requestBody.getFile().isEmpty()) {
				String fileName = requestBody.getFile().getOriginalFilename();
				byte[] fileContent = requestBody.getFile().getBytes();
				formation.setFileName(fileName);
				formation.setContent(fileContent);
			}

			formationRepository.save(formation);

			return ResponseEntity.ok().body("Formation updated successfully");
		} catch (IOException e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to update Formation.");
		}
	}

	@GetMapping("/AllFormations")
	@Transactional
	public ResponseEntity<List<FormationDTO>> getAllFormations() {
		try {
			List<FormationDTO> formations = formationService.getAllFormations();
			return ResponseEntity.ok(formations);
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}

	@GetMapping("/mesFormations")
	@Transactional
	public ResponseEntity<List<Formation>> getAll() {
		try {
			List<Formation> formations = formationRepository.findAll();
			return ResponseEntity.ok(formations);
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}

	@GetMapping("/AllFormations/{entrepriseNom}")
	@Transactional
	public ResponseEntity<List<FormationDTO>> getAllFormationsByEnterprise(@PathVariable String entrepriseNom) {
		try {
			List<FormationDTO> formations = formationService.getAllFormationsByEnterprise(entrepriseNom);
			return ResponseEntity.ok(formations);
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}
//	@GetMapping("AllFormations")
//	public ResponseEntity<List<FormationDTO>> getAllFormations() {
//		try {
//			List<Formation> formations = formationRepository.findAll();
//			List<FormationDTO> formationsDTO = formations.stream()
//					.map(this::mapToDTO)
//					.collect(Collectors.toList());
//			return ResponseEntity.ok(formationsDTO);
//		} catch (Exception e) {
//			e.printStackTrace();
//			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
//		}
//	}
	private FormationDTO mapToDTO(Formation formation) {
		FormationDTO dto = new FormationDTO();
		dto.setId(formation.getId());
		dto.setTitre(formation.getTitre());
		dto.setDuree(formation.getDuree());
		dto.setLangue(formation.getLangue());
		dto.setNiveau(formation.getNiveau());
		dto.setFileName(formation.getFileName());
		dto.setDescription(formation.getDescription());
		dto.setFormateur(formation.getFormateur());
		dto.setEntreprise(formation.getEntreprise());
		dto.setStatut(formation.getStatut());
		return dto;
	}

	@GetMapping("/{id}/video")
	public ResponseEntity<byte[]> getVideo(@PathVariable Long id) {
		// Récupérer les données binaires de la vidéo depuis la base de données
		byte[] videoContent = formationService.getVideoContentById(id);
		// Retourner les données binaires de la vidéo dans la réponse HTTP
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
		headers.setContentDispositionFormData("filename", "video.mp4");
		return new ResponseEntity<>(videoContent, headers, HttpStatus.OK);
	}

	@DeleteMapping("DeleteFormation/{id}")
	public ResponseEntity<?> deleteFormation(@PathVariable Long id) {
		Formation formation = formationRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("formation", "id", id));

		formation.getCategories().clear();
		formationRepository.delete(formation);

		return ResponseEntity.ok().build();
	}

	@GetMapping("/downloadFormation/{id}")
	public ResponseEntity<Resource> downloadFile(@PathVariable("id") Long id) {
		try {
			Optional<Formation> fileEntityOptional = formationRepository.findById(id);

			if (fileEntityOptional.isPresent()) {
				Formation formation = fileEntityOptional.get();

				byte[] fileContent = formation.getContent();

				ByteArrayResource resource = new ByteArrayResource(fileContent);

				MediaType mediaType = MediaType.APPLICATION_OCTET_STREAM;
				String filename = formation.getFileName();
				HttpHeaders headers = new HttpHeaders();
				headers.setContentDispositionFormData("attachment", filename);

				return ResponseEntity.ok()
						.headers(headers)
						.contentType(mediaType)
						.body(resource);
			} else {
				return ResponseEntity.notFound().build();
			}
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}

	@GetMapping("/GetFormation/{id}")
	public ResponseEntity<Resource> getFormationVideo(@PathVariable("id") Long id) {
		try {
			Optional<Formation> fileEntityOptional = formationRepository.findById(id);

			if (fileEntityOptional.isPresent()) {
				Formation formation = fileEntityOptional.get();

				formation.setParticipants(formation.getParticipants() + 1);
				formationRepository.save(formation);

				byte[] fileContent = formation.getContent();
				ByteArrayResource resource = new ByteArrayResource(fileContent);
				MediaType mediaType = MediaType.APPLICATION_OCTET_STREAM;

				HttpHeaders headers = new HttpHeaders();
				headers.setContentDisposition(ContentDisposition.builder("inline").filename(formation.getFileName()).build()); // Set to "inline" to display in browser

				return ResponseEntity.ok()
						.headers(headers)
						.contentType(mediaType)
						.body(resource);
			} else {
				return ResponseEntity.notFound().build();
			}
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}
	@GetMapping("/formations/{categoryName}/{entreprise}")
	@Transactional
	public ResponseEntity<List<FormationDTO>> getFormationsByCategoryAndEntreprise(@PathVariable String categoryName, @PathVariable String entreprise) {
		try {
			List<FormationDTO> formations = formationService.getFormationsByCategoryAndEntreprise(categoryName, entreprise);
			return ResponseEntity.ok(formations);
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}

	@GetMapping("/formations/{id}")
	@Transactional
	public ResponseEntity<Optional<Formation>> getFormationsID(@PathVariable Long id) {
		try {
			Optional<Formation> formations = formationService.getFormationsByID(id);
			return ResponseEntity.ok(formations);
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}



}
