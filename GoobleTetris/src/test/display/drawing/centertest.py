'''
Created on 2012-03-04

@author: Gooble
'''
import unittest
from main.display.center import Center

class Test(unittest.TestCase):
    
    def test_get_centered_start_x_for_width_given_clamp_values(self):
        startval = Center(start=0, end=20).objectWithLength(5)
        self.assertEqual(7, startval)
    
    def test_get_centered_start_x_for_width_nonzero_start(self):
        startval = Center(start=20, end=80).objectWithLength(50)
        self.assertEqual(25, startval)
    
if __name__=="__main__":
    unittest.main()