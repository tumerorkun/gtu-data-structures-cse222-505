import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

class CountingComponents {
    private Integer[][] matrix;
    private int row;
    private int column;
    private int count = 0;
    private String fileName;

    CountingComponents(String name) {
        fileName = name;
        getCRSizes();
        createMatrix();
    }

    /**
     * @return component count as int
     */
    int getComponentCount() {
        components();
        return count;
    }

    /**
     * get needed row and column size to create matrix
     */
    private void getCRSizes() {
        String line;
        int row_local = 0;
        int column_local = 0;
        try {
            FileReader fileReader1 = new FileReader(fileName);
            BufferedReader bufferedReader1 = new BufferedReader(fileReader1);
            while ((line = bufferedReader1.readLine()) != null) {
                column_local = line.split(" ").length;
                row_local++;
            }
            bufferedReader1.close();
        } catch (FileNotFoundException ex) {
            System.out.println("Unable to open file '" + fileName + "'");
        } catch (IOException ex) {
            System.out.println("Error reading file '" + fileName + "'");
        }
        row = row_local;
        column = column_local;
    }

    /**
     * parse and create matrix as 2D array
     */
    private void createMatrix() {
        String line;
        Integer[][] matrixL = new Integer[row][column];
        try {
            // FileReader reads text files in the default encoding.
            FileReader fileReader = new FileReader(fileName);
            // Always wrap FileReader in BufferedReader.
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            int u = 0;
            while ((line = bufferedReader.readLine()) != null) {
                String[] lineArr = line.split(" ");
                for (int i = 0, l = lineArr.length; i < l; i++) {
                    matrixL[u][i] = Integer.parseInt(lineArr[i]);
                }
                u++;
            }
            // Always close files.
            bufferedReader.close();
        } catch (FileNotFoundException ex) {
            System.out.println("Unable to open file '" + fileName + "'");
        } catch (IOException ex) {
            System.out.println("Error reading file '" + fileName + "'");
        }
        matrix = matrixL;
    }

    /**
     * traverse 2D array and count components
     */
    private void components() {
        for (int r = 0; r < row; r++) {
            for (int c = 0; c < column; c++) {
                if (matrix[r][c] == 1) {
                    countComponent(r, c);
                }
                // System.out.print(matrix[r][c]);
            }
            // System.out.println(' ');
        }
    }

    /**
     * traverse and print all element in the matrix
     */
    void printMatrix() {
        for (int r = 0; r < row; r++) {
            for (int c = 0; c < column; c++) {
                if (matrix != null) {
                    System.out.print(matrix[r][c]);
                }
            }
            System.out.println(' ');
        }
    }

    /**
     * get one lement which has a value 1 and check neighbours if they are 1 too and
     * stack them until stack is empty repeat
     * 
     * @param ro row number which is value 1 in the matrix
     * @param co rcolumn number which is value 1 in the matrix
     */
    private void countComponent(int ro, int co) {
        Stack<Integer[]> stack = new Stack<>();
        Integer[] el = { ro, co };
        stack.push(el);
        while (!stack.empty()) {
            Integer[] n = stack.pop();
            matrix[n[0]][n[1]] = 5;
            int r = n[0];
            int c = n[1];
            if (c > 0) {
                if (matrix[r][c - 1] == 1) {
                    Integer[] neighbour = { r, c - 1 };
                    stack.push(neighbour);
                }
            }
            if ((c % (column - 1)) != 0) {
                if (matrix[r][c + 1] == 1) {
                    Integer[] neighbour = { r, c + 1 };
                    stack.push(neighbour);
                }
            }
            if (c == 0) {
                if (matrix[r][c + 1] == 1) {
                    Integer[] neighbour = { r, c + 1 };
                    stack.push(neighbour);
                }
            }
            if (r > 0) {
                if (matrix[r - 1][c] == 1) {
                    Integer[] neighbour = { r - 1, c };
                    stack.push(neighbour);
                }
            }
            if ((r % (row - 1)) != 0) {
                if (matrix[r + 1][c] == 1) {
                    Integer[] neighbour = { r + 1, c };
                    stack.push(neighbour);
                }
            }
            if (r == 0) {
                if (matrix[r + 1][c] == 1) {
                    Integer[] neighbour = { r + 1, c };
                    stack.push(neighbour);
                }
            }
        }
        count++;
    }
}
