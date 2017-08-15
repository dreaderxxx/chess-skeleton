package chess;

import chess.pieces.*;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Stream;

/**
 * Class that represents the current state of the game.  Basically, what pieces are in which positions on the
 * board.
 */
public class GameState {

    /**
     * The current player
     */
    private Player currentPlayer = Player.White;

    /**
     * A map of board positions to pieces at that position
     */
    private Map<Position, Piece> positionToPieceMap;

    /**
     * Create the game state.
     */
    public GameState() {
        positionToPieceMap = new HashMap<>();
    }

    public Player getCurrentPlayer() {
        return currentPlayer;
    }

    public Player getNextPlayer() {
        return currentPlayer == Player.White ? Player.Black : Player.White;
    }


    /**
     * Call to initialize the game state into the starting positions
     */
    public void reset() {
        // White Pieces
        placePiece(new Rook(Player.White), new Position("a1"));
        placePiece(new Knight(Player.White), new Position("b1"));
        placePiece(new Bishop(Player.White), new Position("c1"));
        placePiece(new Queen(Player.White), new Position("d1"));
        placePiece(new King(Player.White), new Position("e1"));
        placePiece(new Bishop(Player.White), new Position("f1"));
        placePiece(new Knight(Player.White), new Position("g1"));
        placePiece(new Rook(Player.White), new Position("h1"));
        placePiece(new Pawn(Player.White), new Position("a2"));
        placePiece(new Pawn(Player.White), new Position("b2"));
        placePiece(new Pawn(Player.White), new Position("c2"));
        placePiece(new Pawn(Player.White), new Position("d2"));
        placePiece(new Pawn(Player.White), new Position("e2"));
        placePiece(new Pawn(Player.White), new Position("f2"));
        placePiece(new Pawn(Player.White), new Position("g2"));
        placePiece(new Pawn(Player.White), new Position("h2"));

        // Black Pieces
        placePiece(new Rook(Player.Black), new Position("a8"));
        placePiece(new Knight(Player.Black), new Position("b8"));
        placePiece(new Bishop(Player.Black), new Position("c8"));
        placePiece(new Queen(Player.Black), new Position("d8"));
        placePiece(new King(Player.Black), new Position("e8"));
        placePiece(new Bishop(Player.Black), new Position("f8"));
        placePiece(new Knight(Player.Black), new Position("g8"));
        placePiece(new Rook(Player.Black), new Position("h8"));
        placePiece(new Pawn(Player.Black), new Position("a7"));
        placePiece(new Pawn(Player.Black), new Position("b7"));
        placePiece(new Pawn(Player.Black), new Position("c7"));
        placePiece(new Pawn(Player.Black), new Position("d7"));
        placePiece(new Pawn(Player.Black), new Position("e7"));
        placePiece(new Pawn(Player.Black), new Position("f7"));
        placePiece(new Pawn(Player.Black), new Position("g7"));
        placePiece(new Pawn(Player.Black), new Position("h7"));
    }

    /**
     * Get the piece at the position specified by the String
     *
     * @param colrow The string indication of position; i.e. "d5"
     * @return The piece at that position, or null if it does not exist.
     */
    public Piece getPieceAt(String colrow) {
        Position position = new Position(colrow);
        return getPieceAt(position);
    }

    /**
     * Get the piece at a given position on the board
     *
     * @param position The position to inquire about.
     * @return The piece at that position, or null if it does not exist.
     */
    public Piece getPieceAt(Position position) {
        return positionToPieceMap.get(position);
    }

    /**
     * @return Get all possible moves
     */
    public Stream<Move> moves() {
        return getAllMovesForPlayer(getCurrentPlayer()).filter(this::isValidMove);
    }

    /**
     * Method to move a piece from a given position to next position
     *
     * @param from The place of piece to move
     * @param to   The place where to move
     * @return true if move was successful, false otherwise
     */
    public boolean move(Position from, Position to) {
        Piece piece = getPieceAt(from);
        if (piece != null && piece.getOwner() == getCurrentPlayer()) {
            final Move move = new Move(from, to);
            if (piece.getPossibleMoves(from, this).filter(this::isValidMove).anyMatch(m -> m.equals(move))) {
                doMove(move);
                return true;
            }
        }
        return false;
    }

    /**
     * @return true if game is over by checkmate, false otherwise
     */
    public boolean isCheckMate() {
        return moves().count() == 0 && isCheck(this, getNextPlayer());
    }

    /**
     * @return true if game is over by draw, false otherwise
     */
    public boolean isDraw() {
        return moves().count() == 0 && !isCheck(this, getNextPlayer());
    }

    private boolean isCheck(GameState gameState, Player player) {
        return gameState.getAllMovesForPlayer(player)
                .anyMatch(m -> gameState.getPieceAt(m.getTo()) != null
                        && gameState.getPieceAt(m.getTo()) instanceof King);
    }

    private void doMove(Move move) {
        placePiece(positionToPieceMap.remove(move.getFrom()), move.getTo());
        changePlayer();
    }

    private Stream<Move> getAllMovesForPlayer(final Player player) {
        return positionToPieceMap.entrySet().stream()
                .filter(e -> e.getValue().getOwner() == player)
                .map(e -> e.getValue().getPossibleMoves(e.getKey(), this))
                .flatMap(Function.identity());
    }

    /**
     * Method to place a piece at a given position
     *
     * @param piece    The piece to place
     * @param position The position
     */
    private void placePiece(Piece piece, Position position) {
        positionToPieceMap.put(position, piece);
    }

    /**
     * Changes {@link #currentPlayer} to opponent
     */
    private void changePlayer() {
        currentPlayer = getNextPlayer();
    }

    /**
     * Verifies if specified move is valid(king is not under attack) and can be executed
     *
     * @param move {@link Move}
     * @return true if move is valid(king is not under attack)
     */
    private boolean isValidMove(Move move) {
        GameState copy = copyState();
        copy.doMove(move);
        return !isCheck(copy, copy.getCurrentPlayer());
    }

    private GameState copyState() {
        GameState state = new GameState();
        positionToPieceMap.forEach((position, piece) -> state.placePiece(piece, position));
        state.currentPlayer = getCurrentPlayer();
        return state;
    }
}
