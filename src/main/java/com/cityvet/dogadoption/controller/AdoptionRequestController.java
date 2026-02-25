    package com.cityvet.dogadoption.controller;

    import com.cityvet.dogadoption.entity.AdoptionRequest;
    import com.cityvet.dogadoption.entity.Dog;
    import com.cityvet.dogadoption.entity.User;
    import com.cityvet.dogadoption.repository.AdoptionRequestRepository;
    import com.cityvet.dogadoption.repository.DogRepository;
    import com.cityvet.dogadoption.repository.UserRepository;
    import org.springframework.http.ResponseEntity;
    import org.springframework.web.bind.annotation.*;

    import java.util.List;

    @RestController
    public class AdoptionRequestController {

        private final AdoptionRequestRepository adoptionRequestRepository;
        private final DogRepository dogRepository;
        private final UserRepository userRepository;

        public AdoptionRequestController(AdoptionRequestRepository adoptionRequestRepository,
                                         DogRepository dogRepository,
                                         UserRepository userRepository) {
            this.adoptionRequestRepository = adoptionRequestRepository;
            this.dogRepository = dogRepository;
            this.userRepository = userRepository;
        }

        // ✅ USER submits adoption request
        // Example: POST /dogs/1/requests?userId=1
        @PostMapping("/dogs/{dogId}/requests")
        public ResponseEntity<?> createRequest(@PathVariable Long dogId,
                                               @RequestParam Long userId,
                                               @RequestBody AdoptionRequest body) {

            Dog dog = dogRepository.findById(dogId).orElse(null);
            if (dog == null) return ResponseEntity.notFound().build();

            User user = userRepository.findById(userId).orElse(null);
            if (user == null) return ResponseEntity.badRequest().body("User not found");

            AdoptionRequest req = new AdoptionRequest();
            req.setDog(dog);
            req.setUser(user);
            req.setContactNumber(body.getContactNumber());
            req.setMessage(body.getMessage());
            req.setStatus("PENDING");

            return ResponseEntity.ok(adoptionRequestRepository.save(req));
        }

        // ✅ ADMIN views all adoption requests
        @GetMapping("/requests")
        public List<AdoptionRequest> getAllRequests() {
            return adoptionRequestRepository.findAll();
        }

        // ✅ USER views their own requests
        @GetMapping("/requests/my")
        public List<AdoptionRequest> getMyRequests(@RequestParam Long userId) {
            return adoptionRequestRepository.findByUserId(userId);
        }

        // ✅ ADMIN approves or rejects request
        // Example: PUT /requests/1/status?status=APPROVED
        @PutMapping("/requests/{requestId}/status")
        public ResponseEntity<?> updateRequestStatus(@PathVariable Long requestId,
                                                     @RequestParam String status) {

            String normalized = status.toUpperCase();

            if (!normalized.equals("PENDING") &&
                    !normalized.equals("APPROVED") &&
                    !normalized.equals("REJECTED")) {
                return ResponseEntity.badRequest()
                        .body("Invalid status. Use PENDING, APPROVED, or REJECTED.");
            }

            return adoptionRequestRepository.findById(requestId)
                    .map(req -> {
                        req.setStatus(normalized);

                        Dog dog = req.getDog();

                        if (normalized.equals("APPROVED")) {
                            dog.setStatus("ADOPTED");
                            dogRepository.save(dog);
                        }

                        if (normalized.equals("REJECTED")) {
                            if (!"ADOPTED".equalsIgnoreCase(dog.getStatus())) {
                                dog.setStatus("AVAILABLE");
                                dogRepository.save(dog);
                            }
                        }

                        return ResponseEntity.ok(adoptionRequestRepository.save(req));
                    })
                    .orElse(ResponseEntity.notFound().build());
        }
    }