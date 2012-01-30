'''
Created on 2012-01-29

@author: Gooble
'''

import main.point
import main.edges

class Translation(object):
    
    def __init__(self, points, edges=main.edges.Edges()):
        self.points = points
        self.edges = edges
        
    def in_direction(self, direction):
        new_points = []
        
        for point in self.points:
            new_x = point.x + direction.x_dir
            new_y = point.y + direction.y_dir
            
            if self.edges.is_point_outside_boundary(main.point.Point(new_x, new_y)):
                return self.points
            
            new_points.append(main.point.Point(new_x, new_y))
        
        return new_points