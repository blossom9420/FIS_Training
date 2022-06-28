package vn.fis.training.phl.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vn.fis.training.phl.model.Order;

@Repository
public interface OrderRepoitory extends JpaRepository<Order, Long> {
}
