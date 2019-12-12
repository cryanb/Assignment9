import javax.swing.JFrame;

public class Main {

	public static void main(String[] args) {
		buildGameViews();
	}

	public static void buildGameViews() {
		JFrame window = new JFrame("TicTacToe");
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.getContentPane().add(new GameView());
		window.setBounds(300, 200, 300, 300);
		window.setVisible(true);
	}
}
