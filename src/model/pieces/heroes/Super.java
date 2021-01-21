package model.pieces.heroes;

import java.awt.Point;

import exceptions.GameActionException;
import exceptions.InvalidPowerDirectionException;
import exceptions.OccupiedCellException;
import exceptions.PowerAlreadyUsedException;
import exceptions.WrongTurnException;
import model.game.Direction;
import model.game.Game;
import model.game.Player;
import model.pieces.Piece;

public class Super extends ActivatablePowerHero {

	public Super(Player player, Game game, String name) {
		super(player, game, name);
	}

	public void usePower(Direction d, Piece target, Point newPos) throws GameActionException {

		if (getGame().getCurrentPlayer() != getOwner()) {
			throw new WrongTurnException(this);
		}
		if (isPowerUsed()) {
			throw new PowerAlreadyUsedException(this);
		} else {
			setPowerUsed(true);
		}
		
			switch (d) {
			case UP:
				if (getPosI() >= 2) {
					Piece targetU2 = getGame().getCellAt(getPosI() - 2, getPosJ()).getPiece();

					if (targetU2 != null) {
						if (targetU2.getOwner() != this.getOwner()) {
							attack(targetU2);
						} 
					}
				}
				if (getPosI() >= 1) {
					Piece targetU1 = getGame().getCellAt(getPosI() - 1, getPosJ()).getPiece();

					if (targetU1 != null) {
						if (targetU1.getOwner() != this.getOwner()) {
							attack(targetU1);
						} 
					}
				}
				break;
			case DOWN:

				if (getPosI() <= 4) {
					Piece target2 = getGame().getCellAt(getPosI() + 2, getPosJ()).getPiece();

					if (target2 != null) {
						if (target2.getOwner() != getOwner()) {
							attack(target2);
						} 
					}
				}
				if (getPosI() <= 5) {
					Piece target1 = getGame().getCellAt(getPosI() + 1, getPosJ()).getPiece();

					if (target1 != null) {
						if (target1.getOwner() != this.getOwner()) {
							attack(target1);
						} 
					}
				}
				break;
			case LEFT:
				if (getPosJ() >= 2) {
					Piece targetD2 = getGame().getCellAt(getPosI(), getPosJ() - 2).getPiece();

					if (targetD2 != null) {
						if (targetD2.getOwner() != this.getOwner()) {
							attack(targetD2);
						} 
					}

				}
				if (getPosJ() >= 1) {
					Piece targetD1 = getGame().getCellAt(getPosI(), getPosJ() - 1).getPiece();

					if (targetD1 != null) {
						if (targetD1.getOwner() != this.getOwner()) {
							attack(targetD1);
						} 
					}
				}
				break;
			case RIGHT:
				if (getPosJ() <= 3) {
					Piece targetR2 = getGame().getCellAt(getPosI(), getPosJ() + 2).getPiece();

					if (targetR2 != null) {
						if (targetR2.getOwner() != this.getOwner()) {
							attack(targetR2);
							
						}
					}
				}
				 if (getPosJ() <= 4) {
						Piece targetR1 = getGame().getCellAt(getPosI(), getPosJ() + 1).getPiece();

					if (targetR1 != null) {
						if (targetR1.getOwner() != this.getOwner()) {
							attack(targetR1);
							
						}
					}
				}
				break;
			default:
				throw new InvalidPowerDirectionException("wrong", this, d);
			} 
		
			
		
		this.setPowerUsed(true);
		getGame().switchTurns();

	}

}
