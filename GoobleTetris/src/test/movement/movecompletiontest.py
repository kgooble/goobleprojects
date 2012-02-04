'''
Created on 2012-02-04

@author: Gooble
'''
from main.boarddisplay import BoardDisplay
from main.config import Configuration
from main.movement.direction import LEFT
from main.movement.movecompletion import MoveCompletion
from main.movement.transform.point import Point
import unittest
from main.shapes.tile import T_TILE


class Test(unittest.TestCase):

    def test_move_left(self):
        config = Configuration().create(["0 0 0 0 0 0",
                                         "1 0 0 1 0 0",
                                         "1 0 0 0 0 0"])
        display = BoardDisplay(None, config)
        completion = MoveCompletion(config)
        old_points = [Point(1, 3)]
        new_points = completion.move(LEFT, old_points)
        completion.move_tiles(old_points, new_points, T_TILE)
        self.assertEqual([Point(1, 2)], new_points)
        self.assertEqual( "0 0 0 0 0 0 \n"\
                         +"1 0 1 0 0 0 \n"\
                         +"1 0 0 0 0 0 \n", display.get_pieces_string())
        


if __name__ == "__main__":
    #import sys;sys.argv = ['', 'Test.testName']
    unittest.main()