'''
Created on 2012-01-29

@author: Gooble
'''
from main.edges import Edges
from main.randomspawn import RandomSpawn
from main.pointtransformer import PointTransformer
from main.direction import DOWN
from main import tile
from main.validitychecker import MoveValidity

class Board(object):

    def __init__(self, pieces, spawner=RandomSpawn(), edges=Edges()):
        self.edges = edges
        self.pieces = pieces
        self.spawner = spawner
        self.validity = MoveValidity(edges)
        self.cur_shape = None
        self.game_over = False
        
    def is_valid_move(self, old_points, new_points):
        return self.validity.is_valid_move(self.pieces, old_points, new_points)
    
    def is_valid_first_move(self, new_points):
        return self.validity.is_valid_first_move(self.pieces, new_points)
    
    def is_game_over(self):
        return self.game_over
                
    def get_pieces(self):
        return self.pieces

    def step(self):
        if self.cur_shape is None:
            self.cur_shape = self.spawner.get_next_centered_shape(self.edges.get_width())
            self._do_first_move()
        else:
            old_points = self.cur_shape.get_points()
            new_points = self._do_move(old_points)
            self._move_tiles(old_points, new_points)
            if new_points == old_points:
                self.cur_shape = None
                self.step()
          
                    
    def _set_points_in_board_to_tile(self, points, tile):
        for point in points:
            if not self.edges.is_point_outside_boundary(point):
                self.pieces[point.row][point.col] = tile


    def _do_first_move(self):
        if self.is_valid_first_move(self.cur_shape.get_points()):
            self._set_points_in_board_to_tile(self.cur_shape.get_points(), self.cur_shape.get_tile())
        else:
            self.game_over = True


    def _do_move(self, old_points):
        new_points = PointTransformer(self.cur_shape).translate_in_dir(DOWN)
        if self.is_valid_move(old_points, new_points):
            self.cur_shape.set_position(new_points)
        else:
            new_points = old_points
        return new_points


    def _move_tiles(self, old_points, new_points):
        self._set_points_in_board_to_tile(old_points, tile.EMPTY_TILE)
        self._set_points_in_board_to_tile(new_points, self.cur_shape.get_tile())
