'''
Created on 2012-01-31

@author: Gooble
'''
import unittest

from main.point import Point
from main import shapetype
from main.shape import Shape

class Test(unittest.TestCase):

    def setUp(self):
        self.shape = Shape(shapetype.T)

    def test_create_shape_creates_correct_set_of_initial_points(self):
        self.assertEqual([Point(0, 0),
                          Point(0, -1),
                          Point(-1, 0),
                          Point(0, 1)], self.shape.get_points())
        
    def test_shape_move_left(self):
        self.shape.move_left()
        self.assertEqual([Point(0, -1),
                          Point(0, -2),
                          Point(-1, -1),
                          Point(0, 0)], self.shape.get_points())

    


if __name__ == "__main__":
    #import sys;sys.argv = ['', 'Test.test_create_shape_creates_correct_set_of_initial_points']
    unittest.main()