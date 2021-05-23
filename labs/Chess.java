import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Chess {
    public static void main(String[] args) {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                board[i][j]=0;
            }
        }
        solve(0);

    }
    static int[][] board = new int[8][8];



    public  static void setQueen(int i,int j){
        for (int x = 0; x < 8; x++) {
            board[x][j]+=1;
            board[i][x]+=1;
            if((0 <= i+j-x)&&(i+j-x<8)) board[i+j-x][x]+=1;
            if((0<=i-j+x)&&(i-j+x<8)) board[i-j+x][x]+=1;
        }
        board[i][j] = -1;
    }

    public  static void dropQueen(int i,int j){
        for (int x = 0; x < 8; x++) {
            board[x][j]-=1;
            board[i][x]-=1;
            if((0 <= i+j-x)&&(i+j-x<8)) board[i+j-x][x]-=1;
            if((0<=i-j+x)&&(i-j+x<8)) board[i-j+x][x]-=1;
        }
        board[i][j] = 0;
    }

    public static void printPos() {
        List<String> ans = new ArrayList<String>();
        String abc = "abcdefgh";
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (board[i][j]==-1){
                    ans.add(abc.charAt(j)+Integer.toString(i+1));
                }
            }
        }
        System.out.println(ans);
    }
    int ch = 0;
    public static void solve(int i){
        for (int j = 0; j < 8; j++) {
            if (board[i][j]==0){
                setQueen(i,j);
                if(i==7){
                    printPos();
                }else{
                    solve(i+1);
                }
                dropQueen(i,j);
            }
        }
    }
}
