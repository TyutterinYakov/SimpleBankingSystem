package banking.store.repository;

import banking.store.entity.TransactionalEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionalRepository extends JpaRepository<TransactionalEntity, Long> {
}
