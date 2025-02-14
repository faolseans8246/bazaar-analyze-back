package org.example.bazaaranalyze.repository;

import org.example.bazaaranalyze.entity.CardsBase;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface CardRepos extends JpaRepository<CardsBase, UUID> {

    Optional<CardsBase> findByCardNumber(String cardNumber);
    Optional<CardsBase> findByCardHolder(String cardHolder);
}
