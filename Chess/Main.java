package Chess;

import java.util.Scanner;

enum PieceType{
    KING, QUEEN, PAWN, ROOKIE, BISHOP, KNIGHT;
}

class Player{
    private int id;
    private String name;
    private Boolean isWhite;
    Player(int id, String name, Boolean isWhite){
        this.id = id;
        this.name = name;
        this.isWhite = isWhite;
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public Boolean getIsWhite() {
        return isWhite;
    }
    public void setIsWhite(Boolean isWhite) {
        this.isWhite = isWhite;
    }
    
}

class Game{
    private Board board;
    private Player player[] = new Player[2];

    Game(Player player1, Player player2){
        this.player[0] = player1;
        this.player[1] = player2;
    }

    void init(){
        board = new Board();
        board.initBoard();
        board.displayBoard();
    }

    void movePiece(IPiece piece, int startx, int starty, int endx, int endy){
        Cell start = this.board.getCell(startx, starty);
        Cell end = this.board.getCell(endx, endy);
        if(piece.canMove(start, end)){
            piece.move(start, end);
        }
    }

    public Board getBoard() {
        return board;
    }
    public void setBoard(Board board) {
        this.board = board;
    }
    public Player[] getPlayer() {
        return player;
    }
    public void setPlayer(Player[] player) {
        this.player = player;
    }
    
}

class Board{
    private Cell[][] cells = new Cell[8][8];
    Board(){
        for(int i = 0; i<8; ++i){
            for(int j = 0; j<8; ++j){
                cells[i][j] = null;
            }
        }
    }
    public void initBoard(){
        for(int j = 0; j<8; ++j){
            cells[0][j] = new Cell(0, j, new KingPiece(true, true));
        }
        for(int j = 8; j<8; ++j){
            cells[7][j] = new Cell(7, j, new KingPiece(true, false));
        }
    }

    public Cell getCell(int x, int y){
        return this.cells[x][y];
    }

    public void displayBoard(){
        for(int i = 0; i<8; ++i){
            for(int j = 0; j<8; ++j){
                if(this.cells[i][j] != null)
                System.out.println(this.cells[i][j].getPiece().getPieceType());
            }
        }
    }
}

class Cell{
    private int x, y;
    IPiece piece;
    Cell(int x, int y, IPiece piece){
        this.x = x;
        this.y = y;
        this.piece = piece;
    }

    public void setPiece(IPiece piece){
        this.piece = piece;
    }

    public IPiece getPiece(){
        return piece;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }
    
}

interface IPiece{
    Boolean canMove(Cell start, Cell end);
    PieceType getPieceType();
    void move(Cell start, Cell end);
    void setIsAlive(boolean isAlive);
}

class KingPiece implements IPiece{

    private Boolean isAlive;
    private Boolean isWhite;
    private PieceType pieceType = PieceType.KING;

    KingPiece(Boolean isAlive, Boolean isWhite){
        this.isAlive = isAlive;
        this.isWhite = isWhite;
    }

    @Override
    public Boolean canMove(Cell start, Cell end) {
        Boolean canMove = true;
        return canMove;
    }

    @Override
    public void move(Cell start, Cell end) {
        if(end.getPiece() != null){
            end.getPiece().setIsAlive(false);
        }
        end.setPiece(start.getPiece());
        start.setPiece(null);
    }

    public Boolean getIsAlive() {
        return isAlive;
    }

    public Boolean getIsWhite() {
        return isWhite;
    }

    public void setIsWhite(Boolean isWhite) {
        this.isWhite = isWhite;
    }

    @Override
    public void setIsAlive(boolean isAlive) {
        this.isAlive = isAlive;
    }
    
    @Override
    public PieceType getPieceType() {
        return pieceType;
    }

    public void setPieceType(PieceType pieceType) {
        this.pieceType = pieceType;
    }

    

}

public class Main {
    public static void main(String[] agrs){

        Player player1 = new Player(1,"Sushant Singh" , true);
        Player player2 = new Player(2,"Abhishek Singh" , false);
        Player currPlayer = player1;
        Boolean winner = false;
        Game game = new Game(player1, player2);
        game.init();
        Scanner scanner = new Scanner(System.in);
        while(true){
            int x1 = scanner.nextInt();
            int y1 = scanner.nextInt();
            int x2 = scanner.nextInt();
            int y2 = scanner.nextInt();
            Cell start = game.getBoard().getCell(x1, y1);
            Cell end = game.getBoard().getCell(x2, y2);
            IPiece currPiece = start.getPiece();
            if(currPiece.canMove(start, end)){
                if(end.getPiece().getPieceType() == PieceType.KING){
                    winner = true;
                    break;
                }
                game.movePiece(currPiece, x1, y1, x2, y2);
                if(currPlayer == player1){
                    currPlayer = player2;
                }
                else currPlayer = player1;
            }
            else {
                System.out.println("Not a valid move");
            }
        }
        if(winner){
            System.out.println(currPlayer.getName() + " is the winner");
        }  
        if(scanner!=null)
            scanner.close();
    }
}
