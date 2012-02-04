'''
Created on 2012-01-29

@author: Gooble
'''
from main.shapes.spawn.randomspawn import RandomSpawn
from main.movement.movecompletion import MoveCompletion
from main.rowclearing import RowClearing
from main.rowshift import RowShift

class Board(object):

    def __init__(self, pieces, spawner=RandomSpawn()):
        self.pieces = pieces
        self.spawner = spawner
        self.cur_shape = None
        self.game_over = False
        
    def is_game_over(self):
        return self.game_over
                
    def get_pieces(self):
        return self.pieces
    
    def drop_shape(self):
        if self.cur_shape is None:
            return
        old_points = self.cur_shape.get_points()
        new_points = self._move_shape_down_one_row(old_points)
        while new_points != old_points:
            old_points = new_points
            new_points = self._move_shape_down_one_row(old_points)
        self._move_tiles(self.cur_shape.get_points(), new_points)

    def step(self):
        if self.cur_shape is None:
            self._clear_full_rows()
            self.cur_shape = self.spawner.get_next_centered_shape(len(self.pieces[0]))
            self._do_first_move()
        else:
            old_points = self.cur_shape.get_points()
            new_points = self._move_shape_down_one_row(old_points)
            self._move_tiles(old_points, new_points)
            self._restart_cycle_if_not_moved(old_points, new_points)
            
    def move(self, direction):
        if self.cur_shape is not None:
            new_points = MoveCompletion(self.pieces).move(direction, self.cur_shape.get_points())
            self._move_tiles(self.cur_shape.get_points(), new_points)
            
    def rotate(self):
        if self.cur_shape is not None:
            new_points = MoveCompletion(self.pieces).rotate(self.cur_shape.get_points())
            self._move_tiles(self.cur_shape.get_points(), new_points)
                
    def _clear_full_rows(self):
        rows_cleared = RowClearing(self.pieces).clear_and_get_rows()
        for row in rows_cleared:
            self.pieces = RowShift(self.pieces).down(row) 
                   
    def _restart_cycle_if_not_moved(self, old_points, new_points):
        if new_points == old_points:
            self.cur_shape = None
            self.step()
                    
    def _do_first_move(self):
        completion = MoveCompletion(self.pieces)
        if completion.is_valid_first_move(self.cur_shape.get_points()):
            self._move_tiles([], self.cur_shape.get_points())
        else:
            self.game_over = True

    def _move_shape_down_one_row(self, old_points):
        new_points = MoveCompletion(self.pieces).get_next_points(old_points)
        return new_points

    def _move_tiles(self, old_points, new_points):
        self.cur_shape.set_position(new_points)
        MoveCompletion(self.pieces).move_tiles(old_points, new_points, self.cur_shape.get_tile())
