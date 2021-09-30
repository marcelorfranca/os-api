package com.mrfti.meuapi.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mrfti.meuapi.domain.OS;

@Repository
public interface OSRepository extends JpaRepository<OS, Integer>{

}
