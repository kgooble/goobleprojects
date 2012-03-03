'''
Created on 2011-10-03

@author: Gooble
'''
from main.game.movements.move import Move

class Movement(object):

    def __init__(self, board, row, col):
        self.board = board
        self.row = row
        self.col = col
        self.piece = board.get_piece(row, col)
        if self.piece is not None:
            self.origin = self.piece.get_origin()
        
    def get_available_moves(self):
        if self.piece is None:
            return []
        
        king_moves = []
        if self.piece.is_king():
            king_moves = self._add_moves_for_col(-self.origin.value, -1)\
                       + self._add_moves_for_col(-self.origin.value, +1)
        return king_moves \
            + self._add_moves_for_col(self.origin.value, -1) \
            + self._add_moves_for_col(self.origin.value, +1)
    
    def _add_moves_for_col(self, row_dir, col_dir):
        new_row = self.row + row_dir
        new_col = self.col + col_dir
        if self.board.valid_position(new_row, new_col) and self.board.get_piece(new_row, new_col) is None:
            move_list = Move()
            move_list.add((self.row, self.col), (new_row, new_col))
            return [move_list]
        elif self.board.get_piece(new_row, new_col) is not None and self.board.get_piece(new_row, new_col).get_origin() != self.origin:
            return self._calculate_jumped_moves(new_row, new_col, row_dir, col_dir)
            
        return []
        

    def _continue_adding_jumps_in_same_dir(self, row_dir, new_row, new_col, move, moves):
        new_new_row = new_row + row_dir
        self._add_jumped_moves(new_new_row, new_col, row_dir, -1, move, moves)
        self._add_jumped_moves(new_new_row, new_col, row_dir, +1, move, moves)
        return new_new_row


    def _account_for_king_movements(self, row_dir, col_dir, new_row, new_col, move, moves, new_new_row):
        if self.piece.is_king():
            new_new_row = new_row - row_dir
            self._add_jumped_moves(new_new_row, new_col, -row_dir, col_dir, move, moves)

    def _calculate_jumped_moves(self, row, col, row_dir, col_dir):
        #We have the row & column on which there is a piece. we have to keep going
        #along the diagonal -> if the next piece is occupied, return empty list - this isn't a valid move
        #if it's not occupied... that's the tricky part
        new_row = row + row_dir
        new_col = col + col_dir
        
        if self.board.get_piece(new_row, new_col) is not None or self.board.invalid_position(new_row, new_col):
            return []
        
        move = Move()
        move.add((row-row_dir, col-col_dir), (new_row, new_col))
        moves = [move]
        
        #Keep going along the same direction
        new_new_row = self._continue_adding_jumps_in_same_dir(row_dir, new_row, new_col, move, moves)
        
        self._account_for_king_movements(row_dir, col_dir, new_row, new_col, move, moves, new_new_row)
        
        return moves
    
    def _add_jumped_moves(self, row, col, row_dir, col_dir, move, moves):
        if self.board.get_piece(row, col + col_dir) is not None and self.board.get_piece(row, col + col_dir).get_origin() != self.origin:
            new_moves = self._calculate_jumped_moves(row, col + col_dir, row_dir, col_dir)
            if len(new_moves) > 0 and move in moves:
                moves.remove(move)
            [moves.append(move + m) for m in new_moves]
        