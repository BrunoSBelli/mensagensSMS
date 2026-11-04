package com.raposo.mensagensSMS.infrastructure.repositories;

import com.raposo.mensagensSMS.infrastructure.entity.SmsMessageEntity;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SmsMessageRepository extends JpaRepository<SmsMessageEntity, Long> {


}
