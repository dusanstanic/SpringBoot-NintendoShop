package com.nintendo.shop.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nintendo.shop.dto.ConsoleDto;
import com.nintendo.shop.dto.GameDto;
import com.nintendo.shop.models.Console;
import com.nintendo.shop.models.Game;
import com.nintendo.shop.models.GameConsole;
import com.nintendo.shop.models.Image;
import com.nintendo.shop.repository.ConsoleRepository;
import com.nintendo.shop.repository.GameRespository;
import com.nintendo.shop.repository.ImageRepository;

@Service
public class GameService {

	@Autowired
	ConsoleService consoleService;

	@Autowired
	GameRespository gr;

	@Autowired
	ConsoleRepository cr;;

	@Autowired
	ImageRepository ir;

	public List<GameDto> getAllGames() {
		List<GameDto> gameDtos = new ArrayList<>();
		List<Game> games = gr.getGames();

		for (Game game : games) {
			GameDto gameDto = convertGameToGameDto(game);

			List<Image> images = ir.getImagesByGameId(game.getId());
			for (Image image : images) {
				gameDto.getImages().add(image);
			}

			gameDtos.add(gameDto);
		}

		return gameDtos;
	}

	public List<GameDto> getAllGamesContaining(String search) {
		List<GameDto> gameDtos = new ArrayList<>();
		List<Game> games = gr.getAllGamesContaining(search);

		if (games != null) {
			for (Game game : games) {
				GameDto gameDto = convertGameToGameDto(game);

				List<Image> images = ir.getImagesByGameId(game.getId());
				for (Image image : images) {
					gameDto.getImages().add(image);
				}

				gameDtos.add(gameDto);
			}
		}

		return gameDtos;
	}

	public GameDto getGameDtoById(int id) {
		Game game = gr.getGameById(id);
		GameDto gameDto = convertGameToGameDto(game);
		return gameDto;
	}

	public boolean save(Game game) {
		Game savedGame = gr.save(game);
		return true;
	}

	public boolean updateGameDto(Game game) {
		gr.update(game);
		return true;
	}

	public GameDto convertGameToGameDto(Game game) {
		GameDto gameDto = new GameDto();

		gameDto.setId(game.getId());
		gameDto.setTitle(game.getTitle());
		gameDto.setPrice(game.getPrice());
		gameDto.setReleaseDate(game.getReleaseDate());
		gameDto.setDescription(game.getDescription());
		gameDto.setPgRating(game.getPgRating());
		gameDto.setImage(game.getImage());
		gameDto.setGenre(game.getGenre());
		gameDto.setStatus(game.getStatus());

		List<ConsoleDto> consoleDtos = consoleService.convertConsolesToConsoleDtos(game.getConsoles());
		gameDto.setConsoles(consoleDtos);

		return gameDto;
	}

}

/*
 * /* List<Console> consoles = gameDto.getConsoles();
 * 
 * List<GameConsole> gameConsoles = gcr.getGameConsolesByGameId(game.getId());
 * 
 * // List of all unpicked consoles for this game List<GameConsole>
 * gameConsolesToRemove = gameConsoles.stream() .filter(gc ->
 * consoles.stream().noneMatch(c -> gc.getConsole().getId() == c.getId()))
 * .collect(Collectors.toList()); System.out.println("*********************");
 * System.out.println(gameConsolesToRemove);
 * System.out.println("*********************"); for (GameConsole gameConsole :
 * gameConsolesToRemove) { gcr.delete(gameConsole.getId()); }
 * 
 * // Add it will not add duplicate for (Console console : consoles) {
 * GameConsole gameConsole = new GameConsole(); gameConsole.setGame(game);
 * gameConsole.setConsole(console); List<GameConsole> gameConsoleExists =
 * gcr.getGameConsoleByGameAndConsoleId(game.getId(), console.getId()); if
 * (gameConsoleExists.size() == 0) { gcr.createGameConsole(gameConsole); } }
 */
