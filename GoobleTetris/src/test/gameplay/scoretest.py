'''
Created on 2012-02-04

@author: Gooble
'''
import unittest
from main.gameplay.score import Score

class Test(unittest.TestCase):

    def setUp(self):
        self.score = Score()
        self.assertEqual(0, self.score.get_value())

    def test_score_100_points_for_one_line_cleared(self):
        self.score.add_pts_for_lines(1)
        self.assertEqual(100, self.score.get_value())
        
    def test_bonus_if_four_lines_cleared(self):
        self.score.add_pts_for_lines(4)
        self.assertEqual(800, self.score.get_value())
        
    def test_if_got_two_tetris_in_a_row_bonus_points(self):
        self.score.add_pts_for_lines(4)
        self.score.add_pts_for_lines(4)
        self.assertEqual(2000, self.score.get_value())


if __name__ == "__main__":
    #import sys;sys.argv = ['', 'Test.testName']
    unittest.main()