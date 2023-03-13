package org.shruthipattanasetty.foodbankdonation.daos;

import jakarta.transaction.Transactional;
import org.shruthipattanasetty.foodbankdonation.models.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
@Transactional(rollbackOn = Exception.class)
public interface AddressRepoI extends JpaRepository<Address,Integer> {
}
