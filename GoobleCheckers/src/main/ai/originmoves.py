'''
Created on 2011-12-10

@author: Gooble
'''
import main.game.movements.movement as movement

class OriginMoves(object):
    
    def __init__(self, board, origin):
        self.board = board
        self.origin = origin
        
    def get_moves(self):
        all_moves = []
        for row in range(0, self.board.DEFAULT_HEIGHT):
            for col in range(0, self.board.DEFAULT_WIDTH):
                self._get_moves_for_position(row, col, all_moves)
            
        return all_moves
    
    def _get_moves_for_position(self, row, col, all_moves):
        if self.board.get_piece(row, col) is not None and self.board.get_piece(row, col).get_origin() == self.origin:
            moves = movement.Movement(self.board, row, col).get_available_moves()
            all_moves.extend(moves)