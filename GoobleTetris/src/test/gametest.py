'''
Created on 2012-02-04

@author: Gooble
'''
import unittest
from main.board import Board
from main.shapes.spawn.oneshapespawn import OneShapeSpawn
from main.shapes.tile import T_TILE
from main.config import Configuration
from main.boarddisplay import BoardDisplay
from main.movement.direction import LEFT, RIGHT
from main.game import Game

class Test(unittest.TestCase):

    def test_game_scores_for_rows_cleared(self):
        config = Configuration().create(["0 0 0 0 0",
                                         "0 0 0 0 0",
                                         "0 0 0 0 0",
                                         "0 0 0 0 0",
                                         "0 0 0 0 0"])
        board = Board(config, OneShapeSpawn(T_TILE))
        display = BoardDisplay(board)
        game = Game(board)
        game.step()
        self.assertEqual( "0 1 1 1 0 \n"\
                         +"0 0 0 0 0 \n"\
                         +"0 0 0 0 0 \n"\
                         +"0 0 0 0 0 \n"\
                         +"0 0 0 0 0 \n", display.get_pieces_string())
        game.step()
        game.step()
        game.step()
        game.step()
        self.assertEqual( "0 0 0 0 0 \n"\
                         +"0 0 0 0 0 \n"\
                         +"0 0 0 0 0 \n"\
                         +"0 0 1 0 0 \n"\
                         +"0 1 1 1 0 \n", display.get_pieces_string())
        game.step()
        self.assertEqual( "0 1 1 1 0 \n"\
                         +"0 0 0 0 0 \n"\
                         +"0 0 0 0 0 \n"\
                         +"0 0 1 0 0 \n"\
                         +"0 1 1 1 0 \n", display.get_pieces_string())
        game.step()
        game.rotate()
        self.assertEqual( "0 0 1 0 0 \n"\
                         +"0 0 1 1 0 \n"\
                         +"0 0 1 0 0 \n"\
                         +"0 0 1 0 0 \n"\
                         +"0 1 1 1 0 \n", display.get_pieces_string())
        game.move(LEFT)
        game.move(LEFT)
        game.drop()
        self.assertEqual( "0 0 0 0 0 \n"\
                         +"0 0 0 0 0 \n"\
                         +"1 0 0 0 0 \n"\
                         +"1 1 1 0 0 \n"\
                         +"1 1 1 1 0 \n", display.get_pieces_string())
        
        game.step()
        game.step()
        game.rotate()
        game.rotate()
        game.rotate()
        game.move(RIGHT)
        game.move(RIGHT)
        game.drop()
        self.assertEqual( "0 0 0 0 0 \n"\
                         +"0 0 0 0 0 \n"\
                         +"1 0 0 0 1 \n"\
                         +"1 1 1 1 1 \n"\
                         +"1 1 1 1 1 \n", display.get_pieces_string())
        
        game.step()
        self.assertEqual( "0 1 1 1 0 \n"\
                         +"0 0 0 0 0 \n"\
                         +"0 0 0 0 0 \n"\
                         +"0 0 0 0 0 \n"\
                         +"1 0 0 0 1 \n", display.get_pieces_string())
        self.assertEqual(200, game.get_score())
        self.assertEqual(48, game.get_speed())
        
        


if __name__ == "__main__":
    unittest.main()