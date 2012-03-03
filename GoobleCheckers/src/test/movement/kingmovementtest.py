'''
Created on 2011-10-08

@author: Gooble
'''
from main.game import origin
from main.game.movements.movement import Movement
from test.util.testboard import TestBoard
from test.util.testcase import as_move_list
import unittest

class KingMovementTest(unittest.TestCase):

    def setUp(self):
        self.tboard = TestBoard()

    def test_king_can_move_in_either_direction(self):
        # # # # # # # # # #
        #  0 1 2 3 4 5 6 7#
        #0 _ _ _ _ _ _ _ _#
        #1 _ _ _ _ _ _ _ _#
        #2 _ x _ x _ _ _ _#
        #3 _ _ b _ _ _ _ _#
        #4 _ x _ T _ _ _ _#
        #5 _ _ _ _ x _ _ _#
        #6 _ _ _ _ _ _ _ _#
        #7 _ _ _ _ _ _ _ _#
        # # # # # # # # # #
        self.tboard.place_king(3, 2, origin.BOTTOM)
        self.tboard.place_piece(4, 3, origin.TOP)
        calc = Movement(self.tboard.game.board, 3, 2)
        moves = calc.get_available_moves()
        self.assertEqual(4, len(moves))
        self.assertEqual(as_move_list([(3, 2), (4, 1)]), moves[0])
        self.assertEqual(as_move_list([(3, 2), (5, 4)]), moves[1])
        self.assertEqual(as_move_list([(3, 2), (2, 1)]), moves[2])
        self.assertEqual(as_move_list([(3, 2), (2, 3)]), moves[3])
        
    def test_king_moves_case_1(self):
        # # # # # # # # # #
        #  0 1 2 3 4 5 6 7#
        #0 _ _ _ _ _ _ _ _#
        #1 _ _ _ _ x _ _ _#
        #2 _ x _ t _ t _ _#
        #3 _ _ b _ _ _ x _#
        #4 _ x _ x _ T _ _#
        #5 _ _ _ _ x _ _ _#
        #6 _ _ _ _ _ t _ _#
        #7 _ _ _ _ _ _ x _#
        # # # # # # # # # #
        self.tboard.place_king(2, 3, origin.TOP)
        self.tboard.place_king(2, 5, origin.TOP)
        self.tboard.place_king(3, 2, origin.BOTTOM)
        self.tboard.place_piece(4, 5, origin.TOP)
        self.tboard.place_king(6, 5, origin.TOP)
        calc = Movement(self.tboard.game.board, 3, 2)
        moves = calc.get_available_moves()
        self.assertEqual(4, len(moves))
        self.assertEqual(as_move_list([(3, 2), (4, 1)]), moves[0])
        self.assertEqual(as_move_list([(3, 2), (4, 3)]), moves[1])
        self.assertEqual(as_move_list([(3, 2), (2, 1)]), moves[2])
        self.assertEqual(as_move_list([(3, 2), (1, 4), (3, 6), (5, 4), (7, 6)]), moves[3])
        
    def test_king_moves_doesnt_jump_over_own_dudes(self):
        # # # # # # # # # #
        #  0 1 2 3 4 5 6 7#
        #0 _ _ _ _ _ _ _ _#
        #1 _ _ _ _ x _ _ _#
        #2 _ x _ b _ t _ _#
        #3 _ _ t _ _ _ _ _#
        #4 _ T _ t _ _ _ _#
        #5 _ _ _ _ _ _ _ _#
        #6 _ _ _ _ _ _ _ _#
        #7 _ _ _ _ _ _ _ _#
        # # # # # # # # # #
        self.tboard.place_king(2, 3, origin.BOTTOM)
        self.tboard.place_king(2, 5, origin.TOP)
        self.tboard.place_king(3, 2, origin.TOP)
        self.tboard.place_piece(4, 1, origin.TOP)
        self.tboard.place_king(4, 3, origin.TOP)
        calc = Movement(self.tboard.game.board, 3, 2)
        moves = calc.get_available_moves()
        self.assertEqual(2, len(moves))
        self.assertEqual(as_move_list([(3, 2), (2, 1)]), moves[0])
        self.assertEqual(as_move_list([(3, 2), (1, 4)]), moves[1])
        
    def test_king_moves_case_2(self):
        # # # # # # # # # #
        #  0 1 2 3 4 5 6 7#
        #0 _ _ _ _ _ _ _ _#
        #1 _ _ _ _ _ _ _ _#
        #2 _ _ _ _ _ _ _ _#
        #3 _ _ _ _ _ _ _ _#
        #4 _ _ _ _ _ _ _ _#
        #5 _ _ _ _ _ _ _ _#
        #6 _ _ x _ x _ _ _#
        #7 _ _ _ t _ _ _ _#
        # # # # # # # # # #
        self.tboard.place_king(7, 3, origin.TOP)
        calc = Movement(self.tboard.game.board, 7, 3)
        moves = calc.get_available_moves()
        self.assertEqual(2, len(moves))
        self.assertEqual(as_move_list([(7, 3), (6, 2)]), moves[0])
        self.assertEqual(as_move_list([(7, 3), (6, 4)]), moves[1])
        
    def test_king_can_move_around_and_stuff_stays_consistent(self):
        # # # # # # # # # #
        #  0 1 2 3 4 5 6 7#
        #0 _ _ _ _ _ _ _ _#
        #1 x _ x _ _ _ _ _#
        #2 _ t _ _ _ x _ _#
        #3 x _ B _ B _ _ _#
        #4 _ _ _ x _ _ _ _#
        #5 _ _ _ _ _ _ _ _#
        #6 _ _ _ _ _ _ _ _#
        #7 _ _ _ _ _ _ _ _#
        # # # # # # # # # #
        self.tboard.place_king(2, 1, origin.TOP)
        self.tboard.place_piece(3, 2, origin.BOTTOM)
        self.tboard.place_piece(3, 4, origin.BOTTOM)
        calc = Movement(self.tboard.game.board, 2, 1)
        moves = calc.get_available_moves()
        self.assertEqual(4, len(moves))
        self.assertEqual(as_move_list([(2, 1), (1, 0)]), moves[0])
        self.assertEqual(as_move_list([(2, 1), (1, 2)]), moves[1])
        self.assertEqual(as_move_list([(2, 1), (3, 0)]), moves[2])
        self.assertEqual(as_move_list([(2, 1), (4, 3), (2, 5)]), moves[3])
        
        self.tboard.game.move_piece((2, 1), (4, 3))
        self.assertIsNone(self.tboard.game.get_piece(2, 1))
        self.assertIsNotNone(self.tboard.game.get_piece(3, 2))
        self.assertIsNotNone(self.tboard.game.get_piece(4, 3))
        
        self.assertTrue(self.tboard.game.get_piece(4, 3).is_king())
        
        self.tboard.game.move_piece((4, 3), (2, 5))
        self.assertIsNone(self.tboard.game.get_piece(3, 2))
        self.assertIsNone(self.tboard.game.get_piece(3, 4))
        self.assertIsNotNone(self.tboard.game.get_piece(2, 5))


if __name__ == "__main__":
    #import sys;sys.argv = ['', 'KingMovementTest.testName']
    unittest.main()