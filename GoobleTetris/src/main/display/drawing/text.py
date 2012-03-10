'''
Created on 2012-03-10

@author: Gooble
'''
class DrawableText(object):
    def __init__(self, text, x, y):
        self.text = text
        self.x = x
        self.y = y
        
    def draw(self, screen):
        screen.blit(self.text, [self.x, self.y])
        
    def contains(self, point):
        pointx = point[0]
        pointy = point[1]
        return self.x <= pointx <= self.x + self.get_width() and self.y <= pointy <= self.y + self.get_height()
    
    def get_width(self):
        return self.text.get_width()
    
    def get_height(self):
        return self.text.get_height()
    
    def set_x(self, x):
        self.x = x
        
    def set_y(self, y):
        self.y = y
        
    def get_x(self):
        return self.x

    def get_y(self):
        return self.y