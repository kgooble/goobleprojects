'''
Created on 2012-01-31

@author: Gooble
'''
from main.movement.direction import DOWN
from main.movement.transform.point import Point
from main.movement.transform.pointtransformer import PointTransformer
from main.shapes.shape import Shape
from main.shapes.tile import T_TILE
import unittest


class Test(unittest.TestCase):

    def test_translates_given_points(self):
        transformer = PointTransformer(Shape(T_TILE).get_points())
        self.assertEqual([Point(1, 0),
                          Point(1, -1),
                          Point(0, 0),
                          Point(1, 1)], 
                         transformer.translate_in_dir(DOWN))
        
    def test_rotate_points_not_centered_at_0(self):
        transformer = PointTransformer([Point(4, 3),
                                        Point(4, 2),
                                        Point(4, 4),
                                        Point(3, 3)])
        self.assertEqual([Point(4, 3),
                          Point(3, 3),
                          Point(5, 3),
                          Point(4, 4)], transformer.rotate())
        
    def test_rotate_points_not_centered_at_0_again(self):
        transformer = PointTransformer([Point(1, 2),
                                        Point(1, 1),
                                        Point(1, 3),
                                        Point(0, 2)])
        
        self.assertEqual([Point(1, 2),
                          Point(0, 2),
                          Point(2, 2),
                          Point(1, 3)], transformer.rotate())
        
if __name__ == "__main__":
    unittest.main()