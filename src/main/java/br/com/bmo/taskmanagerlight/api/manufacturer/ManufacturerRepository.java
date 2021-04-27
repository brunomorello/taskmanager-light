package br.com.bmo.taskmanagerlight.api.manufacturer;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.bmo.taskmanagerlight.api.shared.domain.manufacturer.Manufacturer;

@Repository
public interface ManufacturerRepository extends JpaRepository<Manufacturer, Long>{

	List<Manufacturer> findAll();
	Optional<Manufacturer> findByFormalName(String formalName);
	
	@Query("SELECT m FROM Manufacturer m WHERE m.displayName LIKE %:displayName%")
	List<Manufacturer> findByDisplayNameLike(String displayName);
	
	@Query("SELECT m FROM Manufacturer m WHERE m.address LIKE %:address%")
	List<Manufacturer> findByAddressLike(String address);
}
