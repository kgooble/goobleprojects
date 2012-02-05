'''
Created on 2012-01-30

@author: Gooble
'''
from main.movement.transform.point import Point
from main.movement.transform.rotation import Rotation
from main.shapes import shapetype
import unittest


class Test(unittest.TestCase):

    def test_rotate_s_shape_90_degs(self):
        zero = [Point(0, 0), Point(0, 1), Point(1, 0), Point(1, -1)]
        ninety = [Point(0, 0), Point(1, 0), Point(0, -1), Point(-1, -1)]
        one_eighty = [Point(0, 0), Point(0, -1), Point(-1, 0), Point(-1, 1)]
        two_seventy = [Point(0, 0), Point(-1, 0), Point(0, 1), Point(1, 1)]
        self.assertEqual(ninety, Rotation().rotate_90(zero))
        self.assertEqual(one_eighty, Rotation().rotate_90(ninety))
        self.assertEqual(two_seventy, Rotation().rotate_90(one_eighty))
        self.assertEqual(zero, Rotation().rotate_90(two_seventy))
        
    def test_rotate_t_shape(self):
        zero = shapetype.T.get_initial_points()
        ninety = [Point(0, 0), Point(-1, 0), Point(0, 1), Point(1, 0)]
        self.assertEqual(ninety, Rotation().rotate_90(zero))
        
    def test_rotate_line_shape(self):
        zero = shapetype.I.get_initial_points()
        ninety = [Point(0, 0), Point(0, 1), Point(0, -1), Point(0, -2)]
        one_eighty = [Point(0, 0), Point(1, 0), Point(-1, 0), Point(-2, 0)]
        self.assertEqual(ninety, Rotation().rotate_90(zero))
        self.assertEqual(one_eighty, Rotation().rotate_90(ninety))
        

if __name__ == "__main__":
    #import sys;sys.argv = ['', 'Test.test_rotate_points']
    unittest.main()