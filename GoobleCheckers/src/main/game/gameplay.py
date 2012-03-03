'''
Created on 2011-12-11

@author: Gooble
'''
from main.game.board import Board
from main.game.turn import Turn
from main.game.state import State
from main.ai.originmoves import OriginMoves

class GamePlay(object):
    
    def __init__(self):
        self.board = Board()
        self.current_turn = Turn(self)
        self.state = State(self.board)
        
    def is_current_turn(self, origin):
        return self.current_turn.origin == origin
        
    def make_move(self, move):
        for position_change in move:
            self.move_piece(position_change.from_loc, position_change.to_loc)
        
    def move_piece(self, from_loc, to_loc):
        self.current_turn.handle_movement(from_loc, to_loc)
        
        if self.current_turn.is_over():
            self.current_turn.handle_jumps()
            self.king_piece_if_necessary(to_loc)
            self.current_turn = Turn(self, self.current_turn.origin)
            self.pass_if_needed()
                
    def pass_if_needed(self):
        if len(OriginMoves(self.board, self.current_turn.origin).get_moves()) == 0:
            self.current_turn = Turn(self, self.current_turn.origin)
            if len(OriginMoves(self.board, self.current_turn.origin).get_moves()) == 0:
                self.state.set_draw()
            
    def king_piece_if_necessary(self, king_loc):
        piece = self.board.get_piece(king_loc[0], king_loc[1])
        piece.set_king(self._should_piece_be_king(piece, king_loc[0]))
        
    def get_piece(self, row, col):
        return self.board.get_piece(row, col)
    
    def _should_piece_be_king(self, piece, row):
        if piece.is_king():
            return True
        if piece.is_from_top() and row == self.board.DEFAULT_HEIGHT - 1:
            return True
        if piece.is_from_bottom() and row == 0:
            return True
        return False
    
    def set_piece(self, row, col, piece):
        if self.board.invalid_position(row, col):
            return
        self.state.update_state_for_insertion(row, col, piece)
        self.board.set_piece(row, col, piece)
    
    def is_game_over(self):
        return self.state.is_game_over()
    
    def get_score(self, origin):
        return self.state.get_score(origin)
    
