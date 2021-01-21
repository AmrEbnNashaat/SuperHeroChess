package model.pieces.heroes;

import java.awt.Point;

import exceptions.GameActionException;
import exceptions.InvalidPowerDirectionException;
import exceptions.PowerAlreadyUsedException;
import exceptions.WrongTurnException;
import model.game.Direction;
import model.game.Game;
import model.game.Player;
import model.pieces.Piece;

public class Ranged extends ActivatablePowerHero {

	public Ranged(Player player, Game game, String name) {
		super(player, game, name);
	}

	public void usePower(Direction d, Piece target, Point newPos) throws GameActionException {

		if (getGame().getCurrentPlayer() != getOwner()) {
			throw new WrongTurnException(this);
		}
		if (isPowerUsed()) {
			throw new PowerAlreadyUsedException(this);
		} else {
			setPowerUsed(true); }
		target = null;
		int posI = getPosI();
		int posJ = getPosJ();
		try {
			switch (d) {
			case UP:
				while (target == null) {
					posI = posI -1;
					target = getGame().getCellAt(posI, posJ).getPiece();
				}

			case DOWN:
				while (target == null) {
					posI = posI + 1;
					target = getGame().getCellAt(posI, posJ).getPiece();
				}
				break;
			case LEFT:
				while (target == null) {
					posJ = posJ - 1;
					target = getGame().getCellAt(posI, posJ).getPiece();
				}
				break;
			case RIGHT:
				while (target == null) {
					posJ = posJ + 1;
					target = getGame().getCellAt(posI, posJ).getPiece();
				}
				break;
			default:
				throw new InvalidPowerDirectionException(this, d);
			}

			if (target.getOwner() == getOwner())
				throw new InvalidPowerDirectionException(this, d);
			attack(target);
		} catch (ArrayIndexOutOfBoundsException e) {
			throw new InvalidPowerDirectionException(this, d);
		}
		getGame().switchTurns();
	}

}
