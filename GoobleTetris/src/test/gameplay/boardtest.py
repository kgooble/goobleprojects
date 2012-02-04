'''
Created on 2012-01-29

@author: Gooble
'''
import unittest
import main.config

from main.boarddisplay import BoardDisplay
from main.shapes.spawn.oneshapespawn import OneShapeSpawn
from main.shapes.tile import T_TILE
from main.movement.direction import LEFT
from main.gameplay.board import Board

class Test(unittest.TestCase):

    def setUp(self):
        config = main.config.Configuration().create(["0 0 0 0 0 0",
                                                     "1 0 0 0 0 0",
                                                     "1 0 0 0 0 0"])
        self.board = Board(config, OneShapeSpawn(T_TILE))
        self.display = BoardDisplay(self.board)
        self.board.step()
        self.assertEqual( 
                  "0 0 1 1 1 0 \n"\
                 +"1 0 0 0 0 0 \n"\
                 +"1 0 0 0 0 0 \n", self.display.get_pieces_string())

    def test_move_in_dir(self):
        self.board.move(LEFT)
        self.assertEqual( 
                  "0 1 1 1 0 0 \n"\
                 +"1 0 0 0 0 0 \n"\
                 +"1 0 0 0 0 0 \n", self.display.get_pieces_string())
        self.board.step()
        self.assertEqual( 
                  "0 0 1 0 0 0 \n"\
                 +"1 1 1 1 0 0 \n"\
                 +"1 0 0 0 0 0 \n", self.display.get_pieces_string())
        self.board.move(LEFT)
        self.assertEqual( 
                  "0 0 1 0 0 0 \n"\
                 +"1 1 1 1 0 0 \n"\
                 +"1 0 0 0 0 0 \n", self.display.get_pieces_string())
        
    def test_rotate(self):
        self.board.step()
        self.board.rotate()
        self.assertEqual( 
                  "0 0 0 1 0 0 \n"\
                 +"1 0 0 1 1 0 \n"\
                 +"1 0 0 1 0 0 \n", self.display.get_pieces_string())
        self.board.rotate()
        self.assertEqual( 
                  "0 0 0 0 0 0 \n"\
                 +"1 0 1 1 1 0 \n"\
                 +"1 0 0 1 0 0 \n", self.display.get_pieces_string())
        self.board.rotate()
        self.assertEqual( 
                  "0 0 0 1 0 0 \n"\
                 +"1 0 1 1 0 0 \n"\
                 +"1 0 0 1 0 0 \n", self.display.get_pieces_string())
        self.board.step()
        self.assertEqual( 
                  "0 0 0 1 0 0 \n"\
                 +"1 0 1 1 0 0 \n"\
                 +"1 0 0 1 0 0 \n", self.display.get_pieces_string())
        self.assertTrue(self.board.is_game_over())
        
    def test_step_when_row_full_clears_all_full_rows(self):
        config = main.config.Configuration().create(["0 0 0 0 0 0",
                                                     "1 0 0 0 0 0",
                                                     "1 1 1 1 1 1"])
        self.board = Board(config, OneShapeSpawn(T_TILE))
        self.board.step()
        self.display = BoardDisplay(self.board)
        self.assertEqual( 
                  "0 0 1 1 1 0 \n"\
                 +"0 0 0 0 0 0 \n"\
                 +"1 0 0 0 0 0 \n", self.display.get_pieces_string())
        self.assertEqual(1, self.board.get_rows_cleared())
        
    def test_drop_shape(self):
        config = main.config.Configuration().create(["0 0 0 0 0 0",
                                                     "1 0 0 0 0 0",
                                                     "1 1 0 0 0 1"])
        self.board = Board(config, OneShapeSpawn(T_TILE))
        self.board.step()
        self.display = BoardDisplay(self.board)
        self.board.drop_shape()
        self.assertEqual( 
                  "0 0 0 0 0 0 \n"\
                 +"1 0 0 1 0 0 \n"\
                 +"1 1 1 1 1 1 \n", self.display.get_pieces_string())
        self.board.step()
        self.assertEqual( 
                  "0 0 1 1 1 0 \n"\
                 +"0 0 0 0 0 0 \n"\
                 +"1 0 0 1 0 0 \n", self.display.get_pieces_string())
        self.board.step()
        self.assertEqual( 
                  "0 0 0 1 0 0 \n"\
                 +"0 0 1 1 1 0 \n"\
                 +"1 0 0 1 0 0 \n", self.display.get_pieces_string())

if __name__ == "__main__":
    unittest.main()