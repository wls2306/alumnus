package com.bcu.alumnus.repo;

import com.bcu.alumnus.entity.Part;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface PartRepository extends JpaRepository<Part,Integer> {

    Part findByPartId(String id);

    @Query(value = "select partName from Part where partId=?1" )
    String findPartNameByPartId(Integer partId);

}
