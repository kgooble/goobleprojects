'''
Created on 2012-03-17

@author: Gooble
'''
import unittest
from test.display.drawing.mock.mockdrawablefactory import MockDrawableFactory
from main.display.drawing.layout import Layout
        
class Test(unittest.TestCase):
    
    def setUp(self):
        self.layout = Layout(width=500, height=500)
    
    def test_add_to_row_0_aligns_one_obj_in_center(self):
        drawable = self.make_drawable()
        
        self.add(0, drawable)
        self.align()
        
        self.assert_x_y(drawable, 225, 237)
        
    def test_add_to_row_0_aligns_two_obj_in_center_of_same_height(self):
        drawable = self.make_drawable()
        drawable2 = self.make_drawable()
        
        self.add(0, drawable, drawable2)
        self.align()
        
        self.assert_x_y(drawable, 195, 237)
        self.assert_x_y(drawable2, 255, 237)
    
    def test_add_to_row_0_aligns_two_obj_in_center_of_different_height(self):
        drawable = self.make_drawable()
        drawable2 = self.make_drawable(height=50)
        
        self.add(0, drawable, drawable2)
        self.align()
        
        self.assert_x_y(drawable, 195, 237)
        self.assert_x_y(drawable2, 255, 225)
        
        
    def test_add_to_row_0_and_1_align_all_in_center(self):
        drawable = self.make_drawable()
        drawable2 = self.make_drawable()
        
        self.add(0, drawable)
        self.add(1, drawable2)
        self.align()
        
        self.assert_x_y(drawable, 225, 220)
        self.assert_x_y(drawable2, 225, 255)
        
    def test_add_several_drawables(self):
        toggle_left = self.make_drawable(width=25)
        label = self.make_drawable(height=40)
        toggle_right = self.make_drawable(width=25)
        long_text = self.make_drawable(width=100)
        
        self.add(0, toggle_left, label, toggle_right)
        self.add(1, long_text)
        self.align()
        
        self.assert_x_y(toggle_left, 190, 219)
        self.assert_x_y(label, 225, 212)
        self.assert_x_y(toggle_right, 285, 219)
        self.assert_x_y(long_text, 200, 262)
        
    def test_skip_a_row(self):
        drawable = self.make_drawable()
        drawable2 = self.make_drawable()
        
        self.add(0, drawable)
        self.add(2, drawable2)
        self.align()
        self.assert_x_y(drawable, 225, 215)
        self.assert_x_y(drawable2, 225, 260)
        
    def make_drawable(self, width=50, height=25):
        drawable = MockDrawableFactory().create_text("", 0, 0)
        drawable.width = width
        drawable.height = height
        return drawable
        
    def add(self, row, *drawables):
        for drawable in drawables:
            self.layout.add_to_row(row, drawable)
        
    def align(self):
        self.layout.align_all()
        
    def assert_x_y(self, drawable, x, y):
        self.assertEqual(x, drawable.get_x())
        self.assertEqual(y, drawable.get_y())
        
if __name__ == "__main__":
    unittest.main()