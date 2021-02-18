package com.nintendo.shop.models;



import java.io.Serializable;
import javax.persistence.*;

/**
 * The persistent class for the game_console database table.
 * 
 */
@Entity
@Table(name = "game_console")
@NamedQuery(name = "GameConsole.findAll", query = "SELECT g FROM GameConsole g")
public class GameConsole implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	// bi-directional many-to-one association to Console
	@ManyToOne
	private Console console;

	// bi-directional many-to-one association to Game
	@ManyToOne
	private Game game;

	public GameConsole() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Console getConsole() {
		return this.console;
	}

	public void setConsole(Console console) {
		this.console = console;
	}

	public Game getGame() {
		return this.game;
	}

	public void setGame(Game game) {
		this.game = game;
	}

	@Override
	public String toString() {
		return "GameConsole [id=" + id + ", console=" + console + ", game=" + game + "]";
	}

	
	
}