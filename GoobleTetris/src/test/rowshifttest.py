'''
Created on 2012-02-04

@author: Gooble
'''
import unittest
from main.config import Configuration
from main.boarddisplay import BoardDisplay
from main.rowshift import RowShift

class Test(unittest.TestCase):


    def test_shift_all_rows_down(self):
        config = Configuration().create(["0 0 0 0 0",
                                         "0 0 1 1 0",
                                         "1 1 1 1 1",
                                         "0 0 0 0 0"])
        pieces = RowShift(config).down(3)
        display = BoardDisplay(None, pieces)
        self.assertEqual( "0 0 0 0 0 \n"\
                         +"0 0 0 0 0 \n"\
                         +"0 0 1 1 0 \n"\
                         +"1 1 1 1 1 \n", display.get_pieces_string())
        
    def test_shift_all_rows_down_with_things_on_top_row(self):
        config = Configuration().create(["0 0 1 0 0",
                                         "0 0 1 1 0",
                                         "1 1 1 1 1",
                                         "0 0 0 0 0"])
        pieces = RowShift(config).down(3)
        display = BoardDisplay(None, pieces)
        self.assertEqual( "0 0 0 0 0 \n"\
                         +"0 0 1 0 0 \n"\
                         +"0 0 1 1 0 \n"\
                         +"1 1 1 1 1 \n", display.get_pieces_string())
        
    def test_shift_all_rows_given_start_row(self):
        config = Configuration().create(["0 0 1 0 0",
                                         "0 0 1 1 0",
                                         "0 0 0 0 0",
                                         "1 1 1 1 1"])
        pieces = RowShift(config).down(2)
        display = BoardDisplay(None, pieces)
        self.assertEqual( "0 0 0 0 0 \n"\
                         +"0 0 1 0 0 \n"\
                         +"0 0 1 1 0 \n"\
                         +"1 1 1 1 1 \n", display.get_pieces_string())


if __name__ == "__main__":
    #import sys;sys.argv = ['', 'Test.test_shift_all_rows_down']
    unittest.main()