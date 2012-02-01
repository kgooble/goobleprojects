'''
Created on 2012-01-30

@author: Gooble
'''

from main.point import Point

class Rotation(object):

    def rotate_90(self, points):
        new_points = []
        for point in points:
            new_points.append(Point(point.col, -point.row))
                
        return new_points