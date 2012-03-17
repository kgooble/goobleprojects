'''
Created on 2012-03-17

@author: Gooble
'''
from main.display.center import Center
X_PADDING = 10
Y_PADDING = 10

class Layout(object):
    def __init__(self, width, height):
        self.width = width
        self.height = height
        self.drawables = []
        
    def add_to_row(self, row_index, drawable):
        while len(self.drawables) <= row_index:
            self.drawables.append([])
        self.drawables[row_index].append(drawable)
    
    def align_all(self):
        self.align_y(self.align_x())

    def align_x(self):
        return [self.max_height(row) for row in self.drawables]

    def align_y(self, max_heights):
        top_border = self.calculate_start_y(max_heights)
        for i in range(0, len(self.drawables)):
            row = self.drawables[i]
            max_obj_height = max_heights[i]
            for obj in row:
                y = Center(start=top_border, total_length=max_obj_height).object_with_length(obj.get_height())
                obj.set_y(y)
            top_border += max_obj_height + Y_PADDING
    
    def max_height(self, row):
        x = self.calculate_start_x(row)
        max_obj_height = 0
        for obj in row:
            obj.set_x(x)
            x += obj.get_width() + X_PADDING
            if max_obj_height < obj.get_height():
                max_obj_height = obj.get_height()
        return max_obj_height
    
    def calculate_start_y(self, max_heights):
        total_obj_height = 0
        for max_height in max_heights:
            total_obj_height += max_height
        
        total_obj_height += (len(self.drawables) - 1) * Y_PADDING
        top_border = int((self.height - total_obj_height) / 2)
        return top_border
                
    def calculate_start_x(self, row):
        total_row_obj_width = 0
        for obj in row:
            total_row_obj_width += obj.get_width()
        total_row_obj_width += (len(row) - 1) * X_PADDING
        return int((self.width - total_row_obj_width) / 2)