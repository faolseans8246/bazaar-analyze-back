package org.example.bazaaranalyze.repository;

import org.example.bazaaranalyze.model.TransactionModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface TransactionRepos extends JpaRepository<TransactionModel, UUID> {

}
