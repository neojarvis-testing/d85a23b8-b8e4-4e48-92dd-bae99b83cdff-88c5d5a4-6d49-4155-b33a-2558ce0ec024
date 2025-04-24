package com.examly.springapp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.examly.springapp.model.PropertyInquiry;

@Repository
public interface PropertyInquiryRepo extends JpaRepository<PropertyInquiry,Long>{
    
@Query("select pi from PropertyInquiry pi where pi.user.userId=?1")
public List<PropertyInquiry> findInquiryByUser(long userId);
}
