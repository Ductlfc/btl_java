import javax.swing.*;
import java.awt.*;

public class CenterPanel extends JPanel {
    public CellPanel[][] boardCell = new CellPanel[8][8];

    private BoardState boardState;
    private CellPanel selectedCell;
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
            if(clickedCellPanel.isValidMove){
                //move
                clickedCellPanel.AddImage(selectedCell.currentChessPiece);
                selectedCell.removePiece();
                selectedCell=null;
                //chuyen trang thai ve 0 chon
                boardState = BoardState.NONE_SELECTED;
                deSelectAllCells();
            }
            else {
                boardState = BoardState.NONE_SELECTED;
                deSelectAllCells();
            }
        }
    }
    public void deSelectAllCells() {
        for(int i=0; i<8; i++) {
            for(int j=0; j<8; j++) {
                boardCell[i][j].deSelect();
            }
        }
    }
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
    }

    private boolean checkValidCoordinate(int n){
        return n>=0 && n<8;
    }
    private boolean checkValidCoordinate(int x, int y){
        return checkValidCoordinate(x)&&checkValidCoordinate(y);
    }
}
