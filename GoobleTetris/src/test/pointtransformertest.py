'''
Created on 2012-01-31

@author: Gooble
'''
import unittest

from main.shape import Shape
from main import shapetype
from main.pointtransformer import PointTransformer
from main.direction import DOWN
from main.point import Point

class Test(unittest.TestCase):

    def test_translates_given_points(self):
        transformer = PointTransformer(Shape(shapetype.T).get_points())
        self.assertEqual([Point(1, 0),
                          Point(1, -1),
                          Point(0, 0),
                          Point(1, 1)], 
                         transformer.translate_in_dir(DOWN))
        

if __name__ == "__main__":
    #import sys;sys.argv = ['', 'Test.test_rotates_given_shape']
    unittest.main()