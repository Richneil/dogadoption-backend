package com.cityvet.dogadoption.controller;

import com.cityvet.dogadoption.entity.Dog;
import com.cityvet.dogadoption.repository.DogRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/dogs")
public class DogController {

    private final DogRepository dogRepository;

    public DogController(DogRepository dogRepository) {
        this.dogRepository = dogRepository;
    }

    // ✅ USER can view all dogs
    @GetMapping
    public List<Dog> getAllDogs() {
        return dogRepository.findAll();
    }

    // ✅ USER can view one dog
    @GetMapping("/{id}")
    public ResponseEntity<Dog> getDogById(@PathVariable Long id) {
        return dogRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // ✅ ADMIN will add dogs (we’ll secure this later)
    @PostMapping
    public Dog createDog(@RequestBody Dog dog) {
        dog.setStatus(dog.getStatus() == null ? "AVAILABLE" : dog.getStatus());
        return dogRepository.save(dog);
    }

    // ✅ ADMIN will update dogs (secure later)
    @PutMapping("/{id}")
    public ResponseEntity<Dog> updateDog(@PathVariable Long id, @RequestBody Dog updatedDog) {
        return dogRepository.findById(id)
                .map(existing -> {
                    existing.setName(updatedDog.getName());
                    existing.setBreed(updatedDog.getBreed());
                    existing.setAge(updatedDog.getAge());
                    existing.setStatus(updatedDog.getStatus());
                    return ResponseEntity.ok(dogRepository.save(existing));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    // ✅ ADMIN will delete dogs (secure later)
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDog(@PathVariable Long id) {
        if (!dogRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        dogRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}