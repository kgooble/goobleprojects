'''
Created on 2011-11-27

@author: Gooble
'''
from main.game import origin
from test.util.testboard import TestBoard
import main.view.slotting as slotting
import unittest
from main.game.gameplay import GamePlay

class MockEvent(list):
    
    def __init__(self, x, y):
        self.append(x)
        self.append(y)
        self.x = x
        self.y = y

class SlottingTest(unittest.TestCase):

    def setUp(self):
        self.tboard = TestBoard()
        self.game = GamePlay()
        self.slotting = slotting.Slotting(self.game)

    def test_piece_gets_selected_and_slotted_when_slot_is_valid_move_and_when_move_is_complete_turn_is_ended(self):
        self.assertIsNone(self.game.current_turn.piece)
        self.slotting.select_piece(MockEvent(120, 120))
        self.assertEqual(2, self.slotting.start_row)
        self.assertEqual(2, self.slotting.start_col)
        self.slotting.release_piece(MockEvent(100, 200))
        self.assertIsNone(self.game.get_piece(2, 2))
        self.assertIsNotNone(self.game.get_piece(3, 1))
        self.assertIsNone(self.game.current_turn.piece)
        
    def test_piece_does_not_get_placed_in_new_slot_when_invalid_move_and_start_row_and_col_reset(self):
        self.slotting.select_piece(MockEvent(100, 100))
        self.assertEqual(1, self.slotting.start_row)
        self.assertEqual(1, self.slotting.start_col)
        self.slotting.release_piece(MockEvent(300, 300))
        self.assertIsNotNone(self.game.get_piece(1, 1))
        self.assertIsNotNone(self.game.get_piece(2, 2))
        self.assertIsNone(self.game.get_piece(3, 1))
        self.assertIsNone(self.game.get_piece(4, 4))
        self.assertIsNone(self.slotting.start_row)
        self.assertIsNone(self.slotting.start_col)
        
    def test_when_doing_consecutive_moves_starting_row_and_col_changes(self):
        # # # # # # # # # #
        #  0 1 2 3 4 5 6 7#
        #0 _ _ _ _ _ _ _ _#
        #1 _ _ _ _ _ _ _ _#
        #2 _ T _ _ _ _ _ _#
        #3 x _ B _ _ _ _ _#
        #4 _ _ _ x _ _ _ _#
        #5 _ _ _ _ B _ _ _#
        #6 _ _ _ _ _ x _ _#
        #7 _ _ _ _ _ _ _ _#
        # # # # # # # # # #
        self.tboard.place_piece(2, 1, origin.TOP)
        self.tboard.place_piece(3, 2, origin.BOTTOM)
        self.tboard.place_piece(5, 4, origin.BOTTOM)
        self.slotting = slotting.Slotting(self.tboard.game)
        self.board = self.tboard.game
        
        self.slotting.select_piece(MockEvent(100, 140))
        self.assertEqual(2, self.slotting.start_row)
        self.assertEqual(1, self.slotting.start_col)
        self.slotting.release_piece(MockEvent(200, 250))
        
        self.assertIsNone(self.board.get_piece(2, 1))
        self.assertIsNotNone(self.board.get_piece(3, 2))
        self.assertIsNotNone(self.board.get_piece(4, 3))
        self.assertIsNotNone(self.board.current_turn.piece)
        
        self.slotting.select_piece(MockEvent(200, 250))
        self.assertEqual(4, self.slotting.start_row)
        self.assertEqual(3, self.slotting.start_col)
        self.slotting.release_piece(MockEvent(330,400))
        
        self.assertIsNone(self.board.current_turn.piece)
        self.assertIsNone(self.board.get_piece(3, 2))
        self.assertIsNone(self.board.get_piece(5, 4))
        self.assertIsNotNone(self.board.get_piece(6, 5))
        
    def test_slotting_releases_piece_fully_and_does_not_grab_new_piece_in_same_loc(self):
        # # # # # # # # # #
        #  0 1 2 3 4 5 6 7#
        #0 _ _ _ _ _ _ _ _#
        #1 _ _ _ _ _ _ _ _#
        #2 _ _ _ _ _ _ _ _#
        #3 _ _ _ T _ _ _ _#
        #4 _ _ B _ _ _ _ _#
        #5 _ x _ _ _ _ _ _#
        #6 _ _ T _ _ _ _ _#
        #7 _ _ _ B _ _ _ _#
        # # # # # # # # # #
        self.tboard.place_piece(3, 3, origin.TOP)
        self.tboard.place_piece(4, 2, origin.BOTTOM)
        self.tboard.place_piece(6, 2, origin.TOP)
        self.tboard.place_piece(7, 3, origin.BOTTOM)
        
        self.slotting = slotting.Slotting(self.tboard.game)
        self.board = self.tboard.game
        
        self.slotting.select_piece(MockEvent(200, 200))
        self.assertEqual(3, self.slotting.start_row)
        self.assertEqual(3, self.slotting.start_col)
        self.slotting.release_piece(MockEvent(80, 330))
        self.assertIsNone(self.board.get_piece(3, 3))
        self.assertIsNone(self.board.get_piece(4, 2))
        self.assertIsNotNone(self.board.get_piece(5, 1))
        
        self.assertFalse(self.slotting.is_holding_piece())
        

        
    def test_slotting_is_holding_piece(self):
        self.assertFalse(self.slotting.is_holding_piece())
        self.slotting.start_row = 5
        self.slotting.start_col = 5
        self.assertTrue(self.slotting.is_holding_piece())


if __name__ == "__main__":
    unittest.main()