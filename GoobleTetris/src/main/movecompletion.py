'''
Created on 2012-02-01

@author: Gooble
'''
from main.direction import DOWN
from main.pointtransformer import PointTransformer
from main.movevalidity import MoveValidity
from main import tile
from main.edgesfrompieces import EdgesFromPieces

class MoveCompletion(object):

    def __init__(self, pieces):
        self.pieces = pieces
        self.validity = MoveValidity(self.pieces)
        self.edges = EdgesFromPieces(self.pieces).create_edges()
        
    def get_next_points(self, old_points):
        return self.move(DOWN, old_points)
        
    def move(self, direction, old_points):
        new_points = PointTransformer(old_points).translate_in_dir(direction)
        return self._get_valid_points(old_points, new_points)
        
    def _get_valid_points(self, old_points, new_points):
        if self.is_valid_move(old_points, new_points):
            return new_points
        else:
            return old_points
        
    def rotate(self, old_points):
        new_points = PointTransformer(old_points).rotate()
        return self._get_valid_points(old_points, new_points)
        
    def is_valid_move(self, old_points, new_points):
        return self.validity.is_valid_move(old_points, new_points)
    
    def move_tiles(self, old_points, new_points, cur_tile):
        self._set_points_in_board_to_tile(old_points, tile.EMPTY_TILE)
        self._set_points_in_board_to_tile(new_points, cur_tile)
        
    def _set_points_in_board_to_tile(self, points, tile):
        for point in points:
            if not self.edges.is_point_outside_boundary(point):
                self.pieces[point.row][point.col] = tile
