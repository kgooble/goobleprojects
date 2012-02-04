'''
Created on 2012-01-31

@author: Gooble
'''
from main.movement.transform.rotation import Rotation
from main.movement.transform.translation import Translation
from main.movement.transform.point import Point

class PointTransformer(object):

    def __init__(self, points, translator=Translation(), rotator=Rotation()):
        self.points = points
        self.translator = translator
        self.rotator = rotator
        
    def translate_in_dir(self, direction):
        return self.translator.in_direction(self.points, direction)
    
    def rotate(self):
        first_point = self.points[0]
        num_moved_left = first_point.col
        num_moved_up = first_point.row
        
        transformable_points = self._center_pts_at_zero(num_moved_left, num_moved_up)
        rotated_points = self.rotator.rotate_90(transformable_points)
        
        return self._return_points_to_original_center(rotated_points, num_moved_up, num_moved_left)
    
    def _center_pts_at_zero(self, num_moved_left, num_moved_up):
        transformable_points = []
        for point in self.points:
            transformable_points.append(Point(point.row - num_moved_up, point.col - num_moved_left))
        return transformable_points
    
    def _return_points_to_original_center(self, transformed_points, num_moved_up, num_moved_left):
        result_points = []
        for point in transformed_points:
            result_points.append(Point(point.row + num_moved_up, point.col + num_moved_left))
        return result_points
        