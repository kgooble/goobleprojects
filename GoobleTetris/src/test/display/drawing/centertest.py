'''
Created on 2012-03-04

@author: Gooble
'''
import unittest
from main.display.center import Center


class MockPiece(object):
    
    def __init__(self, x, y):
        self.x = x
        self.y = y

    def get_width(self):
        return 10
    
    def get_height(self):
        return 10
    
    def get_x(self):
        return self.x
    
    def get_y(self):
        return self.y
    
    def set_x(self, x):
        self.x = x
        
    def set_y(self, y):
        self.y = y

class Test(unittest.TestCase):
    
    def test_get_centered_start_x_for_width_given_clamp_values(self):
        startval = Center(start=0, end=20).object_with_length(5)
        self.assertEqual(7, startval)
    
    def test_get_centered_start_x_for_width_nonzero_start(self):
        startval = Center(start=20, end=80).object_with_length(50)
        self.assertEqual(25, startval)
        
    def test_center_shape_keeps_relative_positions_intact(self):
        pieces = [MockPiece(10, 10), MockPiece(10, 20), MockPiece(20, 10)]
        Center(start=20, end=80).pieces_along_x(pieces)
        
        self.assertEqual(40, pieces[0].x)
        self.assertEqual(10, pieces[0].y)
        
        self.assertEqual(40, pieces[1].x)
        self.assertEqual(20, pieces[1].y)
        
        self.assertEqual(50, pieces[2].x)
        self.assertEqual(10, pieces[2].y)
    
if __name__=="__main__":
    unittest.main()