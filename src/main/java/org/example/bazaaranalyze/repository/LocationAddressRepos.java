package org.example.bazaaranalyze.repository;


import org.example.bazaaranalyze.entity.LocationAddressBase;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface LocationAddressRepos extends JpaRepository<LocationAddressBase, UUID> {
}
