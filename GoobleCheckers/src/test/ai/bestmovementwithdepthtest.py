'''
Created on 2011-12-20

@author: Gooble
'''
import unittest

from test.util.testboard import TestBoard
from test.util.testcase import as_move_list
from main.game import origin
from main.ai.bestmovedepth import BestMoveWithDepth

class BestMovementWithDepthTest(unittest.TestCase):

    def setUp(self):
        self.tboard = TestBoard()

    def test_calculate_moves_up_to_depth_one_does_not_touch_original_board_and_selects_best_move_considering_others_result_in_more_lost_pieces(self):
        # # # # # # # # # #
        #  0 1 2 3 4 5 6 7#
        #0 _ _ _ _ _ _ _ _#
        #1 _ _ _ _ _ _ _ _#
        #2 _ _ T _ _ _ _ _#
        #3 _ x _ x _ _ _ _#
        #4 B _ _ _ _ _ _ _#
        #5 _ _ _ _ _ _ _ _#
        #6 _ _ _ _ _ _ _ _#
        #7 _ _ _ _ _ _ _ _#
        # # # # # # # # # #
        self.tboard.place_piece(2, 2, origin.TOP)
        self.tboard.place_piece(4, 0, origin.BOTTOM)
        best_move = BestMoveWithDepth(1, origin.TOP).calculate_for(self.tboard.game)
        self.assertEquals(as_move_list([(2, 2), (3, 3)]), best_move)
        self.assertIsNone(self.tboard.game.get_piece(3, 3))
        self.assertIsNotNone(self.tboard.game.get_piece(2, 2))
        
    def test_error_raised_if_depth_of_0_given(self):
        self.assertRaises(NameError, BestMoveWithDepth(0, origin.BOTTOM).calculate_for, self.tboard.game)


if __name__ == "__main__":
    #import sys;sys.argv = ['', 'Test.testName']
    unittest.main()