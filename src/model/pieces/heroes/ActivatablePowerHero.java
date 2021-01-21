package model.pieces.heroes;

import java.awt.Point;

import exceptions.GameActionException;
import model.game.Direction;
import model.game.Game;
import model.game.Player;
import model.pieces.Piece;

public abstract class ActivatablePowerHero extends Hero {

	private boolean powerUsed;

	public ActivatablePowerHero(Player player, Game game, String name) {
		super(player, game, name);
	}

	public boolean isPowerUsed() {
		return powerUsed;
	}

	public void setPowerUsed(boolean powerUsed) {
		this.powerUsed = powerUsed;
	}

	abstract public void usePower(Direction d, Piece target, Point newPos) throws GameActionException;
	
}
