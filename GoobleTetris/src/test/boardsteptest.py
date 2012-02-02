'''
Created on 2012-01-31

@author: Gooble
'''
import unittest
from main.board import Board
from main.config import Configuration
from main import tile
from main.oneshapespawn import OneShapeSpawn
from main.boarddisplay import BoardDisplay

class Test(unittest.TestCase):

    def test_step_until_settled_and_game_over(self):
        config = Configuration().create(["0 0 0 0 0",
                                         "0 0 0 0 0",
                                         "0 0 0 0 0",
                                         "0 0 0 0 0"])
        board = Board(config, OneShapeSpawn(tile.T_TILE))
        display = BoardDisplay(board)
        self.assertEqual( "0 0 0 0 0 \n"\
                         +"0 0 0 0 0 \n"\
                         +"0 0 0 0 0 \n"\
                         +"0 0 0 0 0 \n", display.get_pieces_string())
        
        board.step()
        self.assertEqual( "0 1 1 1 0 \n"\
                         +"0 0 0 0 0 \n"\
                         +"0 0 0 0 0 \n"\
                         +"0 0 0 0 0 \n", display.get_pieces_string())
        
        board.step()
        self.assertEqual( "0 0 1 0 0 \n"\
                         +"0 1 1 1 0 \n"\
                         +"0 0 0 0 0 \n"\
                         +"0 0 0 0 0 \n", display.get_pieces_string())
        
        board.step()
        self.assertEqual( "0 0 0 0 0 \n"\
                         +"0 0 1 0 0 \n"\
                         +"0 1 1 1 0 \n"\
                         +"0 0 0 0 0 \n", display.get_pieces_string())
        
        board.step()
        self.assertEqual( "0 0 0 0 0 \n"\
                         +"0 0 0 0 0 \n"\
                         +"0 0 1 0 0 \n"\
                         +"0 1 1 1 0 \n", display.get_pieces_string())
        
        board.step()
        self.assertEqual( "0 1 1 1 0 \n"\
                         +"0 0 0 0 0 \n"\
                         +"0 0 1 0 0 \n"\
                         +"0 1 1 1 0 \n", display.get_pieces_string())
        
        board.step()
        self.assertEqual( "0 0 1 0 0 \n"\
                         +"0 1 1 1 0 \n"\
                         +"0 0 1 0 0 \n"\
                         +"0 1 1 1 0 \n", display.get_pieces_string())
        self.assertFalse(board.is_game_over())
        
        board.step()
        self.assertEqual( "0 0 1 0 0 \n"\
                         +"0 1 1 1 0 \n"\
                         +"0 0 1 0 0 \n"\
                         +"0 1 1 1 0 \n", display.get_pieces_string())
        self.assertTrue(board.is_game_over())
        


if __name__ == "__main__":
    unittest.main()