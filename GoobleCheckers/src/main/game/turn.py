'''
Created on 2011-10-23

@author: Gooble
'''
from main.game import origin
from main.game.movements.movement import Movement
from main.game.movements.move import Move

'''
a turn will become the list of currently taken moves.

if a player takes a move, one of the following will happen:
- if a negating move exists, that move will be deleted from the move list
- otherwise, the move will be added to the current set of moves

a negating move is the move with the same to and fro locations, but switched.
for example, a move from 1,2 to 2,3 would have a negating move of 2,3 to 1,2.

each move can quickly calculate whether it is a jump or not, 
'''
def other_origin(prev_origin):
    if prev_origin == origin.TOP:
        return origin.BOTTOM
    else:
        return origin.TOP


class Turn(object):

    def __init__(self, game, origin=origin.BOTTOM):
        self.game = game
        self.piece = None
        self.origin = other_origin(origin)
        self.moves = Move()
        self.over = False
    
    def handle_movement(self, from_loc, to_loc):
        if self._illegal_move(from_loc, to_loc):
            return
        
        self.game.set_piece(to_loc[0], to_loc[1], self.game.get_piece(from_loc[0], from_loc[1]))
        self.game.set_piece(from_loc[0], from_loc[1], None)
        
        self.moves.add(from_loc, to_loc)
        
        self._check_if_turn_complete(to_loc)
        
    def _check_if_turn_complete(self, to_loc):
        new_moves = Movement(self.game.board, to_loc[0], to_loc[1]).get_available_moves()
        for move in new_moves:
            if move.contains_jump() and move.jumps_are_not_backward_from(self.moves) \
            and (self.moves.contains_jump() or len(self.moves) == 0):
                return
        self.over = True
        
        
    def _illegal_move(self, from_loc, to_loc):
        moves = Movement(self.game.board, from_loc[0], from_loc[1]).get_available_moves()
        piece = self.game.get_piece(from_loc[0], from_loc[1])
        if piece is not None and piece.get_origin() != self.origin:
            return True
        
        if self.piece is not None and self.piece is not piece:
            return True
        
        return not self._is_valid_forward_movement(to_loc, piece, moves) and \
               not self._is_valid_backward_movement(from_loc, to_loc)
    
    def _is_valid_forward_movement(self, to_loc, piece, moves):
        for move_list in moves:
            if (len(self.moves) > 0 and move_list.contains_jump_ending_in(to_loc)) \
            or (len(self.moves) == 0 and move_list.contains_move_ending_in(to_loc)):
                self.piece = piece
                return True
        return False
    
    def _is_valid_backward_movement(self, from_loc, to_loc):
        for move in self.moves:
            if move.is_backwards_version_of(from_loc, to_loc):
                return True
        
        return False
    
    def handle_jumps(self):
        for move in self.moves:
            if move.is_jump():
                row, col = move.get_jumped_piece()
                self.game.set_piece(row, col, None)
    
    def is_over(self):
        return self.over
    
    def is_computers_turn(self, computer):
        return self.origin == computer.origin