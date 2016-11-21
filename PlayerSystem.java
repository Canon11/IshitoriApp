public class PlayerSystem {
	Player player1;
	Player player2;
	Player currentPlayer;
	
	class Player {
		String name;
		int stock;
		Player(String name) {
			this.name = name;
			this.stock = 3;
		}
		
		String getName() {
			return this.name;
		}
		
		int getStock() {
			return this.stock;
		}
		
		void reStock() {
			this.stock = 3;
		}
		
		void catchStone() {
			stock--;
		}
	}
	
	public PlayerSystem() {
		player1 = new Player("プレイヤー１");
		player2 = new Player("プレイヤー２");
		currentPlayer = player1;
	}
	
	public void catchStone() {
		currentPlayer.catchStone();
		if (currentPlayer.getStock() == 0) {
			changeTurn();
		}
	}
	
	public void changeTurn() {
		if (currentPlayer == player1) {
			currentPlayer = player2;
			player1.reStock();
		} else {
			currentPlayer = player1;
			player2.reStock();
		}
	}
}
