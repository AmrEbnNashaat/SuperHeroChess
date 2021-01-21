package model.pieces.heroes;

import model.game.Game;
import model.game.Player;
import model.pieces.Piece;
import model.pieces.sidekicks.SideKick;
public abstract class Hero extends Piece {

	public Hero(Player player, Game game, String name) {
		super(player, game, name);
	}
	
	
	public void attack(Piece target) {
		//If currentPlayer attacking Owner of Target piece, exception
		if(getOwner() == target.getOwner()) {
			//EXCEPTION
			
		} else if(target instanceof Armored) {
			if(((Armored) target).isArmorUp() == true) { //if true					
				((Armored) target).setArmorUp(false);
			}
			else {
				getOwner().setPayloadPos(getOwner().getPayloadPos() + 1); //Adding 1 to PayLoad Pos
				target.getOwner().getDeadCharacters().add(target); 
				target.getGame().getCellAt(target.getPosI(), target.getPosJ()).setPiece(null);
				getGame().checkWinner();

			}
		}
		else { //Beginning of Else
			if(target instanceof Hero) { //If hero attacking Hero
				getOwner().setPayloadPos(getOwner().getPayloadPos() + 1); //Adding 1 to PayLoad Pos
				target.getOwner().getDeadCharacters().add(target); 
				target.getGame().getCellAt(target.getPosI(), target.getPosJ()).setPiece(null);
				getGame().checkWinner();
			}
			
			
			}
			if(target instanceof SideKick) {
				//EL SIDEKICK + 1/2
				
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
			
			
		} //end of Else

			
	}

