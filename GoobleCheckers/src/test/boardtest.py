'''
Created on 2011-10-02

@author: Gooble
'''
from main.game import origin
from main.game.board import Board
from test.util.testboard import TestBoard
import unittest
from main.game.gameplay import GamePlay

class BoardTest(unittest.TestCase):
    
    def setUp(self):
        self.game = GamePlay()
        self.tboard = TestBoard()

    def test_board_initial_configuration_correct(self):
        for col in range (0, Board.DEFAULT_WIDTH):
            [self.check_piece(row, col) for row in range(0, Board.DEFAULT_HEIGHT)]
                
    def check_piece(self, row, col):
        piece = self.game.board.get_piece(row, col)
        if row < 3 and (row + col) % 2 == 0:
            self.assertIsNotNone(piece)
            self.assertTrue(piece.is_from_top())
        elif row > 4 and (row + col) % 2 == 0:
            self.assertIsNotNone(piece)
            self.assertTrue(piece.is_from_bottom())
        else:
            self.assertIsNone(piece)
            
    def test_move_piece_to_invalid_location_does_nothing(self):
        original_board = Board()
        self.game.move_piece((2, 2), (5, 6))
        for row in range (0, Board.DEFAULT_HEIGHT):
            [self.check_pieces_equal(self.game.board.get_piece(row, col), original_board.get_piece(row, col)) for col in range(0, Board.DEFAULT_WIDTH)]
            
    def check_pieces_equal(self, piece1, piece2):
        if piece1 is None or piece2 is None:
            self.assertIsNone(piece1)
            self.assertIsNone(piece2)
            return
        
        self.assertEqual(piece1.get_origin(), piece2.get_origin())
        
    def test_move_piece_to_valid_location_only_works_if_location_is_in_move_list(self):
        self.assertIsNone(self.game.board.get_piece(3, 1))
        self.game.move_piece((2, 0), (3, 1))
        self.assertIsNone(self.game.board.get_piece(2, 0))
        self.assertIsNotNone(self.game.board.get_piece(3, 1))
        self.assertTrue(self.game.board.get_piece(3, 1).is_from_top())
        self.assertFalse(self.game.is_game_over())
        
    def test_game_over_when_one_type_of_piece_runs_out_simple_board(self):
        # # # # # # # # # #
        #  0 1 2 3 4 5 6 7#
        #0 _ _ _ _ _ _ _ _#
        #1 x _ x _ _ _ _ _#
        #2 _ t _ _ _ _ _ _#
        #3 x _ B _ _ _ _ _#
        #4 _ _ _ x _ _ _ _#
        #5 _ _ _ _ _ _ _ _#
        #6 _ _ _ _ _ _ _ _#
        #7 _ _ _ _ _ _ _ _#
        # # # # # # # # # #
        self.tboard.place_king(2, 1, origin.TOP)
        self.tboard.place_piece(3, 2, origin.BOTTOM)
        
        self.assertFalse(self.tboard.game.is_game_over())
        self.tboard.game.move_piece((2, 1), (4, 3))
        self.assertTrue(self.tboard.game.is_game_over())
        
        self._check_board_empty_except([(4, 3)])
        self.assertTrue(self.tboard.game.is_game_over())
        self.assertEqual(1, self.tboard.game.state.num_top_pieces)
        self.assertEqual(0, self.tboard.game.state.num_bottom_pieces)
        
    def _check_board_empty_except(self, list_of_exceptions):
        for row in range(0, Board.DEFAULT_HEIGHT):
            for col in range (0, Board.DEFAULT_WIDTH):
                if (row, col) in list_of_exceptions: continue
                self.assertIsNone(self.tboard.game.get_piece(row, col), "There is a piece at " + str(row) + ", " + str(col))
                
        for loc in list_of_exceptions:
            self.assertIsNotNone(self.tboard.game.get_piece(loc[0], loc[1]), "There is NO piece at " + str(row) + ", " + str(col))
        
    def test_game_over_when_one_type_of_piece_runs_out_less_simple_board(self):
        # # # # # # # # # #
        #  0 1 2 3 4 5 6 7#
        #0 _ _ _ _ _ _ _ _#
        #1 x _ x _ _ _ _ _#
        #2 _ t _ _ _ _ _ _#
        #3 x _ B _ _ _ _ _#
        #4 _ _ _ x _ B _ _#
        #5 _ _ _ _ _ _ _ _#
        #6 _ _ _ _ _ _ _ _#
        #7 _ _ _ _ _ _ _ _#
        # # # # # # # # # #
        self.tboard.place_king(2, 1, origin.TOP)
        self.tboard.place_piece(3, 2, origin.BOTTOM)
        self.tboard.place_piece(4, 5, origin.BOTTOM)
        
        self.assertFalse(self.tboard.game.is_game_over())
        
        ## The counts work because when you move a piece, you're removing it from the board.--
        ## And when you put it back somewhere else, you're incrementing once more.++
        
        self.tboard.game.move_piece((2, 1), (4, 3))
        self._check_board_configuration(num_top=1, num_bottom=1, non_empty_cells=[(4, 3), (4, 5)])
        
        self.tboard.game.move_piece((4, 5), (3, 4))
        self._check_board_configuration(num_top=1, num_bottom=1, non_empty_cells=[(4, 3), (3, 4)])
        
        self.tboard.game.move_piece((4, 3), (2, 5))
        self._check_board_configuration(num_top=1, num_bottom=0, non_empty_cells=[(2, 5)])
        
        
    def test_board_has_current_origin_pass_if_no_moves_for_that_origin(self):
        # # # # # # # # # #
        #  0 1 2 3 4 5 6 7#
        #0 _ T _ T _ T _ _#
        #1 T _ T _ _ _ _ _#
        #2 _ B _ _ _ _ _ _#
        #3 _ _ _ _ _ _ _ _#
        #4 _ _ _ _ _ _ _ _#
        #5 _ _ _ _ _ _ _ _#
        #6 _ _ _ _ _ _ _ _#
        #7 _ _ _ _ _ _ _ _#
        # # # # # # # # # #
        self.tboard.place_piece(0, 1, origin.TOP)
        self.tboard.place_piece(0, 3, origin.TOP)
        self.tboard.place_piece(0, 5, origin.TOP)
        self.tboard.place_piece(1, 0, origin.TOP)
        self.tboard.place_piece(1, 2, origin.TOP)
        self.tboard.place_piece(2, 1, origin.BOTTOM)
        
        self.tboard.game.move_piece((0, 5), (1, 4))
        self.assertEqual(origin.TOP, self.tboard.game.current_turn.origin)
        
    def test_game_over_if_two_passes_occur_in_a_row(self):
        # # # # # # # # # #
        #  0 1 2 3 4 5 6 7#
        #0 _ _ _ _ _ _ _ _#
        #1 _ _ _ _ _ _ _ _#
        #2 _ _ T _ _ _ _ _#
        #3 _ _ _ _ _ _ _ _#
        #4 T _ T _ T _ T _#
        #5 _ T _ T _ T _ T#
        #6 B _ B _ B _ B _#
        #7 _ B _ B _ B _ B#
        # # # # # # # # # #
        self.tboard.place_piece(2, 2, origin.TOP)
        self.tboard.place_piece(4, 0, origin.TOP)
        self.tboard.place_piece(4, 2, origin.TOP)
        self.tboard.place_piece(4, 4, origin.TOP)
        self.tboard.place_piece(4, 6, origin.TOP)
        self.tboard.place_piece(5, 1, origin.TOP)
        self.tboard.place_piece(5, 3, origin.TOP)
        self.tboard.place_piece(5, 5, origin.TOP)
        self.tboard.place_piece(5, 7, origin.TOP)
        self.tboard.place_piece(6, 0, origin.BOTTOM)
        self.tboard.place_piece(6, 2, origin.BOTTOM)
        self.tboard.place_piece(6, 4, origin.BOTTOM)
        self.tboard.place_piece(6, 6, origin.BOTTOM)
        self.tboard.place_piece(7, 1, origin.BOTTOM)
        self.tboard.place_piece(7, 3, origin.BOTTOM)
        self.tboard.place_piece(7, 5, origin.BOTTOM)
        self.tboard.place_piece(7, 7, origin.BOTTOM)
        
        self.tboard.game.move_piece((2, 2), (3, 1))
        self.assertTrue(self.tboard.game.is_game_over())
        
        
    def _check_board_configuration(self, num_top, num_bottom, non_empty_cells):
        self._check_board_empty_except(non_empty_cells)
        self.assertEqual(num_top, self.tboard.game.state.num_top_pieces)
        self.assertEqual(num_bottom, self.tboard.game.state.num_bottom_pieces)
        self.assertEqual(num_top == 0 or num_bottom == 0, self.tboard.game.is_game_over())

if __name__ == "__main__":
    #import sys;sys.argv = ['', 'Test.testName']
    unittest.main()