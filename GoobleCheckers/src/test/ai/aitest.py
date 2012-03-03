'''
Created on 2011-12-05

@author: Gooble
'''
from main.view.dimensions import DEFAULT_COLS, DEFAULT_WIDTH, DEFAULT_HEIGHT, DEFAULT_ROWS
from test.util.testboard import TestBoard
from test.view.slottingtest import MockEvent
import main.ai.easyai as easyai
import main.game.origin as origin
import main.view.checkers
import unittest

class AITest(unittest.TestCase):

    def test_if_it_is_bottoms_turn_then_ai_auto_moves_piece_on_board(self):
        self.gamepanel = main.view.checkers.Checkers(ai=easyai.EasyAI())
        self.tboard = TestBoard(self.gamepanel.game)
        # # # # # # # # # #
        #  0 1 2 3 4 5 6 7#
        #0 _ _ _ _ _ _ _ _#
        #1 _ _ _ _ _ _ _ _#
        #2 _ _ _ _ _ _ _ _#
        #3 _ _ T _ _ _ _ _#
        #4 B _ _ B _ _ _ _#
        #5 _ _ _ _ x _ _ _#
        #6 _ _ _ _ _ B _ _#
        #7 _ _ _ _ _ _ x _#
        # # # # # # # # # #
        self.tboard.place_piece(3, 2, origin.TOP)
        self.tboard.place_piece(4, 0, origin.BOTTOM)
        self.tboard.place_piece(4, 3, origin.BOTTOM)
        self.tboard.place_piece(6, 5, origin.BOTTOM)
        
        self.move_piece(3, 2, 5, 4)
        self.move_piece(5, 4, 7, 6)
        
        self.assertTrue(self.tboard.game.get_piece(7, 6).is_king())
        self.assertTrue(self.tboard.game.get_piece(7, 6).is_king())
        self.assertIsNone(self.tboard.game.get_piece(4, 3))
        self.assertIsNone(self.tboard.game.get_piece(6, 5))
        
        self.gamepanel.make_ai_move()
        # AI moved piece
        self.assertIsNone(self.tboard.game.get_piece(4, 0))
        self.assertIsNotNone(self.tboard.game.get_piece(3, 1))

    def move_piece(self, from_row, from_col, to_row, to_col):
        self.gamepanel.slotting.select_piece(MockEvent(from_col * (DEFAULT_WIDTH/DEFAULT_COLS),
                                                  from_row * (DEFAULT_HEIGHT/DEFAULT_ROWS)))
        self.gamepanel.slotting.release_piece(MockEvent(to_col * (DEFAULT_WIDTH/DEFAULT_COLS),
                                                   to_row * (DEFAULT_HEIGHT/DEFAULT_ROWS)))

if __name__ == "__main__":
    unittest.main()