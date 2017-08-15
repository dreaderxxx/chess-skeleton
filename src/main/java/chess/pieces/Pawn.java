package chess.pieces;

import chess.GameState;
import chess.Player;
import chess.Position;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Stream;

/**
 * The Pawn
 */
public class Pawn extends Piece {
    public Pawn(Player owner) {
        super(owner);
    }

    @Override
    protected char getIdentifyingCharacter() {
        return 'p';
    }

    @Override
    public Stream<Position> generatePositions(Position start, GameState gameState) {

        Set<Position> positions = new HashSet<>();
        // simple one square forward move if free
        Position position = start.moveRelatively(0, sign());
        Piece piece = gameState.getPieceAt(position);
        if (piece == null) {
            positions.add(position);
        }
        // two squares move if free
        Position position2 = start.moveRelatively(0, sign() * 2);
        Piece piece2 = gameState.getPieceAt(position2);
        if (((getOwner() == Player.White && start.getRow() == 2) || (getOwner() == Player.Black && start.getRow() == 7))
                && piece == null && piece2 == null) {
            positions.add(position2);
        }
        // left attack move
        Position left = start.moveRelatively(-1, sign());
        Piece leftPiece = gameState.getPieceAt(left);
        if (leftPiece != null && leftPiece.getOwner() != getOwner()) {
            positions.add(left);
        }
        // right attack move
        Position right = start.moveRelatively(1, sign());
        Piece rightPiece = gameState.getPieceAt(left);
        if (rightPiece != null && rightPiece.getOwner() != getOwner()) {
            positions.add(right);
        }

        return positions.stream();
    }

    private int sign() {
        return getOwner() == Player.White ? 1 : -1;
    }
}
