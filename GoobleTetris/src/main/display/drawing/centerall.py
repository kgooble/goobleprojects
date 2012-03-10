'''
Created on 2012-03-09

@author: Gooble
'''
from main.display.center import Center
PADDING = 10

class CenterAll(object):
    
    def __init__(self, drawables):
        self.drawables = drawables
        
    def in_screen_sized(self, width, height):
        total_drawables_height = self.assign_x_and_get_total_height(width)
        self.assign_y_values(height, total_drawables_height)
        
    def assign_x_and_get_total_height(self, width):
        total_drawables_height = 0
        for drawable in self.drawables:
            drawable.x = Center(total_length=width).object_with_length(drawable.get_width())
            total_drawables_height += drawable.get_height()
        
        total_drawables_height += (len(self.drawables) - 1) * PADDING
        return total_drawables_height

    def assign_y_values(self, height, total_drawables_height):
        y = Center(total_length=height).object_with_length(total_drawables_height)
        for drawable in self.drawables:
            drawable.y = y
            y += PADDING + drawable.get_height()