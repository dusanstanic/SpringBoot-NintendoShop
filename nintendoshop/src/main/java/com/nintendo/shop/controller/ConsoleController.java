package com.nintendo.shop.controller;

import java.util.List;

import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.nintendo.shop.dto.ConsoleDto;
import com.nintendo.shop.dto.GameDto;
import com.nintendo.shop.models.Console;
import com.nintendo.shop.models.Game;
import com.nintendo.shop.repository.ConsoleRepository;
import com.nintendo.shop.service.ConsoleService;

@CrossOrigin
@RestController
@RequestMapping(value = "/consoles")
public class ConsoleController {

	@Autowired
	ConsoleService consoleService;
	
	@GetMapping(value = "")
	public List<ConsoleDto> getConsoles() {
		return consoleService.getAllConsoles();
	}
	
	@GetMapping(value = "/consoleTypes")
	public List<String> findAllConsoleTypes() {
		return consoleService.findAllConsoleTypes();
	}
	
	@PostMapping
	public ResponseEntity<Boolean> save(@RequestBody Console console) {
		boolean isConsoleSaved = consoleService.save(console);
		return ResponseEntity.ok(isConsoleSaved);
	}
	
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Boolean> delete(@PathVariable int id) {
		boolean isConsoleDeleted = consoleService.delete(id);
		return ResponseEntity.ok(isConsoleDeleted);
	}
	
	@PostMapping(value = "/contain")
	public List<ConsoleDto> getAllGamesContaining(@RequestBody String search) {
		System.out.println("******" + search.replace("=", "") + "*********");
		return consoleService.getAllGamesContaining(search.replace("=", "").replace("+", " "));
	}
	
	
}
