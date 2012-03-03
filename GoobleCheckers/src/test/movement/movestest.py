'''
Created on 2011-12-11

@author: Gooble
'''
import unittest
from main.game.movements.move import Move


class MovesTest(unittest.TestCase):

    def test_pieces_eaten_for_non_jump_is_0(self):
        move = Move()
        move.add((3, 3), (4, 4))
        self.assertEqual(0, move.get_pieces_eaten())
        
    def test_pieces_eaten_for_jump_is_how_many_moves_are_in_it(self):
        move = Move()
        move.add((6, 6), (4, 4))
        self.assertEqual(1, move.get_pieces_eaten())
        
        move.add((4, 4), (2, 2))
        self.assertEqual(2, move.get_pieces_eaten())


if __name__ == "__main__":
    #import sys;sys.argv = ['', 'Test.testName']
    unittest.main()