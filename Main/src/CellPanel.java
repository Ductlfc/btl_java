import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.nio.file.Path;

public class CellPanel extends JPanel {
    private final Color blueColor = new Color(52, 174, 235);
    private final Color greenColor = new Color(123, 245, 66);
    private final Color redColor = new Color(255, 125, 125);

    public int x;
    public int y;

    public ChessPiece currentChessPiece;
    public boolean isValidMove;

    private JLabel imageLabel;
    private PieceColor originCellColor;
    public CellPanel(boolean isWhite, int x, int y) {
        isValidMove = false;
        originCellColor = isWhite? PieceColor.WHITE: PieceColor.BLACK;
        this.x = x;
        this.y = y;
        this.setBackground(isWhite? Color.white: new Color(66, 68, 71));
        imageLabel = new JLabel();
        imageLabel.setHorizontalAlignment(SwingConstants.CENTER);
        imageLabel.setVerticalAlignment(SwingConstants.CENTER);
        this.add(imageLabel);
        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                GameFrame.Instance.centerPanel.onClickCellPanel(x,y);
            }
        });
    }
    public void AddImage(ChessPiece chessPiece) {
        currentChessPiece = chessPiece;
        BufferedImage pieceImage = GetBufferedImageFromFile(chessPiece);
        Image image = pieceImage.getScaledInstance(60, 60, Image.SCALE_SMOOTH);
        imageLabel.setIcon(new ImageIcon(image));
        imageLabel.setVisible(true);
    }
    public void removePiece(){
        currentChessPiece = null;
        imageLabel.setVisible(false);
    }

    public void seLect() {
        this.setBackground(blueColor);
    }
    public void deSelect() {
        this.setBackground(originCellColor == PieceColor.WHITE?Color.WHITE:new Color(66, 68, 71));
        isValidMove = false;
    }
    public void setColor(boolean isMove){
        isValidMove = true;
        if(isMove){
            setBackground(blueColor);
        }
        else{
            setBackground(redColor);
        }
    }
    private BufferedImage GetBufferedImageFromFile(ChessPiece chessPiece) {
        Path path = FileSystems.getDefault().getPath("").toAbsolutePath();
        String fileString = path + "/piece/";
        if(chessPiece.color==PieceColor.WHITE){
            fileString+="w_";
        }
        else{
            fileString+="b_";
        }
        fileString += chessPiece.type.toString().toLowerCase() + ".png";
        File file = new File(fileString);
        try {
            BufferedImage image = ImageIO.read(file);
            return image;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
