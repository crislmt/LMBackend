package com.lomuto.lmbackend.services;


import com.lomuto.lmbackend.entities.Movie;
import com.lomuto.lmbackend.entities.MoviePurchase;
import com.lomuto.lmbackend.entities.Purchase;
import com.lomuto.lmbackend.entities.User;
import com.lomuto.lmbackend.exceptions.IllegalDateException;
import com.lomuto.lmbackend.exceptions.MovieQuantityUnavailableException;
import com.lomuto.lmbackend.exceptions.NoSuchUserException;
import com.lomuto.lmbackend.repositories.MoviePurchaseRepository;
import com.lomuto.lmbackend.repositories.PurchaseRepository;
import com.lomuto.lmbackend.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Date;
import javax.persistence.EntityManager;


@Service
public class PurchaseService {
    @Autowired
    private PurchaseRepository purchaseRepository;
    @Autowired
    private MoviePurchaseRepository moviePurchaseRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private EntityManager entityManager;

    @Transactional(readOnly = false)
    public Purchase addPurchase(Purchase purchase) throws MovieQuantityUnavailableException {
        Purchase result= purchaseRepository.save(purchase);
        for(MoviePurchase mp: result.getPurchaseMovies()){
            mp.setPurchase(result);
            MoviePurchase justAdded= moviePurchaseRepository.save(mp);
            Movie movie= justAdded.getMovie();
            int newQuantity=movie.getQuantity() - mp.getQuantity();
            if(newQuantity<0){
                throw new MovieQuantityUnavailableException();
            }
            movie.setQuantity(newQuantity);
            entityManager.refresh(mp);
        }
        entityManager.refresh(result);
        return result;
    }

    @Transactional(readOnly = true)
    public List<Purchase> getPurchaseByUser(User user) throws NoSuchUserException{
        if(!userRepository.existsById(user.getId())){
            throw new NoSuchUserException();
        }
        return purchaseRepository.findByUser(user);
    }

    @Transactional(readOnly = true)
    public List<Purchase> getPurchaseByUserInPeriod(User user, Date startDate, Date endDate, int pageNumber, int pageSize, String sortBy) throws NoSuchUserException{
        if(!userRepository.existsById(user.getId())){
            throw new NoSuchUserException();
        }
        if(startDate.compareTo(endDate)>=0){
            throw new IllegalDateException();
        }
        Pageable paging= PageRequest.of(pageNumber, pageSize, Sort.by(sortBy));
        Page<Purchase> purchasePage=purchaseRepository.findByUserInPeriod(startDate, endDate, user, paging);
        return purchasePage.getContent();
    }

    @Transactional(readOnly = true)
    public List<Purchase> getAllPurchases(){return purchaseRepository.findAll();}
}
