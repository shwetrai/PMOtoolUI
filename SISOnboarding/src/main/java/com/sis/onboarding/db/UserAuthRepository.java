/**
 * 
 */
package com.sis.onboarding.db;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

/**
 * @author shwetrai
 *
 */
public interface UserAuthRepository extends MongoRepository<UserAuth, String> {
	
	
	@Override
	Optional<UserAuth> findById(String id);
	
    @Query("{ 'name' : ?0 }")
    Optional<UserAuth> findByModel(String name);
    
    @Query("{ 'cid' : ?0 }")
    Optional<UserAuth> findByUserId(String name);

    @Override
    void delete(UserAuth deleted);

}
