'''
Created on 2012-02-26

@author: Gooble
'''
import unittest
from main.view.drawing.drawables import Drawables, DrawableBackground, DrawablePiece, DrawableKing
from main.view.drawing.colours import WHITE,BLACK,RED,BLUE
from test.view.mock.mockgame import MockGame
from test.view.mock.mockslotting import MockSlotting

class Test(unittest.TestCase):

    def test_create_when_not_holding_piece(self):
        slotting = MockSlotting()
        game = MockGame(slotting)
        drawables = Drawables(game).create(None)
        
        # two drawables per occupied slot, and one drawable per unoccupied --> 4 * 2 + 2 = 10
        self.assertEqual(10, len(drawables))
        
        self.assertEqual(DrawableBackground(0, 0, 10, 15, WHITE), drawables[0])
        self.assertEqual(DrawablePiece(0, 0, 10, 15, BLACK), drawables[1])
        
        self.assertEqual(DrawableBackground(10, 0, 10, 15, BLUE), drawables[2])
        
        self.assertEqual(DrawableBackground(20, 0, 10, 15, WHITE), drawables[3])
        self.assertEqual(DrawableKing(20, 0, 10, 15, BLACK), drawables[4])
        
        self.assertEqual(DrawableBackground(0, 15, 10, 15, BLUE), drawables[5])
        self.assertEqual(DrawablePiece(0, 15, 10, 15, RED), drawables[6])
        
        self.assertEqual(DrawableBackground(10, 15, 10, 15, WHITE), drawables[7])
        
        self.assertEqual(DrawableBackground(20, 15, 10, 15, BLUE), drawables[8])
        self.assertEqual(DrawableKing(20, 15, 10, 15, RED), drawables[9])
        
    def test_create_when_holding_piece(self):
        slotting = MockSlotting(0, 0)
        game = MockGame(slotting)
        drawables = Drawables(game).create((15, 15))
        
        self.assertEqual(10, len(drawables))
        
        self.assertEqual(DrawableBackground(0, 0, 10, 15, WHITE), drawables[0])
        
        self.assertEqual(DrawableBackground(10, 0, 10, 15, BLUE), drawables[1])
        
        self.assertEqual(DrawableBackground(20, 0, 10, 15, WHITE), drawables[2])
        self.assertEqual(DrawableKing(20, 0, 10, 15, BLACK), drawables[3])
        
        self.assertEqual(DrawableBackground(0, 15, 10, 15, BLUE), drawables[4])
        self.assertEqual(DrawablePiece(0, 15, 10, 15, RED), drawables[5])
        
        self.assertEqual(DrawableBackground(10, 15, 10, 15, WHITE), drawables[6])
        
        self.assertEqual(DrawableBackground(20, 15, 10, 15, BLUE), drawables[7])
        self.assertEqual(DrawableKing(20, 15, 10, 15, RED), drawables[8])
        
        self.assertEqual(DrawablePiece(10, 7, 10, 15, BLACK), drawables[9])
        
    def test_dont_do_anything_special_if_empty_cell_clicked(self):
        slotting = MockSlotting(0, 1)
        game = MockGame(slotting)
        drawables = Drawables(game).create((15, 15))
        
        self.assertEqual(10, len(drawables))
        
        self.assertEqual(DrawableBackground(0, 0, 10, 15, WHITE), drawables[0])
        self.assertEqual(DrawablePiece(0, 0, 10, 15, BLACK), drawables[1])
        
        self.assertEqual(DrawableBackground(10, 0, 10, 15, BLUE), drawables[2])
        
        self.assertEqual(DrawableBackground(20, 0, 10, 15, WHITE), drawables[3])
        self.assertEqual(DrawableKing(20, 0, 10, 15, BLACK), drawables[4])
        
        self.assertEqual(DrawableBackground(0, 15, 10, 15, BLUE), drawables[5])
        self.assertEqual(DrawablePiece(0, 15, 10, 15, RED), drawables[6])
        
        self.assertEqual(DrawableBackground(10, 15, 10, 15, WHITE), drawables[7])
        
        self.assertEqual(DrawableBackground(20, 15, 10, 15, BLUE), drawables[8])
        self.assertEqual(DrawableKing(20, 15, 10, 15, RED), drawables[9])
        

if __name__ == "__main__":
    #import sys;sys.argv = ['', 'Test.test_create']
    unittest.main()