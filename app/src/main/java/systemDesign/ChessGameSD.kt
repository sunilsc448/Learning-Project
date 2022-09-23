package systemDesign

import kotlin.math.abs

/**
1. The chessboard is two-dimensional of size 8x8 and has 64 squares where the pieces can stand.
2. There are two online players, where one player controls the white pieces and the other player controls the black pieces. The color is assigned randomly to the players.
3. Each player gets alternate turns, starting with the white side.
4. There are 6 types of chess pieces on the board: king, queen, knight, bishop, rook and pawn.
5. At the start of the game, each player has 1 king, 1 queen, 2 knights, 2 bishops, 2 rooks and 8 pawns.
6. Each piece can only move according to its own fixed rules.
7. A piece can be captured if the opponent player’s piece moves to its current position.
8. Once a piece is captured, it’s removed from the chessboard.
9. There’s no option to cancel once a move is made.
10. There can be two outcomes of the game: checkmate (one side wins) or stalemate (draw).
11. Once an outcome is achieved, the game ends.
 */

class ChessGameSD{
    private val players:MutableList<ChessPlayer> get() = players
    private val moves:MutableList<ChessMove> get() = moves
    private val board:ChessBoard get() = board
    private var status:ChessGameStatus = ChessGameStatus.ACTIVE
    private lateinit var currentTurnPlayer:ChessPlayer

    fun initialize(p1:ChessPlayer, p2:ChessPlayer){
        players.add(p1)
        players.add(p2)
        board.resetBoard()
        if(p1.color == ChessColor.WHITE){
            this.currentTurnPlayer = p1
        }else{
            this.currentTurnPlayer = p2
        }
        moves.clear()
    }

    fun gameExit():Boolean = status != ChessGameStatus.ACTIVE

    fun playerMove(player:ChessPlayer, start: ChessSpot, end: ChessSpot):Boolean{
        val move = ChessMove(player, start, end)
        return makeMove(move, player)
    }

    private fun makeMove(move: ChessMove, player: ChessPlayer):Boolean{
        if(player != currentTurnPlayer)
            return false

        if(player.color != currentTurnPlayer.color)
            return false

        if(!move.start.piece?.canMove(board, move.start, move.end)!!){
            return false
        }

        val destPiece = move.end.piece
        if(destPiece != null){
            destPiece.isKilled = true
            move.pieceKilled = destPiece
        }else{
            move.pieceMoved = move.start.piece
        }

        moves.add(move)

        move.end.piece = move.start.piece
        move.start.piece = null

        if(move.end.piece is King){
            if(move.player.color == ChessColor.WHITE){
                status = ChessGameStatus.WHITE_WIN
            }else{
                status = ChessGameStatus.BLACK_WIN
            }
        }

        if(this.currentTurnPlayer == players[0]){
            currentTurnPlayer = players[1]
        }else{
            currentTurnPlayer = players[0]
        }
        return true
    }
}

class ChessSpot(val x:Int, val y:Int, var piece:ChessPiece?)

abstract class ChessPiece(val color:ChessColor){
    var isKilled:Boolean = false
    abstract fun canMove(board:ChessBoard, start: ChessSpot, end: ChessSpot):Boolean
}

class Pawn(private val panColor: ChessColor):ChessPiece(panColor){
    override fun canMove(board: ChessBoard, start: ChessSpot, end: ChessSpot): Boolean {
        if (end.piece?.color == panColor) {
            return false
        }
        if(start.piece?.color == ChessColor.WHITE){
            val x: Int = end.x - start.x
            return x == 1
        }else if(start.piece?.color == ChessColor.BLACK){
            val x: Int = start.x - end.x
            return x == 1
        }else{
            return false
        }
    }
}

class Rook(private val panColor: ChessColor):ChessPiece(panColor){
    override fun canMove(board: ChessBoard, start: ChessSpot, end: ChessSpot):Boolean {
        if (end.piece?.color == panColor) {
            return false
        }
        val x: Int = abs(start.x - end.x)
        val y: Int = abs(start.y - end.y)
        return x + y > 0
    }
}

