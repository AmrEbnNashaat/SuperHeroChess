package model.pieces.sidekicks;

import model.game.Game;
import model.game.Player;
import model.pieces.Piece;
import model.pieces.heroes.Armored;
import model.pieces.heroes.Hero;
import model.pieces.heroes.Medic;
import model.pieces.heroes.Ranged;
import model.pieces.heroes.Speedster;
import model.pieces.heroes.Super;
import model.pieces.heroes.Tech;

public abstract class SideKick extends Piece {

	public SideKick(Player player, Game game, String name) {
		super(player, game, name);
	}

	@Override
	public String toString() {
		return "K";
	}
	public void attack(Piece target) {
		// If currentPlayer attacking Owner of Target piece, exception
		

		if (target instanceof Armored) {
			if (((Armored) target).isArmorUp() == true) { // if true
				((Armored) target).setArmorUp(false);
			} else {
				getOwner().setPayloadPos(getOwner().getPayloadPos() + 1); // Adding 1 to PayLoad Pos
				target.getOwner().getDeadCharacters().add(target);
				target.getGame().getCellAt(target.getPosI(), target.getPosJ()).setPiece(null);
				getGame().checkWinner();

			}
		} else { // Beginning of Else
			if (target instanceof Hero) { // If hero attacking Hero
				getOwner().setPayloadPos(getOwner().getPayloadPos() + 1); // Adding 1 to PayLoad Pos
				target.getOwner().getDeadCharacters().add(target);
				target.getGame().getCellAt(target.getPosI(), target.getPosJ()).setPiece(null);
				switch(target.getClass().getSimpleName()) {
				case "Armored": 
					getGame().getCellAt(getPosI(), getPosJ()).setPiece(new Armored(getOwner(),getGame(),getName()));
				break;
				case "Medic":
					getGame().getCellAt(getPosI(), getPosJ()).setPiece(new Medic(getOwner(),getGame(),getName()));
				break;
				case "Speedster":
					getGame().getCellAt(getPosI(), getPosJ()).setPiece(new Speedster(getOwner(),getGame(),getName()));
				break;
				case "Super":
					getGame().getCellAt(getPosI(), getPosJ()).setPiece(new Super(getOwner(),getGame(),getName()));
				break;
				case "Ranged":
					getGame().getCellAt(getPosI(), getPosJ()).setPiece(new Ranged(getOwner(),getGame(),getName()));
				break;
				case "Tech":
					getGame().getCellAt(getPosI(), getPosJ()).setPiece(new Tech(getOwner(),getGame(),getName()));
				break;
				}
				getGame().checkWinner();
			}

			else {
				if (target instanceof SideKick) {
					getOwner().setSideKilled(getOwner().getSideKilled()+1);
					if(getOwner().getSideKilled()%2 == 0) {
						getOwner().setPayloadPos(getOwner().getPayloadPos()+1);
					} else {
						getOwner().setPayloadPos(getOwner().getPayloadPos());
					}
					target.getOwner().getDeadCharacters().add(target);
					target.getGame().getCellAt(target.getPosI(), target.getPosJ()).setPiece(null);
					getGame().checkWinner();

				}
			}

		}
	}
}
