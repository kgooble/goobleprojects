'''
Created on 2012-01-31

@author: Gooble
'''
import unittest
from main.board import Board
from main.config import Configuration
from main import tile
from main.oneshapespawn import OneShapeSpawn
from main.direction import DOWN, LEFT, RIGHT
from main.edges import Edges

class BoardDisplay(object):
    
    def __init__(self, board):
        self.board = board
    
    def get_pieces_string(self):
        result = ""
        for row in self.board.get_pieces():
            for piece in row:
                result += str(piece) + " "
            result += "\n" 
        return result

class Test(unittest.TestCase):

    def setUp(self):
        pass


    def tearDown(self):
        pass


    def test_at_first_step_board_spawns_shape_at_top(self):
        config = Configuration().create(["0 0 0 0 0",
                                         "0 0 0 0 0",
                                         "0 0 0 0 0",
                                         "0 0 0 0 0"])
        board = Board(config, OneShapeSpawn(tile.T_TILE), Edges({LEFT: 0, RIGHT: 4, DOWN: 3}))
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
        


if __name__ == "__main__":
    unittest.main()