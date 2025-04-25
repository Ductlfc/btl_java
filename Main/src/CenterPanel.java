import javax.swing.*;
import java.awt.*;

public class CenterPanel extends JPanel {
    public CellPanel[][] boardCell = new CellPanel[8][8];

    private BoardState boardState;
    private CellPanel selectedCell;
<<<<<<< HEAD

=======
>>>>>>> a87cc762092c2847fbf1bae42803dd657be04f26
    public CenterPanel() {
        boardState = BoardState.NONE_SELECTED;
        boolean isWhite = true;
        this.setLayout(new GridLayout(8, 8));
        for(int i=0; i<8; i++) {
            for(int j=0; j<8; j++) {
                CellPanel cellPanel = new CellPanel(isWhite, i, j);
                if(i==1 || i==6){
                    cellPanel.AddImage(new ChessPiece(PieceType.TOT, i==1?PieceColor.BLACK : PieceColor.WHITE));
                }
                if(i==0 && (j==0 || j==7)){
                    cellPanel.AddImage(new ChessPiece(PieceType.XE, PieceColor.BLACK));
                }
                if(i==7 && (j==0 || j==7)){
                    cellPanel.AddImage(new ChessPiece(PieceType.XE, PieceColor.WHITE));
                }
                if(i==0 && (j==1 || j==6)){
                    cellPanel.AddImage(new ChessPiece(PieceType.MA, PieceColor.BLACK));
                }
                if(i==7 && (j==1 || j==6)){
                    cellPanel.AddImage(new ChessPiece(PieceType.MA, PieceColor.WHITE));
                }
                if(i==0 && (j==2 || j==5)){
                    cellPanel.AddImage(new ChessPiece(PieceType.TUONG, PieceColor.BLACK));
                }
                if(i==7 && (j==2 || j==5)){
                    cellPanel.AddImage(new ChessPiece(PieceType.TUONG, PieceColor.WHITE));
                }
                if(i==0 && (j==3 || j==4)){
                    cellPanel.AddImage(new ChessPiece(j==3?PieceType.HAU : PieceType.VUA, PieceColor.BLACK));
                }
                if(i==7 && (j==3 || j==4)){
                    cellPanel.AddImage(new ChessPiece(j==3?PieceType.VUA : PieceType.HAU, PieceColor.WHITE));
                }

                this.add(cellPanel);
                boardCell[i][j] = cellPanel;
                isWhite = !isWhite;
            }
            isWhite = !isWhite;
        }
        selectedCell = null;

    }
<<<<<<< HEAD

=======
>>>>>>> a87cc762092c2847fbf1bae42803dd657be04f26
    public void onClickCellPanel(int x, int y) {


        CellPanel clickedCellPanel = boardCell[x][y];
        clickedCellPanel.seLect();

        if(boardState==BoardState.NONE_SELECTED){
            deSelectAllCells();
            if(clickedCellPanel.currentChessPiece!=null){
                switch(clickedCellPanel.currentChessPiece.type){
                    case TOT -> {
                        TotCheck(x,y);
                    }
                    case XE -> {
                        XeCheck(x,y);
                    }
                    case MA -> {
                        MaCheck(x,y);
                    }
                    case TUONG -> {
                        TuongCheck(x,y);
                    }
                    case HAU -> {
                        HauCheck(x,y);
                    }
                    case VUA -> {
                        VuaCheck(x,y);
                    }
                }


                selectedCell = clickedCellPanel;
                boardState = BoardState.PIECE_SELECTED;
            }
            else {

            }
        }
        else if(boardState==BoardState.PIECE_SELECTED){
<<<<<<< HEAD
            if(clickedCellPanel.isValidMove) {
                if(selectedCell.currentChessPiece.type == PieceType.VUA && Math.abs(y - selectedCell.y) == 2) {
                    performCastling(x, y); // Thêm: Gọi hàm xử lý nhập thành
                } else {
                    clickedCellPanel.AddImage(selectedCell.currentChessPiece);
                    selectedCell.currentChessPiece.hasMoved = true; // Thêm: Đánh dấu quân cờ đã di chuyển
                    selectedCell.removePiece();
                }
                selectedCell = null;
=======
            if(clickedCellPanel.isValidMove){
                //move
                clickedCellPanel.AddImage(selectedCell.currentChessPiece);
                selectedCell.removePiece();
                selectedCell=null;
                //chuyen trang thai ve 0 chon
>>>>>>> a87cc762092c2847fbf1bae42803dd657be04f26
                boardState = BoardState.NONE_SELECTED;
                deSelectAllCells();
            }
            else {
                boardState = BoardState.NONE_SELECTED;
                deSelectAllCells();
            }
        }
    }
<<<<<<< HEAD

=======
>>>>>>> a87cc762092c2847fbf1bae42803dd657be04f26
    public void deSelectAllCells() {
        for(int i=0; i<8; i++) {
            for(int j=0; j<8; j++) {
                boardCell[i][j].deSelect();
            }
        }
    }
<<<<<<< HEAD

    // SỬA: Đảm bảo Vua và Xe không bị đặt trùng sau nhập thành
    private void performCastling(int x, int y) {
        ChessPiece king = selectedCell.currentChessPiece;
        int kingOriginY = selectedCell.y;

        // Di chuyển vua
        CellPanel kingDestCell = boardCell[x][y];
        kingDestCell.removePiece();
        kingDestCell.AddImage(king);
        king.hasMoved = true;
        selectedCell.removePiece();

        // Di chuyển xe
        boolean isKingside = y > kingOriginY;
        int rookOriginY = isKingside ? 7 : 0;
        int rookDestY = isKingside ? y - 1 : y + 1; // ✅ đặt Xe bên cạnh Vua

        CellPanel rookCell = boardCell[x][rookOriginY];
        ChessPiece rook = rookCell.currentChessPiece;
        if (rook != null && rook.type == PieceType.XE) {
            CellPanel rookDestCell = boardCell[x][rookDestY];
            rookDestCell.removePiece();
            rookDestCell.AddImage(rook);
            rook.hasMoved = true;
            rookCell.removePiece();
        }
    }


=======
>>>>>>> a87cc762092c2847fbf1bae42803dd657be04f26
    private void TotCheck(int x, int y) {
        ChessPiece thisPiece = boardCell[x][y].currentChessPiece;
        if(thisPiece.color==PieceColor.WHITE){
            int maxMove = (x==6) ? 2 : 1;
            for(int i=x-1;i>=x-maxMove;i--){
                if(!checkValidCoordinate(i,y)) break;
                ChessPiece _chessPiece = boardCell[i][y].currentChessPiece;
                if(_chessPiece!=null){
                    break;
                }
                else {
                    boardCell[i][y].setColor(true);
                }
            }
            if(checkValidCoordinate(x-1,y-1)) {//cheo tren trai
                CellPanel _cellPanel = boardCell[x-1][y-1];
                if(_cellPanel.currentChessPiece!=null){
                    if(_cellPanel.currentChessPiece.color!=thisPiece.color){
                        _cellPanel.setColor(false);
                    }
                }
            }
            if(checkValidCoordinate(x-1,y+1)) {//cheo tren phai
                CellPanel _cellPanel = boardCell[x-1][y+1];
                if(_cellPanel.currentChessPiece!=null){
                    if(_cellPanel.currentChessPiece.color!=thisPiece.color){
                        _cellPanel.setColor(false);
                    }
                }
            }
        }
        else{
            int maxMove = (x==1) ? 2 : 1;
            for(int i=x+1;i<=x+maxMove;i++){
                if(!checkValidCoordinate(i,y)) break;
                ChessPiece _chessPiece = boardCell[i][y].currentChessPiece;
                if(_chessPiece!=null){
                    break;
                }
                else {
                    boardCell[i][y].setColor(true);
                }
            }
            if(checkValidCoordinate(x+1,y-1)) {//cheo tren trai
                CellPanel _cellPanel = boardCell[x+1][y-1];
                if(_cellPanel.currentChessPiece!=null){
                    if(_cellPanel.currentChessPiece.color!=thisPiece.color){
                        _cellPanel.setColor(false);
                    }
                }
            }
            if(checkValidCoordinate(x+1,y+1)) {//cheo tren phai
                CellPanel _cellPanel = boardCell[x+1][y+1];
                if(_cellPanel.currentChessPiece!=null){
                    if(_cellPanel.currentChessPiece.color!=thisPiece.color){
                        _cellPanel.setColor(false);
                    }
                }
            }
        }
    }

    private void XeCheck(int x, int y) {
        ChessPiece thisPiece = boardCell[x][y].currentChessPiece;
        for(int i=x-1;i>=0;i--){
            // len tren
            if(!checkValidCoordinate(i,y)) break;
            CellPanel _cellPanel = boardCell[i][y];
            if(_cellPanel.currentChessPiece!=null){
                if(_cellPanel.currentChessPiece.color!=thisPiece.color){
                    _cellPanel.setColor(false);
                }
                else {

                }
                break;
            }
            else{
                _cellPanel.setColor(true);
            }

        }

        for(int i=x+1;i<8;i++){
            // xuong duoi
            if(!checkValidCoordinate(i,y)) break;
            CellPanel _cellPanel = boardCell[i][y];
            if(_cellPanel.currentChessPiece!=null){
                if(_cellPanel.currentChessPiece.color!=thisPiece.color){
                    _cellPanel.setColor(false);
                }
                else {

                }
                break;
            }
            else{
                _cellPanel.setColor(true);
            }

        }

        for(int j=y-1;y>=0;j--){
            // sang trai
            if(!checkValidCoordinate(x,j)) break;
            CellPanel _cellPanel = boardCell[x][j];
            if(_cellPanel.currentChessPiece!=null){
                if(_cellPanel.currentChessPiece.color!=thisPiece.color){
                    _cellPanel.setColor(false);
                }
                else {

                }
                break;
            }
            else{
                _cellPanel.setColor(true);
            }

        }

        for(int j=y+1;j<8;j++){
            // sang phai
            if(!checkValidCoordinate(x,j)) break;
            CellPanel _cellPanel = boardCell[x][j];
            if(_cellPanel.currentChessPiece!=null){
                if(_cellPanel.currentChessPiece.color!=thisPiece.color){
                    _cellPanel.setColor(false);
                }
                else {

                }
                break;
            }
            else{
                _cellPanel.setColor(true);
            }

        }
    }

    private void MaCheck(int x, int y) {
        ChessPiece thisPiece = boardCell[x][y].currentChessPiece;
        int[] dx = {2, 2, -2, -2, 1, 1, -1, -1};
        int[] dy = {1, -1, 1, -1, 2, -2, 2, -2};
        for(int i=0;i<8;i++){
            if(!checkValidCoordinate(x+dx[i], y+dy[i])) continue;
            CellPanel _cellPanel = boardCell[x+dx[i]][y+dy[i]];
            if(_cellPanel.currentChessPiece!=null){
                if(_cellPanel.currentChessPiece.color!=thisPiece.color){
                    _cellPanel.setColor(false);
                }
            }
            else{
                _cellPanel.setColor(true);
            }
        }

    }

    private void TuongCheck(int x, int y) {
        ChessPiece thisPiece = boardCell[x][y].currentChessPiece;
        for(int i=1;i<8;i++){//cheo tren trai
            if(!checkValidCoordinate(x-i, y-i)) break;
            CellPanel _cellPanel = boardCell[x-i][y-i];
            if(_cellPanel.currentChessPiece!=null){
                if(_cellPanel.currentChessPiece.color!=thisPiece.color){
                    _cellPanel.setColor(false);
                }
                else {}
                break;
            }
            else{
                _cellPanel.setColor(true);
            }
        }

        for(int i=1;i<8;i++){//cheo tren phai
            if(!checkValidCoordinate(x-i, y+i)) break;
            CellPanel _cellPanel = boardCell[x-i][y+i];
            if(_cellPanel.currentChessPiece!=null){
                if(_cellPanel.currentChessPiece.color!=thisPiece.color){
                    _cellPanel.setColor(false);
                }
                else {}
                break;
            }
            else{
                _cellPanel.setColor(true);
            }
        }

        for(int i=1;i<8;i++){//cheo duoi trai
            if(!checkValidCoordinate(x+i, y-i)) break;
            CellPanel _cellPanel = boardCell[x+i][y-i];
            if(_cellPanel.currentChessPiece!=null){
                if(_cellPanel.currentChessPiece.color!=thisPiece.color){
                    _cellPanel.setColor(false);
                }
                else {}
                break;
            }
            else{
                _cellPanel.setColor(true);
            }
        }

        for(int i=1;i<8;i++){//cheo duoi phai
            if(!checkValidCoordinate(x+i, y+i)) break;
            CellPanel _cellPanel = boardCell[x+i][y+i];
            if(_cellPanel.currentChessPiece!=null){
                if(_cellPanel.currentChessPiece.color!=thisPiece.color){
                    _cellPanel.setColor(false);
                }
                else {}
                break;
            }
            else{
                _cellPanel.setColor(true);
            }
        }
    }

    private void HauCheck(int x, int y) {
        XeCheck(x, y);
        TuongCheck(x, y);
    }

    private void VuaCheck(int x, int y) {
        ChessPiece thisPiece = boardCell[x][y].currentChessPiece;
        for(int i=-1;i<=1;i++){
            for(int j=-1;j<=1;j++){
                if(i==0 && j==0) continue;
                if(!checkValidCoordinate(x+i, y+j)) continue;
                CellPanel _cellPanel = boardCell[x+i][y+j];
                if(_cellPanel.currentChessPiece!=null){
                    if(_cellPanel.currentChessPiece.color!=thisPiece.color){
                        _cellPanel.setColor(false);
                    }
                }
                else {
                    _cellPanel.setColor(true);
                }
            }
        }
<<<<<<< HEAD
        if (!thisPiece.hasMoved) {
            boolean kingside = checkCastling(x, y, true);
            boolean queenside = checkCastling(x, y, false);

            if (kingside && checkValidCoordinate(x, y + 2)) {
                boardCell[x][y + 2].setColor(true);
            }
            if (queenside && checkValidCoordinate(x, y - 2)) {
                boardCell[x][y - 2].setColor(true);
            }
        }
    }

    // THÊM: Kiểm tra nếu ô bị chiếu bởi quân địch
    private boolean isCellUnderAttack(int x, int y, PieceColor defenderColor) {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                ChessPiece attacker = boardCell[i][j].currentChessPiece;
                if (attacker != null && attacker.color != defenderColor) {
                    if (isValidMoveWithoutColorCheck(i, j, x, y)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    // THÊM: kiểm tra cơ bản quân địch có thể tấn công ô (toX, toY) không
    private boolean isValidMoveWithoutColorCheck(int fromX, int fromY, int toX, int toY) {
        int dx = Math.abs(fromX - toX);
        int dy = Math.abs(fromY - toY);
        ChessPiece piece = boardCell[fromX][fromY].currentChessPiece;
        if (piece == null) return false;

        return switch (piece.type) {
            case MA -> (dx == 2 && dy == 1) || (dx == 1 && dy == 2);
            case TOT -> {
                int dir = piece.color == PieceColor.WHITE ? -1 : 1;
                yield (toX - fromX == dir && Math.abs(toY - fromY) == 1);
            }
            case XE -> {
                if (fromX == toX) {
                    boolean clear = true;
                    for (int j = Math.min(fromY, toY) + 1; j < Math.max(fromY, toY); j++)
                        if (boardCell[fromX][j].currentChessPiece != null) clear = false;
                    yield clear;
                }
                if (fromY == toY) {
                    boolean clear = true;
                    for (int i = Math.min(fromX, toX) + 1; i < Math.max(fromX, toX); i++)
                        if (boardCell[i][fromY].currentChessPiece != null) clear = false;
                    yield clear;
                }
                yield false;
            }
            case TUONG -> {
                if (dx == dy) {
                    int stepX = (toX - fromX) / dx;
                    int stepY = (toY - fromY) / dy;
                    for (int i = 1; i < dx; i++)
                        if (boardCell[fromX + i * stepX][fromY + i * stepY].currentChessPiece != null)
                            yield false;
                    yield true;
                }
                yield false;
            }
            case HAU -> isValidMoveWithoutColorCheck(fromX, fromY, toX, toY, PieceType.XE) ||
                    isValidMoveWithoutColorCheck(fromX, fromY, toX, toY, PieceType.TUONG);
            case VUA -> dx <= 1 && dy <= 1;
        };
    }

    private boolean isValidMoveWithoutColorCheck(int fromX, int fromY, int toX, int toY, PieceType pretendType) {
        ChessPiece original = boardCell[fromX][fromY].currentChessPiece;
        boardCell[fromX][fromY].currentChessPiece = new ChessPiece(pretendType, original.color);
        boolean result = isValidMoveWithoutColorCheck(fromX, fromY, toX, toY);
        boardCell[fromX][fromY].currentChessPiece = original;
        return result;
    }

    private boolean checkCastling(int x, int y, boolean kingside) {
        ChessPiece king = boardCell[x][y].currentChessPiece;
        if (king.hasMoved || king.type != PieceType.VUA) return false; // Vua đã di chuyển hoặc không phải vua

        // Kiểm tra xem vua có đang bị chiếu ở vị trí ban đầu
        if (isCellUnderAttack(x, y, king.color)) return false;

        int rookY = kingside ? 7 : 0;
        CellPanel rookCell = boardCell[x][rookY];
        // Kiểm tra xe tồn tại, đúng loại, chưa di chuyển và cùng màu với vua
        if (rookCell.currentChessPiece == null || rookCell.currentChessPiece.type != PieceType.XE ||
                rookCell.currentChessPiece.hasMoved || rookCell.currentChessPiece.color != king.color) {
            return false;
        }

        // Kiểm tra các ô giữa vua và xe phải trống
        int betweenStart = Math.min(y, rookY) + 1;
        int betweenEnd = Math.max(y, rookY) - 1;
        for (int j = betweenStart; j <= betweenEnd; j++) {
            if (j == y) continue;
            if (boardCell[x][j].currentChessPiece != null) return false;
        }

        // Kiểm tra trạng thái sau nhập thành: vua không bị chiếu ở ô đích
        int kingDestY = kingside ? y + 2 : y - 2;
        int rookDestY = kingside ? y + 1 : y - 1;
        ChessPiece rook = rookCell.currentChessPiece;
        ChessPiece tempKingDest = boardCell[x][kingDestY].currentChessPiece;
        ChessPiece tempRookDest = boardCell[x][rookDestY].currentChessPiece;

        // Mô phỏng nhập thành tạm thời
        boardCell[x][y].currentChessPiece = null;
        boardCell[x][rookY].currentChessPiece = null;
        boardCell[x][kingDestY].currentChessPiece = king;
        boardCell[x][rookDestY].currentChessPiece = rook;

        // Kiểm tra xem vua có bị chiếu ở vị trí đích
        boolean kingSafe = !isCellUnderAttack(x, kingDestY, king.color);

        // Hoàn tác di chuyển
        boardCell[x][y].currentChessPiece = king;
        boardCell[x][rookY].currentChessPiece = rook;
        boardCell[x][kingDestY].currentChessPiece = tempKingDest;
        boardCell[x][rookDestY].currentChessPiece = tempRookDest;

        return kingSafe; // Chỉ cần vua an toàn ở ô đích, không quan tâm đến các ô đi qua
=======
>>>>>>> a87cc762092c2847fbf1bae42803dd657be04f26
    }

    private boolean checkValidCoordinate(int n){
        return n>=0 && n<8;
    }
<<<<<<< HEAD

=======
>>>>>>> a87cc762092c2847fbf1bae42803dd657be04f26
    private boolean checkValidCoordinate(int x, int y){
        return checkValidCoordinate(x)&&checkValidCoordinate(y);
    }
}
