package KotlinSamples

import android.util.MutableInt
import java.lang.StringBuilder
import kotlin.math.sign

class MatrixSamples {
    init {
//        zigzagConversion("AB", 1)

//        val inputArray = arrayOf(intArrayOf(1, 0, 0, 1), intArrayOf(1, 0, 1, 0), intArrayOf(1, 0, 0, 1), intArrayOf(1, 0, 0, 0))
//        val count:Int = maxAreaIsland(inputArray)
//        println("count $count")

//        val inputArray = arrayOf(intArrayOf(1, 2, 3, 4), intArrayOf(5, 6, 7, 8),
//            intArrayOf(9, 10, 11, 12), intArrayOf(13, 14, 15, 16), intArrayOf(17, 18, 19, 20))
//        printWaveMatrix(inputArray)
    }


//    Input: grid = [
//    ["1","1","1","1","0"],
//    ["1","1","0","1","0"],
//    ["1","1","0","0","0"],
//    ["0","0","0","0","0"]
//    ]
//    Output: 1
//    Input: grid = [
//    ["1","1","0","0","0"],
//    ["1","1","0","0","0"],
//    ["0","0","1","0","0"],
//    ["0","0","0","1","1"]
//    ]
//    Output: 3
    //Find Number OF Islands >> BFS
    fun numIslands(grid: Array<CharArray>): Int {
        var count = 0
        for (i in grid.indices) {
            for (j in 0 until grid[i].size) {
                if (grid[i][j] == '1') {
                    count++
                    val islandLength = BFS(i, j, grid, 0)
                    println(islandLength)
                }
            }
        }
        return count
    }

    fun BFS(i: Int, j: Int, grid: Array<CharArray>, count: Int):Int {
        if (i < 0 || i >= grid.size || j < 0 || j >= grid[i].size || grid[i][j] == '0') {
            return count
        }
        var counter = count + 1
        grid[i][j] = '0'
        counter =  BFS(i + 1, j, grid, counter) // down
        counter =  BFS(i - 1, j, grid, counter) //up
        counter =  BFS(i, j + 1, grid, counter) //right
        counter =  BFS(i, j - 1, grid, counter) //left
        return counter
    }

    // Pascal's Triangle
//    Input: numRows = 5
//    Output: [[1],[1,1],[1,2,1],[1,3,3,1],[1,4,6,4,1]]
//    Input: numRows = 1
//    Output: [[1]]
    fun generatePascalTriangle(numRows: Int): List<List<Int>> {
        val parentList: MutableList<List<Int>> = ArrayList()

        if (numRows == 0)
            return parentList

        //add [1]
        val firstRow: MutableList<Int> = ArrayList()
        firstRow.add(1)
        parentList.add(firstRow)

        for (i in 1 until numRows) {
            //add [1] in the beginning
            val newRow: MutableList<Int> = ArrayList()
            newRow.add(1)

            val prevRow = parentList[i - 1]

            for (j in 1 until i) {
                newRow.add(prevRow[j - 1] + prevRow[j])
            }

            //add [1] in the end
            newRow.add(1)
            parentList.add(newRow)
        }
        return parentList
    }

    fun maxAreaIsland(inputArray: Array<IntArray>): Int {
        val m = inputArray.size
        val n = inputArray[0].size
        var maxCount = 0
        val visited:Array<BooleanArray> = Array(m){BooleanArray(n)}
        for (i in 0 until m){
            for (j in 0 until n){
                if(!visited[i][j] && inputArray[i][j] == 1) {
                    val count = findTheLargestPath(i, j, m, n,inputArray, visited, 1)
                    if(count > maxCount){
                        maxCount = count
                    }
                }
            }
        }
        return maxCount
    }

    private fun findTheLargestPath(i: Int, j: Int, rowMax: Int, colMax: Int, array: Array<IntArray>, visited: Array<BooleanArray>, count:Int):Int {
        if(i >= rowMax || j >= colMax){
            return count
        }
        var counter = count
        visited[i][j] = true
        val directions = arrayOf(intArrayOf(0, 1), intArrayOf(1, 1), intArrayOf(1, 0), intArrayOf(1, -1),
            intArrayOf(0,-1), intArrayOf(-1, -1), intArrayOf(-1, 0), intArrayOf(-1, 1))
        for (dirIndx in directions.indices){
            val newi = i + directions[dirIndx][0]
            val newj = j + directions[dirIndx][1]
            if(newi >= 0 && newj >= 0 && newi < rowMax && newj < colMax && !visited[newi][newj] && array[newi][newj] == 1){
                counter = findTheLargestPath(newi, newj, rowMax, colMax,array, visited, counter+1)
            }
        }
        return counter
    }

    fun printWaveMatrix(array:Array<IntArray>){
        val rowLength = array.size
        val colLength = array[0].size
        println("wave matrix : ")
        var i = 0
        while (i < colLength) {
            wave(i, rowLength, array, colLength)
            i++
        }
    }

    private fun wave(index: Int = 0, rowLength: Int, array: Array<IntArray>, colLength: Int) {
        if(index % 2 == 0){
            for (i in 0 until rowLength) {
                print("${array[i][index]} \t")
            }
        }else{
            for (i in rowLength - 1 downTo 0) {
                print("${array[i][index + 1]} \t")
            }
        }
    }

    fun zeroMatrix(array: Array<IntArray>) {
        val m = array.size
        val n = array[0].size
        val visited:Array<BooleanArray> = Array(m){BooleanArray(n)}
        for (i in 0 until m){
            for (j in 0 until n){
                if(array[i][j] == 0 && !visited[i][j]){
                    markEntireRowZero(array, i, j, m, n, visited)
                    markEntireColumnZero(array, i, j, m, n, visited)
                }
            }
        }
        print(array)
    }

    private fun markEntireRowZero(array: Array<IntArray>, i: Int, j: Int, m: Int, n: Int, visited: Array<BooleanArray>) {
        for (index in 0 until n){
            visited[index][j] = true
            array[index][j] = 0
        }
    }

    fun markEntireColumnZero(array: Array<IntArray>, i: Int, j: Int, m:Int, n:Int, visited: Array<BooleanArray>) {
        for (index in 0 until m){
            visited[i][index] = true
            array[i][index] = 0
        }
    }

    fun rotateArrayBy90ClockWise(array: Array<IntArray>){
       val n = array.size
       for (i in 0 until n/2){
           print("inside i loop $i")
           for (j in i until n-i-1){
               val temp = array[i][j]
               array[i][j] = array[n-j-1][i]
               array[n-j-1][i] = array[n-i-1][n-j-1]
               array[n-i-1][n-j-1] = array[j][n-i-1]
               array[j][n-i-1] = temp
           }
       }
       println(array)
    }

    fun rotateArrayBy90AntiClockWise(array: Array<IntArray>){
        val n = array.size
        for (i in 0 until n/2){
            print("inside i loop $i")
            for (j in i until n-i-1){
                val temp = array[i][j]
                array[i][j] = array[j][n-i-1]
                array[j][n-i-1] = array[n-i-1][n-j-1]
                array[n-i-1][n-j-1] = array[n-j-1][i]
                array[n-j-1][i] = temp
            }
        }
        println(array)
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