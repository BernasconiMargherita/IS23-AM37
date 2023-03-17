package it.polimi.ingsw.model;

public class Board {
    private TileSlot[][] board;
    private CardCommonTarget commonTarget1;
    private CardCommontarget commonTarget2;
    private CommonDeck commonDeck;
    private EndGameToken endGameToken;
    private TileDeck bag;

    private static boolean[][] dueGiocatori=
                    {{false,false,false,false,false,false,false,false,false},
                    {false,false,false,true,true,false,false,false,false},
                    {false,false,false,true,true,true,false,false,false},
                    {false,false,true,true,true,true,true,true,false},
                    {false,true,true,true,true,true,true,true,false},
                    {false,true,true,true,true,true,true,false,false},
                    {false,false,false,true,true,true,false,false,false},
                    {false,false,false,false,true,true,false,false,false},
                    {false,false,false,false,false,false,false,false,false}};

    private static boolean[][] treGiocatori=
                    {{false,false,false,true,false,false,false,false,false},
                    {false,false,false,true,true,false,false,false,false},
                    {false,false,true,true,true,true,true,false,false},
                    {false,false,true,true,true,true,true,true,true},
                    {false,true,true,true,true,true,true,true,false},
                    {true,true,true,true,true,true,true,false,false},
                    {false,false,true,true,true,true,true,false,false},
                    {false,false,false,false,true,true,false,false,false},
                    {false,false,false,false,false,true,false,false,false}};

    private static boolean[][] quattroGiocatori=
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

       if (numOfPlayers==2){
           this.board=new TileSlot[9][9];
           for (int j=0;j<=8;j++) {
               for (int k = 0; k <= 8; k++) {
                   if (dueGiocatori[j][k]) board[j][k]=bag.RandomDraw();
               }
               }
           }
       if (numOfPlayers==3){
           this.board=new TileSlot[9][9];
           for (int j=0;j<=8;j++) {
               for (int k = 0; k <= 8; k++) {
                   if (treGiocatori[j][k]) board[j][k]=bag.RandomDraw();
               }
           }
       }
       if (numOfPlayers==4){
           this.board=new TileSlot[9][9];
           for (int j=0;j<=8;j++) {
               for (int k = 0; k <= 8; k++) {
                   if (quattroGiocatori[j][k]) board[j][k]=bag.RandomDraw();
               }
           }
       }

   }
}
