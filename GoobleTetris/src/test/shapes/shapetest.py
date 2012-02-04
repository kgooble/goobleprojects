'''
Created on 2012-01-31

@author: Gooble
'''
from main.movement.transform.point import Point
from main.shapes.shape import Shape
from main.shapes.tile import T_TILE
import unittest

class Test(unittest.TestCase):

    def setUp(self):
        self.shape = Shape(T_TILE)

    def test_create_shape_creates_correct_set_of_initial_points(self):
        self.assertEqual([Point(0, 0),
                          Point(0, -1),
                          Point(-1, 0),
                          Point(0, 1)], self.shape.get_points())
        
    def test_shape_move_left(self):
        points = [Point(0, -1),
          Point(0, -2),
          Point(-1, -1),
          Point(0, 0)]
        self.shape.set_position(points)
        self.assertEqual(points, self.shape.get_points())


if __name__ == "__main__":
    unittest.main()