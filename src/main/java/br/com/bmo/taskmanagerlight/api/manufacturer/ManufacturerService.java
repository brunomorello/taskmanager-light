package br.com.bmo.taskmanagerlight.api.manufacturer;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.util.MultiValueMap;

import br.com.bmo.taskmanagerlight.shared.domain.manufacturer.Manufacturer;
import br.com.bmo.taskmanagerlight.shared.exceptions.ResourceNotFoundException;

@Service
public class ManufacturerService {

	@Autowired
	private ManufacturerRepository repository;
	
	private final ManufacturerViewFactory viewFactory = new ManufacturerViewFactory();

	public ManufacturerView findById(Long id) {
		return repository.findById(id).map(viewFactory::factory).orElseThrow(() -> new ResourceNotFoundException("Cannot find Manufacturer"));
	}
	
	public ManufacturerListView findByDisplayNameLike(String name) {
		List<Manufacturer> manufacturesFound = repository.findByDisplayNameLike(name);
		
		if (manufacturesFound.size() == 0) {
			throw new ResourceNotFoundException("Cannot find manufacturers");
		}
		
		return new ManufacturerListView(manufacturesFound);
	}
	
	public ManufacturerView createManufacturer(ManufacturerForm form) {
		return viewFactory.factory(repository.save(form.parse()));
	}

	public ManufacturerView updateManufacturer(Long id, @Valid ManufacturerForm form) {
		repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Cannot find Manufacturer"));
		Manufacturer manufacturer = form.parse();
		manufacturer.setId(id);
		return viewFactory.factory(repository.save(manufacturer));
	}

	public ManufacturerListView findByAddressLike(String address) {
		List<Manufacturer> manufacturersFoundByAddress = repository.findByAddressLike(address);
		
		if (manufacturersFoundByAddress.size() == 0)
			throw new ResourceNotFoundException("Cannot find manufacturers");
		
		return new ManufacturerListView(manufacturersFoundByAddress);
	}


	public Page<ManufacturerView> queryManufacturerBy(MultiValueMap<String, String> queryParams, Integer pageNum, Integer pageSize,
			String sortBy) {
		
		PageRequest paging = PageRequest.of(pageNum, pageSize, Sort.by(sortBy));
		ManufacturerSpecificationBuilder specBuilder = new ManufacturerSpecificationBuilder();
		Specification<Manufacturer> spec = specBuilder.with(queryParams.toSingleValueMap()).build();
		
		Page<Manufacturer> result = repository.findAll(spec, paging);
		
		if (result.hasContent())
			return result.map(viewFactory::factory);
		
		return null;
	}
	
}
