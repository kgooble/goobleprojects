'''
Created on 2012-01-29

@author: Gooble
'''
import unittest
import main.point
import main.config
import main.board

class Test(unittest.TestCase):

    def setUp(self):
        pass


    def tearDown(self):
        pass


    def test_is_valid_position_false_if_at_least_one_spot_occupied(self):
        config = main.config.Configuration().create(["0 0 0 0 0 0",
                                                     "1 1 0 0 0 0",
                                                     "1 0 0 0 0 0"])
        board = main.board.Board(config)
        self.assertFalse(board.is_valid_position([main.point.Point(1, 1)]))
        self.assertTrue(board.is_valid_position([main.point.Point(0, 0)]))
        self.assertFalse(board.is_valid_position([main.point.Point(0, 0), 
                                                  main.point.Point(1, 1)]))
        self.assertTrue(board.is_valid_position([main.point.Point(2, 3), 
                                                 main.point.Point(2, 4)]))


if __name__ == "__main__":
    #import sys;sys.argv = ['', 'Test.test_is_valid_position_false_if_at_least_one_spot_occupied']
    unittest.main()