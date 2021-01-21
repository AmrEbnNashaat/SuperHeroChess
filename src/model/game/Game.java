package model.game;

import java.util.Arrays;
import java.util.Collections;

import model.pieces.Piece;
import model.pieces.heroes.Armored;
import model.pieces.heroes.Medic;
import model.pieces.heroes.Ranged;
import model.pieces.heroes.Speedster;
import model.pieces.heroes.Super;
import model.pieces.heroes.Tech;
import model.pieces.sidekicks.SideKickP1;
import model.pieces.sidekicks.SideKickP2;

public class Game {

	private final int payloadPosTarget = 6;
	private final int boardWidth = 6;
	private final int boardHeight = 7;
	private Player player1;
	private Player player2;
	private Player currentPlayer;
	private Cell[][] board;

	public Game(Player player1, Player player2) {
		this.player1 = player1;
		this.player2 = player2;
		currentPlayer = player1;
		board = new Cell[boardHeight][boardWidth];
		assemblePieces();
		}
	
	

	public Player getCurrentPlayer() {
		return currentPlayer;
	}

	public void setCurrentPlayer(Player currentPlayer) {
		this.currentPlayer = currentPlayer;
	}

	public Player getPlayer1() {
		return player1;
	}

	public Player getPlayer2() {
		return player2;
	}

	public int getPayloadPosTarget() {
		return payloadPosTarget;
	}

	@Override
	public String toString() {
		String s = "";
		System.out.println("      " + getPlayer2().getName());
		System.out.print("| ");
		for (int i = 0; i < board[0].length; i++)
			System.out.print("--");
		System.out.println("|");
		for (int i = 0; i < board.length; i++) {
			System.out.print("| ");
			for (int j = 0; j < board[0].length; j++) {
				if (board[i][j] == null)
					System.out.print("n ");
				else
					System.out.print(board[i][j] + " ");
			}
			System.out.println("|");
		}
		System.out.print("| ");
		for (int i = 0; i < board[0].length; i++)
			System.out.print("--");
		System.out.println("|");
		System.out.println("    " + getPlayer1().getName());
		return s;
	}

	public int getBoardWidth() {
		return boardWidth;
	}

	public int getBoardHeight() {
		return boardHeight;
	}

