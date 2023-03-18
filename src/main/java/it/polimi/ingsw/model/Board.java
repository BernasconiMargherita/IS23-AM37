package it.polimi.ingsw.model;

public class Board {
    private TileSlot[][] board;
    private CardCommonTarget commonTarget1;
    private CardCommonTarget commonTarget2;
    private CommonDeck commonDeck;
    private EndGameToken endGameToken;
    private TileDeck bag;

    private static final boolean[][] dueGiocatori=
                    {{false,false,false,false,false,false,false,false,false},
                    {false,false,false,true,true,false,false,false,false},
                    {false,false,false,true,true,true,false,false,false},
                    {false,false,true,true,true,true,true,true,false},
                    {false,true,true,true,true,true,true,true,false},
                    {false,true,true,true,true,true,true,false,false},
                    {false,false,false,true,true,true,false,false,false},
                    {false,false,false,false,true,true,false,false,false},
                    {false,false,false,false,false,false,false,false,false}};

    private static final boolean[][] treGiocatori=
                    {{false,false,false,true,false,false,false,false,false},
                    {false,false,false,true,true,false,false,false,false},
                    {false,false,true,true,true,true,true,false,false},
                    {false,false,true,true,true,true,true,true,true},
                    {false,true,true,true,true,true,true,true,false},
                    {true,true,true,true,true,true,true,false,false},
                    {false,false,true,true,true,true,true,false,false},
                    {false,false,false,false,true,true,false,false,false},
                    {false,false,false,false,false,true,false,false,false}};

    private static final boolean[][] quattroGiocatori=
                    {{false,false,false,true,true,false,false,false,false},
                    {false,false,false,true,true,true,false,false,false},
                    {false,false,true,true,true,true,true,false,false},
                    {false,true,true,true,true,true,true,true,true},
                    {true,true,true,true,true,true,true,true,true},
                    {true,true,true,true,true,true,true,true,false},
                    {false,false,true,true,true,true,true,false,false},
                    {false,false,false,true,true,true,false,false,false},
                    {false,false,false,false,true,true,false,false,false}};


   Board(int numOfPlayers){
       this.bag=new TileDeck();
       this.commonDeck=new CommonDeck();
       this.commonTarget1=new CardCommonTarget(commonDeck.RandomDraw());
       this.commonTarget2=new CardCommonTarget(commonDeck.RandomDraw());
       this.board=new TileSlot[9][9];

       if (numOfPlayers==2){
           for (int j=0;j<=8;j++) {
               for (int k = 0; k <= 8; k++) {
                   if (dueGiocatori[j][k]) board[j][k].AssignTile(bag.RandomDraw());
               }
           }
       }
       if (numOfPlayers==3){
           for (int j=0;j<=8;j++) {
               for (int k = 0; k <= 8; k++) {
                   if (treGiocatori[j][k]) board[j][k].AssignTile(bag.RandomDraw());
               }
           }
       }
       if (numOfPlayers==4){
           for (int j=0;j<=8;j++) {
               for (int k = 0; k <= 8; k++) {
                   if (quattroGiocatori[j][k]) board[j][k].AssignTile(bag.RandomDraw());
               }
           }
       }

   }

   public void RefillBoard(int numOfPlayers){

       if (numOfPlayers==2){
           for (int j=0;j<=8;j++) {
               for (int k = 0; k <= 8; k++) {
                   if ((dueGiocatori[j][k])&&(board[j][k].IsFree())) board[j][k].AssignTile(bag.RandomDraw());
               }
           }
       }
       if (numOfPlayers==3){
           for (int j=0;j<=8;j++) {
               for (int k = 0; k <= 8; k++) {
                   if ((treGiocatori[j][k])&&(board[j][k].IsFree())) board[j][k].AssignTile(bag.RandomDraw());
               }
           }
       }
       if (numOfPlayers==4){
           for (int j=0;j<=8;j++) {
               for (int k = 0; k <= 8; k++) {
                   if ((quattroGiocatori[j][k])&&(board[j][k].IsFree())) board[j][k].AssignTile(bag.RandomDraw());
               }
           }
       }
   }

   public Tile[] RemoveCardFromBoard(Coordinates[] positions) throws InvalidSlotException{
       for (Coordinates position : positions) {
           if (board[position.getX()][position.getY()].IsFree()) throw new InvalidSlotException();

           Tile[] selectedTile = new Tile[positions.length];


       }





}
