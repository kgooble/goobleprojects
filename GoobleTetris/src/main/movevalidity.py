'''
Created on 2012-02-01

@author: Gooble
'''
from main.edgesfrompieces import EdgesFromPieces

class MoveValidity(object):

    def __init__(self, pieces):
        self.pieces = pieces
        self.edges = EdgesFromPieces(pieces).create_edges()

    def is_valid_move(self, old_points, new_points):
        for point in new_points:
            if self._outside_or_occupied_point(old_points, point):
                return False
        return True

    def is_valid_first_move(self, new_points):
        for point in new_points:
            if self._within_boundary_and_occupied(point):
                return False
        return True

    def _outside_or_occupied_point(self, old_points, point):
        return self.edges.is_point_outside_boundary(point) \
            or (not self.pieces[point.row][point.col].is_empty()\
                and point not in old_points)
            
    def _within_boundary_and_occupied(self, point):
        return not self.edges.is_point_outside_boundary(point) \
            and not self.pieces[point.row][point.col].is_empty()