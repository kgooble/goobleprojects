'''
Created on 2012-01-29

@author: Gooble
'''
from main.randomspawn import RandomSpawn
from main.movevalidity import MoveValidity
from main.movecompletion import MoveCompletion

class Board(object):

    def __init__(self, pieces, spawner=RandomSpawn()):
        self.pieces = pieces
        self.spawner = spawner
        self.validity = MoveValidity(pieces)
        self.cur_shape = None
        self.game_over = False
        
    def is_valid_first_move(self, new_points):
        return self.validity.is_valid_first_move(new_points)
    
    def is_game_over(self):
        return self.game_over
                
    def get_pieces(self):
        return self.pieces

    def step(self):
        if self.cur_shape is None:
            self.cur_shape = self.spawner.get_next_centered_shape(len(self.pieces[0]))
            self._do_first_move()
        else:
            old_points = self.cur_shape.get_points()
            new_points = self._do_move(old_points)
            self._move_tiles(old_points, new_points)
            self._restart_cycle_if_not_moved(old_points, new_points)
            
    def move(self, direction):
        if self.cur_shape is not None:
            completion = MoveCompletion(self.pieces)
            new_points = completion.move(direction, self.cur_shape.get_points())
            self._move_tiles(self.cur_shape.get_points(), new_points)
                    
    def _restart_cycle_if_not_moved(self, old_points, new_points):
        if new_points == old_points:
            self.cur_shape = None
            self.step()
                    
    def _do_first_move(self):
        if self.is_valid_first_move(self.cur_shape.get_points()):
            self._move_tiles([], self.cur_shape.get_points())
        else:
            self.game_over = True

    def _do_move(self, old_points):
        new_points = MoveCompletion(self.pieces).get_next_points(old_points)
        self.cur_shape.set_position(new_points)
        return new_points

    def _move_tiles(self, old_points, new_points):
        MoveCompletion(self.pieces).move_tiles(old_points, new_points, self.cur_shape.get_tile())
