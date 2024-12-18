package com.sbp.ollamaExemple.repositories;
import com.sbp.ollamaExemple.entity.Response;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ResponseRepository extends CrudRepository<Response, Long> {

}
