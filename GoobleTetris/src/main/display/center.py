'''
Created on 2012-03-04

@author: Gooble
'''

class Center(object):
    def __init__(self, start=0, end=0, total_length=0):
        self.start = start
        if total_length == 0:
            self.total_length = end - start
        else:
            self.total_length = total_length
        
    def object_with_length(self, length):
        abs_start = int((self.total_length - length) / 2)
        return abs_start + self.start
    
    def pieces_along_x(self, pieces):
        length = 0
        calced_xs = []
        
        min_x = pieces[0].get_x()
        for piece in pieces:
            if piece.get_x() not in calced_xs:
                calced_xs.append(piece.get_x())
                length += piece.get_width()
            if piece.get_x() < min_x:
                min_x = piece.get_x()
        
        abs_start = int((self.total_length - length) / 2) + self.start
        
        difference = abs_start - min_x
        for piece in pieces:
            piece.set_x(piece.get_x() + difference)
        