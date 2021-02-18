package com.nintendo.shop.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nintendo.shop.models.Genre;
import com.nintendo.shop.repository.GameRespository;
import com.nintendo.shop.repository.GenreRepository;

@CrossOrigin
@RestController
@RequestMapping(value = "/genre")
public class GenreController {

	@Autowired
	GenreRepository gr;

	@GetMapping(value = "")
	public List<Genre> getGenres() {
		return gr.getGenres();
	}
	
	@GetMapping(value="/{id}")
	public Genre getGenreById(@PathVariable int id) {
		return gr.getGenreById(id);
	}

}
