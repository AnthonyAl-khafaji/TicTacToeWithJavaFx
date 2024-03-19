package application;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class ExtendedTicTacToe extends Application {
    private static final int BOARD_SIZE = 5;
    private static final int WINNING_LENGTH = 5;
    private Button[][] buttons;
    private boolean playerXTurn = true;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Extended Tic Tac Toe");

        GridPane gridPane = new GridPane();
        buttons = new Button[BOARD_SIZE][BOARD_SIZE];
        
        for (int row = 0; row < BOARD_SIZE; row++) {
            for (int col = 0; col < BOARD_SIZE; col++) {
                Button button = new Button();
                button.setMinSize(50, 50); // Adjust the size as needed
                button.setOnAction(e -> {
                    handleButtonClick(button);
                });
                buttons[row][col] = button;
                gridPane.add(button, col, row);
            }
        }

        Scene scene = new Scene(gridPane, 300, 300);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void handleButtonClick(Button button) {
        if (!button.getText().isEmpty()) {
            return; // Already marked, ignore
        }

        if (playerXTurn) {
            button.setText("X");
        } else {
            button.setText("O");
        }

        if (checkForWin()) {
            // Handle win condition
            System.out.println((playerXTurn ? "X" : "O") + " wins!");
            // You can add more here, like displaying a dialog box
        } else {
            playerXTurn = !playerXTurn; // Switch turns
        }
    }

    private boolean checkForWin() {
        // Check rows
        for (int row = 0; row < BOARD_SIZE; row++) {
            for (int col = 0; col <= BOARD_SIZE - WINNING_LENGTH; col++) {
                boolean win = true;
                for (int i = 0; i < WINNING_LENGTH; i++) {
                    if (!buttons[row][col + i].getText().equals(playerXTurn ? "X" : "O")) {
                        win = false;
                        break;
                    }
                }
                if (win) {
                    return true;
                }
            }
        }

        // Check columns
        for (int col = 0; col < BOARD_SIZE; col++) {
            for (int row = 0; row <= BOARD_SIZE - WINNING_LENGTH; row++) {
                boolean win = true;
                for (int i = 0; i < WINNING_LENGTH; i++) {
                    if (!buttons[row + i][col].getText().equals(playerXTurn ? "X" : "O")) {
                        win = false;
                        break;
                    }
                }
                if (win) {
                    return true;
                }
            }
        }

        // Check diagonals (top-left to bottom-right)
        for (int row = 0; row <= BOARD_SIZE - WINNING_LENGTH; row++) {
            for (int col = 0; col <= BOARD_SIZE - WINNING_LENGTH; col++) {
                boolean win = true;
                for (int i = 0; i < WINNING_LENGTH; i++) {
                    if (!buttons[row + i][col + i].getText().equals(playerXTurn ? "X" : "O")) {
                        win = false;
                        break;
                    }
                }
                if (win) {
                    return true;
                }
            }
        }

        // Check diagonals (top-right to bottom-left)
        for (int row = 0; row <= BOARD_SIZE - WINNING_LENGTH; row++) {
            for (int col = WINNING_LENGTH - 1; col < BOARD_SIZE; col++) {
                boolean win = true;
                for (int i = 0; i < WINNING_LENGTH; i++) {
                    if (!buttons[row + i][col - i].getText().equals(playerXTurn ? "X" : "O")) {
                        win = false;
                        break;
                    }
                }
                if (win) {
                    return true;
                }
            }
        }

        return false; // No winner yet
    }

}
