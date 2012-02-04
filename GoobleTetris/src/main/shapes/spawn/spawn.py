'''
Created on 2012-02-01

@author: Gooble
'''
from main.movement.transform.point import Point

class AbstractSpawn(object):
    
    def get_next_centered_shape(self, board_width):
        shape = self.create_shape()
        new_points = []
        for point in shape.get_points():
            new_points.append(Point(point.row, point.col + int(board_width / 2)))
        shape.set_position(new_points)
        return shape
    
    def create_shape(self):
        pass