'''
Created on 2012-02-04

@author: Gooble
'''
import unittest
from main.display.screencoords import ScreenCoords


class Test(unittest.TestCase):

    def test_calculate_screen_coordinates_from_row_and_col_0_0(self):
        coords = ScreenCoords(row=0, col=0, tile_width=20)
        self.assertEqual(0, coords.get_start_x())
        self.assertEqual(0, coords.get_start_y())
        self.assertEqual(20, coords.get_end_x())
        self.assertEqual(20, coords.get_end_y())
        
    def test_row_1_col_0(self):
        coords = ScreenCoords(row=1, col=0, tile_width=20)
        self.assertEqual(0, coords.get_start_x())
        self.assertEqual(20, coords.get_start_y())
        self.assertEqual(20, coords.get_end_x())
        self.assertEqual(40, coords.get_end_y())
        

if __name__ == "__main__":
    unittest.main()