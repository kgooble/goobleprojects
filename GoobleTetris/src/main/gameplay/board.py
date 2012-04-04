'''
Created on 2012-01-29

@author: Gooble
'''
from main.shapes.spawn.randomspawn import RandomSpawn
from main.movement.movecompletion import MoveCompletion
from main.config import DEFAULT_CONFIG
from main.gameplay.rowclearing import RowClearing
from main.gameplay.rowshift import RowShift

import threading

class Board(object):

    def __init__(self, pieces=None, spawner=None):
        if pieces is None:
            pieces = [row[:] for row in DEFAULT_CONFIG]
        if spawner is None:
            spawner = RandomSpawn()
        self.pieces = pieces
        self.spawner = spawner
        self.cur_shape = None
        self.game_over = False
        self.num_rows_cleared = 0
        self.movement_lock = threading.RLock()
        
    def is_game_over(self):
        return self.game_over
                
    def get_pieces(self):
        return self.pieces
    
    def drop_shape(self):
        if self.cur_shape is None:
            return
        self._move_tiles(self.cur_shape.get_points(), self.get_predicted_points())
        
    def get_predicted_points(self):
        old_points = self.cur_shape.get_points()
        new_points = self._move_shape_down_one_row(old_points)
        while new_points != old_points:
            old_points = new_points
            new_points = self._move_shape_down_one_row(old_points)
        return new_points
        
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
            
    def get_rows_cleared(self):
        rows_cleared = self.num_rows_cleared
        self.num_rows_cleared = 0
        return rows_cleared
    
    def get_next_shape(self):
        return self.spawner.get_next_shapetype()
                
    def _clear_full_rows(self):
        rows_cleared = RowClearing(self.pieces).clear_and_get_rows()
        for row in rows_cleared:
            self.pieces = RowShift(self.pieces).down(row)
        self.num_rows_cleared = len(rows_cleared)
                   
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
        self.movement_lock.acquire()
        self.cur_shape.set_position(new_points)
        MoveCompletion(self.pieces).move_tiles(old_points, new_points, self.cur_shape.get_tile())
        self.movement_lock.release()
