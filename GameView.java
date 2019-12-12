import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class GameView extends JPanel {
	SymbolFactory sf = new SymbolFactory();
	int turn = 0;

	public GameView() {
		// Task 1: Set the gridlayout
		setLayout(new GridLayout(3, 3));
		initializesquares();
	}

	// Task 2: Instantiate squares
	static JButton squares[] = new JButton[9];

	public void initializesquares() {
		for (int i = 0; i <= 8; i++) {
			squares[i] = new JButton();
			squares[i].setText("");
			squares[i].addActionListener(new buttonListener());
			add(squares[i]);
		}
	}

	class buttonListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			JButton buttonClicked = (JButton) e.getSource();
			if (turn % 2 == 0) {
				GameSymbol gs1 = sf.setSymbol(turn);
				buttonClicked.setText(gs1.getSymbol());
				buttonClicked.setEnabled(false);
				turn++;
			}
			// check if winner after player turn
			checkIfWin();
			computerTurn();
			// check if winner after computer turn
			checkIfWin();
		}
	}

	public void checkIfWin() {
		if (checkForWin() == true) {
			if (turn % 2 == 1) {
				JOptionPane.showMessageDialog(null, "Player Wins");
				System.exit(0);
			} else {
				JOptionPane.showMessageDialog(null, "Computer Wins");
				System.exit(0);
			}
		}
		if (turn == 9 && checkForWin() == false) {
			JOptionPane.showMessageDialog(null, "Tie");
			System.exit(0);
		}
	}

	public boolean checkForWin() {
		// horizontal win check
		if (checkAdjacent(0, 1) && checkAdjacent(1, 2))
			return true;
		else if (checkAdjacent(3, 4) && checkAdjacent(4, 5))
			return true;
		else if (checkAdjacent(6, 7) && checkAdjacent(7, 8))
			return true;

		// vertical win check
		else if (checkAdjacent(0, 3) && checkAdjacent(3, 6))
			return true;
		else if (checkAdjacent(1, 4) && checkAdjacent(4, 7))
			return true;
		else if (checkAdjacent(2, 5) && checkAdjacent(5, 8))
			return true;

		// diagonal win check
		else if (checkAdjacent(0, 4) && checkAdjacent(4, 8))
			return true;
		else if (checkAdjacent(2, 4) && checkAdjacent(4, 6))
			return true;
		else
			return false;
	}

	public boolean checkAdjacent(int a, int b) {
		if (squares[a].getText().equals(squares[b].getText()) && !squares[a].getText().equals(""))
			return true;
		else
			return false;
	}

	public void computerTurn() {
		// random number generator
		int i = (int) (Math.random() * 9);
		GameSymbol gs2 = sf.setSymbol(turn);
		if (squares[i].isEnabled() == true) {
			squares[i].setText(gs2.getSymbol());
			squares[i].setEnabled(false);
			turn++;
		} else {
			while (squares[i].isEnabled() == false && turn != 9) {
				i = (int) (Math.random() * 9);
			}
			squares[i].setText(gs2.getSymbol());
			squares[i].setEnabled(false);
			turn++;
		}
	}
}