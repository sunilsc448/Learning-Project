package KotlinSamples

import java.lang.StringBuilder
import kotlin.math.sign

class MatrixSamples {
    init {
//        zigzagConversion("AB", 1)
    }

    fun shiftGrid(grid: Array<IntArray>, k: Int): List<List<Int>> {
        val m = grid.size
        val n = grid.get(0).size

        for(i in 0 until k){
            shift(grid, m, n)
        }

        var output:MutableList<List<Int>> = mutableListOf()
        for (i in 0 until m){
            output.add(grid.get(i).toList())
        }
        return output
    }

    fun shift(grid: Array<IntArray>, m: Int, n:Int){
        var temp = grid[0][0]
        for (i in 0 until m){
            for (j in 0 until n){
                if(i == m-1 && j == n-1){
                    grid[0][0] = temp
                    return
                }
                if(j < n-1){
                    val tempLocal = grid[i][j+1]
                    grid[i][j+1] = temp
                    temp = tempLocal
                }else{
                    val tempLocal = grid[i + 1][0]
                    grid[i + 1][0] = temp
                    temp = tempLocal
                }
            }
        }
    }

    fun gameOfLife(board: Array<IntArray>): Unit {
        val m = board.size
        val n = board.get(0).size
        val visited:Array<BooleanArray> = Array(m){ BooleanArray(n)}

        for(i in 0 until m)
            for(j in 0 until n){
                board[i][j] = game(board, i, j, m, n, visited)
            }
    }

    fun game(board: Array<IntArray>, i:Int, j:Int, m:Int, n:Int, visited: Array<BooleanArray>):Int{
        val directrions = arrayOf(intArrayOf(-1, 0), intArrayOf(-1, -1), intArrayOf(0, -1), intArrayOf(1, -1),
            intArrayOf(1, 0), intArrayOf(1,1), intArrayOf(0, 1), intArrayOf(-1, 1))
        var currentVal = board[i][j]
        var countOfLives = 0
        for(dir in 0..7){
            val newi = i + directrions[dir][0]
            val newj = j + directrions[dir][1]
            if(newi > 0 && newj > 0 && newi < m && newj < n && board[newi][newj] == 1) {
                if(visited[newi][newj]){
                    if(board[newi][newj] == 0)
                        countOfLives++
                }else{
                    if (board[newi][newj] == 1)
                        countOfLives++
                }
                countOfLives++;
            }
        }
        if(currentVal == 0){
            if(countOfLives == 3){
                visited[i][j] = true
                currentVal = 1
            }
        }else{
            if(countOfLives < 2){
                visited[i][j] = true
                currentVal = 0
            }else if(countOfLives > 3){
                visited[i][j] = true
                currentVal = 0
            }
        }
        return currentVal
    }

    fun generateMatrix(n: Int): Array<IntArray> {
        val rowMax = n
        val colMax = n
        val rowEnd = n-1
        val colEnd = n-1
        var resultArray:Array<IntArray> = Array(n){IntArray(n)}
        val counter = 0
        generateSpiralMatrix(resultArray, n, rowMax, colMax, 0, 0, n-1, n-1, counter)
        return resultArray
    }

    fun generateSpiralMatrix(resultArray: Array<IntArray>, n:Int, rowMax:Int, colMax: Int,
                             rowStart:Int, colStart: Int,rowEnd:Int, colEnd:Int, counter: Int){
        if(colStart > colEnd || rowStart > rowEnd || counter >= (n*n))
            return

        var counterLocal = counter

        //firstrow
        for(i in rowStart..rowEnd){
            resultArray[rowStart][i] = ++counterLocal
        }

        //lastcolumn
        for(i in rowStart+1..rowEnd){
            resultArray[i][colEnd] = ++counterLocal
        }

        //bottomrow
        for(i in colEnd-1 downTo colStart){
            resultArray[rowEnd][i] = ++counterLocal
        }

        //firstcolumn
        var i = rowEnd-1
        while (i > rowStart){
            resultArray[i][colStart] = ++counterLocal
            i--
        }

        generateSpiralMatrix(resultArray, n, rowMax, colMax, rowStart + 1, colStart + 1, rowEnd-1, colEnd - 1, counterLocal)
    }

    //Using Matrix
    fun zigzagConversion(s: String, numRows: Int):String{
        val insertCount = 0
        val numColumns = s.length
        val array:Array<Array<Char>> = Array(numRows){ Array(numColumns){'\u0000'} }
        zigzag(numRows,0, insertCount, s, array)

        return getStringFromArrayIteration(array)
    }

    private fun zigzag(rowcount:Int, colIndex:Int, count:Int, s: String, array: Array<Array<Char>>) {
        var insertCount = count
        var columnIndex= colIndex
        for (i in 0 until rowcount){
            if(insertCount == s.length){
                return
            }
            array[i][columnIndex] = s[insertCount++]
        }

        for (i in rowcount-1-1 downTo 1){
            if(insertCount == s.length){
                return
            }
            array[i][columnIndex + 1] = s[insertCount++]
            columnIndex++
        }
        if(insertCount != s.length){
            zigzag(rowcount, columnIndex+1, insertCount, s, array)
        }
    }

    private fun getStringFromArrayIteration(array: Array<Array<Char>>): String {
        var retStr = ""
        for (i in 0 until array.size){
            for (j in 0 until array[0].size){
                if(array[i][j] != '\u0000') {
                    retStr += array[i][j]
                }
            }
        }
        return retStr
        //PAHNAPLSIIGYIR
    }
}