'''
Created on 2012-03-09

@author: Gooble
'''
import unittest
from test.display.drawing.mock.mockdrawable import MockDrawable
from main.display.drawing.centerall import CenterAll

class Test(unittest.TestCase):


    def test_for_one_drawable_center_it_on_the_screen(self):
        drawables = [MockDrawable("", 0, 0)]
        CenterAll(drawables).in_screen_sized(500, 500)
        
        self.assertEqual(225, drawables[0].x)
        self.assertEqual(237, drawables[0].y)


    def test_for_all_drawables_center_them_on_the_screen(self):
        drawables = [MockDrawable("", 0, 0), MockDrawable("", 0, 0)]
        CenterAll(drawables).in_screen_sized(500, 500)
        
        self.assertEqual(225, drawables[0].x)
        self.assertEqual(220, drawables[0].y)
        
        self.assertEqual(225, drawables[1].x)
        self.assertEqual(255, drawables[1].y)
    

if __name__ == "__main__":
    unittest.main()