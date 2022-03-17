package banking.store.repository;

import banking.store.entity.BillEntity;
import banking.store.entity.ClientEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BillRepository extends JpaRepository<BillEntity, Long> {
    Optional<BillEntity> findByMainBillAndClient(boolean b, ClientEntity client);
}
