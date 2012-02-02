'''
Created on 2012-01-31

@author: Gooble
'''
import unittest

from main.shape import Shape
from main import tile
from main.pointtransformer import PointTransformer
from main.direction import DOWN
from main.point import Point

class Test(unittest.TestCase):

    def test_translates_given_points(self):
        transformer = PointTransformer(Shape(tile.T_TILE))
        self.assertEqual([Point(1, 0),
                          Point(1, -1),
                          Point(0, 0),
                          Point(1, 1)], 
                         transformer.translate_in_dir(DOWN))
        
    def test_rotate_points_not_centered_at_0(self):
        shape = Shape(tile.T_TILE)
        shape.set_position([Point(4, 3),
                            Point(4, 2),
                            Point(4, 4),
                            Point(3, 3)])
        transformer = PointTransformer(shape)
        
        self.assertEqual([Point(4, 3),
                          Point(3, 3),
                          Point(5, 3),
                          Point(4, 4)], transformer.rotate())
        
if __name__ == "__main__":
    unittest.main()