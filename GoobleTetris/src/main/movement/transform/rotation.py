'''
Created on 2012-01-30

@author: Gooble
'''
from main.movement.transform.point import Point

class Rotation(object):

    def rotate_90(self, points):
        return [Point(point.col, -point.row) for point in points]