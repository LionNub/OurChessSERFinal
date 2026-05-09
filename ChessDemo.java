// Griffin, Hernest, Jared
import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import javax.swing.border.Border;

import java.awt.BorderLayout;

/** Entry point: opens a window containing the chess board demo. */
public class ChessDemo {

    public static void main(String[] args) {
        Board myBoard = new Board();  
		myBoard.boardSetup();
        
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Demo for Chess");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setResizable(false);
            // frame.setContentPane(new ChessBoardPanel());
            frame.setLayout(new BorderLayout());
            StatusPanel statPanel = new StatusPanel();
			CapturedSideBar capturedBar = new CapturedSideBar();
			frame.add(new ChessBoardPanel(myBoard, statPanel), BorderLayout.CENTER); 
			frame.add(capturedBar, BorderLayout.EAST);

            frame.add(statPanel, BorderLayout.NORTH); //
            frame.pack();
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
        });
    }
}
