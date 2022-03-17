package banking.store.repository;

import banking.store.entity.WithdrawEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WithdrawRepository extends JpaRepository<WithdrawEntity, Long> {
}
