package model.pieces.heroes;

import java.awt.Point;

import exceptions.GameActionException;
import exceptions.InvalidPowerDirectionException;
import exceptions.InvalidPowerTargetException;
import exceptions.PowerAlreadyUsedException;
import exceptions.WrongTurnException;
import model.game.Direction;
import model.game.Game;
import model.game.Player;
import model.pieces.Piece;

public class Medic extends ActivatablePowerHero {

	public Medic(Player player, Game game, String name) {
		super(player, game, name);
	}

	public void usePower(Direction d, Piece target, Point newPos)
			throws GameActionException {
		// beyraga3 target men el deadcharacters
		// powerUsed == false bta3 el piece
		// if piece is killed, payLoad is not updated
		if (target != null) {
			target.setPosI(getPosI());
			target.setPosJ(getPosJ());
		}
		if (getGame().getCurrentPlayer() != getOwner()) {
			throw new WrongTurnException(this);
		}
		if (this.isPowerUsed() == true) {
			throw new PowerAlreadyUsedException(this);
		}
		if (getOwner() != target.getOwner()) {
			throw new InvalidPowerTargetException(this, target);
		}
		if (!getGame().getCurrentPlayer().getDeadCharacters().contains(target)) {
			throw new InvalidPowerTargetException(this, target);
		}
		getOwner().getDeadCharacters().remove(target);

		switch (d) {
		case UP:
			if (target.getPosI() <= 0) {
				target.setPosI(6);
			}
			if (getGame().getCellAt(target.getPosI() - 1, target.getPosJ())
					.getPiece() != null) {

				throw new InvalidPowerTargetException(this, target);
			}

			getGame().getCellAt(target.getPosI() - 1, target.getPosJ())
					.setPiece(target);
			target.setPosI(target.getPosI() - 1);
			target.setPosJ(target.getPosJ());
			if (target instanceof Armored
					&& ((Armored) target).isArmorUp() == false) {
				((Armored) target).setArmorUp(true);
			}
			if (target instanceof ActivatablePowerHero
					&& ((ActivatablePowerHero) target).isPowerUsed() == true) {
				((ActivatablePowerHero) target).setPowerUsed(true);
			}

			getGame().switchTurns();
			setPowerUsed(true);

			break;
		case DOWN:

			if (target.getPosI() >= 6) {

				target.setPosI(0);
			}
			if (getGame().getCellAt(target.getPosI() + 1, target.getPosJ())
					.getPiece() != null) {

				throw new InvalidPowerTargetException(this, target);
			}
			getGame().getCellAt(target.getPosI() + 1, target.getPosJ())
					.setPiece(target);
			target.setPosI(target.getPosI() + 1);
			target.setPosJ(target.getPosJ());
			if (target instanceof Armored
					&& ((Armored) target).isArmorUp() == false) {
				((Armored) target).setArmorUp(true);
			}
			if (target instanceof ActivatablePowerHero
					&& ((ActivatablePowerHero) target).isPowerUsed() == true) {
				((ActivatablePowerHero) target).setPowerUsed(true);
			}
			getGame().switchTurns();
			setPowerUsed(true);

			break;
		case RIGHT:

			if (target.getPosJ() >= 5) {

				target.setPosJ(0);

			}

			if (getGame().getCellAt(target.getPosI(), target.getPosJ() + 1)
					.getPiece() != null) {

				throw new InvalidPowerTargetException(this, target);
			}
			getGame().getCellAt(target.getPosI(), target.getPosJ() + 1)
					.setPiece(target);
			target.setPosI(target.getPosI());
			target.setPosJ(target.getPosJ() + 1);
			if (target instanceof Armored
					&& ((Armored) target).isArmorUp() == false) {
				((Armored) target).setArmorUp(true);
			}
			if (target instanceof ActivatablePowerHero
					&& ((ActivatablePowerHero) target).isPowerUsed() == true) {
				((ActivatablePowerHero) target).setPowerUsed(true);
			}
			getGame().switchTurns();
			setPowerUsed(true);

			break;
		case LEFT:
			if (target.getPosJ() <= 0) {
				target.setPosJ(5);
			}
			if (getGame().getCellAt(target.getPosI(), target.getPosJ() - 1)
					.getPiece() != null) {
				throw new InvalidPowerTargetException(this, target);
			}
			getGame().getCellAt(target.getPosI(), target.getPosJ() - 1)
					.setPiece(target);
			target.setPosI(target.getPosI());
			target.setPosJ(target.getPosJ() - 1);
			if (target instanceof Armored
					&& ((Armored) target).isArmorUp() == false) {
				((Armored) target).setArmorUp(true);
			}
			if (target instanceof ActivatablePowerHero
					&& ((ActivatablePowerHero) target).isPowerUsed() == true) {
				((ActivatablePowerHero) target).setPowerUsed(true);
			}
			getGame().switchTurns();
			setPowerUsed(true);

			break;
		case UPRIGHT:
			if (target.getPosI() <= 0)
				target.setPosI(6);
			if (target.getPosJ() >= 5)
				target.setPosJ(0);
			if ((target.getPosI() > 0 && target.getPosJ() < 5)) {

				if (getGame().getCellAt(target.getPosI() - 1,
						target.getPosJ() + 1).getPiece() != null) {
					throw new InvalidPowerTargetException(this, target);
				}

				getGame().getCellAt(target.getPosI() - 1, target.getPosJ() + 1)
						.setPiece(target);
				target.setPosI(target.getPosI() - 1);
				target.setPosJ(target.getPosJ() + 1);
				if (target instanceof Armored
						&& ((Armored) target).isArmorUp() == false) {
					((Armored) target).setArmorUp(true);
				}
				if (target instanceof ActivatablePowerHero
						&& ((ActivatablePowerHero) target).isPowerUsed() == true) {
					((ActivatablePowerHero) target).setPowerUsed(true);
				}
				getGame().switchTurns();
				setPowerUsed(true);
			}
			break;
		case UPLEFT:
			if (target.getPosI() <= 0)
				target.setPosI(6);
			if (target.getPosJ() <= 0)
				target.setPosJ(5);
			if (getGame().getCellAt(target.getPosI() - 1, target.getPosJ() - 1)
					.getPiece() != null) {
				throw new InvalidPowerTargetException(this, target);
			}
			getGame().getCellAt(target.getPosI() - 1, target.getPosJ() - 1)
					.setPiece(target);
			target.setPosI(target.getPosI() - 1);
			target.setPosJ(target.getPosJ() - 1);
			if (target instanceof Armored
					&& ((Armored) target).isArmorUp() == false) {
				((Armored) target).setArmorUp(true);
			}
			if (target instanceof ActivatablePowerHero
					&& ((ActivatablePowerHero) target).isPowerUsed() == true) {
				((ActivatablePowerHero) target).setPowerUsed(true);
			}
			setPowerUsed(true);
			break;
		case DOWNRIGHT:
			if (target.getPosI() >= 6)
				target.setPosI(0);
			if (target.getPosJ() >= 5)
				target.setPosJ(0);
			if (getGame().getCellAt(target.getPosI() + 1, target.getPosJ() + 1)
					.getPiece() != null) {
				throw new InvalidPowerTargetException(this, target);
			}
			getGame().getCellAt(target.getPosI() + 1, target.getPosJ() + 1)
					.setPiece(target);
			target.setPosI(target.getPosI() + 1);
			target.setPosJ(target.getPosJ() + 1);
			if (target instanceof Armored
					&& ((Armored) target).isArmorUp() == false) {
				((Armored) target).setArmorUp(true);
			}
			if (target instanceof ActivatablePowerHero
					&& ((ActivatablePowerHero) target).isPowerUsed() == true) {
				((ActivatablePowerHero) target).setPowerUsed(true);
			}
			getGame().switchTurns();
			setPowerUsed(true);
			break;
		case DOWNLEFT:
			if (target.getPosI() >= 6)
				target.setPosI(0);
			if (target.getPosJ() <= 0)
				target.setPosJ(5);
			if (getGame().getCellAt(target.getPosI() + 1, target.getPosJ() - 1)
					.getPiece() != null) {

				throw new InvalidPowerTargetException(this, target);
			}

			getGame().getCellAt(target.getPosI() + 1, target.getPosJ() - 1)
					.setPiece(target);
			target.setPosI(target.getPosI() + 1);
			target.setPosJ(target.getPosJ() - 1);
			if (target instanceof Armored
					&& ((Armored) target).isArmorUp() == false) {
				((Armored) target).setArmorUp(true);
			}
			if (target instanceof ActivatablePowerHero
					&& ((ActivatablePowerHero) target).isPowerUsed() == true) {
				((ActivatablePowerHero) target).setPowerUsed(true);
			}
			getGame().switchTurns();
			setPowerUsed(true);
			break;
		default:
			throw new InvalidPowerTargetException(this, target);
		}

	}

}
