public class Percolation {
    private int N;
    private int[][] cells;
    private WeightedQuickUnionUF wquf;
    private int test;
    
    public Percolation(int N) {
        this.N = N;
        wquf = new WeightedQuickUnionUF(N*N);
        cells = new int[N][N];
        //everything is full
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                cells[i][j] = 1;
            }
        }
        
    }
    
    private void checkIfValid(int i, int j) {
        if (i < 1 || i > this.N || j < 1 || j > this.N)
            throw new java.lang.IndexOutOfBoundsException();
    }
    
    //set cells[i][j] = 1
    public void open(int i, int j) {
        checkIfValid(i, j);
        //if already open, don't even worry about it
        if (isOpen(i, j)) return;
        //open the cell
        this.cells[i][j] = 0;
        //check to see if stuff needs to connect
        //unique id = rowLength * row # + col #;
        //e.g. 2,1 = 21 while 1,2 = 12 when N = 10;
        //check if there's an open cell right above the current cell
        if (i >= 1 && isOpen(i-1, j)) {
            int currentUniqueId = this.N * (i) + (j);
            int previousColUniqueId = this.N * (i - 1) + (j);
            wquf.union(currentUniqueId, previousColUniqueId); 
        }
        //check if there's an open cell below the current cell
        if (i <= this.N && isOpen(i+1, j)) {
            int currentUniqueId = this.N * (i) + (j);
            int previousColUniqueId = this.N * i + (j);
            wquf.union(currentUniqueId, previousColUniqueId); 
        }
        //check if there's an open cell to the left of the current cell
        if (j >= 1 && isOpen(i, j-1)) {
            int currentUniqueId = this.N * (i) + (j);
            int previousColUniqueId = this.N * (i) + (j);
            wquf.union(currentUniqueId, previousColUniqueId); 
        }
        //check if there's an open cell to the right of the current cell
        if (j <= this.N && isOpen(i, j+1)) {
            int currentUniqueId = this.N * (i) + (j);
            int previousColUniqueId = this.N * (i) + j;
            wquf.union(currentUniqueId, previousColUniqueId); 
        }
    }
    
    //check if cells[i][j] = 0
    public boolean isOpen(int i, int j) {
        return this.cells[i][j] == 0;
    }
    
    //check if cells[i][j] = 1
    public boolean isFull(int i, int j) {
        checkIfValid(i, j);
        for (int m = 1; m <= this.N; m++) {
            int cur = this.N * (i) + (j);
            if (isOpen(i, j) && wquf.connected(m, cur)) {
                return true;
            }
        }
        return false;
    }
    
    public boolean percolates() {
        //check 0 to 9 vs 90 to 99
        String topOpen = "";
        String bottomOpen = "";
        for (int i = 1; i <= this.N; i++) {
            if(isOpen(1,i))
                topOpen += i + ",";
            if(isOpen(this.N, i))
                bottomOpen += ((this.N) * this.N + i) + ",";
        }
        if (!"".equals(topOpen) && !"".equals(bottomOpen)) {
            for(String s:topOpen.split(",")){
                for(String s1: bottomOpen.split(",")){
                    return wquf.connected(Integer.parseInt(s), Integer.parseInt(s1));
                }
            }
        }
        return false;
        /*
        //loop through initial column count
        for (int i = 0; i < this.N; i++) {
            //loop through secondary column count
            for (int j = 0; j < this.N; j++) {
                //get the bottom row's unique id
                int cur = (this.N - 1) * this.N + j;

                if (isOpen(1, i + 1) 
                        && isOpen(this.N, j + 1) 
                        && wquf.connected(i, cur)) {
                    //System.out.println("it percolates!");
                    return true;
                }
            }
        }
        //percolation not found :(
        return false;*/
    }
}