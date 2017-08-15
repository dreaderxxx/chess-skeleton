package chess.pieces;

import chess.GameState;
import chess.Move;
import chess.Player;
import chess.Position;

import java.util.Collection;
import java.util.stream.Stream;

/**
 * A base class for chess pieces
 */
public abstract class Piece {
    private final Player owner;

    protected Piece(Player owner) {
        this.owner = owner;
    }

    public char getIdentifier() {
        char id = getIdentifyingCharacter();
        if (owner.equals(Player.White)) {
            return Character.toLowerCase(id);
        } else {
            return Character.toUpperCase(id);
        }
    }

    public Player getOwner() {
        return owner;
    }

    /**
     * Generating stream of possible moves
     *
     * @param start initial position
     * @return stream of possible position for his piece
     */
    public Stream<Move> getPossibleMoves(Position start, final GameState gameState) {
        return generatePositions(start, gameState)
                .filter(Position::isValid)
                .filter(p -> isNotFriendlyFire(p, gameState))
                .map(end -> new Move(start, end));
    }

    /**
     * Check whether position is valid and accumulates it
     *
     * @param positions collector for positions
     * @param pos       candidate position
     * @param gameState state
     * @return true if no more possible moves(last move in direction)
     */
    protected boolean isEndPosition(Collection<Position> positions, Position pos, GameState gameState) {
        positions.add(pos);
        return gameState.getPieceAt(pos) != null;
    }

    /**
     * Checks if specified position is valid to move for
     *
     * @param position  target position
     * @param gameState state
     * @return true if position free or occupied by opponent, false otherwise
     */
    private boolean isNotFriendlyFire(Position position, GameState gameState) {
        return gameState.getPieceAt(position) == null || gameState.getPieceAt(position).getOwner() != getOwner();
    }

    protected abstract Stream<Position> generatePositions(Position start, GameState gameState);

    protected abstract char getIdentifyingCharacter();
}
