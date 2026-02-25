package com.cityvet.dogadoption.repository;

import com.cityvet.dogadoption.entity.Dog;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DogRepository extends JpaRepository<Dog, Long> {
}