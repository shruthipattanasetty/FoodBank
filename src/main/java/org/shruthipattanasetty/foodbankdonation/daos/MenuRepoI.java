package org.shruthipattanasetty.foodbankdonation.daos;

import org.shruthipattanasetty.foodbankdonation.models.Menu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface MenuRepoI extends JpaRepository<Menu,Integer> {
}
