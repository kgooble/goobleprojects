'''
Created on 2012-01-29

@author: Gooble
'''
from main.movement.transform.point import Point

class Translation(object):
    
    def in_direction(self, points, direction):
        new_points = []
        for point in points:
            new_row = point.row + direction.row_dir
            new_col = point.col + direction.col_dir
            new_points.append(Point(new_row, new_col))
        
        return new_points