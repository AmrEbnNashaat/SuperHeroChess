package model.pieces;

import exceptions.GameActionException;
import exceptions.OccupiedCellException;
import exceptions.UnallowedMovementException;
import exceptions.WrongTurnException;
import model.game.Direction;
import model.game.Game;
import model.game.Player;
import model.pieces.heroes.Armored;
import model.pieces.heroes.Medic;
import model.pieces.heroes.Super;
import model.pieces.heroes.Tech;
import model.pieces.sidekicks.SideKickP1;
import model.pieces.sidekicks.SideKickP2;

public abstract class Piece implements Movable {

	private String name;
	private Player owner;
	private Game game;
	private int posI;
	private int posJ;

	public Piece(Player p, Game g, String name) {
		this.owner = p;
		this.game = g;
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public int getPosI() {
		return posI;
	}

	public void setPosI(int i) {
		posI = i;
	}

	public int getPosJ() {
		return posJ;
	}

	public void setPosJ(int j) {
		posJ = j;
	}

	public Game getGame() {
		return game;
	}

	public Player getOwner() {
		return owner;
	}

	abstract public void attack(Piece target);

	public void move(Direction r) throws GameActionException {
		if(getOwner() != getGame().getCurrentPlayer()) {
			throw new WrongTurnException(this);
		}
		if(this instanceof Super && (r == Direction.DOWNLEFT || r == Direction.DOWNRIGHT || r == Direction.UPLEFT || r == Direction.UPRIGHT)) {
			throw new UnallowedMovementException(this, r);
		}
		if(this instanceof Medic && (r == Direction.DOWNLEFT || r == Direction.DOWNRIGHT || r == Direction.UPLEFT || r == Direction.UPRIGHT)) {
			throw new UnallowedMovementException(this, r);
		}
		if(this instanceof Tech && (r == Direction.UP || r == Direction.DOWN || r == Direction.LEFT || r == Direction.RIGHT)) {
			throw new UnallowedMovementException(this, r);
		}
		if(this instanceof SideKickP1 && (r ==  Direction.DOWNLEFT || r == Direction.DOWNRIGHT || r == Direction.DOWN)) {
			throw new UnallowedMovementException(this, r);
		}
		if(this instanceof SideKickP2 && (r ==  Direction.UPRIGHT || r == Direction.UPLEFT || r == Direction.UP)) {
			throw new UnallowedMovementException(this, r);
		}
		
		switch (r) {
		case UP:
			moveUp();
			break;
		case DOWN:
			moveDown();
			break;
		case LEFT:
			moveLeft();
			break;
		case RIGHT:
			moveRight();
			break;
		case DOWNRIGHT:
			moveDownRight();
			break;
		case DOWNLEFT:
			moveDownLeft();
			break;
		case UPRIGHT:
			moveUpRight();
			break;
		case UPLEFT:
			moveUpLeft();
			break;
		}
		getGame().switchTurns();
	}


	public void attackMove(int oldJ, int oldI, int newI, int newJ, Direction r) throws GameActionException {
		Piece target = getGame().getCellAt(newI, newJ).getPiece();
		boolean ArmorCheck = false;

		if (target != null) {
			if (getOwner() != target.getOwner()) {
				if (target instanceof Armored) {
					ArmorCheck = ((Armored) target).isArmorUp();
				}
				attack(target);
			} else
				throw new OccupiedCellException(this, r);
		}
		if (!ArmorCheck) { //if armor is down, then we need to ATTACK
			Piece piece = getGame().getCellAt(oldI, oldJ).getPiece();
			piece.setPosI(newI);
			piece.setPosJ(newJ);
			getGame().getCellAt(newI, newJ).setPiece(piece);
			getGame().getCellAt(oldI, oldJ).setPiece(null);
		}
	}
	public void moveUp() throws GameActionException {
		int oldJ = this.getPosJ(); // 0
		int oldI = this.getPosI(); // 0
		int newI;
		int newJ;
		if (oldI == 0) { // WRAPPING
			newI = 6; // (6,0)
			newJ = oldJ; // 0

		} else { // NO WRAPPING
			newJ = oldJ;
			newI = oldI - 1;
		}

		attackMove(oldJ, oldI, newI, newJ, Direction.UP);
	}


	public void moveDown() throws GameActionException {
		int oldJ = this.getPosJ();
		int oldI = this.getPosI();
		int newJ;
		int newI;
		if (oldI == 6) {
			newJ = oldJ;
			newI = 0;
		} else {
			newJ = oldJ;
			newI = oldI + 1;

		}
		attackMove(oldJ, oldI, newI, newJ, Direction.DOWN);

	}

	public void moveRight() throws GameActionException {
		int oldJ = this.getPosJ();
		int oldI = this.getPosI();
		int newJ;
		int newI;
		if (oldJ == 5) {
			newJ = 0;
			newI = oldI;

		} else {
			newJ = oldJ + 1;
			newI = oldI;

		}
		attackMove(oldJ, oldI, newI, newJ, Direction.RIGHT);

	}

	public void moveLeft() throws GameActionException {
		int oldJ = this.getPosJ();
		int oldI = this.getPosI();
		int newJ;
		int newI;
		if (oldJ == 0) {
			newJ = 5;
			newI = oldI;

		} else {
			newJ = oldJ - 1;
			newI = oldI;

		}
		attackMove(oldJ, oldI, newI, newJ, Direction.LEFT);

	}

	public void moveDownLeft() throws GameActionException {
		int oldJ = this.getPosJ();
		int oldI = this.getPosI();
		int newJ;
		int newI;
		if (oldI == 6 && oldJ == 0) {
			newJ = 5;
			newI = 0;
		} else if (oldJ == 0) {
			newI = oldI + 1;
			newJ = 5;
		} else if (oldI == 6) {
			newI = 0;
			newJ = oldJ - 1;
		} else {
			newI = oldI + 1;
			newJ = oldJ - 1;
		}
		attackMove(oldJ, oldI, newI, newJ, Direction.DOWNLEFT);
	}

	public void moveDownRight() throws GameActionException {
		int oldJ = this.getPosJ();
		int oldI = this.getPosI();
		int newJ;
		int newI;
		if (oldI == 6 && oldJ == 5) {
			newJ = 0;
			newI = 0;
		} else if (oldJ == 5) {
			newI = oldI + 1;
			newJ = 0;
		} else if (oldI == 6) {
			newI = 0;
			newJ = oldJ + 1;
		} else {
			newI = oldI + 1;
			newJ = oldJ + 1;
		}
		attackMove(oldJ, oldI, newI, newJ, Direction.DOWNRIGHT);

	}

	public void moveUpRight() throws GameActionException {
		int oldJ = this.getPosJ();
		int oldI = this.getPosI();
		int newJ;
		int newI;
		if (oldI == 0 && oldJ == 5) {
			newI = 6;
			newJ = 0;
		} else if (oldJ == 5) {
			newJ = 0;
			newI = oldI - 1;
		} else if (oldI == 0) {
			newJ = oldJ + 1;
			newI = 6;
		} else {
			newI = oldI - 1;
			newJ = oldJ + 1;
		}
		attackMove(oldJ, oldI, newI, newJ, Direction.UPRIGHT);

	}

	public void moveUpLeft() throws GameActionException {
		int oldJ = this.getPosJ();
		int oldI = this.getPosI();
		int newJ;
		int newI;
		if (oldI == 0 && oldJ == 0) {
			newI = 6;
			newJ = 5;
		} else if (oldI == 0) {
			newI = 6;
			newJ = oldJ - 1;
		} else if (oldJ == 0) {
			newI = oldI - 1;
			newJ = 5;
		} else {
			newI = oldI - 1;
			newJ = oldJ - 1;
		}
		attackMove(oldJ, oldI, newI, newJ, Direction.UPLEFT);

	}

}
