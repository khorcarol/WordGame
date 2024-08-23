package stk31.WordGame.word_game;

import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.*;

import stk31.WordGame.word_game.grid.*;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.io.File;

/**
 * Game This is a basic class that can be modified to create a word game.
 * <p>
 * Hint: Can this class be converted into a singleton?
 *
 * @author Stephen Cummins
 * @version 1.0 Released 11/10/2005
 */
public class Game extends JPanel {
    private static final long serialVersionUID = 1L;
    private static Game game;
    private static String currentword = "";
    private String basePath = new File("").getAbsolutePath(); // Get the absolute path of the current directory
    private final String relativePath = "src\\main\\resources\\words.txt"; // Relative path
    private final String filePath = Paths.get(basePath, relativePath).toString(); // Combine paths
    private final ArrayList<Tile> tileselection = new ArrayList<Tile>();
    private int currentscore = 0;

    private JLabel scoreLabel;


    /**
     * Creates an instance of the Game.
     */
    private Game() {
        // build the GUI as soon as the default constructor is called.
        buildGUI();
    }

    public static Game getGame() {
        if (game == null) {
            game = new Game();
        }
        return game;
    }

    public boolean validMove(Tile tile, Grid grid) {
        if (tileselection.isEmpty()) {
            return true;
        }
        Point lastTilePoint = grid.positionOf(tileselection.getLast());
        Point newTilePoint = grid.positionOf(tile);
        return (Math.abs(newTilePoint.getX() - lastTilePoint.getX()) <= 1.0) && (Math.abs(newTilePoint.getY() - lastTilePoint.getY()) <= 1.0);
    }


    public void setCurrentWord() {
        String submit = "";
        for (Tile t : tileselection) {
            submit = submit.concat(String.valueOf(t.letter()));
        }
        currentword = submit.toLowerCase();

    }

    public void setCurrentScore() {
        int val = 0;
        for (Tile t : tileselection) {
            val += t.value();
        }
        currentscore += val * tileselection.size();
    }

    public boolean checkWord() {
        System.out.println((filePath));
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            setCurrentWord();
            System.out.println("Current word is " + currentword);
            while ((line = reader.readLine()) != null) {
                if (currentword.equals(line)) {
                    setCurrentScore();
                    System.out.println("Valid word");
                    System.out.println("Total score: " + currentscore);
                    return true;
                }
            }
            System.out.println("Invalid word");
            return false;

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void deactivate(Tile tile, Grid grid, GridGUI gui) {
        tile.active(false);
        gui.setTileBackground(grid.positionOf(tile), Color.darkGray);
        gui.setTileForeground(grid.positionOf(tile), Color.lightGray);

    }

    public void activate(Tile tile, Grid grid, GridGUI gui) {
        tile.active(true);
        gui.setTileForeground(grid.positionOf(tile), Color.yellow);
        gui.setTileBackground(grid.positionOf(tile), Color.blue);

    }

    /**
     * This method will construct each element of the game's GUI
     */
    public void buildGUI() {
        final JFrame frame = new JFrame("Java Word Game");
        TileCollection collection = new TileCollection();
        final Grid grid = new Grid(6, 6, collection);
        final GridGUI gui = new GridGUI(grid);
        gui.setTileForeground(Color.yellow);
        gui.setTileBackground(Color.blue);

        JPanel controls = new JPanel();
        JPanel test = new JPanel();
        JPanel wordEntry = new JPanel();
        JPanel buttons = new JPanel();

        buttons.setLayout(new BorderLayout());
        wordEntry.setLayout(new BorderLayout());
        controls.setLayout(new BorderLayout());

        scoreLabel = new JLabel("Total Score: 0");
        JLabel val = new JLabel("");
        controls.add(scoreLabel);
        test.add(val);

        JButton newGame = new JButton("New Game");
        newGame.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                game = null;
                getGame();
            }
        });


        JButton submit = new JButton("Submit");

        submit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (checkWord()) {
                    for (Tile t : tileselection) {
                        deactivate(t, grid, gui);
                    }
                    val.setText("Valid Word: " + currentword);

                } else {
                    for (Tile t : tileselection) {
                        activate(t, grid, gui);
                    }
                    val.setText("Invalid Word: " + currentword);
                }
                tileselection.clear();
                scoreLabel.setText("Total Score: " + currentscore);
            }
        });


        gui.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent actionEvent) {
                Tile source = (Tile) actionEvent.getSource();
                if (source.checkActive() && validMove(source, grid)) {
                    gui.setTileBackground(grid.positionOf(source), Color.red);
                    gui.setTileForeground(grid.positionOf(source), Color.green);
                    source.active(false);
                    // changes flag on tile to show
                    // used;

                    //add to arraylist of letter pressed
                    tileselection.add(source);
                } else if (tileselection.contains(source)) {
                    int index = tileselection.indexOf(source);
                    for (int i = tileselection.size(); i > index; i--) {
                        Tile last = tileselection.removeLast();
                        activate(last, grid, gui);
                    }
                }

            }
        });

        frame.setTitle("Java Word Game");
        JPanel northPanel = new JPanel();
        northPanel.setLayout(new FlowLayout());
        buttons.setLayout(new FlowLayout());

        frame.getContentPane().setLayout(new BorderLayout());
        frame.getContentPane().add(gui, BorderLayout.WEST);
        frame.getContentPane().add(buttons, BorderLayout.SOUTH);
        frame.getContentPane().add(northPanel, BorderLayout.NORTH);

        northPanel.add(controls);
        northPanel.add(test);

        buttons.add(submit);
        buttons.add(newGame);

        frame.pack();
        frame.setResizable(false);
        frame.toFront();

        frame.setBackground(Color.lightGray);
        frame.setVisible(true);
    }
}