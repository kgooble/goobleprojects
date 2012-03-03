'''
Created on 2011-10-03

@author: Gooble
'''
from main.game.board import Board
from main.game.movements.movement import Movement
from test.util.testcase import as_move_list
import unittest

class MovementCalculatorTest(unittest.TestCase):

    def test_get_available_moves_from_location_with_no_piece(self):
        calc = Movement(Board(), 0, 1)
        moves = calc.get_available_moves()
        self.assertEqual(len(moves), 0)
        
    def test_get_avail_moves_from_location_with_piece_that_can_move_from_top(self):
        calc = Movement(Board(), 2, 2)
        moves = calc.get_available_moves();
        self.assertEqual(len(moves), 2)
        self.assertEqual(as_move_list([(2, 2), (3, 1)]), moves[0])
        self.assertEqual(as_move_list([(2, 2), (3, 3)]), moves[1])
        
    def test_get_avail_moves_from_location_with_piece_that_can_move_from_bottom(self):
        calc = Movement(Board(), 5, 1)
        moves = calc.get_available_moves()
        self.assertEqual(len(moves), 2)
        self.assertEqual(as_move_list([(5, 1), (4, 0)]), moves[0])
        self.assertEqual(as_move_list([(5, 1), (4, 2)]), moves[1])
        
    def test_get_avail_moves_from_corner_location_from_bottom_gives_only_one_valid_move(self):
        calc = Movement(Board(), 5, 7)
        moves = calc.get_available_moves()
        self.assertEqual(len(moves), 1)
        self.assertEqual(as_move_list([(5, 7), (4, 6)]), moves[0])
        
    def test_get_avail_moves_from_corner_location_from_top_gives_only_one_valid_move(self):
        calc = Movement(Board(), 2, 0)
        moves = calc.get_available_moves()
        self.assertEqual(len(moves), 1)
        self.assertEqual(as_move_list([(2, 0), (3, 1)]), moves[0])
        

if __name__ == "__main__":
    #import sys;sys.argv = ['', 'Test.testName']
    unittest.main()