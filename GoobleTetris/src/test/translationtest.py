'''
Created on 2012-01-29

@author: Gooble
'''
import unittest

import main.translation 
import main.point
import main.direction

class Test(unittest.TestCase):
    
    def setUp(self):
        self.trans = main.translation.Translation()

    def test_if_translate_to_left_shift_all_positions_to_left(self):
        new_points = self.trans.in_direction(
          [main.point.Point(4, 2), 
           main.point.Point(1, 5)], main.direction.LEFT)
        self.check_point(4, 1, new_points[0])
        self.check_point(1, 4, new_points[1])

    def test_if_translate_to_right_then_shift_all_positions_to_right(self):
        new_points = self.trans.in_direction(
          [main.point.Point(4, 2), 
           main.point.Point(1, 5)], main.direction.RIGHT)
        self.check_point(4, 3, new_points[0])
        self.check_point(1, 6, new_points[1])
        
    def test_if_translate_down_then_all_points_go_down(self):
        new_points = self.trans.in_direction(
          [main.point.Point(4, 2), 
           main.point.Point(1, 5)], main.direction.DOWN)
        self.check_point(5, 2, new_points[0])
        self.check_point(2, 5, new_points[1])
        
    def check_point(self, row, col, point):
        self.assertEqual(row, point.row)
        self.assertEqual(col, point.col)

if __name__ == "__main__":
    unittest.main()