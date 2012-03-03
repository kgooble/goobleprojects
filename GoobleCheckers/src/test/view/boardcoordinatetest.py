'''
Created on 2011-11-26

@author: Gooble
'''
import unittest
import main.view.boardcoordinate as bc

class BoardCoordinateTest(unittest.TestCase):

    def test_slotting_case_all_coordinates_in_first_row_first_cell(self):
        self.check_slot_in_entire_cell_range(0, 0, 0, 0)
        
    def test_slotting_case_all_coordinates_in_first_row_second_cell(self):
        self.check_slot_in_entire_cell_range(60, 0, 0, 1)
        
    def test_slotting_case_random_spot_in_first_row_first_cell(self):
        self.check_slot(1, 40, 0, 0)
        
    def test_slotting_case_all_coordinates_in_third_row_second_cell(self):
        self.check_slot_in_entire_cell_range(60, 120, 2, 1)
        
    def test_slotting_case_final_cell_very_corner_gives_invalid_coordinate(self):
        self.check_slot(480, 480, 8, 8)
        
    def test_slotting_case_1(self):
        self.check_slot(3 * (480/8), 2 * (480/8), 2, 3)
        
    def check_slot_in_entire_cell_range(self, xrangestart, yrangestart, expected_row, expected_col):
        for x in range(xrangestart, xrangestart+60):
            for y in range(yrangestart, yrangestart+60):
                self.check_slot(x, y, expected_row, expected_col)
            
    def check_slot(self, canvasx, canvasy, row, col):
        position = bc.BoardCoordinate(canvas_size=(480, 480), board_size=(8, 8)).get_from(x=canvasx, y=canvasy)
        self.assertEqual((row, col), position, "failed for values: canvas=" + str((canvasx, canvasy)) + "row=" + str(row) + "col=" + str(col) + " got instead: " + str(position))


if __name__ == "__main__":
    unittest.main()