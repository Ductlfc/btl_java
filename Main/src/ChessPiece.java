public class ChessPiece {
    public PieceType type;
    public PieceColor color;
    public boolean hasMoved;
    public ChessPiece(PieceType type, PieceColor color) {
        this.type = type;
        this.color = color;
        this.hasMoved = false;
    }
}
