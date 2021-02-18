package com.nintendo.shop.models;

import java.io.Serializable;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;


/**
 * The persistent class for the image database table.
 * 
 */
@Entity
@NamedQuery(name="Image.findAll", query="SELECT i FROM Image i")
public class Image implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int id;

	private String destination;

	//bi-directional many-to-one association to Console
	@JsonIgnore
	@ManyToOne
	private Console console;

	//bi-directional many-to-one association to Game
	@JsonIgnore
	@ManyToOne
	private Game game;

	public Image() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDestination() {
		return this.destination;
	}

	public void setDestination(String destination) {
		this.destination = destination;
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

}