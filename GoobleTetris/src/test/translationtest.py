'''
Created on 2012-01-29

@author: Gooble
'''
import unittest

import main.translation 
import main.point
import main.direction
import main.edges

class Test(unittest.TestCase):
    
    def setUp(self):
        self.trans = main.translation.Translation(
          [main.point.Point(4, 2), 
           main.point.Point(1, 5)])

    def test_if_translate_to_left_shift_all_positions_to_left(self):
        new_points = self.trans.in_direction(main.direction.LEFT)
        self.check_point(3, 2, new_points[0])
        self.check_point(0, 5, new_points[1])

    def test_if_translate_to_right_then_shift_all_positions_to_right(self):
        new_points = self.trans.in_direction(main.direction.RIGHT)
        self.check_point(5, 2, new_points[0])
        self.check_point(2, 5, new_points[1])
        
    def test_if_translate_down_then_all_points_go_down(self):
        new_points = self.trans.in_direction(main.direction.DOWN)
        self.check_point(4, 3, new_points[0])
        self.check_point(1, 6, new_points[1])

    def test_if_given_right_edge_and_points_already_farthest_right_no_points_shifted(self):
        new_points = self.get_new_points_with_edged_translation(main.direction.RIGHT, 4)
        self.check_pts_same(new_points)

    def test_if_given_left_edge_and_points_already_farthest_left_no_points_shifted(self):
        new_points = self.get_new_points_with_edged_translation(main.direction.LEFT, 1)
        self.check_pts_same(new_points)
        
    def test_if_given_bottom_edge_and_points_already_bottommost_then_no_points_shifted(self):
        new_points = self.get_new_points_with_edged_translation(main.direction.DOWN, 5)
        self.check_pts_same(new_points)

    def get_new_points_with_edged_translation(self, direction, limit):
        trans = main.translation.Translation(
            [main.point.Point(4, 2), main.point.Point(1, 5)], 
             main.edges.Edges({direction:limit}))
        new_points = trans.in_direction(direction)
        return new_points

    def check_pts_same(self, new_points):
        self.check_point(4, 2, new_points[0])
        self.check_point(1, 5, new_points[1])
        
    def check_point(self, x, y, point):
        self.assertEqual(x, point.x)
        self.assertEqual(y, point.y)

if __name__ == "__main__":
    unittest.main()