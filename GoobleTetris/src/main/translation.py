'''
Created on 2012-01-29

@author: Gooble
'''

import main.point

class Translation(object):
    
    def in_direction(self, points, direction):
        new_points = []
        for point in points:
            new_row = point.row + direction.row_dir
            new_col = point.col + direction.col_dir
            new_points.append(main.point.Point(new_row, new_col))
        
        return new_points