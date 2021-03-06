package com.edza.crud.resources;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.edza.crud.dto.UserDTO;
import com.edza.crud.services.UserService;

@RestController
@RequestMapping(value = "/users")
public class UserResource {
	
	@Autowired
	private UserService service;
	
	@GetMapping
	public ResponseEntity<List<UserDTO>> findAll() {
		List<UserDTO> list = service.findAll();
		return ResponseEntity.ok().body(list);
	}
	
	@GetMapping(value = "/{codigo}")
	public ResponseEntity<UserDTO> findByCodigo(@PathVariable Long codigo) {
		UserDTO userDto = service.findByCodigo(codigo);
		return ResponseEntity.ok().body(userDto);
	}

	@PostMapping
	public ResponseEntity<UserDTO> insert(@RequestBody UserDTO dto) {
		dto = service.insert(dto);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{codigo}")
				.buildAndExpand(dto.getCodigo()).toUri();
		return ResponseEntity.created(uri).body(dto);
	}
	
	@PutMapping(value = "/{codigo}")
	public ResponseEntity<UserDTO> update(@PathVariable Long codigo, @RequestBody UserDTO dto) {
		dto = service.update(codigo, dto);
		return ResponseEntity.ok().body(dto);
	}
	
	@DeleteMapping(value = "/{codigo}")
	public ResponseEntity<UserDTO> delete(@PathVariable Long codigo) {
		service.delete(codigo);
		return ResponseEntity.noContent().build();
	}
}
