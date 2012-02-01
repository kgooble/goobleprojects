'''
Created on 2012-01-30

@author: Gooble
'''
import unittest

from main.point import Point
from main.rotation import Rotation

class Test(unittest.TestCase):

    def test_rotate_s_shape_90_degs(self):
        zero_deg_points = [Point(0, 0), 
                           Point(0, 1), 
                           Point(-1, 0), 
                           Point(-1, -1)]
        ninety_deg_points = [Point(0, 0), 
                             Point(-1, 0), 
                             Point(0, -1), 
                             Point(1, -1)]
        one_eighty_deg_points = [Point(0, 0),
                                 Point(0, -1),
                                 Point(1, 0),
                                 Point(1, 1)]
        two_seventy_deg_points = [Point(0, 0),
                                  Point(1, 0),
                                  Point(0, 1),
                                  Point(-1, 1)]
        self.assertEqual(ninety_deg_points, Rotation().rotate_90(zero_deg_points))
        self.assertEqual(one_eighty_deg_points, Rotation().rotate_90(ninety_deg_points))
        self.assertEqual(two_seventy_deg_points, Rotation().rotate_90(one_eighty_deg_points))
        self.assertEqual(zero_deg_points, Rotation().rotate_90(two_seventy_deg_points))
        

if __name__ == "__main__":
    #import sys;sys.argv = ['', 'Test.test_rotate_points']
    unittest.main()