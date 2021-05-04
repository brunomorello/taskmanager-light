package br.com.bmo.taskmanagerlight.api.manufacturer;

import java.net.URI;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/api/manufacturer")
public class ManufacturerController {

	@Autowired
	private ManufacturerService service;

	@GetMapping("/{id}")
	public ResponseEntity<ManufacturerView> findById(@PathVariable Long id) {
		try {
			return ResponseEntity.ok(service.findById(id));
		} catch (Exception e) {
			return ResponseEntity.notFound().build();
		}
	}

	@GetMapping("/search")
	public ResponseEntity<ManufacturerListView> findQueryParams(@RequestParam Map<String, String> queryMap) {
		service.findByQueryParams(queryMap);
		return ResponseEntity.notFound().build();
	}

	@PostMapping("/")
	public ResponseEntity<?> create(@Valid @RequestBody ManufacturerForm form, UriComponentsBuilder uriBuilder) {

		ManufacturerView view = service.createManufacturer(form);

		URI uri = uriBuilder.path("/api/manufacturer/{id}").buildAndExpand(view.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}

	@PutMapping("/{id}")
	public ResponseEntity<ManufacturerView> update(@Valid @RequestBody ManufacturerForm form, @PathVariable Long id) {
		try {
			return ResponseEntity.ok(service.updateManufacturer(id, form));
		} catch (Exception e) {
			return ResponseEntity.notFound().build();
		}
	}
}
