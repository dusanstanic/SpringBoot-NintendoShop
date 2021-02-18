package com.nintendo.shop.service;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nintendo.shop.dto.ConsoleDto;
import com.nintendo.shop.models.Console;
import com.nintendo.shop.repository.ConsoleRepository;

@Service
public class ConsoleService {
	
	@Autowired
	ConsoleRepository consoleRepository;
	
	public List<ConsoleDto> getAllConsoles() {		
		List<Console> consoles = consoleRepository.getConsoles();
		return convertConsolesToConsoleDtos(consoles);
	}
	
	public List<ConsoleDto> getAllGamesContaining(String search) {		
		List<Console> consoles = consoleRepository.getAllGamesContaining(search);
		return convertConsolesToConsoleDtos(consoles);
	}
	
	public List<String> findAllConsoleTypes() {
		return consoleRepository.getConsoleTypes();
	}
	 
	public List<ConsoleDto> convertConsolesToConsoleDtos(List<Console> consoles) {
		List<ConsoleDto> consoleDtos = new ArrayList<>();
		
		for(Console console : consoles) {
			ConsoleDto consoleDto = convertConsoleToConsoleDto(console);
			consoleDtos.add(consoleDto);
		}
		
		return consoleDtos;
	}
	
	public ConsoleDto convertConsoleToConsoleDto(Console console) {
		ConsoleDto consoleDto = new ConsoleDto();
		
		consoleDto.setId(console.getId());
		consoleDto.setTitle(console.getTitle());
		consoleDto.setPrice(console.getPrice());
		consoleDto.setDescription(console.getDescription());
		consoleDto.setReleaseDate(console.getReleaseDate());
		consoleDto.setCondition(console.getCondition());
		consoleDto.setType(console.getType());
		consoleDto.setImage(console.getImage());
		consoleDto.setLogo(console.getLogo());
		
		return consoleDto;
	}
	
	public boolean save(Console console) {
		consoleRepository.save(console);
		return true;
	}
	
	public boolean delete(int id) {
		consoleRepository.delete(id);
		return true;
	}
	
}
