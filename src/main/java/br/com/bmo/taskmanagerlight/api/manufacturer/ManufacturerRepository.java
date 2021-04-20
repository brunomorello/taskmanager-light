package br.com.bmo.taskmanagerlight.api.manufacturer;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ManufacturerRepository extends JpaRepository<Manufacturer, Long>{

	List<Manufacturer> findAll();
	Optional<Manufacturer> findByFormalName(String formalName);
	Optional<Manufacturer> findByDisplayName(String displayName);
	
	@Query("SELECT m from Manufacturer m WHERE m.address LIKE %:address%")
	List<Manufacturer> findByAddressLike(String address);
}
