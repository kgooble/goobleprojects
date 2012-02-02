'''
Created on 2012-02-01

@author: Gooble
'''
import unittest
from main.point import Point
from main.movevalidity import MoveValidity
from main.config import Configuration


class Test(unittest.TestCase):

    def setUp(self):
        config = Configuration().create(["0 0 0 0 0 0",
                                         "1 1 0 0 0 0",
                                         "1 0 0 0 0 0"])
        self.validity = MoveValidity(config)

    def test_is_invalid_position_if_at_least_one_spot_occupied_by_other_piece(self):
        self.assertFalse(self.validity.is_valid_move([Point(2, 2)], 
                                                  [Point(1, 1)]))
        self.assertFalse(self.validity.is_valid_move([Point(2, 2),
                                                   Point(3, 3)], 
                                                  [Point(0, 0), 
                                                   Point(1, 1)]))
        
    def test_is_valid_position_if_no_spots_occupied(self):
        self.assertTrue(self.validity.is_valid_move([Point(1, 1)], 
                                                 [Point(0, 0)]))
        self.assertTrue(self.validity.is_valid_move([Point(2, 2),
                                                  Point(2, 3)], 
                                                 [Point(2, 3), 
                                                  Point(2, 4)]))
        
    def test_is_valid_position_if_all_spots_occupied_by_self(self):
        self.assertTrue(self.validity.is_valid_move([Point(1, 1)], [Point(1, 1)]))
        
        
    def test_is_invalid_position_if_even_one_spot_occupied_by_other(self):
        self.assertFalse(self.validity.is_valid_move([Point(1, 1),
                                                   Point(2, 2),
                                                   Point(2, 1)], 
                                                  [Point(1, 0), 
                                                   Point(2, 0),
                                                   Point(2, 1)]))
        
    def test_is_invalid_position_if_beyond_edge_of_board(self):
        self.assertFalse(self.validity.is_valid_move([Point(1, 0),
                                                   Point(2, 0)], 
                                                  [Point(1, -1), 
                                                   Point(2, -1)]))


if __name__ == "__main__":
    #import sys;sys.argv = ['', 'Test.testName']
    unittest.main()