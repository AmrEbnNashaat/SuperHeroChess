package model.pieces.heroes;

import exceptions.GameActionException;
import model.game.Direction;
import model.game.Game;
import model.game.Player;

public class Speedster extends NonActivatablePowerHero {

	public Speedster(Player player, Game game, String name) {
		super(player, game, name);
	}

	public void moveUp() throws GameActionException {
		int oldJ = this.getPosJ();
		int oldI = this.getPosI();
		int newI;
		int newJ;
		if (oldI == 0) {
			newI = 5;
			newJ = oldJ;

		} else if (oldI == 1) {
			newI = 6;
			newJ = oldJ;

		} else {
			newJ = oldJ;
			newI = oldI - 2;
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
			newI = 1;
		} else if (oldI == 5) {
			newJ = oldJ;
			newI = 0;

		} else {
			newJ = oldJ;
			newI = oldI + 2;
		}
		attackMove(oldJ, oldI, newI, newJ, Direction.DOWN);

	}

	public void moveRight() throws GameActionException {
		int oldJ = this.getPosJ();
		int oldI = this.getPosI();
		int newJ;
		int newI;
		if (oldJ == 5) {
			newJ = 1;
			newI = oldI;
		} else if (oldJ == 4) {
			newJ = 0;
			newI = oldI;

		} else {
			newJ = oldJ + 2;
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
			newJ = 4;
			newI = oldI;

		} else if (oldJ == 1) {
			newJ = 5;
			newI = oldI;
		} else {
			newJ = oldJ - 2;
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
			newI = 1;
			newJ = 4;
		} else if (oldI == 5 && oldJ == 1) {
			newI = 0;
			newJ = 5;
		} else if (oldI == 5 && oldJ == 0) {
			newI = 0;
			newJ = 4;
		} else if (oldI == 6 && oldJ == 1) {
			newI = 1;
			newJ = 5;
		} else if (oldJ == 0) {
			newI = oldI + 2;
			newJ = 4;
		} else if (oldJ == 1) {//
			newI = oldI + 2;
			newJ = 5;
		} else if (oldI == 6) {
			newI = 1;
			newJ = oldJ - 2;
		} else if (oldI == 5) {//
			newI = 0;
			newJ = oldJ - 2;
		} else {
			newI = oldI + 2;
			newJ = oldJ - 2;
		}
		attackMove(oldJ, oldI, newI, newJ, Direction.DOWNLEFT);
	}

	public void moveDownRight() throws GameActionException {
		int oldJ = this.getPosJ();
		int oldI = this.getPosI();
		int newJ;
		int newI;
		if (oldI == 6 && oldJ == 5) {
			newJ = 1;
			newI = 1;
		} else if (oldI == 5 && oldJ == 4) {
			newJ = 0;
			newI = 0;
		} else if (oldI == 5 && oldJ == 5) {
			newJ = 1;
			newI = 0;
		} else if (oldI == 6 && oldJ == 4) {
			newJ = 0;
			newI = 1;
		} else if (oldJ == 5) {
			newI = oldI + 2;
			newJ = 1;
		} else if (oldJ == 4) {
			newI = oldI + 2;
			newJ = 0;
		} else if (oldI == 6) {
			newI = 1;
			newJ = oldJ + 2;
		} else if (oldI == 5) {
			newJ = oldJ + 2;
			newI = 0;
		} else {
			newI = oldI + 2;
			newJ = oldJ + 2;
		}
		attackMove(oldJ, oldI, newI, newJ, Direction.DOWNRIGHT);

	}

	public void moveUpRight() throws GameActionException {
		int oldJ = this.getPosJ();
		int oldI = this.getPosI();
		int newJ;
		int newI;
		if (oldI == 0 && oldJ == 5) {
			newI = 5;
			newJ = 1;

		} else if (oldI == 1 && oldJ == 4) {
			newI = 6;
			newJ = 0;
		} else if (oldI == 1 && oldJ == 5) {
			newI = 5;
			newJ = 0;

		} else if (oldI == 0 && oldJ == 4) {
			newI = 5;
			newJ = 0;
		} else if (oldJ == 5) {
			newJ = 1;
			newI = oldI - 2;
		} else if (oldJ == 4) {
			newJ = 0;
			newI = oldI - 2;
		} else if (oldI == 0) {
			newJ = oldJ + 2;
			newI = 5;

		} else if (oldI == 1) {
			newI = 6;
			newJ = oldJ + 2;
		} else {
			newI = oldI - 2;
			newJ = oldJ + 2;
		}
		attackMove(oldJ, oldI, newI, newJ, Direction.UPRIGHT);

	}

	public void moveUpLeft() throws GameActionException {
		int oldJ = this.getPosJ();
		int oldI = this.getPosI();
		int newJ;
		int newI;
		if (oldI == 0 && oldJ == 0) {
			newI = 5;
			newJ = 4;
		} else if (oldI == 1 && oldJ == 1) {
			newI = 6;
			newJ = 5;
		} else if (oldI == 1 && oldJ == 0) {
			newI = 6;
			newJ = 4;
		} else if (oldI == 0 && oldJ == 1) {
			newI = 5;
			newJ = 5;
		} else if (oldI == 0) {
			newI = 5;
			newJ = oldJ - 2;
		} else if (oldI == 1) {
			newI = 6;
			newJ = oldJ - 2;
		} else if (oldJ == 0) {
			newI = oldI - 2;
			newJ = 4;
		} else if (oldJ == 1) {
			newI = oldI - 2;
			newJ = 5;
		} else {
			newI = oldI - 2;
			newJ = oldJ - 2;
		}
		attackMove(oldJ, oldI, newI, newJ, Direction.UPLEFT);

	}

}
