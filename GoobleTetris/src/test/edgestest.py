'''
Created on 2012-01-31

@author: Gooble
'''
import unittest
from main.edges import Edges
from main.point import Point


class Test(unittest.TestCase):

    def setUp(self):
        self.edges = Edges()

    def test_horizontal_boundaries(self):
        self.assertTrue(self.edges.is_point_outside_boundary(Point(0, -1)))
        self.assertFalse(self.edges.is_point_outside_boundary(Point(0, 0)))
        self.assertFalse(self.edges.is_point_outside_boundary(Point(0, 5)))
        self.assertFalse(self.edges.is_point_outside_boundary(Point(0, 9)))
        self.assertTrue(self.edges.is_point_outside_boundary(Point(0, 10)))
        
    def test_vertical_boundaries(self):
        self.assertTrue(self.edges.is_point_outside_boundary(Point(20, 0)))
        self.assertFalse(self.edges.is_point_outside_boundary(Point(19, 0)))
        self.assertFalse(self.edges.is_point_outside_boundary(Point(0, 0)))
        self.assertTrue(self.edges.is_point_outside_boundary(Point(-1, 0)))
        
    def test_get_width(self):
        self.assertEqual(10, self.edges.get_width())


if __name__ == "__main__":
    unittest.main()