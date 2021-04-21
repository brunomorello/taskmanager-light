package br.com.bmo.taskmanagerlight.api.manufacturer;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.bmo.taskmanagerlight.api.shared.exceptions.ResourceNotFoundException;

@Service
public class ManufacturerService {

	@Autowired
	private ManufacturerRepository repository;
	
	private final ManufacturerViewFactory viewFactory = new ManufacturerViewFactory();

	public ManufacturerView findById(Long id) {
		return repository.findById(id).map(viewFactory::factory).orElseThrow(() -> new ResourceNotFoundException("Cannot find Manufacturer"));
	}
	
	public ManufacturerView findByDisplayName(String name) {
		return repository.findByDisplayName(name).map(viewFactory::factory).orElseThrow(() -> new ResourceNotFoundException("Cannot find Manufacturer"));
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
	
}
