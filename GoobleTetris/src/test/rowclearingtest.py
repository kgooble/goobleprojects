'''
Created on 2012-02-04

@author: Gooble
'''
import unittest
from main.config import Configuration
from main.boarddisplay import BoardDisplay
from main.rowclearing import RowClearing

class Test(unittest.TestCase):

    def test_clear_all_full_rows_and_return_rows_cleared(self):
        config = Configuration().create(
                 ["0 0 0 0 0",
                  "0 0 0 0 0",
                  "1 0 1 0 1",
                  "1 1 1 1 1",
                  "1 0 1 0 1",
                  "1 1 1 1 1",])
        rows = RowClearing(config).clear_and_get_rows()
        display = BoardDisplay(None, config)
        self.assertEqual( "0 0 0 0 0 \n"\
                         +"0 0 0 0 0 \n"\
                         +"1 0 1 0 1 \n"\
                         +"0 0 0 0 0 \n"\
                         +"1 0 1 0 1 \n"\
                         +"0 0 0 0 0 \n", display.get_pieces_string())
        self.assertEqual([3, 5], rows)


if __name__ == "__main__":
    #import sys;sys.argv = ['', 'Test.test_clear_all_full_rows_and_return_rows_cleared']
    unittest.main()