package com.nintendo.shop.models;

import java.io.Serializable;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.List;


/**
 * The persistent class for the genre database table.
 * 
 */
@Entity
@NamedQuery(name="Genre.findAll", query="SELECT g FROM Genre g")
public class Genre implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int id;

	private String type;

	//bi-directional many-to-one association to Game
	@JsonIgnore
	@OneToMany(mappedBy="genre")
	private List<Game> games;

	public Genre() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public List<Game> getGames() {
		return this.games;
	}

	public void setGames(List<Game> games) {
		this.games = games;
	}

	public Game addGame(Game game) {
		getGames().add(game);
		game.setGenre(this);

		return game;
	}

	public Game removeGame(Game game) {
		getGames().remove(game);
		game.setGenre(null);

		return game;
	}

	@Override
	public String toString() {
		return "Genre [id=" + id + ", type=" + type + ", games=" + games + "]";
	}
	
	

}