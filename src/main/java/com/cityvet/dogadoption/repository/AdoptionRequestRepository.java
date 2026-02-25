package com.cityvet.dogadoption.repository;

import com.cityvet.dogadoption.entity.AdoptionRequest;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AdoptionRequestRepository extends JpaRepository<AdoptionRequest, Long> {
    List<AdoptionRequest> findByUserId(Long userId);
    List<AdoptionRequest> findByDogId(Long dogId);
}