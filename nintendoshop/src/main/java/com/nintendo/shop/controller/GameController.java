package com.nintendo.shop.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nintendo.shop.dto.GameDto;
import com.nintendo.shop.models.Game;
import com.nintendo.shop.models.GameConsole;
import com.nintendo.shop.repository.GameRespository;
import com.nintendo.shop.service.GameService;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping(value = "/games")
public class GameController {

	@Autowired
	GameRespository gr;

	@Autowired
	GameService gs;

	@GetMapping(value = "")
	public List<GameDto> getGames() {
		return gs.getAllGames();
	}

	@GetMapping(value = "/{id}")
	public GameDto getGameById(@PathVariable int id) {
		return gs.getGameDtoById(id);
	}
	
	@PostMapping(value = "/contain")
	public List<GameDto> getAllGamesContaining(@RequestBody String search) {
		System.out.println("******" + search.replace("=", "") + "*********");
		return gs.getAllGamesContaining(search.replace("=", "").replace("+", " "));
	}

	@PostMapping
	public ResponseEntity<Boolean> createGame(@RequestBody Game game) {
		boolean savedGame = gs.save(game);
		return ResponseEntity.ok(savedGame);
	}

	@PutMapping
	public ResponseEntity<Boolean> update(@RequestBody Game game) {
		boolean updateGame = gs.updateGameDto(game);
		return ResponseEntity.ok(updateGame);
	}

	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Boolean> delete(@PathVariable int id) {
		boolean deleteGame = gr.delete(id);
		return ResponseEntity.ok(deleteGame);
	}

	@GetMapping(value = "/pgRating/{pgRatings}")
	public List<Game> getGamesByPgRatings(@PathVariable List<String> pgRatings) {
		return gr.getGamesByPgRatings(pgRatings);
	}

}