class Bishop(private val panColor: ChessColor):ChessPiece(panColor){
    override fun canMove(board: ChessBoard, start: ChessSpot, end: ChessSpot):Boolean {
        if (end.piece?.color == panColor) {
            return false
        }
        val x: Int = abs(start.x - end.x)
        val y: Int = abs(start.y - end.y)
        return x == y
    }
}

class Knight(private val panColor: ChessColor):ChessPiece(panColor){
    override fun canMove(board: ChessBoard, start: ChessSpot, end: ChessSpot):Boolean {
        if (end.piece?.color == panColor) {
            return false
        }
        val x: Int = abs(start.x - end.x)
        val y: Int = abs(start.y - end.y)
        return x * y == 2
    }
}

class King(private val panColor: ChessColor):ChessPiece(panColor){
    override fun canMove(board: ChessBoard, start: ChessSpot, end: ChessSpot):Boolean {
        if (end.piece?.color == panColor) {
            return false
        }
        val x: Int = abs(start.x - end.x)
        val y: Int = abs(start.y - end.y)
        return x + y == 1
    }
}

class Queen(private val panColor: ChessColor):ChessPiece(panColor){
    override fun canMove(board: ChessBoard, start: ChessSpot, end: ChessSpot):Boolean {
        if (end.piece?.color == panColor) {
            return false
        }
        val x: Int = abs(start.x - end.x)
        val y: Int = abs(start.y - end.y)
        return x + y == 1
    }
}

class ChessBoard(private val boxes:Array<Array<ChessSpot>>){
    fun resetBoard(){
        boxes[0][0] = ChessSpot(0, 0, Rook(ChessColor.WHITE))
        boxes[0][1] = ChessSpot(0, 1, Bishop(ChessColor.WHITE))
        boxes[0][2] = ChessSpot(0, 2, Knight(ChessColor.WHITE))
        boxes[0][3] = ChessSpot(0, 3, Queen(ChessColor.WHITE))
        boxes[0][4] = ChessSpot(0, 4, King(ChessColor.WHITE))
        boxes[0][5] = ChessSpot(0, 5, Knight(ChessColor.WHITE))
        boxes[0][6] = ChessSpot(0, 6, Bishop(ChessColor.WHITE))
        boxes[0][7] = ChessSpot(0, 7, Rook(ChessColor.WHITE))

        for (i in 0..7){
            boxes[1][i] = ChessSpot(1, i, Pawn(ChessColor.WHITE))
        }

        boxes[7][0] = ChessSpot(7, 0, Rook(ChessColor.BLACK))
        boxes[7][1] = ChessSpot(7, 1, Bishop(ChessColor.BLACK))
        boxes[7][2] = ChessSpot(7, 2, Knight(ChessColor.BLACK))
        boxes[7][3] = ChessSpot(7, 3, Queen(ChessColor.BLACK))
        boxes[7][4] = ChessSpot(7, 4, King(ChessColor.BLACK))
        boxes[7][5] = ChessSpot(7, 5, Knight(ChessColor.BLACK))
        boxes[7][6] = ChessSpot(7, 6, Bishop(ChessColor.BLACK))
        boxes[7][7] = ChessSpot(7, 7, Rook(ChessColor.BLACK))

        for (i in 0..7){
            boxes[6][i] = ChessSpot(6, i, Pawn(ChessColor.BLACK))
        }

        //empty patches
        for (i in 2..5){
            for (j in 0 .. 8){
                boxes[i][j] = ChessSpot(i, j, null)
            }
        }
    }

    fun getSpot(x:Int, y:Int):ChessSpot{
        return boxes[x][y]
    }
}

class ChessPlayer(account:ChessPlayerAccount, val color: ChessColor, val timeLeftInSec:Int)

class ChessPlayerAccount(userName:String, password:String, name:String, email:String, phone:String)

enum class ChessColor{
    WHITE, BLACK
}

enum class ChessGameStatus{
    ACTIVE, PAUSED, BLACK_WIN, WHITE_WIN, FORFEIT
}

class ChessMove(val player:ChessPlayer, val start: ChessSpot, val end: ChessSpot){
    var pieceMoved: ChessPiece? = null
    var pieceKilled:ChessPiece? = null
}