	public void assemblePieces() {

		// i = 1, j = random from (0 to 5)
		// i = 4, x random from (0 to 5)
		// Collections.shuffle found at StackOverFlow
		Integer[] j = new Integer[6];
		Integer[] x = new Integer[6];
		for (int i = 0; i < j.length; i++) {
			j[i] = i;
			x[i] = i;
		}
		Collections.shuffle(Arrays.asList(j));
		Collections.shuffle(Arrays.asList(x));

		Piece armoredP1 = new Armored(player1, this, "ArmoredP1");
		armoredP1.setPosI(5);
		armoredP1.setPosJ(x[0]);
		board[5][x[0]] = new Cell(armoredP1);
		Piece armoredP2 = new Armored(player2, this, "ArmoredP2");
		armoredP2.setPosI(1);
		armoredP2.setPosJ(j[0]);
		board[1][j[0]] = new Cell(armoredP2);
		
		Piece medicP1 = new Medic(player1, this, "MedicP1");
		medicP1.setPosI(5);
		medicP1.setPosJ(x[1]);
		board[5][x[1]] = new Cell(medicP1);
		Piece medicP2 = new Medic(player2, this, "MedicP2");
		medicP2.setPosI(1);
		medicP2.setPosJ(j[1]);
		board[1][j[1]] = new Cell(medicP2);

		Piece rangedP1 = new Ranged(player1, this, "RangedP1");
		rangedP1.setPosI(5);
		rangedP1.setPosJ(x[2]);
		board[5][x[2]] = new Cell(rangedP1);
		Piece rangedP2 = new Ranged(player2, this, "RangedP2");
		rangedP2.setPosI(1);
		rangedP2.setPosJ(j[2]);
		board[1][j[2]] = new Cell(rangedP2);

		Piece speedsterP1 = new Speedster(player1, this, "SpeedsterP2");
		speedsterP1.setPosI(5);
		speedsterP1.setPosJ(x[3]);
		board[5][x[3]] = new Cell(speedsterP1);
		Piece speedsterP2 = new Speedster(player2, this, "SpeedsterP2");
		speedsterP2.setPosI(1);
		speedsterP2.setPosJ(j[3]);
		board[1][j[3]] = new Cell(speedsterP2);

		Piece superP1 = new Super(player1, this, "SuperP1");
		superP1.setPosI(5);
		superP1.setPosJ(x[4]);
		board[5][x[4]] = new Cell(superP1);
		Piece superP2 = new Super(player2, this, "SuperP2");
		superP2.setPosI(1);
		superP2.setPosJ(j[4]);
		board[1][j[4]] = new Cell(superP2);

		Piece techP1 = new Tech(player1, this, "TechP1");
		techP1.setPosI(5);
		techP1.setPosJ(x[5]);
		board[5][x[5]] = new Cell(techP1);
		Piece techP2 = new Tech(player2, this, "TechP2");
		techP2.setPosI(1);
		techP2.setPosJ(j[5]);
		board[1][j[5]] = new Cell(techP2);

		// Setting Player 1's Sidekicks
		Piece sidekickP1N1 = new SideKickP1(this, "SideKickP1");
		sidekickP1N1.setPosI(4);
		sidekickP1N1.setPosJ(0);
		board[4][0] = new Cell(sidekickP1N1);
		Piece sidekickP1N2 = new SideKickP1(this, "SideKickP1");
		sidekickP1N2.setPosI(4);
		sidekickP1N2.setPosJ(1);
		board[4][1] = new Cell(sidekickP1N2);
		Piece sidekickP1N3 = new SideKickP1(this, "SideKickP1");
		sidekickP1N3.setPosI(4);
		sidekickP1N3.setPosJ(2);
		board[4][2] = new Cell(sidekickP1N3);
		Piece sidekickP1N4 = new SideKickP1(this, "SideKickP1");
		sidekickP1N4.setPosI(4);
		sidekickP1N4.setPosJ(3);
		board[4][3] = new Cell(sidekickP1N4);
		Piece sidekickP1N5 = new SideKickP1(this, "SideKickP1");
		sidekickP1N5.setPosI(4);
		sidekickP1N5.setPosJ(4);
		board[4][4] = new Cell(sidekickP1N5);
		Piece sidekickP1N6 = new SideKickP1(this, "SideKickP1");
		sidekickP1N6.setPosI(4);
		sidekickP1N6.setPosJ(5);
		board[4][5] = new Cell(sidekickP1N6);
		// Setting player 2's sidekicks
		Piece sidekickP2N1 = new SideKickP2(this, "SideKickP1");
		sidekickP2N1.setPosI(2);
		sidekickP1N1.setPosJ(0);
		board[2][0] = new Cell(sidekickP2N1);
		Piece sidekickP2N2 = new SideKickP2(this, "SideKickP1");
		sidekickP2N2.setPosI(2);
		sidekickP2N2.setPosJ(1);
		board[2][1] = new Cell(sidekickP2N2);
		Piece sidekickP2N3 = new SideKickP2(this, "SideKickP1");
		sidekickP2N3.setPosI(2);
		sidekickP2N3.setPosJ(2);
		board[2][2] = new Cell(sidekickP2N3);
		Piece sidekickP2N4 = new SideKickP2(this, "SideKickP1");
		sidekickP2N4.setPosI(2);
		sidekickP2N4.setPosJ(3);
		board[2][3] = new Cell(sidekickP2N4);
		Piece sidekickP2N5 = new SideKickP2(this, "SideKickP1");
		sidekickP2N5.setPosI(2);
		sidekickP2N5.setPosJ(4);
		board[2][4] = new Cell(sidekickP2N5);
		Piece sidekickP2N6 = new SideKickP2(this, "SideKickP1");
		sidekickP2N6.setPosI(2);
		sidekickP2N6.setPosJ(5);
		board[2][5] = new Cell(sidekickP2N6);

		// Defining first empty
		board[0][0] = new Cell();
		board[0][1] = new Cell();
		board[0][2] = new Cell();
		board[0][3] = new Cell();
		board[0][4] = new Cell();
		board[0][5] = new Cell();

		board[3][0] = new Cell();
		board[3][1] = new Cell();
		board[3][2] = new Cell();
		board[3][3] = new Cell();
		board[3][4] = new Cell();
		board[3][5] = new Cell();

		board[6][0] = new Cell();
		board[6][1] = new Cell();
		board[6][2] = new Cell();
		board[6][3] = new Cell();
		board[6][4] = new Cell();
		board[6][5] = new Cell();

	}

	public Cell getCellAt(int i, int j) {
		return board[i][j];
	}

	public void switchTurns() {
		if (currentPlayer == player1) {
			setCurrentPlayer(player2);
		} else
			setCurrentPlayer(player1);
	}

	public boolean checkWinner() {
		if (currentPlayer.getPayloadPos() == 6) {
			return true;
		} else
			return false;
	}

}
