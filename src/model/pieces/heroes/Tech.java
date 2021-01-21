package model.pieces.heroes;

import java.awt.Point;
import java.net.NetworkInterface;

import exceptions.GameActionException;
import exceptions.InvalidPowerTargetException;
import exceptions.PowerAlreadyUsedException;
import exceptions.WrongTurnException;
import model.game.Cell;
import model.game.Direction;
import model.game.Game;
import model.game.Player;
import model.pieces.Piece;

public class Tech extends ActivatablePowerHero {

	public Tech(Player player, Game game, String name) {
		super(player, game, name);
	}

	@Override
	public void usePower(Direction d, Piece target, Point newPos) throws GameActionException {
		if (getGame().getCurrentPlayer() != getOwner()) {
			throw new WrongTurnException(this);
		}
		if(isPowerUsed()){
			throw new PowerAlreadyUsedException(this);
		}
		if (getOwner() == target.getOwner()) {
			if (newPos != null) { // TELEPORT
				Cell newLoc = getGame().getCellAt(newPos.x, newPos.y);
				if (newLoc.getPiece() != null) {
					throw new InvalidPowerTargetException(this, target);
				}
				if (getOwner() != target.getOwner()) {
					throw new InvalidPowerTargetException(this, target);
				}

				if (getGame().getCellAt(getPosI(), getPosJ()).getPiece() == null) {
					throw new InvalidPowerTargetException(this, target);
				}
				
				int newI = newPos.x;
				int newJ = newPos.y; //newPos(1,0)
				getGame().getCellAt(target.getPosI(), target.getPosJ()).setPiece(null);
				target.setPosI(newI);
				target.setPosJ(newJ);
				
				getGame().getCellAt(newI, newJ).setPiece(target);
				
				setPowerUsed(true);
			} else if (target instanceof Armored) { // getting armor up again
				if (((Armored) target).isArmorUp() == false) {
					((Armored) target).setArmorUp(true);
					setPowerUsed(true);
				} else {
					throw new InvalidPowerTargetException(this, target);
				}
			} else if (target instanceof ActivatablePowerHero) {
				if(((ActivatablePowerHero) target).isPowerUsed() == false) {
					throw new InvalidPowerTargetException(this, target);
				}
				if (((ActivatablePowerHero) target).isPowerUsed() == true) {
					((ActivatablePowerHero) target).setPowerUsed(false);	
					setPowerUsed(true);
				
				} 
				

			} 

		} 
		if (getOwner() != target.getOwner()) { //HACK
			if (target instanceof Armored) {
				
				if (((Armored) target).isArmorUp() == true) {
					((Armored) target).setArmorUp(false);
					setPowerUsed(true);
					
				} else {
					throw new InvalidPowerTargetException(this, target);
				}
			}
			if (target instanceof ActivatablePowerHero) {
				if(((ActivatablePowerHero) target).isPowerUsed()==true){
					throw new InvalidPowerTargetException(this, target);
				}
				if (((ActivatablePowerHero) target).isPowerUsed() == true) {
					((ActivatablePowerHero) target).setPowerUsed(false);
					setPowerUsed(true);
				} 
			}
		}
		
	}

}
