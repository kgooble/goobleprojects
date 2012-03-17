'''
Created on 2012-03-17

@author: Gooble
'''
from main.display.center import Center
X_PADDING = 10
Y_PADDING = 10

class Layout(object):
    def __init__(self):
        self.drawables = []
        
    def draw(self, screen):
        self.align_all(screen.get_width(), screen.get_height())
        for row in self.drawables:
            for obj in row:
                obj.draw(screen)
        
    def add_to_row(self, row_index, *new_drawables):
        while len(self.drawables) <= row_index:
            self.drawables.append([])
        for drawable in new_drawables:
            self.drawables[row_index].append(drawable)
    
    def align_all(self, width, height):
        self.align_y(self.align_x(width), height)

    def align_x(self, width):
        return [self.max_height(row, width) for row in self.drawables]

    def align_y(self, max_heights, height):
        top_border = self.calculate_start_y(max_heights, height)
        for i in range(0, len(self.drawables)):
            row = self.drawables[i]
            max_obj_height = max_heights[i]
            for obj in row:
                y = Center(start=top_border, total_length=max_obj_height).object_with_length(obj.get_height())
                obj.set_y(y)
            top_border += max_obj_height + Y_PADDING
    
    def max_height(self, row, width):
        x = self.calculate_start_x(row, width)
        max_obj_height = 0
        for obj in row:
            obj.set_x(x)
            x += obj.get_width() + X_PADDING
            if max_obj_height < obj.get_height():
                max_obj_height = obj.get_height()
        return max_obj_height
    
    def calculate_start_y(self, max_heights, height):
        total_obj_height = 0
        for max_height in max_heights:
            total_obj_height += max_height
        
        total_obj_height += (len(self.drawables) - 1) * Y_PADDING
        top_border = int((height - total_obj_height) / 2)
        return top_border
                
    def calculate_start_x(self, row, width):
        total_row_obj_width = 0
        for obj in row:
            total_row_obj_width += obj.get_width()
        total_row_obj_width += (len(row) - 1) * X_PADDING
        return int((width - total_row_obj_width) / 2)