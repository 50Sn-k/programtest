package com.example.sampleweb.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.sampleweb.entity.Communication;

public interface CommunicationRepository extends JpaRepository<Communication,String>{
}