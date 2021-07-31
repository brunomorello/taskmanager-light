package br.com.bmo.taskmanagerlight.api.goods.product;

import java.net.URI;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.bmo.taskmanagerlight.api.goods.GoodsView;
import br.com.bmo.taskmanagerlight.shared.domain.goods.Goods;

@RequestMapping("/api/product/")
@RestController
public class ProductController {

	@Autowired
	private ProductService service;
	
	@GetMapping("{id}")
	public ResponseEntity<GoodsView> findById(@PathVariable Long id) {
		try  {
			return ResponseEntity.ok(service.getGoodsById(id)); 
		} catch (Exception e) {
			return ResponseEntity.notFound().build();
		}
	}

	@GetMapping("search")
	public ResponseEntity<Page<ProductView>> findByQueryParams(
								@RequestParam(defaultValue = "0") Integer pageNum,
								@RequestParam(defaultValue = "10") Integer pageSize,
								@RequestParam(defaultValue = "id") String sortBy,
								@RequestParam MultiValueMap<String, String> queryParams) {
		if(!queryParams.isEmpty()) {
			Page<ProductView> result = service.queryProductsBy(queryParams, pageNum, pageSize, sortBy);
			return ResponseEntity.ok(result);
		}
		return ResponseEntity.badRequest().build();
	}
	
	@PostMapping
	public ResponseEntity<?> create(@Valid @RequestBody ProductForm form, UriComponentsBuilder uriBuilder) {

		Goods product = service.create(form);
		URI uri = uriBuilder.path("/api/product/{id}").buildAndExpand(product.getId()).toUri();
		return ResponseEntity.created(uri).build();
		
	}
	
	@PutMapping("{id}")
	public ResponseEntity<GoodsView> update(@Valid @RequestBody ProductForm form, @PathVariable Long id) {
		
		try {
			return ResponseEntity.ok(service.update(form, id));
		} catch (Exception e) {
			return ResponseEntity.notFound().build();
		}
	}

	@GetMapping
	public ResponseEntity<Page<ProductView>> getAllGoods(
								@RequestParam(defaultValue = "0") Integer pageNum,
								@RequestParam(defaultValue = "10") Integer pageSize,
								@RequestParam(defaultValue = "id") String sortBy) {
		return ResponseEntity.ok(service.getAllGoods(pageNum, pageSize, sortBy));
	}
	
}
