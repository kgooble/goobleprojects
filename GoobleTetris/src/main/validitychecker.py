'''
Created on 2012-02-01

@author: Gooble
'''

class MoveValidity(object):

    def __init__(self, edges):
        self.edges = edges

    def is_valid_move(self, pieces, old_points, new_points):
        for point in new_points:
            if self._outside_or_occupied_point(pieces, old_points, point):
                return False
            
        return True

    def is_valid_first_move(self, pieces, new_points):
        for point in new_points:
            if self._within_boundary_and_occupied(pieces, point):
                return False
        return True

    def _outside_or_occupied_point(self, pieces, old_points, point):
        return self.edges.is_point_outside_boundary(point) \
            or (not pieces[point.row][point.col].is_empty()\
                and point not in old_points)
            
    def _within_boundary_and_occupied(self, pieces, point):
        return not self.edges.is_point_outside_boundary(point) \
            and not pieces[point.row][point.col].is_empty()