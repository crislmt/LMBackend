package com.lomuto.lmbackend.repositories;
import com.lomuto.lmbackend.entities.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
@Repository
public interface PurchaseRepository extends JpaRepository<Purchase, Integer> {

    //User
    List<Purchase> findByUser(User user);

    //PurchaseTime
    List<Purchase> findByPurchaseTime(Date date);
    @Query("select p from Purchase p where p.purchaseTime > ?1 and p.purchaseTime < ?2 and p.user = ?3")
    Page<Purchase> findByUserInPeriod(Date startDate, Date endDate, User user, Pageable pageable);

}
