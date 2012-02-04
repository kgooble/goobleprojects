'''
Created on 2012-02-01

@author: Gooble
'''
from main.movement.movevalidity import MoveValidity
from main.movement.edgesfrompieces import EdgesFromPieces
from main.movement.direction import DOWN
from main.movement.transform.pointtransformer import PointTransformer
from main.shapes.tile import EMPTY_TILE

class MoveCompletion(object):

    def __init__(self, pieces):
        self.pieces = pieces
        
    def get_next_points(self, old_points):
        return self.move(DOWN, old_points)
        
    def move(self, direction, old_points):
        new_points = PointTransformer(old_points).translate_in_dir(direction)
        return self._get_valid_points(old_points, new_points)
        
    def rotate(self, old_points):
        new_points = PointTransformer(old_points).rotate()
        return self._get_valid_points(old_points, new_points)
        
    def is_valid_move(self, old_points, new_points):
        return MoveValidity(self.pieces).is_valid_move(old_points, new_points)
    
    def is_valid_first_move(self, old_points):
        return MoveValidity(self.pieces).is_valid_first_move(old_points)
    
    def move_tiles(self, old_points, new_points, cur_tile):
        self._set_points_in_board_to_tile(old_points, EMPTY_TILE)
        self._set_points_in_board_to_tile(new_points, cur_tile)
        
    def _set_points_in_board_to_tile(self, points, tile):
        edges = EdgesFromPieces(self.pieces).create_edges()
        for point in points:
            if not edges.is_point_outside_boundary(point):
                self.pieces[point.row][point.col] = tile

    def _get_valid_points(self, old_points, new_points):
        if self.is_valid_move(old_points, new_points):
            return new_points
        else:
            return old_points
        