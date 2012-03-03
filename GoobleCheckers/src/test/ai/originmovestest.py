'''
Created on 2011-12-10

@author: Gooble
'''
import unittest
from main.game import origin
from test.util.testboard import TestBoard
from test.util.testcase import as_move_list
from main.ai.originmoves import OriginMoves

class OriginMovesTest(unittest.TestCase):

    def setUp(self):
        self.tboard = TestBoard()
    
    def test_get_all_moves_for_given_origin(self):
        # # # # # # # # # #
        #  0 1 2 3 4 5 6 7#
        #0 _ _ _ _ _ _ _ _#
        #1 _ _ _ _ _ _ _ _#
        #2 _ _ _ _ _ _ _ _#
        #3 _ x x _ x _ _ _#
        #4 B _ _ B _ _ _ _#
        #5 _ _ _ _ x _ x _#
        #6 _ _ _ _ _ B _ _#
        #7 _ _ _ _ _ _ _ _#
        # # # # # # # # # #
        self.tboard.place_piece(4, 0, origin.BOTTOM)
        self.tboard.place_piece(4, 3, origin.BOTTOM)
        self.tboard.place_piece(6, 5, origin.BOTTOM)
        allmoves = OriginMoves(self.tboard.game.board, origin.BOTTOM).get_moves()
        
        self.assertEqual(5, len(allmoves))
        self.assertEqual(as_move_list([(4, 0), (3, 1)]), allmoves[0])
        self.assertEqual(as_move_list([(4, 3), (3, 2)]), allmoves[1])
        self.assertEqual(as_move_list([(4, 3), (3, 4)]), allmoves[2])
        self.assertEqual(as_move_list([(6, 5), (5, 4)]), allmoves[3])
        self.assertEqual(as_move_list([(6, 5), (5, 6)]), allmoves[4])
        
    def test_origin_moves_list_contains_only_non_empty_move_lists(self):
        # # # # # # # # # #
        #  0 1 2 3 4 5 6 7#
        #0 _ _ _ _ _ _ _ _#
        #1 _ _ _ _ _ _ _ _#
        #2 _ _ _ _ _ _ _ _#
        #3 _ _ _ _ _ _ _ _#
        #4 _ _ _ _ _ _ _ _#
        #5 _ _ x _ x _ x _#
        #6 _ _ _ B _ B _ _#
        #7 _ _ _ _ B _ _ _#
        # # # # # # # # # #
        self.tboard.place_piece(6, 3, origin.BOTTOM)
        self.tboard.place_piece(6, 5, origin.BOTTOM)
        self.tboard.place_piece(7, 4, origin.BOTTOM)
        
        allmoves = OriginMoves(self.tboard.game.board,origin.BOTTOM).get_moves()
        self.assertEqual(4, len(allmoves))
        self.assertEqual(as_move_list([(6, 3), (5, 2)]), allmoves[0])
        self.assertEqual(as_move_list([(6, 3), (5, 4)]), allmoves[1])
        self.assertEqual(as_move_list([(6, 5), (5, 4)]), allmoves[2])
        self.assertEqual(as_move_list([(6, 5), (5, 6)]), allmoves[3])

if __name__ == "__main__":
    unittest.main()