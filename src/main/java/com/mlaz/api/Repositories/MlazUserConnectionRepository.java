package com.mlaz.api.Repositories;

import com.mlaz.api.model.MlazUserConnection;
import org.socialsignin.spring.data.dynamodb.repository.EnableScan;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by jimmy on 5/2/17.
 */
@EnableScan
public interface MlazUserConnectionRepository extends CrudRepository<MlazUserConnection, String> {

}
